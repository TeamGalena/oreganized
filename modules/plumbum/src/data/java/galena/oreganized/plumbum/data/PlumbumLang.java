package galena.oreganized.plumbum.data;

import static galena.oreganized.data.provider.OLangProvider.*;

import com.tterrag.registrate.providers.RegistrateLangProvider;
import galena.oreganized.OConstants;
import galena.oreganized.data.provider.ODatagen;
import galena.oreganized.index.OTags;
import galena.oreganized.plumbum.client.tooltip.ClientThermometerTooltip;
import galena.oreganized.plumbum.index.*;
import net.neoforged.fml.common.Mod;

@Mod(OConstants.MOD_ID)
public class PlumbumLang {

    public PlumbumLang() {
        ODatagen.addLangProvider(this::generate);
    }

    private void generate(RegistrateLangProvider provider) {
        provider.addItem(PlumbumItems.LEAD_INGOT::get);
        provider.addItem(PlumbumItems.LEAD_NUGGET::get);
        provider.addItem(PlumbumItems.RAW_LEAD::get);
        provider.addItem(PlumbumItems.THERMOMETER::get);
        provider.addItem(PlumbumItems.MOLTEN_LEAD_BUCKET::get);

        provider.addBlock(PlumbumBlocks.LEAD_ORE::get);
        provider.addBlock(PlumbumBlocks.DEEPSLATE_LEAD_ORE::get);
        provider.addBlock(PlumbumBlocks.RAW_LEAD_BLOCK, "Block of Raw Lead");
        provider.addBlock(PlumbumBlocks.LEAD_BLOCK, "Block of Lead");
        provider.addBlock(PlumbumBlocks.LEAD_BRICKS::get);
        provider.addBlock(PlumbumBlocks.LEAD_PILLAR::get);
        provider.addBlock(PlumbumBlocks.LEAD_BULB::get);
        provider.addBlock(PlumbumBlocks.CUT_LEAD::get);
        provider.addBlock(PlumbumBlocks.LEAD_BARS::get);
        provider.addBlock(PlumbumBlocks.LEAD_DOOR::get);
        provider.addBlock(PlumbumBlocks.LEAD_TRAPDOOR::get);
        provider.addBlock(PlumbumBlocks.STURDY_BUTTON::get);
        provider.addBlock(PlumbumBlocks.STURDY_LEVER::get);
        provider.addBlock(PlumbumBlocks.MOLTEN_LEAD_CAULDRON::get);

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

        provider.addBlock(PlumbumBlocks.MOLTEN_LEAD::get);
        addFluid(provider, PlumbumFluids.MOLTEN_LEAD);

        addDisc(provider, PlumbumItems.MUSIC_DISC_STRUCTURE, "Firch", "structure");

        provider.addEffect(PlumbumEffects.STUNNING, "Brain Damage");
        addPotion(provider, PlumbumPotions.STUNNING, "Brain Damage");

        provider.add("trim_material.oreganized.lead", "Lead material");

        provider.add("effect.oreganized.stunning.description", "Paralyzes the victim periodically with random intervals");

        addDeath(provider, "molten_lead", "%1$s refused to let go of the searing hot metal");

        provider.add(OTags.Items.LIGHTER_THAN_LEAD, "Floats on Lead");
        provider.add(OTags.Items.PROTECTIVE_ARMOR_PART, "Lead Protecting Armor");
        provider.add(OTags.Items.PROTECTIVE_HELMET, "Lead Protecting Helmets");

        provider.add(OTags.Blocks.BLOWS_LEAD_CLOUD, "Lead Cloud Source");
        provider.add(OTags.Blocks.FIRE_HEAT_LEVEL, "Lower Heat Level");
        provider.add(OTags.Blocks.LAVA_HEAT_LEVEL, "Higher Heat Level");
        provider.add(OTags.Blocks.MELTS_LEAD, "Melts Lead Blocks");

        provider.add(OTags.Entities.LIGHTER_THAN_LEAD, "Floats on Lead");

        provider.add(OTags.Fluids.MOLTEN_LEAD, "Molten Lead");
    }

}
