package galena.oreganized.content.entity;

import galena.oreganized.Oreganized;
import galena.oreganized.content.block.GargoyleBlock;
import galena.oreganized.index.OBlockEntities;
import galena.oreganized.index.OCriteriaTriggers;
import galena.oreganized.index.OParticleTypes;
import galena.oreganized.index.OSoundEvents;
import galena.oreganized.index.OTags;
import galena.oreganized.network.OreganizedNetwork;
import galena.oreganized.network.packet.GargoyleParticlePacket;
import galena.oreganized.world.ScaredOfGargoyleGoal;
import java.util.Collection;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.PacketDistributor;
import org.jetbrains.annotations.Nullable;

public class GargoyleBlockEntity extends BlockEntity {

    private static final int COOLDOWN = 20 * 30;
    public static final String GROWL_COOLDOWN_TAG = Oreganized.MOD_ID + ":gargoyle_use_cooldown";

    private int outputSignal = 0;
    private int updateCooldown = 0;
    private int growlCooldown = 0;

    private ParticleOptions drippingFluid;

    private final Direction fluidOffset;

    public GargoyleBlockEntity(BlockPos pos, BlockState state) {
        super(OBlockEntities.GARGOYLE.get(), pos, state);
        var attachment = state.getValue(GargoyleBlock.ATTACHMENT);
        var facing = state.getValue(GargoyleBlock.FACING);
        if (attachment == GargoyleBlock.AttachmentType.FLOOR) {
            fluidOffset = Direction.DOWN;
        } else {
            fluidOffset = facing;
        }
    }

    private static Collection<Mob> getTargets(Level level, BlockPos pos) {
        var box = new AABB(pos).inflate(10.0);
        return level.getEntitiesOfClass(Mob.class, box, it -> it.getMobType() == MobType.UNDEAD);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, GargoyleBlockEntity be) {
        be.growlCooldown--;

        if (be.updateCooldown % 2 == 0 && be.drippingFluid != null) {
            GargoyleBlock.dripParticles(state, level, pos, level.random, be.drippingFluid);
        }

        if (--be.updateCooldown > 0) return;

        be.updateDripParticles(level, pos, state);

        var targets = getTargets(level, pos);
        var vec = Vec3.atCenterOf(pos);
        var closestDistance = targets.stream()
                .mapToDouble(it -> it.distanceToSqr(vec))
                .map(Math::sqrt)
                .map(Math::floor)
                .sorted()
                .findFirst()
                .orElse(100.0);

        var newOutputSignal = Math.max(14 - (int) closestDistance, 0);

        if (newOutputSignal != be.outputSignal) {
            be.outputSignal = newOutputSignal;
            level.updateNeighbourForOutputSignal(pos, be.getBlockState().getBlock());
        }

        be.updateCooldown = 10;
    }

    private void updateDripParticles(Level level, BlockPos pos, BlockState state) {
        for (int i = 1; i <= 2; i++) {
            var targetPos = pos.relative(fluidOffset, i);
            var targetState = level.getBlockState(targetPos);
            var fluid = targetState.getFluidState();

            if (!fluid.isEmpty()) {
                drippingFluid = fluid.getDripParticle();

                if (fluid.is(Fluids.WATER)) {
                    level.getEntitiesOfClass(ServerPlayer.class, new AABB(pos).inflate(10.0, 5.0, 10.0)).forEach(player -> {
                        OCriteriaTriggers.SEE_GARGOYLE_GARGLE.trigger(player);
                    });
                }

                return;
            }

            if (!targetState.isRedstoneConductor(level, pos)) {
                break;
            }

            if (!targetState.isFaceSturdy(level, pos, Direction.UP) || !targetState.isFaceSturdy(level, pos, Direction.DOWN)) {
                break;
            }
        }

        if (level.isRainingAt(pos.above())) {
            drippingFluid = ParticleTypes.DRIPPING_DRIPSTONE_WATER;
        } else {
            drippingFluid = null;
        }
    }

    public int getAnalogOutputSignal() {
        return outputSignal;
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putInt("cooldown", growlCooldown);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        if (tag.contains("cooldown")) growlCooldown = tag.getInt("cooldown");
    }

    public InteractionResult interact(Level level, BlockPos pos, @Nullable Player player, ItemStack stack, boolean simulate) {
        if (growlCooldown > 0) return InteractionResult.PASS;
        if (player != null && player.getPersistentData().getInt(GROWL_COOLDOWN_TAG) > 0) return InteractionResult.PASS;
        if (!stack.is(OTags.Items.GARGOYLE_SNACK)) return InteractionResult.PASS;

        if (player == null || !player.getAbilities().instabuild) {
            stack.shrink(1);
        }

        if (simulate) return InteractionResult.SUCCESS;

        getTargets(level, pos).forEach(mob -> scare(mob, pos));

        level.playSound(null, pos.getX(), pos.getY(), pos.getZ(), OSoundEvents.GARGOYLE_GROWL.get(), SoundSource.BLOCKS, 1.0F, 1.0F);

        growlCooldown = COOLDOWN;
        if (player != null && !player.getAbilities().instabuild) {
            player.getPersistentData().putInt(GROWL_COOLDOWN_TAG, COOLDOWN);
        }

        if (!level.isClientSide) {
            OreganizedNetwork.CHANNEL.send(PacketDistributor.DIMENSION.with(level::dimension), new GargoyleParticlePacket(pos));
        }

        return InteractionResult.SUCCESS;
    }

    private void scare(Entity mob, BlockPos pos) {
        var offset = mob.position().subtract(pos.getX(), pos.getY(), pos.getZ());
        if (offset.length() < 4) {
            var motion = offset.multiply(0.4, 0.2, 0.4);
            mob.push(motion.x, motion.y, motion.z);
        }
        mob.getPersistentData().put(ScaredOfGargoyleGoal.AVOID_TAG_KEY, NbtUtils.writeBlockPos(pos));
    }

    public void spawnParticles() {
        var pos = getBlockPos();
        var state = getBlockState();
        var facing = state.getValue(GargoyleBlock.FACING);
        var attachment = state.getValue(GargoyleBlock.ATTACHMENT);

        ParticleUtils.spawnParticlesOnBlockFaces(level, pos, OParticleTypes.VENGEANCE.get(), UniformInt.of(0, 2));

        if (attachment == GargoyleBlock.AttachmentType.WALL) {
            ParticleUtils.spawnParticlesOnBlockFaces(level, pos.relative(facing.getOpposite()), OParticleTypes.VENGEANCE.get(), UniformInt.of(0, 1));
        }
    }
}
