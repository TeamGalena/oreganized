package galena.oreganized.data;

import static net.minecraft.resources.ResourceLocation.fromNamespaceAndPath;

import com.tterrag.registrate.providers.RegistrateTagsProvider;
import galena.oreganized.ModCompat;
import galena.oreganized.core.CoreModule;
import galena.oreganized.index.CoreTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.fml.common.Mod;

@Mod(CoreModule.MOD_ID)
public class CoreTagsGen {

    public CoreTagsGen() {
        ODatagen.addBlockTagProvider(this::blocks);
    }

    private void blocks(RegistrateTagsProvider.IntrinsicImpl<Block> provider) {
        provider.addTag(CoreTags.Blocks.AMETHYST_CLUSTERS)
                .add(Blocks.AMETHYST_CLUSTER)
                .add(Blocks.LARGE_AMETHYST_BUD)
                .add(Blocks.MEDIUM_AMETHYST_BUD)
                .add(Blocks.SMALL_AMETHYST_BUD);

        provider.addTag(CoreTags.Blocks.QUARTZITE_CLUSTERS)
                .addOptional(fromNamespaceAndPath(ModCompat.NO_MANS_LAND, "quartzite_cluster"))
                .addOptional(fromNamespaceAndPath(ModCompat.NO_MANS_LAND, "large_quartzite_bud"))
                .addOptional(fromNamespaceAndPath(ModCompat.NO_MANS_LAND, "medium_quartzite_bud"))
                .addOptional(fromNamespaceAndPath(ModCompat.NO_MANS_LAND, "small_quartzite_bud"));
    }

}
