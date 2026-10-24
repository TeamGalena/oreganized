package galena.oreganized.plumbum.world.fluid;

import galena.oreganized.plumbum.index.PlumbumBlocks;
import galena.oreganized.plumbum.index.PlumbumParticles;
import galena.oreganized.plumbum.index.PlumbumSounds;
import galena.oreganized.plumbum.world.block.LeadOreBlock;
import galena.oreganized.plumbum.world.block.MoltenLeadBlock;

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.fluids.FluidInteractionRegistry;

public class MoltenLeadFluid extends BaseFlowingFluid {

    public MoltenLeadFluid(Properties properties) {
        super(properties);
        registerDefaultState(defaultFluidState().setValue(LEVEL, 8).setValue(FALLING, false));
    }

    public static void solidify(Level level, BlockPos pos) {
        var random = level.getRandom();

        level.setBlock(pos, PlumbumBlocks.LEAD_BLOCK.value().defaultBlockState(), 3);

        level.playSound(
                null, pos, PlumbumSounds.MOLTEN_LEAD_EXTINGUISH.value(), SoundSource.BLOCKS,
                0.5F, 2.6F + (random.nextFloat() - random.nextFloat()) * 0.8F
        );
    }

    public static void vaporize(Level level, BlockPos pos) {
        LeadOreBlock.spawnCloud(level, pos, 2F);
        level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
        var random = level.getRandom();
        level.playSound(
                null, pos, PlumbumSounds.MOLTEN_LEAD_EXTINGUISH.value(), SoundSource.BLOCKS,
                0.5F, 2.6F + (random.nextFloat() - random.nextFloat()) * 0.8F
        );
    }

    @Override
    protected boolean isRandomlyTicking() {
        return true;
    }

    @Override
    public int getAmount(FluidState state) {
        return 8;
    }

    @Override
    public boolean isSource(FluidState state) {
        return !state.getValue(FALLING);
    }

    @Override
    protected void createFluidStateDefinition(StateDefinition.Builder<Fluid, FluidState> builder) {
        super.createFluidStateDefinition(builder);
        builder.add(LEVEL);
    }

    @Override
    protected void spread(Level level, BlockPos pos, FluidState fluidState) {
        if (fluidState.isEmpty()) return;

        BlockState blockstate = level.getBlockState(pos);
        if (blockstate.getValue(MoltenLeadBlock.WAITING)) return;

        BlockPos belowPos = pos.below();
        BlockState belowState = level.getBlockState(belowPos);

        var leadBelow = belowState.getFluidState().is(this);

        if (fluidState.isSource()) {
            if ((leadBelow && !belowState.getFluidState().isSource())
                    || (!leadBelow && !belowState.getFluidState().isEmpty())
            ) {
                if (!FluidInteractionRegistry.canInteract(level, belowPos)) {
                    spreadTo(level, belowPos, belowState, Direction.DOWN, fluidState);
                }
                level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
            } else if (canSpreadTo(level, pos, blockstate, Direction.DOWN, belowPos, belowState, level.getFluidState(belowPos), fluidState.getType())) {
                spreadTo(level, belowPos, belowState, Direction.DOWN, fluidState.setValue(FALLING, true));
            }
        }
    }

    @Override
    protected void spreadTo(LevelAccessor level, BlockPos pos, BlockState state, Direction direction, FluidState fluid) {
        if (!state.isAir()) this.beforeDestroyingBlock(level, pos, state);
        level.setBlock(pos, fluid.createLegacyBlock().setValue(MoltenLeadBlock.WAITING, MoltenLeadBlock.shouldWait(level, pos)), 3);
    }

    @Override
    protected void beforeDestroyingBlock(LevelAccessor level, BlockPos pos, BlockState state) {
        if (state.getFluidState().is(this)) return;
        level.levelEvent(1501, pos, 0);
    }

    @Override
    public void animateTick(Level world, BlockPos pos, FluidState fluidState, RandomSource random) {
        BlockPos abovePos = pos.above();
        if (world.getBlockState(abovePos).isAir() && !world.getBlockState(abovePos).isSolidRender(world, abovePos)) {
            if (random.nextInt(300) == 0) {
                world.playLocalSound(
                        pos, PlumbumSounds.MOLTEN_LEAD_AMBIENT.value(), SoundSource.BLOCKS,
                        0.2F + random.nextFloat() * 0.2F, 0.9F + random.nextFloat() * 0.15F, false
                );
            }
        }
    }

    @Override
    @Nullable
    public ParticleOptions getDripParticle() {
        return PlumbumParticles.DRIPPING_LEAD.get();
    }
}
