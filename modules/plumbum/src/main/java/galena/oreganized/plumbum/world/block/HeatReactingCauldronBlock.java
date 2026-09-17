package galena.oreganized.plumbum.world.block;

import galena.oreganized.plumbum.config.PlumbumConfigs;
import galena.oreganized.plumbum.index.PlumbumTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.AbstractCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;

public abstract class HeatReactingCauldronBlock extends AbstractCauldronBlock {

    public HeatReactingCauldronBlock(Properties properties, CauldronInteraction.InteractionMap interactions) {
        super(properties, interactions);
    }

    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, LevelReader level, BlockPos pos, Player player) {
        return new ItemStack(Items.CAULDRON);
    }

    protected double getContentHeight(BlockState state) {
        return 0.9375;
    }

    public boolean isFull(BlockState state) {
        return true;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        this.tick(state, level, pos, random);
    }

    private boolean isHot(ServerLevel level, BlockPos pos) {
        if (level.dimensionType().ultraWarm()) return true;
        var below = level.getBlockState(pos.below());
        return below.is(PlumbumTags.Blocks.MELTS_LEAD);
    }

    abstract void heatUp(BlockState state, ServerLevel level, BlockPos pos, RandomSource random);

    abstract void coolDown(BlockState state, ServerLevel level, BlockPos pos, RandomSource random);

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!PlumbumConfigs.COMMON.cauldronLeadMelting.get()) return;
        if (random.nextInt(1) == 0) {
            if (isHot(level, pos)) {
                heatUp(state, level, pos, random);
            } else {
                coolDown(state, level, pos, random);
            }
        }
    }

}
