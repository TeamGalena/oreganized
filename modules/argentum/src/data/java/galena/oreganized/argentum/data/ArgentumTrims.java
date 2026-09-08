package galena.oreganized.argentum.data;

import static galena.oreganized.data.extensions.ORegistryBootstrapExtensions.registerTrim;
import static galena.oreganized.data.provider.RegistrateSpriteSourceProvider.BLOCKS_ATLAS;

import com.teamabnormals.blueprint.core.api.BlueprintTrims;
import com.teamabnormals.blueprint.core.other.tags.BlueprintTrimMaterialTags;
import com.tterrag.registrate.providers.RegistrateTagsProvider;
import galena.oreganized.OConstants;
import galena.oreganized.argentum.index.ArgentumItems;
import galena.oreganized.data.ODatagen;
import galena.oreganized.data.provider.RegistrateSpriteSourceProvider;
import java.util.Map;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.neoforged.fml.common.Mod;

@Mod(OConstants.MOD_ID)
public class ArgentumTrims {

    private static final ResourceKey<TrimMaterial> SILVER = ResourceKey.create(Registries.TRIM_MATERIAL, OConstants.modLoc("silver"));

    public ArgentumTrims() {
        ODatagen.addSpriteSourceProvider(this::spriteSources);
        ODatagen.addDataRegistryEntries(Registries.TRIM_MATERIAL, this::trimMaterials);
        ODatagen.addTrimMaterialTagProvider(this::tags);
    }

    private void trimMaterials(BootstrapContext<TrimMaterial> context) {
        registerTrim(context, SILVER, ArgentumItems.SILVER_INGOT, Style.EMPTY.withColor(10663869), Map.of());
    }

    private void spriteSources(RegistrateSpriteSourceProvider provider) {
        provider.addAtlasSource(BlueprintTrims.ARMOR_TRIMS_ATLAS, BlueprintTrims.materialPatternPermutations(SILVER));
        provider.addAtlasSource(BLOCKS_ATLAS, BlueprintTrims.materialPermutationsForItemLayers(SILVER));
    }

    private void tags(RegistrateTagsProvider.Impl<TrimMaterial> provider) {
        provider.addTag(BlueprintTrimMaterialTags.GENERATES_OVERRIDES).add(SILVER);
    }

}
