package galena.oreganized.content.item;

import galena.oreganized.content.entity.MinecartShrapnelBomb;
import java.util.function.Supplier;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseRailBlock;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.RailShape;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.NotNull;

public class MinecartShrapnelBombItem extends MinecartItem {

    public final Supplier<EntityType<MinecartShrapnelBomb>> minecart;

    private final DispenseItemBehavior dispenserBehaviour = new DefaultDispenseItemBehavior() {
        private final DefaultDispenseItemBehavior defaultBehaviour = new DefaultDispenseItemBehavior();

        public ItemStack execute(BlockSource source, ItemStack stack) {
            var direction = source.state().getValue(DispenserBlock.FACING);
            var level = source.level();

            var center = source.center();
            var x = center.x() + (double) direction.getStepX() * 1.125;
            var y = Math.floor(center.y()) + (double) direction.getStepY();
            var z = center.z() + (double) direction.getStepZ() * 1.125;

            var pos = source.pos().relative(direction);
            var state = level.getBlockState(pos);
            var railShape = state.getBlock() instanceof BaseRailBlock ? state.getValue(((BaseRailBlock) state.getBlock()).getShapeProperty()) : RailShape.NORTH_SOUTH;

            double yOffset;
            if (state.is(BlockTags.RAILS)) {
                yOffset = railShape.isAscending() ? 0.6 : 0.1;
            } else {
                if (!state.isAir() || !level.getBlockState(pos.below()).is(BlockTags.RAILS)) {
                    return defaultBehaviour.dispense(source, stack);
                }

                var belowState = level.getBlockState(pos.below());
                var belowRailShape = belowState.getBlock() instanceof BaseRailBlock ? belowState.getValue(((BaseRailBlock) belowState.getBlock()).getShapeProperty()) : RailShape.NORTH_SOUTH;

                yOffset = direction != Direction.DOWN && belowRailShape.isAscending() ? -0.4 : -0.9;
            }

            var minecart = createMinecart(level, x, y + yOffset, z);
            if (minecart == null) return stack;

            EntityType.createDefaultStackConfig(level, stack, null).accept(minecart);
            level.addFreshEntity(minecart);
            stack.shrink(1);
            return stack;
        }
    };

    public MinecartShrapnelBombItem(AbstractMinecart.Type base, Supplier<EntityType<MinecartShrapnelBomb>> minecart) {
        super(base, new Properties().stacksTo(1));
        this.minecart = minecart;
        DispenserBlock.registerBehavior(this, dispenserBehaviour);
    }

    private AbstractMinecart createMinecart(Level level, double x, double y, double z) {
        var minecart = this.minecart.get().create(level);
        if (minecart == null) return null;

        minecart.setPos(x, y, z);
        minecart.xo = x;
        minecart.yo = y;
        minecart.zo = z;
        return minecart;
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos blockpos = context.getClickedPos();
        BlockState blockstate = level.getBlockState(blockpos);
        if (!blockstate.is(BlockTags.RAILS)) {
            return InteractionResult.FAIL;
        } else {
            ItemStack stack = context.getItemInHand();
            if (!level.isClientSide) {
                RailShape railshape = blockstate.getBlock() instanceof BaseRailBlock ? ((BaseRailBlock) blockstate.getBlock()).getRailDirection(blockstate, level, blockpos, null) : RailShape.NORTH_SOUTH;
                double d0 = 0.0D;
                if (railshape.isAscending()) {
                    d0 = 0.5D;
                }

                var minecart = createMinecart(level, (double) blockpos.getX() + 0.5D, (double) blockpos.getY() + 0.0625D + d0, (double) blockpos.getZ() + 0.5D);
                if (stack.has(DataComponents.CUSTOM_NAME)) {
                    minecart.setCustomName(stack.getHoverName());
                }

                level.addFreshEntity(minecart);
                level.gameEvent(GameEvent.ENTITY_PLACE, blockpos, GameEvent.Context.of(context.getPlayer(), level.getBlockState(blockpos.below())));
            }

            stack.shrink(1);
            return InteractionResult.sidedSuccess(level.isClientSide);
        }
    }

    protected void playSound(BlockSource source) {
        source.level().levelEvent(1000, source.pos(), 0);
    }

}
