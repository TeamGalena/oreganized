package galena.oreganized.argentum;

import galena.oreganized.OConstants;
import galena.oreganized.argentum.index.ArgentumAttributes;
import galena.oreganized.argentum.index.ArgentumBlocks;
import galena.oreganized.argentum.index.ArgentumItems;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;

@Mod(OConstants.MOD_ID)
@EventBusSubscriber
public class ArgentumModule {

    public ArgentumModule() {
        ArgentumBlocks.register();
        ArgentumItems.register();
        ArgentumAttributes.register();
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeModificationEvent event) {
        for (var entityType : event.getTypes()) {
            if (event.has(entityType, Attributes.ARMOR)) {
                event.add(entityType, ArgentumAttributes.INVINCIBILITY_FRAMES);
            }
        }
    }

}
