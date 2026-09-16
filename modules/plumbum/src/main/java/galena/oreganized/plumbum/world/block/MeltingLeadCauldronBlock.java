package galena.oreganized.plumbum.world.block;

import com.mojang.serialization.MapCodec;
import galena.oreganized.plumbum.index.PlumbumBlocks;
import galena.oreganized.plumbum.world.MeltingCauldronInteractions;
import java.util.function.ToIntFunction;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractCauldronBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class MeltingLeadCauldronBlock extends HeatReactingCauldronBlock {

    public static final IntegerProperty AGE = BlockStateProperties.AGE_2;
    public static final int MAX_AGE = AGE.getPossibleValues().size() - 1;

    public MeltingLeadCauldronBlock(Properties properties) {
        super(properties, MeltingCauldronInteractions.LEAD);
        registerDefaultState(getStateDefinition().any().setValue(AGE, 0));
    }

    public static ToIntFunction<BlockState> moltenStageEmission() {
        return (state) -> state.getValue(AGE) * 2;
    }

    private static final MapCodec<MoltenLeadCauldronBlock> CODEC = simpleCodec(MoltenLeadCauldronBlock::new);

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(AGE);
    }

    public int getAnalogOutputSignal(BlockState state, Level level, BlockPos blockPos) {
        return state.getValue(AGE) + 1;
    }

    public VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext ctx) {
        return Shapes.block();
    }

    @Override
    void heatUp(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        int age = state.getValue(AGE);
        if (age < MAX_AGE) {
            level.setBlockAndUpdate(pos, state.setValue(AGE, age + 1));
        } else {
            level.setBlockAndUpdate(pos, PlumbumBlocks.MOLTEN_LEAD_CAULDRON.get().withPropertiesOf(state));
        }
    }

    @Override
    void coolDown(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        int age = state.getValue(AGE);
        if (age > 0) {
            level.setBlockAndUpdate(pos, state.setValue(AGE, age - 1));
        }
    }

    @Override
    protected MapCodec<? extends AbstractCauldronBlock> codec() {
        return CODEC;
    }

}
