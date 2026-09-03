package galena.oreganized.register;

import com.teamabnormals.blueprint.core.util.registry.ISubRegistryHelper;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import galena.oreganized.OConstants;
import java.util.function.Function;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ArmorMaterial;

public class ORegistryHelper extends RegistryHelper {

    public ORegistryHelper(String modId) {
        super(modId);
    }

    private <T> void putSimpleSubHelper(ResourceKey<Registry<T>> key) {
        putSubHelper(key, new SimpleRegistryHelper<>(this, key));
    }

    private <T> void putSubHelper(ResourceKey<Registry<T>> key, Function<RegistryHelper, ISubRegistryHelper<T>> factory) {
        putSubHelper(key, factory.apply(this));
    }

    public static ORegistryHelper createRegistryHelper() {
        var helper = new ORegistryHelper(OConstants.MOD_ID);
        helper.putSimpleSubHelper(Registries.ARMOR_MATERIAL);
        helper.putSubHelper(Registries.ATTRIBUTE, AttributeRegistryHelper::new);
        helper.putSubHelper(Registries.ITEM, ItemRegistryHelper::new);
        helper.putSubHelper(Registries.BLOCK, BlockRegistryHelper::new);
        return helper;
    }

    public AttributeRegistryHelper getAttributeSubHelper() {
        return super.getSubHelper(Registries.ATTRIBUTE);
    }

    public SimpleRegistryHelper<ArmorMaterial> getArmorMaterialSubHelper() {
        return super.getSubHelper(Registries.ARMOR_MATERIAL);
    }


}
