package galena.oreganized.engraved.world.item;

import galena.oreganized.engraved.index.EngravedItems;
import galena.oreganized.index.OTags;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

@EventBusSubscriber(Dist.CLIENT)
public class BushHammerItem extends DiggerItem {

    public BushHammerItem(Tier tier, Item.Properties properties) {
        super(tier, OTags.Blocks.MINEABLE_WITH_BUSH_HAMMER, properties);
    }

    @SubscribeEvent
    public static void addTooltips(ItemTooltipEvent event) {
        if (event.getItemStack().is(EngravedItems.BUSH_HAMMER.get())) {
            var tooltip = event.getToolTip();
            var wipTitle = Component.translatable("tooltip.oreganized.wip.title");
            var wipDesc = Component.translatable("tooltip.oreganized.wip.description");

            tooltip.add(wipTitle.withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.BOLD));
            tooltip.add(wipDesc.withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC));
        }
    }

}
