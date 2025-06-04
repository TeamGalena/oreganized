package galena.oreganized.content.block;

import galena.oreganized.OreganizedConfig;
import galena.oreganized.index.OBlocks;
import galena.oreganized.index.OItems;
import galena.oreganized.index.OTags;
import java.util.Optional;
import java.util.function.Supplier;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class MoltenLeadBlock extends LiquidBlock {

    public static final VoxelShape STABLE_SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);

    public static final BooleanProperty WAITING = BooleanProperty.create("waiting");

    public MoltenLeadBlock(Supplier<? extends FlowingFluid> fluid, Properties properties) {
        super(fluid, properties.noCollission().strength(-1.0F, 3600000.0F).noLootTable().lightLevel((state) -> 8));
        registerDefaultState(defaultBlockState().setValue(WAITING, true));
    }

    @Nullable
    @Override
    public BlockPathTypes getBlockPathType(BlockState state, BlockGetter world, BlockPos pos, @Nullable Mob entity) {
        return BlockPathTypes.WALKABLE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(WAITING);
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (state.getFluidState().isSource()) {
            var oldFluid = oldState.getFluidState();
            if (oldFluid.is(FluidTags.WATER)) {
                level.levelEvent(1501, pos, 0);
                level.setBlock(pos, OBlocks.LEAD_BLOCK.get().defaultBlockState(), 3);
            } else if (oldFluid.is(FluidTags.LAVA)) {
                LeadOreBlock.spawnCloud(level, pos, 2F);
                level.setBlockAndUpdate(pos, oldState);
            }
        }

        if (state.getValue(WAITING)) {
            level.scheduleTick(pos, state.getBlock(), OreganizedConfig.COMMON.moltenLeadDelay.get());
        }

        super.onPlace(state, level, pos, oldState, isMoving);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        if (context.isHoldingItem(OItems.MOLTEN_LEAD_BUCKET.get()))
            return level.getBlockState(pos.above()).is(this) ? Shapes.block() : STABLE_SHAPE;

        return Shapes.empty();
    }

    @Override
    public boolean canBeReplaced(BlockState state, Fluid fluid) {
        if (getFluid() == fluid) return false;
        return super.canBeReplaced(state, fluid);
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState replacedWith, boolean dropXp) {
        super.onRemove(state, level, pos, replacedWith, dropXp);

        if (replacedWith.isAir() || !replacedWith.getFluidState().isEmpty()) return;
        if (replacedWith.is(OBlocks.LEAD_BLOCK.get())) return;

        if (state.getFluidState().isSource()) tryEscape(state, level, pos);
    }

    private boolean tryEscape(BlockState state, Level level, BlockPos pos, Direction direction) {
        var adjancentPos = pos.relative(direction);
        var adjacentState = level.getBlockState(adjancentPos);
        if (adjacentState.canBeReplaced(getFluid())) {
            level.setBlockAndUpdate(adjancentPos, state);

            getPickupSound().ifPresent(sound -> {
                level.playSound(null, pos, sound, SoundSource.BLOCKS, 1F, 1F);
            });

            return true;
        }

        return false;
    }

    private boolean tryEscape(BlockState state, Level level, BlockPos pos) {
        if (tryEscape(state, level, pos, Direction.DOWN)) return true;
        for (var direction : Direction.allShuffled(level.random)) {
            if (direction.getAxis().isHorizontal() && tryEscape(state, level, pos, direction)) {
                return true;
            }
        }

        return tryEscape(state, level, pos, Direction.UP);
    }

    @Override
    public void entityInside(BlockState state, Level world, BlockPos pos, Entity entity) {
        if (entity.getY() < pos.getY() + STABLE_SHAPE.max(Direction.Axis.Y)) {
            if (!(entity instanceof LivingEntity) || entity.getFeetBlockState().is(this)) {
                entity.makeStuckInBlock(state, new Vec3(0.9F, 1.0D, 0.9F));
            }

            entity.setSecondsOnFire(10);
            if (!world.isClientSide) entity.setSharedFlagOnFire(true);
        }
    }

    @Override
    public void fallOn(Level world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        if (fallDistance >= 4.0D && entity instanceof LivingEntity living) {
            LivingEntity.Fallsounds fallSound = living.getFallSounds();
            SoundEvent sound = fallDistance < 7.0D ? fallSound.small() : fallSound.big();
            entity.playSound(sound, 1.0F, 1.0F);
        }
    }

    public static boolean isEntityLighterThanLead(Entity entity) {
        if (entity.getType().is(OTags.Entities.LIGHTER_THAN_LEAD)) {
            return true;
        } else {
            return entity instanceof LivingEntity living && living.getItemBySlot(EquipmentSlot.FEET).is(OTags.Items.LIGHTER_THAN_LEAD);
        }
    }

    public static boolean shouldWait(LevelAccessor level, BlockPos pos) {
        var belowPos = pos.below();
        var belowState = level.getBlockState(belowPos);
        return belowState.isFaceSturdy(level, belowPos, Direction.UP);
    }

    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos neighborPos, boolean moving) {
        super.neighborChanged(state, level, pos, block, neighborPos, moving);

        var waiting = state.getValue(WAITING);
        var shouldWait = shouldWait(level, pos);

        if (waiting == shouldWait) return;

        if (shouldWait) {
            level.setBlockAndUpdate(pos, state.setValue(WAITING, true));
        } else {
            level.scheduleTick(pos, state.getBlock(), OreganizedConfig.COMMON.moltenLeadDelay.get());
        }
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        super.tick(state, level, pos, random);

        if (!shouldWait(level, pos)) {
            level.setBlockAndUpdate(pos, state.setValue(WAITING, false));
        }
    }

    @Override
    public Optional<SoundEvent> getPickupSound() {
        return Optional.of(SoundEvents.BUCKET_FILL_LAVA);
    }

    @Override
    public boolean isPathfindable(BlockState state, BlockGetter getter, BlockPos pos, PathComputationType pathFinder) {
        return true;
    }
}
