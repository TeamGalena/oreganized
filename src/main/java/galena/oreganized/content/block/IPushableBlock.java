package galena.oreganized.content.block;

import galena.oreganized.index.OBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public interface IPushableBlock extends EntityBlock {

    void onFullyPushed(Player player, Level level, BlockPos pos, BlockState state);

    default void reset(Level level, BlockPos pos, BlockState state) {}

    default boolean isToggleable(BlockState state) {
        return false;
    }

    @Override
    @Nullable
    default BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new PushableBlockEntity(pos, state);
    }

    @Override
    @Nullable
    default <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if (type == OBlockEntities.PUSHABLE.get()) {
            return (l, p, s, be) -> ((PushableBlockEntity) be).tick(l, p, s);
        }
        return null;
    }
}
