package galena.oreganized.electrum.index;

import galena.oreganized.OConstants;
import galena.oreganized.register.AttributeRegistryHelper;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;
import net.neoforged.neoforge.registries.DeferredHolder;

@EventBusSubscriber
public class ElectrumAttributes {

    private static final AttributeRegistryHelper ATTRIBUTES = OConstants.REGISTRY_HELPER.getAttributeSubHelper();

    public static final DeferredHolder<Attribute, Attribute> KINETIC_DAMAGE = ATTRIBUTES.createRanged("kinetic_damage", 0.0, 0.0, 30.0);


    @SubscribeEvent
    public static void registerAttributes(EntityAttributeModificationEvent event) {
        for (var entityType : event.getTypes()) {
            if (event.has(entityType, Attributes.ATTACK_DAMAGE)) {
                event.add(entityType, ElectrumAttributes.KINETIC_DAMAGE);
            }
        }
    }

}
