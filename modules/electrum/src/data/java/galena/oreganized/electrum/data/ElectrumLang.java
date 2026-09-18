package galena.oreganized.electrum.data;

import static galena.oreganized.data.extensions.OLangExtensions.addAttribute;

import com.tterrag.registrate.providers.RegistrateLangProvider;
import galena.oreganized.OConstants;
import galena.oreganized.data.ODatagen;
import galena.oreganized.electrum.index.ElectrumAttributes;
import galena.oreganized.electrum.index.ElectrumBlocks;
import galena.oreganized.electrum.index.ElectrumItems;
import galena.oreganized.electrum.index.ElectrumTags;
import net.neoforged.fml.common.Mod;

@Mod(OConstants.MOD_ID)
public class ElectrumLang {

    public ElectrumLang() {
        ODatagen.addLangProvider(this::generate);
    }

    private void generate(RegistrateLangProvider provider) {
        provider.addItem(ElectrumItems.ELECTRUM_INGOT::get);
        provider.addItem(ElectrumItems.ELECTRUM_NUGGET::get);
        ElectrumItems.electrumArmor().forEach(it -> provider.addItem(it::get));
        ElectrumSets.electrumTools().forEach(it -> provider.addItem(it::get));
        provider.addItem(ElectrumItems.ELECTRUM_SHIELD::get);

        provider.addBlock(ElectrumBlocks.ELECTRUM_BLOCK, "Block of Electrum");

        provider.addItem(ElectrumItems.SPEEDOMETER, "Speedometer");
        provider.add("tooltip.oreganized.speed", "Speed: %s");

        provider.add("trim_material.oreganized.electrum", "Electrum material");
        provider.add("upgrade.oreganized.electrum_upgrade", "Electrum Upgrade");
        provider.addItem(ElectrumItems.ELECTRUM_UPGRADE_SMITHING_TEMPLATE, "Smithing Template");
        provider.add("item.oreganized.smithing_template.electrum_upgrade.applies_to", "Diamond Equipment");
        provider.add("item.oreganized.smithing_template.electrum_upgrade.ingredients", "Electrum Ingot");
        provider.add("item.oreganized.smithing_template.electrum_upgrade.additions_slot_description", "Add Electrum Ingot");

        addAttribute(provider, ElectrumAttributes.KINETIC_DAMAGE, "Kinetic Damage");
        provider.add(ElectrumTags.Items.HAS_KINETIC_DAMAGE, "Has Kinetic Damage");

        provider.add(ElectrumTags.Blocks.INCORRECT_FOR_ELECTRUM_TOOL, "Incorrect for Electrum Tools");
    }

}
