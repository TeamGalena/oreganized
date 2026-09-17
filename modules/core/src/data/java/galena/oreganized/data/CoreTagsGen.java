package galena.oreganized.data;

import static net.minecraft.resources.ResourceLocation.fromNamespaceAndPath;

import com.tterrag.registrate.providers.RegistrateItemTagsProvider;
import com.tterrag.registrate.providers.RegistrateTagsProvider;
import galena.oreganized.ModCompat;
import galena.oreganized.OConstants;
import galena.oreganized.index.CoreTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.Tags;

@Mod(OConstants.MOD_ID)
public class CoreTagsGen {

    public CoreTagsGen() {
        ODatagen.addItemTagProvider(this::items);
        ODatagen.addBlockTagProvider(this::blocks);
    }

    private void items(RegistrateItemTagsProvider provider) {
        provider.addTag(Tags.Items.NUGGETS).addTags(CoreTags.Items.NUGGETS_SILVER, CoreTags.Items.NUGGETS_LEAD, CoreTags.Items.NUGGETS_ELECTRUM, CoreTags.Items.NUGGETS_NETHERITE);

        provider.addTag(Tags.Items.INGOTS).addTags(CoreTags.Items.INGOTS_SILVER, CoreTags.Items.INGOTS_LEAD, CoreTags.Items.INGOTS_ELECTRUM);

        provider.addTag(Tags.Items.RAW_MATERIALS).addTags(CoreTags.Items.RAW_MATERIALS_SILVER, CoreTags.Items.RAW_MATERIALS_LEAD);
    }

    private void blocks(RegistrateTagsProvider.IntrinsicImpl<Block> provider) {
        provider.addTag(Tags.Blocks.STORAGE_BLOCKS).addTags(
                CoreTags.Blocks.STORAGE_BLOCKS_SILVER,
                CoreTags.Blocks.STORAGE_BLOCKS_LEAD,
                CoreTags.Blocks.STORAGE_BLOCKS_ELECTRUM,
                CoreTags.Blocks.STORAGE_BLOCKS_RAW_SILVER,
                CoreTags.Blocks.STORAGE_BLOCKS_RAW_LEAD
        );

        provider.addTag(Tags.Blocks.ORES).addTags(CoreTags.Blocks.ORES_SILVER, CoreTags.Blocks.ORES_LEAD);
        provider.addTag(Tags.Blocks.ORE_RATES_SINGULAR).addTags(CoreTags.Blocks.ORES_SILVER, CoreTags.Blocks.ORES_LEAD);

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
