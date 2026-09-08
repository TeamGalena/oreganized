package galena.oreganized.electrum.data;

import static galena.oreganized.data.extensions.ORegistryBootstrapExtensions.registerTrim;
import static galena.oreganized.data.provider.RegistrateSpriteSourceProvider.BLOCKS_ATLAS;

import com.teamabnormals.blueprint.core.api.BlueprintTrims;
import galena.oreganized.OConstants;
import galena.oreganized.data.ODatagen;
import galena.oreganized.data.provider.RegistrateSpriteSourceProvider;
import galena.oreganized.electrum.index.ElectrumItems;
import java.util.Map;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.neoforged.fml.common.Mod;

@Mod(OConstants.MOD_ID)
public class ElectrumTrims {

    private static final ResourceKey<TrimMaterial> ELECTRUM = ResourceKey.create(Registries.TRIM_MATERIAL, OConstants.modLoc("electrum"));

    public ElectrumTrims() {
        ODatagen.addSpriteSourceProvider(this::spriteSources);
        ODatagen.addDataRegistryEntries(Registries.TRIM_MATERIAL, this::trimMaterials);
    }

    private void trimMaterials(BootstrapContext<TrimMaterial> context) {
        registerTrim(context, ELECTRUM, ElectrumItems.ELECTRUM_INGOT, Style.EMPTY.withColor(13747326), Map.of());
    }

    private void spriteSources(RegistrateSpriteSourceProvider provider) {
        provider.addAtlasSource(BlueprintTrims.ARMOR_TRIMS_ATLAS, BlueprintTrims.materialPatternPermutations(ELECTRUM));
        provider.addAtlasSource(BLOCKS_ATLAS, BlueprintTrims.materialPatternPermutations(ELECTRUM));
    }

}
