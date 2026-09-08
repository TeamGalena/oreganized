package galena.oreganized.data;

import com.tterrag.registrate.providers.RegistrateLangProvider;
import galena.oreganized.OConstants;
import galena.oreganized.index.OTags;
import net.neoforged.fml.common.Mod;

@Mod(OConstants.MOD_ID)
public class CoreLang {

    public CoreLang() {
        ODatagen.addLangProvider(this::generate);
    }

    private void generate(RegistrateLangProvider provider) {
        provider.add("tooltip.oreganized.wip.title", "Work In Progress");
        provider.add("tooltip.oreganized.wip.description", "Usages for this item will be available in a future release");

        provider.add(OTags.Items.INGOTS_ELECTRUM, "Electrum Ingots");
        provider.add(OTags.Items.INGOTS_SILVER, "Silver Ingots");
        provider.add(OTags.Items.INGOTS_LEAD, "Lead Ingots");
        provider.add(OTags.Items.NUGGETS_ELECTRUM, "Electrum Nuggets");
        provider.add(OTags.Items.NUGGETS_LEAD, "Lead Nuggets");
        provider.add(OTags.Items.NUGGETS_SILVER, "Silver Nuggets");
        provider.add(OTags.Items.NUGGETS_NETHERITE, "Netherite Nuggets");
        provider.add(OTags.Items.ORES_LEAD, "Lead Ores");
        provider.add(OTags.Items.ORES_SILVER, "Silver Ores");
        provider.add(OTags.Items.RAW_MATERIALS_LEAD, "Raw Lead");
        provider.add(OTags.Items.RAW_MATERIALS_SILVER, "Raw Silver");
        provider.add(OTags.Items.STORAGE_BLOCKS_ELECTRUM, "Electrum Storage Blocks");
        provider.add(OTags.Items.STORAGE_BLOCKS_LEAD, "Lead Storage Blocks");
        provider.add(OTags.Items.STORAGE_BLOCKS_SILVER, "Silver Storage Blocks");
        provider.add(OTags.Items.STORAGE_BLOCKS_RAW_LEAD, "Raw Lead Storage Blocks");
        provider.add(OTags.Items.STORAGE_BLOCKS_RAW_SILVER, "Raw Silver Storage Blocks");


        provider.add(OTags.Blocks.ORES_LEAD, "Lead Ores");
        provider.add(OTags.Blocks.ORES_SILVER, "Silver Ores");
        provider.add(OTags.Blocks.STORAGE_BLOCKS_ELECTRUM, "Electrum Storage Blocks");
        provider.add(OTags.Blocks.STORAGE_BLOCKS_LEAD, "Lead Storage Blocks");
        provider.add(OTags.Blocks.STORAGE_BLOCKS_SILVER, "Silver Storage Blocks");
        provider.add(OTags.Blocks.STORAGE_BLOCKS_RAW_LEAD, "Raw Lead Storage Blocks");
        provider.add(OTags.Blocks.STORAGE_BLOCKS_RAW_SILVER, "Raw Silver Storage Blocks");

        provider.add(OTags.Blocks.AMETHYST_CLUSTERS, "Amethyst Clusters");

        provider.add(OTags.Blocks.PREVENTS_LEAD_CLOUD, "Prevents Lead Clouds");

        provider.add(OTags.Enchantments.HEAT_IMMUNITY, "Heat Protective Footwear");
        provider.add(OTags.Enchantments.PREVENTS_LEAD_CLOUD, "Prevents Lead Clouds");
    }

}
