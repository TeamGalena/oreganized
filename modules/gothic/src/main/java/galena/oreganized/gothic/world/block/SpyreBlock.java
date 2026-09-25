package galena.oreganized.gothic.world.block;

import static net.minecraft.world.level.block.state.properties.BlockStateProperties.VERTICAL_DIRECTION;
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
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SpyreBlock extends Block implements SimpleWaterloggedBlock {

    public static final EnumProperty<Thickness> THICKNESS = EnumProperty.create("thickness", Thickness.class);

    private static final VoxelShape TIP_SHAPE_UP = Block.box(5.0F, 0.0F, 5.0F, 11.0F, 11.0F, 11.0F);
    private static final VoxelShape TIP_SHAPE_DOWN = Block.box(5.0F, 5.0F, 5.0F, 11.0F, 16.0F, 11.0F);
    private static final VoxelShape TIP_MERGE_SHAPE = Block.box(5.0F, 0.0F, 5.0F, 11.0F, 11.0F, 11.0F);
    private static final VoxelShape FRUSTUM_SHAPE = Block.box(4.0F, 0.0F, 4.0F, 12.0F, 16.0F, 12.0F);
    private static final VoxelShape MIDDLE_SHAPE = Block.box(3.0F, 0.0F, 3.0F, 13.0F, 16.0F, 13.0F);
    private static final VoxelShape BASE_SHAPE = Block.box(2.0F, 0.0F, 2.0F, 14.0F, 16.0F, 14.0F);

    public SpyreBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState()
                .setValue(THICKNESS, Thickness.TIP)
                .setValue(VERTICAL_DIRECTION, Direction.UP)
                .setValue(WATERLOGGED, false)
        );
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(THICKNESS, WATERLOGGED, VERTICAL_DIRECTION);
    }

    /**
     * this code is written from the perspective of a pointing up spyre
     * it is also used for pointing down spyres, in which case above & below are actually their opposite
     */
    private Thickness getThickness(Direction facing, BlockState above, BlockState below) {
        if (above.is(this)) {
            var aboveFacing = above.getValue(VERTICAL_DIRECTION);
            if (aboveFacing != facing) return Thickness.TIP_MERGE;

            var aboveThickness = above.getValue(THICKNESS);
            if (aboveThickness == Thickness.TIP_MERGE) aboveThickness = Thickness.TIP;
            var ordinal = aboveThickness.ordinal() - 1;

            if (ordinal <= 1) {
                if (below.is(this)) return Thickness.EXTENSION;
                return Thickness.BASE;
            }

            return Thickness.values()[ordinal];
        }

        return Thickness.TIP;
    }

    private Thickness getThickness(Direction facing, LevelAccessor level, BlockPos pos) {
        return getThickness(
                facing,
                level.getBlockState(pos.relative(facing)),
                level.getBlockState(pos.relative(facing.getOpposite()))
        );
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        // TODO use opposite if not working
        var direction = context.getNearestLookingVerticalDirection().getOpposite();
        var thickness = getThickness(direction, context.getLevel(), context.getClickedPos());
        var waterlogged = context.getLevel().getFluidState(context.getClickedPos()).is(Fluids.WATER);

        return defaultBlockState()
                .setValue(VERTICAL_DIRECTION, direction)
                .setValue(WATERLOGGED, waterlogged)
                .setValue(THICKNESS, thickness);
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        if (!canSurvive(state, level, pos)) return Blocks.AIR.defaultBlockState();

        var updated = super.updateShape(state, direction, neighborState, level, pos, neighborPos);
        if (direction.getAxis() != Direction.Axis.Y)
            return updated;

        var facing = updated.getValue(VERTICAL_DIRECTION);

        var thickness = getThickness(facing, level, pos);
        return updated.setValue(THICKNESS, thickness);
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        var direction = state.getValue(VERTICAL_DIRECTION);
        var attachedPos = pos.relative(direction.getOpposite());
        var attached = level.getBlockState(attachedPos);
        return attached.is(this) || attached.isFaceSturdy(level, attachedPos, direction);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        var thickness = state.getValue(THICKNESS);
        var direction = state.getValue(VERTICAL_DIRECTION);
        return switch (thickness) {
            case BASE, EXTENSION -> BASE_SHAPE;
            case FRUSTUM -> FRUSTUM_SHAPE;
            case MIDDLE -> MIDDLE_SHAPE;
            case TIP -> direction == Direction.UP ?
                    TIP_SHAPE_UP
                    : TIP_SHAPE_DOWN;
            case TIP_MERGE -> TIP_MERGE_SHAPE;
        };
    }

    public enum Thickness implements StringRepresentable {
        BASE, EXTENSION, FRUSTUM, MIDDLE, TIP, TIP_MERGE;

        @Override
        public String getSerializedName() {
            return name().toLowerCase(Locale.ROOT);
        }
    }

}
