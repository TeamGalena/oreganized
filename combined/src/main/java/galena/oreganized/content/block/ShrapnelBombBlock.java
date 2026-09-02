package galena.oreganized.content.block;

import galena.oreganized.content.entity.ShrapnelBomb;
import galena.oreganized.index.OSoundEvents;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.TntBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

public class ShrapnelBombBlock extends TntBlock {

    public ShrapnelBombBlock(Properties properties) {
        super(properties);
    }

    public void onCaughtFire(BlockState state, Level world, BlockPos pos, @Nullable Direction direction, @Nullable LivingEntity entity) {
        if (!world.isClientSide) {
            ShrapnelBomb shrapnelBomb = new ShrapnelBomb(world, pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, entity);
            world.addFreshEntity(shrapnelBomb);
            world.playSound(null, shrapnelBomb.getX(), shrapnelBomb.getY(), shrapnelBomb.getZ(), OSoundEvents.SHRAPNEL_BOMB_PRIMED.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
            world.gameEvent(entity, GameEvent.PRIME_FUSE, pos);
        }
    }

    public void wasExploded(Level world, BlockPos pos, Explosion explosion) {
        if (!world.isClientSide) {
            ShrapnelBomb shrapnelBomb = new ShrapnelBomb(world, (double)pos.getX() + 0.5D, pos.getY(), (double)pos.getZ() + 0.5D, explosion.getIndirectSourceEntity());
            int fuse = shrapnelBomb.getFuse();
            shrapnelBomb.setFuse((short)(world.random.nextInt(fuse / 4) + fuse / 8));
            world.addFreshEntity(shrapnelBomb);
        }
    }
}
