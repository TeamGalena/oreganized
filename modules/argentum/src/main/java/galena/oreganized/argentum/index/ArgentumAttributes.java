package galena.oreganized.argentum.index;

import galena.oreganized.OConstants;
import galena.oreganized.register.AttributeRegistrySubHelper;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.neoforged.neoforge.registries.DeferredHolder;

public class ArgentumAttributes {

    private static final AttributeRegistrySubHelper ATTRIBUTES = OConstants.REGISTRY_HELPER.getSubHelper(Registries.ATTRIBUTE);

    public static final DeferredHolder<Attribute, Attribute> INVINCIBILITY_FRAMES = ATTRIBUTES.createRanged("invincibility_frames", 1.0, 0.0, 60);

    public static void register() {
        // Load this class
    }

}
