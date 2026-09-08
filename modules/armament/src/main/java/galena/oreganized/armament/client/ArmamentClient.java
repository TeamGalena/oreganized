package galena.oreganized.armament.client;

import galena.oreganized.OConstants;
import galena.oreganized.armament.index.ArmamentItems;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.ChargedProjectiles;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(Dist.CLIENT)
public class ArmamentClient {

    @SubscribeEvent
    private static void registerItemProperties(FMLClientSetupEvent event) {
        ItemProperties.register(Items.CROSSBOW, OConstants.modLoc("lead_bolt"), (stack, level, user, i) ->
                stack.getOrDefault(DataComponents.CHARGED_PROJECTILES, ChargedProjectiles.EMPTY).contains(ArmamentItems.LEAD_BOLT.get()) ? 1.0F : 0.0F
        );
    }

}
