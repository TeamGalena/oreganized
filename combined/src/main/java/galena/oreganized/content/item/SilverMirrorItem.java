package galena.oreganized.content.item;

import com.mojang.logging.annotations.MethodsReturnNonnullByDefault;
import galena.oreganized.content.ISilver;
import galena.oreganized.index.ODataComponents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

@MethodsReturnNonnullByDefault
public class SilverMirrorItem extends Item implements ISilver {

    public static final int TEXTURED_FRAMES = 8;

    public SilverMirrorItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level world, Entity entity, int i, boolean idk) {
        if (!(entity instanceof Player player)) return;
        BlockPos pos = player.getOnPos();
        int dist = getUndeadDistance(world, pos, player, TEXTURED_FRAMES);

        stack.set(ODataComponents.MIRROR_LEVEL, dist);
    }
}
