package galena.oreganized;

import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import galena.oreganized.register.AttributeRegistrySubHelper;
import galena.oreganized.register.SimpleSubRegistryHelper;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;

class ORegistryHelper {

    private static <T> void putSimpleSubHelper(RegistryHelper helper, ResourceKey<Registry<T>> key) {
        helper.putSubHelper(key, new SimpleSubRegistryHelper<>(helper, key));
    }

    static RegistryHelper createRegistryHelper() {
        var helper = new RegistryHelper(OConstants.MOD_ID);
        putSimpleSubHelper(helper, Registries.ARMOR_MATERIAL);
        helper.putSubHelper(Registries.ATTRIBUTE, new AttributeRegistrySubHelper(helper));
        return helper;
    }

}
