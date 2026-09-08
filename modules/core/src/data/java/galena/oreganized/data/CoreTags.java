package galena.oreganized.data;

import com.tterrag.registrate.providers.RegistrateItemTagsProvider;
import com.tterrag.registrate.providers.RegistrateTagsProvider;
import galena.oreganized.ModCompat;
import galena.oreganized.OConstants;
import galena.oreganized.index.OTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.Tags;

@Mod(OConstants.MOD_ID)
public class CoreTags {

    public CoreTags() {
        ODatagen.addItemTagProvider(this::items);
        ODatagen.addBlockTagProvider(this::blocks);
    }

    private void items(RegistrateItemTagsProvider provider) {
        provider.addTag(OTags.Items.LIGHTER_THAN_LEAD).add(Items.IRON_BOOTS);

        provider.addTag(Tags.Items.NUGGETS).addTags(OTags.Items.NUGGETS_SILVER, OTags.Items.NUGGETS_LEAD, OTags.Items.NUGGETS_ELECTRUM, OTags.Items.NUGGETS_NETHERITE);

        provider.addTag(Tags.Items.INGOTS).addTags(OTags.Items.INGOTS_SILVER, OTags.Items.INGOTS_LEAD, OTags.Items.INGOTS_ELECTRUM);

        provider.addTag(Tags.Items.RAW_MATERIALS).addTags(OTags.Items.RAW_MATERIALS_SILVER, OTags.Items.RAW_MATERIALS_LEAD);

        provider.addTag(OTags.Items.ENCHANTED_BOOK_LIKE)
                .add(Items.ENCHANTED_BOOK)
                .addOptional(ResourceLocation.fromNamespaceAndPath(ModCompat.QUARK, "ancient_tome"));

        provider.addTag(OTags.Items.GARGOYLE_SNACK).addTags(OTags.Items.INGOTS_SILVER);
    }

    private void blocks(RegistrateTagsProvider.IntrinsicImpl<Block> provider) {
        provider.addTag(Tags.Blocks.STORAGE_BLOCKS).addTags(
                OTags.Blocks.STORAGE_BLOCKS_SILVER,
                OTags.Blocks.STORAGE_BLOCKS_LEAD,
                OTags.Blocks.STORAGE_BLOCKS_ELECTRUM,
                OTags.Blocks.STORAGE_BLOCKS_RAW_SILVER,
                OTags.Blocks.STORAGE_BLOCKS_RAW_LEAD
        );

        provider.addTag(Tags.Blocks.ORES).addTags(OTags.Blocks.ORES_SILVER, OTags.Blocks.ORES_LEAD);
        provider.addTag(Tags.Blocks.ORE_RATES_SINGULAR).addTags(OTags.Blocks.ORES_SILVER, OTags.Blocks.ORES_LEAD);
    }

}
