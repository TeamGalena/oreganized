package galena.oreganized.argentum.client;

import galena.oreganized.argentum.index.ArgentumDataComponents;
import galena.oreganized.argentum.index.ArgentumItems;
import net.minecraft.client.renderer.item.ItemProperties;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(Dist.CLIENT)
public class ArgentumClient {

    @SubscribeEvent
    private static void registerItemProperties(FMLClientSetupEvent event) {
        ItemProperties.register(ArgentumItems.SILVER_MIRROR.get(), ArgentumDataComponents.MIRROR_LEVEL.getId(), (stack, world, entity, seed) ->
                stack.getOrDefault(ArgentumDataComponents.MIRROR_LEVEL.get(), 8)
        );
    }

}
