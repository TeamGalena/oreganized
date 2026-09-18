package galena.oreganized.gothic.world.block;

import static net.minecraft.world.level.block.state.properties.BlockStateProperties.WATERLOGGED;

import java.util.Locale;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SpyreBlock extends Block implements SimpleWaterloggedBlock {

    public static final EnumProperty<Thickness> THICKNESS = EnumProperty.create("thickness", Thickness.class);

    private static final VoxelShape TIP_SHAPE = Block.box(5.0F, 0.0F, 5.0F, 11.0F, 11.0F, 11.0F);
    private static final VoxelShape FRUSTUM_SHAPE = Block.box(4.0F, 0.0F, 4.0F, 12.0F, 16.0F, 12.0F);
    private static final VoxelShape MIDDLE_SHAPE = Block.box(3.0F, 0.0F, 3.0F, 13.0F, 16.0F, 13.0F);
    private static final VoxelShape BASE_SHAPE = Block.box(2.0F, 0.0F, 2.0F, 14.0F, 16.0F, 14.0F);

    public SpyreBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(THICKNESS, WATERLOGGED);
    }

    private Thickness getThickness(BlockState above) {
        if (above.is(this)) {
            var aboveThickness = above.getValue(THICKNESS);
            var ordinal = Math.max(0, aboveThickness.ordinal() - 1);
            return Thickness.values()[ordinal];
        }

        return Thickness.TIP;
    }

    private Thickness getThickness(LevelAccessor level, BlockPos pos) {
        return getThickness(level.getBlockState(pos.above()));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        var thickness = getThickness(context.getLevel(), context.getClickedPos());
        var waterlogged = context.getLevel().getFluidState(context.getClickedPos()).is(Fluids.WATER);
        return defaultBlockState()
                .setValue(WATERLOGGED, waterlogged)
                .setValue(THICKNESS, thickness);
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        var updated = super.updateShape(state, direction, neighborState, level, pos, neighborPos);
        if (direction != Direction.UP)
            return updated;

        var thickness = getThickness(neighborState);
        return updated.setValue(THICKNESS, thickness);
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        var belowPos = pos.below();
        var below = level.getBlockState(belowPos);
        return below.is(this) || below.isFaceSturdy(level, belowPos, Direction.UP);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        var thickness = state.getValue(THICKNESS);
        return switch (thickness) {
            case BASE -> BASE_SHAPE;
            case FRUSTUM -> FRUSTUM_SHAPE;
            case MIDDLE -> MIDDLE_SHAPE;
            case TIP -> TIP_SHAPE;
        };
    }

    public enum Thickness implements StringRepresentable {
        BASE, FRUSTUM, MIDDLE, TIP;

        @Override
        public String getSerializedName() {
            return name().toLowerCase(Locale.ROOT);
        }
    }

}
