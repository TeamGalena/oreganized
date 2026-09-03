package galena.oreganized.register;

import com.teamabnormals.blueprint.core.util.registry.ISubRegistryHelper;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import galena.oreganized.OConstants;

import java.util.function.Function;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

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
        helper.putSimpleSubHelper(Registries.FLUID);
        helper.putSimpleSubHelper(NeoForgeRegistries.Keys.FLUID_TYPES);
        helper.putSubHelper(Registries.PARTICLE_TYPE, ParticleTypeRegistryHelper::new);
        helper.putSubHelper(Registries.TRIGGER_TYPE, CriterionTriggerRegistryHelper::new);
        helper.putSimpleSubHelper(NeoForgeRegistries.Keys.ATTACHMENT_TYPES);
        helper.putSimpleSubHelper(Registries.MOB_EFFECT);
        helper.putSubHelper(Registries.DATA_COMPONENT_TYPE, DataComponentRegistryHelper::new);
        return helper;
    }

    public AttributeRegistryHelper getAttributeSubHelper() {
        return super.getSubHelper(Registries.ATTRIBUTE);
    }

    public SimpleRegistryHelper<ArmorMaterial> getArmorMaterialSubHelper() {
        return super.getSubHelper(Registries.ARMOR_MATERIAL);
    }

    public SimpleRegistryHelper<Fluid> getFluidSubHelper() {
        return super.getSubHelper(Registries.FLUID);
    }

    public SimpleRegistryHelper<FluidType> getFluidTypeSubHelper() {
        return super.getSubHelper(NeoForgeRegistries.Keys.FLUID_TYPES);
    }

    public ParticleTypeRegistryHelper getParticleTypeSubHelper() {
        return super.getSubHelper(Registries.PARTICLE_TYPE);
    }

    public CriterionTriggerRegistryHelper getCriterionTriggerSubHelper() {
        return super.getSubHelper(Registries.TRIGGER_TYPE);
    }

    public SimpleRegistryHelper<AttachmentType<?>> getAttachmentTypeSubHelper() {
        return super.getSubHelper(NeoForgeRegistries.Keys.ATTACHMENT_TYPES);
    }

    public SimpleRegistryHelper<MobEffect> getEffectSubHelper() {
        return super.getSubHelper(Registries.MOB_EFFECT);
    }

    public DataComponentRegistryHelper getDataComponentSubHelper() {
        return super.getSubHelper(Registries.DATA_COMPONENT_TYPE);
    }

}
