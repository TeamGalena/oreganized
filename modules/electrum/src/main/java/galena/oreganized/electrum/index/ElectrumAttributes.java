package galena.oreganized.electrum.index;

import galena.oreganized.OConstants;
import galena.oreganized.register.AttributeRegistryHelper;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.neoforged.neoforge.registries.DeferredHolder;

public class ElectrumAttributes {

    private static final AttributeRegistryHelper ATTRIBUTES = OConstants.REGISTRY_HELPER.getAttributeSubHelper();

    public static final DeferredHolder<Attribute, Attribute> KINETIC_DAMAGE = ATTRIBUTES.createRanged("kinetic_damage", 0.0, 0.0, 30.0);

    public static void register() {
        // Load this class
    }

}
