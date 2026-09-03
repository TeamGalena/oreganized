package galena.oreganized.argentum.index;

import galena.oreganized.OConstants;
import galena.oreganized.register.AttributeRegistryHelper;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;
import net.neoforged.neoforge.registries.DeferredHolder;

@EventBusSubscriber
public class ArgentumAttributes {

    private static final AttributeRegistryHelper ATTRIBUTES = OConstants.REGISTRY_HELPER.getAttributeSubHelper();

    public static final DeferredHolder<Attribute, Attribute> INVINCIBILITY_FRAMES = ATTRIBUTES.createRanged("invincibility_frames", 1.0, 0.0, 60);

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeModificationEvent event) {
        for (var entityType : event.getTypes()) {
            if (event.has(entityType, Attributes.ARMOR)) {
                event.add(entityType, ArgentumAttributes.INVINCIBILITY_FRAMES);
            }
        }
    }

}
