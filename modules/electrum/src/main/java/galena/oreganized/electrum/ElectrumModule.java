package galena.oreganized.electrum;

import galena.oreganized.OConstants;
import galena.oreganized.electrum.index.ElectrumAttributes;
import galena.oreganized.electrum.index.ElectrumBlocks;
import galena.oreganized.electrum.index.ElectrumItems;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;

@Mod(OConstants.MOD_ID)
@EventBusSubscriber
public class ElectrumModule {

    public ElectrumModule() {
        ElectrumBlocks.register();
        ElectrumItems.register();
        ElectrumAttributes.register();
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeModificationEvent event) {
        for (var entityType : event.getTypes()) {
            if (event.has(entityType, Attributes.ATTACK_DAMAGE)) {
                event.add(entityType, ElectrumAttributes.KINETIC_DAMAGE);
            }
        }
    }

}
