package galena.oreganized.content.block;

import static net.minecraft.world.level.block.state.properties.BlockStateProperties.HORIZONTAL_FACING;

import galena.oreganized.content.entity.GargoyleBlockEntity;
import galena.oreganized.index.OBlockEntities;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class GargoyleBlock extends HorizontalDirectionalBlock implements EntityBlock {

    public static final EnumProperty<AttachmentType> ATTACHMENT = EnumProperty.create("attachment", AttachmentType.class);

    public final Map<BlockState, VoxelShape> SHAPES = getShapeForEachState(this::getShapeFor);

    public static final DispenseItemBehavior DISPENSE_ITEM_BEHAVIOR = (source, stack) -> {
        var dispenser = source.getBlockState();
        var facing = dispenser.getValue(DispenserBlock.FACING);
        var targetPos = source.getPos().relative(facing);
        var target = source.getLevel().getBlockEntity(targetPos);

        if (target instanceof GargoyleBlockEntity gargoyle) {
            gargoyle.interact(source.getLevel(), targetPos, null, stack, false);
        }

        return stack;
    };

    public GargoyleBlock(Properties properties) {
        super(properties);
        registerDefaultState(getStateDefinition().any().setValue(HORIZONTAL_FACING, Direction.SOUTH).setValue(ATTACHMENT, AttachmentType.FLOOR));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(HORIZONTAL_FACING, ATTACHMENT);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        var base = super.getStateForPlacement(context);
        var clickedFace = context.getClickedFace();

        if (clickedFace.getAxis().isHorizontal() && !context.replacingClickedOnBlock()) {
            return base.setValue(HORIZONTAL_FACING, clickedFace.getOpposite()).setValue(ATTACHMENT, AttachmentType.WALL);
        } else {
            return base.setValue(HORIZONTAL_FACING, context.getHorizontalDirection()).setValue(ATTACHMENT, AttachmentType.FLOOR);
        }
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if (type != OBlockEntities.GARGOYLE.get()) return null;
        BlockEntityTicker<GargoyleBlockEntity> ticker = GargoyleBlockEntity::tick;
        //noinspection unchecked
        return (BlockEntityTicker<T>) ticker;
    }

    @Override
    public boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    @Override
    public int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos) {
        var blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof GargoyleBlockEntity gargoyle) {
            return gargoyle.getAnalogOutputSignal();
        }

        return super.getAnalogOutputSignal(state, level, pos);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        var blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof GargoyleBlockEntity gargoyle) {
            var stack = player.getItemInHand(hand);
            return gargoyle.interact(level, pos, player, stack, false);
        }

        return super.use(state, level, pos, player, hand, hit);
    }

    public static void dripParticles(BlockState state, Level level, BlockPos pos, RandomSource random, ParticleOptions type) {
        double spread = random.nextDouble() * 0.1 - 0.05;
        var offset = AttachmentType.offset(state, pos, spread);
        level.addParticle(type, offset.x, offset.y, offset.z, 0.0, 0.0, 0.0);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new GargoyleBlockEntity(pos, state);
    }

    public enum AttachmentType implements StringRepresentable {
        FLOOR("floor", 0.55, 0.2),
        WALL("wall", 1.3, 0.12);

        private final String name;
        private final double horizontalOffset;
        private final double verticalOffset;

        AttachmentType(String name, double horizontalOffset, double verticalOffset) {
            this.name = name;
            this.horizontalOffset = horizontalOffset;
            this.verticalOffset = verticalOffset;
        }

        public static Vec3 offset(BlockState state, BlockPos pos, double spread) {
            var facing = state.getValue(FACING).getOpposite();
            var attachment = state.getValue(ATTACHMENT);
            double offsetX = facing.getAxis() == Direction.Axis.X ? facing.getStepX() * attachment.horizontalOffset : spread;
            double offsetZ = facing.getAxis() == Direction.Axis.Z ? facing.getStepZ() * attachment.horizontalOffset : spread;

            double x = pos.getX() + 0.5 + offsetX;
            double y = pos.getY() + attachment.verticalOffset + 0.05;
            double z = pos.getZ() + 0.5 + offsetZ;

            return new Vec3(x, y, z);
        }

        @Override
        public String getSerializedName() {
            return this.name;
        }
    }

    protected VoxelShape getShapeFor(BlockState state) {
        var facing = state.getValue(FACING);
        var attachment = state.getValue(ATTACHMENT);

        var xOffset = facing == Direction.EAST ? 1 : 0;
        var xSize = facing.getAxis() == Direction.Axis.X ? 1 : 0;
        var zOffset = facing == Direction.SOUTH ? 1 : 0;
        var zSize = facing.getAxis() == Direction.Axis.Z ? 1 : 0;

        if (attachment == AttachmentType.FLOOR) {

            var base = box(0.0, 0.0, 0.0, 16.0, 2.0, 16.0);
            var legs = box(xOffset * 11.0, 2.0, zOffset * 11.0, xOffset * 11.0 + 5.0 * xSize + 16.0 * zSize, 12.0, zOffset * 11.0 + 5.0 * zSize + 16.0 * xSize);
            var arms = box(xOffset * 12.0 + zSize - facing.getStepX() * 7.0, 2.0, zOffset * 12.0 + xSize - facing.getStepZ() * 7.0, xOffset * 12.0 + 4.0 * xSize + 15.0 * zSize - facing.getStepX() * 7.0, 17.0, zOffset * 12.0 + 4.0 * zSize + 15.0 * xSize - facing.getStepZ() * 7.0);
            var body = box(zSize * 2.0 + xOffset * 5.0 + 1.0, 7.0, xSize * 2.0 + zOffset * 5.0 + 1.0, xSize * 10.0 + xOffset * 5.0 + zSize * 13.0, 16.0, zSize * 10.0 + zOffset * 5.0 + xSize * 13.0);
            var head = box(zSize * 4.0 + xSize * 10.0 - xOffset * 10.0, 7.0, xSize * 4.0 + zSize * 10.0 - zOffset * 10.0, zSize * 12.0 + xSize * 16.0 - xOffset * 10.0, 15.0, xSize * 12.0 + zSize * 16.0 - zOffset * 10.0);

            return Shapes.or(base, legs, arms, body, head);
        }

        if (attachment == AttachmentType.WALL) {
            var base = box(xOffset * 14.0, 0.0, zOffset * 14.0, xOffset * 14.0 + 2.0 * xSize + 16.0 * zSize, 16.0, zOffset * 14.0 + 2.0 * zSize + 16.0 * xSize);
            var head = box(0.0, 0.0, 0.0, 8.0, 8.0, 8.0)
                    .move(facing.getStepX() * -1.25, 5.0 / 16.0, facing.getStepZ() * -1.25)
                    .move(xOffset * 0.5, 0.0, zOffset * 0.5)
                    .move(zSize * 0.25, 0.0, xSize * 0.25);
            var body = box(0.0, 0.0, 0.0, 9.0, 9.0, 9.0)
                    .move(facing.getStepX() * -11.0 / 16.0, 7.0 / 16.0, facing.getStepZ() * -11.0 / 16.0)
                    .move(xOffset * 7.0 / 16.0, 0.0, zOffset * 7.0 / 16.0)
                    .move(zSize * 0.25, 0.0, xSize * 0.25);
            var log = box(xOffset * 14.0, 0.0, zOffset * 14.0, xOffset * 14.0 + 4.0 * xSize + 4.0 * zSize, 4.0, zOffset * 14.0 + 4.0 * zSize + 16.0 * xSize)
                    .move(xSize * 0.125 - xOffset * 0.375, 0.0, zSize * 0.125 - zOffset * 0.375);

            return Shapes.or(base, body, head, log);
        }

        return box(0.0, 0.0, 0.0, 16.0, 16.0, 16.0);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPES.get(state);
    }
}
