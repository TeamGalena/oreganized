package galena.oreganized.plumbum.data;

import static galena.oreganized.data.extensions.OLangExtensions.*;

import com.tterrag.registrate.providers.RegistrateLangProvider;
import galena.oreganized.OConstants;
import galena.oreganized.data.ODatagen;
import galena.oreganized.plumbum.client.tooltip.ClientThermometerTooltip;
import galena.oreganized.plumbum.index.*;
import net.neoforged.fml.common.Mod;

@Mod(OConstants.MOD_ID)
public class PlumbumLang {

    public PlumbumLang() {
        ODatagen.addLangProvider(this::generate);
    }

    private void generate(RegistrateLangProvider provider) {
        autoTranslate(provider, PlumbumItems.LEAD_INGOT);
        autoTranslate(provider, PlumbumItems.LEAD_NUGGET);
        autoTranslate(provider, PlumbumItems.RAW_LEAD);
        autoTranslate(provider, PlumbumItems.THERMOMETER);
        autoTranslate(provider, PlumbumItems.MOLTEN_LEAD_BUCKET);

        autoTranslate(provider, PlumbumBlocks.LEAD_ORE);
        autoTranslate(provider, PlumbumBlocks.DEEPSLATE_LEAD_ORE);
        provider.addBlock(PlumbumBlocks.RAW_LEAD_BLOCK, "Block of Raw Lead");
        provider.addBlock(PlumbumBlocks.LEAD_BLOCK, "Block of Lead");
        autoTranslate(provider, PlumbumBlocks.LEAD_BRICKS);
        autoTranslate(provider, PlumbumBlocks.LEAD_PILLAR);
        autoTranslate(provider, PlumbumBlocks.LEAD_BULB);
        autoTranslate(provider, PlumbumBlocks.CUT_LEAD);
        autoTranslate(provider, PlumbumBlocks.LEAD_BARS);
        autoTranslate(provider, PlumbumBlocks.LEAD_DOOR);
        autoTranslate(provider, PlumbumBlocks.LEAD_TRAPDOOR);
        autoTranslate(provider, PlumbumBlocks.STURDY_BUTTON);
        autoTranslate(provider, PlumbumBlocks.STURDY_LEVER);
        autoTranslate(provider, PlumbumBlocks.MOLTEN_LEAD_CAULDRON);
        autoTranslate(provider, PlumbumBlocks.MELTING_LEAD_CAULDRON);

        provider.add(ClientThermometerTooltip.getDescriptionId(0), "Freezing");
        provider.add(ClientThermometerTooltip.getDescriptionId(1), "Cold");
        provider.add(ClientThermometerTooltip.getDescriptionId(2), "Fine");
        provider.add(ClientThermometerTooltip.getDescriptionId(3), "Warm");
        provider.add(ClientThermometerTooltip.getDescriptionId(4), "Hot");
        provider.add(ClientThermometerTooltip.getDescriptionId(5), "Sweltering");
        provider.add(ClientThermometerTooltip.getDescriptionId(6), "Searing");
        provider.add(ClientThermometerTooltip.getDescriptionId(7), "Scalding");
        provider.add(ClientThermometerTooltip.getDescriptionId(8), "Scorching");

        provider.addBlock(PlumbumBlocks.PURPLE_DATURA, "Purple Datura");
        provider.addBlock(PlumbumBlocks.WHITE_DATURA, "White Datura");
        provider.addBlock(PlumbumBlocks.POTTED_PURPLE_DATURA, "Potted Purple Datura");
        provider.addBlock(PlumbumBlocks.POTTED_WHITE_DATURA, "Potted White Datura");

        autoTranslate(provider, PlumbumBlocks.MOLTEN_LEAD);
        autoTranslate(provider, PlumbumFluids.MOLTEN_LEAD);

        addDisc(provider, PlumbumItems.MUSIC_DISC_STRUCTURE, "Firch", "structure");

        provider.addEffect(PlumbumEffects.STUNNING, "Brain Damage");
        addPotion(provider, PlumbumPotions.STUNNING, "Brain Damage");

        provider.add("trim_material.oreganized.lead", "Lead material");

        provider.add("effect.oreganized.stunning.description", "Paralyzes the victim periodically with random intervals");

        addDeath(provider, "molten_lead", "%1$s refused to let go of the searing hot metal");

        provider.add(PlumbumTags.Items.LIGHTER_THAN_LEAD, "Floats on Lead");
        provider.add(PlumbumTags.Items.PROTECTIVE_ARMOR_PART, "Lead Protecting Armor");
        provider.add(PlumbumTags.Items.PROTECTIVE_HELMET, "Lead Protecting Helmets");

        provider.add(PlumbumTags.Blocks.BLOWS_LEAD_CLOUD, "Lead Cloud Source");
        provider.add(PlumbumTags.Blocks.FIRE_HEAT_LEVEL, "Lower Heat Level");
        provider.add(PlumbumTags.Blocks.LAVA_HEAT_LEVEL, "Higher Heat Level");
        provider.add(PlumbumTags.Blocks.MELTS_LEAD, "Melts Lead Blocks");

        provider.add(PlumbumTags.Entities.LIGHTER_THAN_LEAD, "Floats on Lead");

        provider.add(PlumbumTags.Fluids.MOLTEN_LEAD, "Molten Lead");

        provider.add(PlumbumTags.Blocks.PREVENTS_LEAD_CLOUD, "Prevents Lead Clouds");

        provider.add(PlumbumTags.Enchantments.HEAT_IMMUNITY, "Heat Protective Footwear");
        provider.add(PlumbumTags.Enchantments.PREVENTS_LEAD_CLOUD, "Prevents Lead Clouds");

        provider.add(PlumbumTags.Blocks.INCORRECT_FOR_LEAD_TOOL, "Incorrect for Lead Tools");
    }

}
