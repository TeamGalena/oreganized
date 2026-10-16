package galena.oreganized.register;

import com.teamabnormals.blueprint.core.util.registry.ISubRegistryHelper;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import galena.oreganized.OConstants;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.jetbrains.annotations.ApiStatus;

public class ORegistryHelper extends RegistryHelper {

    private static final Map<String, ORegistryHelper> MODULES = new HashMap<>();

    public ORegistryHelper(String modId) {
        super(modId);
    }

    private <T> void putSimpleSubHelper(ResourceKey<Registry<T>> key) {
        putSubHelper(key, new SimpleRegistryHelper<>(this, key));
    }

    private <T> void putSubHelper(ResourceKey<Registry<T>> key, Function<RegistryHelper, ISubRegistryHelper<T>> factory) {
        putSubHelper(key, factory.apply(this));
    }

    @ApiStatus.Internal
    public static Optional<ORegistryHelper> forModule(String moduleId) {
        return Optional.ofNullable(MODULES.get(moduleId));
    }

    public static ORegistryHelper createRegistryHelper(String moduleId) {
        var helper = new ORegistryHelper(OConstants.MOD_ID);
        var active = ModList.get().getModContainerById(moduleId).orElseThrow();

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
        helper.putSubHelper(Registries.RECIPE_TYPE, RecipeTypeRegistryHelper::new);
        helper.putSimpleSubHelper(Registries.RECIPE_SERIALIZER);
        helper.putSubHelper(Registries.STRUCTURE_TYPE, StructureTypeRegistryHelper::new);
        helper.putSubHelper(Registries.POTION, PotionRegistryHelper::new);
        helper.putSubHelper(Registries.SOUND_EVENT, SoundRegistryHelper::new);

        helper.register(active.getEventBus());

        MODULES.put(moduleId, helper);
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

    public RecipeTypeRegistryHelper getRecipeTypeSubHelper() {
        return super.getSubHelper(Registries.RECIPE_TYPE);
    }

    public SimpleRegistryHelper<RecipeSerializer<?>> getRecipeSerializerSubHelper() {
        return super.getSubHelper(Registries.RECIPE_SERIALIZER);
    }

    public StructureTypeRegistryHelper getStructureTypeSubHelper() {
        return super.getSubHelper(Registries.STRUCTURE_TYPE);
    }

    public PotionRegistryHelper getPotionSubHelper() {
        return super.getSubHelper(Registries.POTION);
    }

}
