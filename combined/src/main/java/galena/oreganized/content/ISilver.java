package galena.oreganized.content;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

public interface ISilver {
    float RANGE = 24.0F;

    default List<Entity> getEntities(Level world, BlockPos pos, @Nullable Player player) {
        return world.getEntities((Entity) player,
                new AABB(pos.getX() + RANGE, pos.getY() + RANGE, pos.getZ() + RANGE,
                        pos.getX() - RANGE, pos.getY() - RANGE, pos.getZ() - RANGE),
                (Entity living) -> living instanceof LivingEntity && ((LivingEntity) living).isInvertedHealAndHarm()
        );
    }

    default boolean isUndeadNearby(Level world, BlockPos pos, @Nullable Player player) {
        for (Entity e : getEntities(world, pos, player)) {
            LivingEntity living = (LivingEntity) e;
            if (living.isInvertedHealAndHarm()) {
                return true;
            }
        }
        return false;
    }

    default int getUndeadDistance(Level world, BlockPos pos, @Nullable Player player, int frames) {
        int dist = frames;

        for (Entity e : getEntities(world, pos, player)) {
            LivingEntity living = (LivingEntity) e;
            double distance = player != null ? living.distanceTo(player) : Math.sqrt(living.distanceToSqr(pos.getX(), pos.getY(), pos.getZ()));
            double ceil = Math.ceil(distance / (RANGE / frames));
            if (distance < RANGE && ((int) ceil) < dist) {
                if (distance < 6) {
                    dist = 1;
                } else dist = Math.max( (int) ceil, 2);

                if (dist > frames) {
                    dist = frames;
                }
            }
        }

        if (!this.isUndeadNearby(world, pos, player)) {
            dist = frames;
        }

        return dist;
    }
}
