package galena.oreganized.data;

import com.tterrag.registrate.providers.RegistrateLangProvider;
import galena.oreganized.OConstants;
import galena.oreganized.index.CoreTags;
import net.neoforged.fml.common.Mod;

@Mod(OConstants.MOD_ID)
public class CoreLang {

    public CoreLang() {
        ODatagen.addLangProvider(this::generate);
    }

    private void generate(RegistrateLangProvider provider) {
        provider.add("tooltip.oreganized.wip.title", "Work In Progress");
        provider.add("tooltip.oreganized.wip.description", "Usages for this item will be available in a future release");

        provider.add(CoreTags.Items.INGOTS_ELECTRUM, "Electrum Ingots");
        provider.add(CoreTags.Items.INGOTS_SILVER, "Silver Ingots");
        provider.add(CoreTags.Items.INGOTS_LEAD, "Lead Ingots");
        provider.add(CoreTags.Items.NUGGETS_ELECTRUM, "Electrum Nuggets");
        provider.add(CoreTags.Items.NUGGETS_LEAD, "Lead Nuggets");
        provider.add(CoreTags.Items.NUGGETS_SILVER, "Silver Nuggets");
        provider.add(CoreTags.Items.NUGGETS_NETHERITE, "Netherite Nuggets");
        provider.add(CoreTags.Items.ORES_LEAD, "Lead Ores");
        provider.add(CoreTags.Items.ORES_SILVER, "Silver Ores");
        provider.add(CoreTags.Items.RAW_MATERIALS_LEAD, "Raw Lead");
        provider.add(CoreTags.Items.RAW_MATERIALS_SILVER, "Raw Silver");
        provider.add(CoreTags.Items.STORAGE_BLOCKS_ELECTRUM, "Electrum Storage Blocks");
        provider.add(CoreTags.Items.STORAGE_BLOCKS_LEAD, "Lead Storage Blocks");
        provider.add(CoreTags.Items.STORAGE_BLOCKS_SILVER, "Silver Storage Blocks");
        provider.add(CoreTags.Items.STORAGE_BLOCKS_RAW_LEAD, "Raw Lead Storage Blocks");
        provider.add(CoreTags.Items.STORAGE_BLOCKS_RAW_SILVER, "Raw Silver Storage Blocks");


        provider.add(CoreTags.Blocks.ORES_LEAD, "Lead Ores");
        provider.add(CoreTags.Blocks.ORES_SILVER, "Silver Ores");
        provider.add(CoreTags.Blocks.STORAGE_BLOCKS_ELECTRUM, "Electrum Storage Blocks");
        provider.add(CoreTags.Blocks.STORAGE_BLOCKS_LEAD, "Lead Storage Blocks");
        provider.add(CoreTags.Blocks.STORAGE_BLOCKS_SILVER, "Silver Storage Blocks");
        provider.add(CoreTags.Blocks.STORAGE_BLOCKS_RAW_LEAD, "Raw Lead Storage Blocks");
        provider.add(CoreTags.Blocks.STORAGE_BLOCKS_RAW_SILVER, "Raw Silver Storage Blocks");

        provider.add(CoreTags.Blocks.AMETHYST_CLUSTERS, "Amethyst Clusters");
    }

}
