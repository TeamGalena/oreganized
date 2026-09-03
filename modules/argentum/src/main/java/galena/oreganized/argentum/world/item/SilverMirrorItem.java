package galena.oreganized.argentum.world.item;

import galena.oreganized.argentum.index.ArgentumDataComponents;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

public class SilverMirrorItem extends Item {

    private static final float RANGE = 24.0F;
    public static final int TEXTURED_FRAMES = 8;

    public SilverMirrorItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level world, Entity entity, int i, boolean idk) {
        if (!(entity instanceof Player player)) return;
        BlockPos pos = player.getOnPos();
        int dist = getUndeadDistance(world, pos, player, TEXTURED_FRAMES);

        stack.set(ArgentumDataComponents.MIRROR_LEVEL, dist);
    }


    private static int getUndeadDistance(Level world, BlockPos pos, @Nullable Player player, int frames) {
        int dist = frames;

        for (Entity e : getEntities(world, pos, player)) {
            LivingEntity living = (LivingEntity) e;
            double distance = player != null ? living.distanceTo(player) : Math.sqrt(living.distanceToSqr(pos.getX(), pos.getY(), pos.getZ()));
            double ceil = Math.ceil(distance / (RANGE / frames));
            if (distance < RANGE && ((int) ceil) < dist) {
                if (distance < 6) {
                    dist = 1;
                } else dist = Math.max((int) ceil, 2);

                if (dist > frames) {
                    dist = frames;
                }
            }
        }

        if (!isUndeadNearby(world, pos, player)) {
            dist = frames;
        }

        return dist;
    }

    private static List<Entity> getEntities(Level world, BlockPos pos, @Nullable Player player) {
        return world.getEntities(player,
                new AABB(pos.getX() + RANGE, pos.getY() + RANGE, pos.getZ() + RANGE,
                        pos.getX() - RANGE, pos.getY() - RANGE, pos.getZ() - RANGE),
                (Entity living) -> living instanceof LivingEntity && ((LivingEntity) living).isInvertedHealAndHarm()
        );
    }

    private static boolean isUndeadNearby(Level world, BlockPos pos, @Nullable Player player) {
        for (var entity : getEntities(world, pos, player)) {
            LivingEntity living = (LivingEntity) entity;
            if (living.isInvertedHealAndHarm()) {
                return true;
            }
        }
        return false;
    }
}
