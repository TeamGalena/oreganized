package galena.oreganized.data;

import galena.oreganized.OConstants;
import galena.oreganized.data.provider.OLangProvider;
import galena.oreganized.index.*;
import galena.oreganized.plumbum.client.tooltip.ClientThermometerTooltip;
import net.createmod.ponder.foundation.PonderIndex;
import net.minecraft.core.Holder;
import net.minecraft.data.PackOutput;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

public class OLang extends OLangProvider {

    public OLang(PackOutput output) {
        super(output, OConstants.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        addDisc(OItems.MUSIC_DISC_STRUCTURE, "Firch", "structure");

        addItem(OItems.SHRAPNEL_BOMB_MINECART, "Minecart with Shrapnel Bomb");
        addItem(OItems.THERMOMETER, "Thermometer");
        addItem(OItems.SPEEDOMETER, "Speedometer");
        addItem(OItems.UNKNOWN_DEVICE, "Unknown Device");
        addBlock(OBlocks.PURPLE_DATURA, "Purple Datura");
        addBlock(OBlocks.WHITE_DATURA, "White Datura");
        addBlock(OBlocks.POTTED_PURPLE_DATURA, "Potted Purple Datura");
        addBlock(OBlocks.POTTED_WHITE_DATURA, "Potted White Datura");
        addBlock(OBlocks.RAW_LEAD_BLOCK, "Block of Raw Lead");
        addBlock(OBlocks.LEAD_BLOCK, "Block of Lead");
        addBlock(OBlocks.LEAD_BRICKS, "Lead Bricks");
        addBlock(OBlocks.LEAD_PILLAR, "Lead Pillar");
        addBlock(OBlocks.LEAD_BULB, "Lead Bulb");
        addBlock(OBlocks.CUT_LEAD, "Cut Lead");
        addBlock(OBlocks.ELECTRUM_BLOCK, "Block of Electrum");
        addBlock(OBlocks.LEAD_BOLT_CRATE, "Crate of Lead Bolts");

        addBlock(OBlocks.RAW_SILVER_BLOCK, "Block of Raw Silver");
        addBlock(OBlocks.SILVER_BLOCKS.base(), "Block of Silver");
        addBlock(OBlocks.SILVER_BLOCKS.blemished(), "Block of Blemished Silver");
        addBlock(OBlocks.SILVER_BLOCKS.tarnished(), "Block of Tarnished Silver");
        addTarnished(OBlocks.SILVER_PILLARS, "Silver Pillar");
        addTarnished(OBlocks.CHISELED_SILVER, "Chiseled Silver");
        addTarnished(OBlocks.SILVER_BARS, "Silver Bars");
        addTarnished(OBlocks.SILVER_BULBS, "Silver Bulb");
        addTarnished(OBlocks.CUT_SILVERS, "Cut Silver");
        addTarnished(OBlocks.SILVER_LATTICES, "Silver Lattice");
        addTarnished(OBlocks.CUT_SILVER_STAIRS, "Cut Silver Stairs");
        addTarnished(OBlocks.CUT_SILVER_SLABS, "Cut Silver Slabs");

        addEffect(OEffects.STUNNING, "Brain Damage");
        addPotion(OPotions.STUNNING, "Brain Damage");
        add("tooltip.oreganized.lead", "Lead material");
        add("trim_material.oreganized.lead", "Lead material");
        add("trim_material.oreganized.silver", "Silver material");
        add("trim_material.oreganized.electrum", "Electrum material");
        add("upgrade.oreganized.electrum_upgrade", "Electrum Upgrade");
        addItem(OItems.ELECTRUM_UPGRADE_SMITHING_TEMPLATE, "Smithing Template");
        add("item.oreganized.smithing_template.electrum_upgrade.applies_to", "Diamond Equipment");
        add("item.oreganized.smithing_template.electrum_upgrade.ingredients", "Electrum Ingot");

        // JEED compat
        add("effect.oreganized.stunning.description", "Paralyzes the victim periodically with random intervals");

        addSubtitle("entity", "shrapnel_bomb.primed", "Shrapnel Bomb fizzes");
        addSubtitle("entity", "bolt_hit", "Bolt hits");
        addSubtitle("block", "gargoyle.growl", "Gargoyle growls");
        addSubtitle("block", "tarnish", "Something tarnishes");
        addSubtitle("block", "polish", "Polishing");

        add("tooltip.oreganized.speed", "Speed: %s");
        add(ClientThermometerTooltip.getDescriptionId(0), "Freezing");
        add(ClientThermometerTooltip.getDescriptionId(1), "Cold");
        add(ClientThermometerTooltip.getDescriptionId(2), "Fine");
        add(ClientThermometerTooltip.getDescriptionId(3), "Warm");
        add(ClientThermometerTooltip.getDescriptionId(4), "Hot");
        add(ClientThermometerTooltip.getDescriptionId(5), "Sweltering");
        add(ClientThermometerTooltip.getDescriptionId(6), "Searing");
        add(ClientThermometerTooltip.getDescriptionId(7), "Scalding");
        add(ClientThermometerTooltip.getDescriptionId(8), "Scorching");
        add("tooltip.oreganized.wip.title", "Work In Progress");
        add("tooltip.oreganized.wip.description", "Usages for this item will be available in a future release");

        add("item.oreganized.smithing_template.electrum_upgrade.additions_slot_description", "Add Electrum Ingot");

        addDeath("lead_bolt", "%1$s was shot by %2$s");
        addDeath("lead_bolt.item", "%1$s was shot by %2$s using %3$s");
        addDeath("molten_lead", "%1$s refused to let go of the searing hot metal");

        addAttribute(OAttributes.KINETIC_DAMAGE, "Kinetic Damage");
        addAttribute(OAttributes.INVINCIBILITY_FRAMES, "Invincibility");

        addItem(OItems.FLINT_AND_PEWTER, "Flint and Pewter");

        addPainting(OPaintingVariants.VINDICATING_BAD, "Vindicating Bad", "Xaidee");

        PonderIndex.getLangAccess().provideLang(OConstants.MOD_ID, this::add);

        add(OTags.Items.CRYSTAL_GLASS, "Crystal Glass");
        add(OTags.Items.CRYSTAL_GLASS_PANES, "Crystal Glass Panes");
        add(OTags.Items.ENCHANTED_BOOK_LIKE, "Enchanted Books");
        add(OTags.Items.GARGOYLE_SNACK, "Gargoyle Snacks");
        add(OTags.Items.HAS_KINETIC_DAMAGE, "Has Kinetic Damage");
        add(OTags.Items.INGOTS_ELECTRUM, "Electrum Ingots");
        add(OTags.Items.INGOTS_SILVER, "Silver Ingots");
        add(OTags.Items.INGOTS_LEAD, "Lead Ingots");
        add(OTags.Items.NUGGETS_ELECTRUM, "Electrum Nuggets");
        add(OTags.Items.NUGGETS_LEAD, "Lead Nuggets");
        add(OTags.Items.NUGGETS_SILVER, "Silver Nuggets");
        add(OTags.Items.NUGGETS_NETHERITE, "Netherite Nuggets");
        add(OTags.Items.ORES_LEAD, "Lead Ores");
        add(OTags.Items.ORES_SILVER, "Silver Ores");
        add(OTags.Items.RAW_MATERIALS_LEAD, "Raw Lead");
        add(OTags.Items.RAW_MATERIALS_SILVER, "Raw Silver");
        add(OTags.Items.STORAGE_BLOCKS_ELECTRUM, "Electrum Storage Blocks");
        add(OTags.Items.STORAGE_BLOCKS_LEAD, "Lead Storage Blocks");
        add(OTags.Items.STORAGE_BLOCKS_SILVER, "Silver Storage Blocks");
        add(OTags.Items.STORAGE_BLOCKS_RAW_LEAD, "Raw Lead Storage Blocks");
        add(OTags.Items.STORAGE_BLOCKS_RAW_SILVER, "Raw Silver Storage Blocks");
        add(OTags.Items.LIGHTER_THAN_LEAD, "Floats on Lead");
        add(OTags.Items.NO_ANVIL_ENCHANT_COST, "Free Anvil Enchantables");
        add(OTags.Items.PROTECTIVE_ARMOR_PART, "Lead Protecting Armor");
        add(OTags.Items.PROTECTIVE_HELMET, "Lead Protecting Helmets");
        add(OTags.Items.TOOLS_BUSH_HAMMER, "Bush Hammers");
        add(OTags.Items.STONE_TYPES_GLANCE, "Processed Glance");

        add(OTags.Blocks.CRYSTAL_GLASS, "Crystal Glass");
        add(OTags.Blocks.CRYSTAL_GLASS_PANES, "Crystal Glass Panes");
        add(OTags.Blocks.ORES_LEAD, "Lead Ores");
        add(OTags.Blocks.ORES_SILVER, "Silver Ores");
        add(OTags.Blocks.STORAGE_BLOCKS_ELECTRUM, "Electrum Storage Blocks");
        add(OTags.Blocks.STORAGE_BLOCKS_LEAD, "Lead Storage Blocks");
        add(OTags.Blocks.STORAGE_BLOCKS_SILVER, "Silver Storage Blocks");
        add(OTags.Blocks.STORAGE_BLOCKS_RAW_LEAD, "Raw Lead Storage Blocks");
        add(OTags.Blocks.STORAGE_BLOCKS_RAW_SILVER, "Raw Silver Storage Blocks");
        add(OTags.Blocks.STONE_TYPES_GLANCE, "Processed Glance");

        add(OTags.Blocks.AMETHYST_CLUSTERS, "Amethyst Clusters");
        add(OTags.Blocks.BLOWS_LEAD_CLOUD, "Lead Cloud Source");
        add(OTags.Blocks.FIRE_HEAT_LEVEL, "Lower Heat Level");
        add(OTags.Blocks.LAVA_HEAT_LEVEL, "Higher Heat Level");
        add(OTags.Blocks.MELTS_LEAD, "Melts Lead Blocks");
        add(OTags.Blocks.INCORRECT_FOR_ELECTRUM_TOOL, "Incorrect for Electrum Tools");
        add(OTags.Blocks.INCORRECT_FOR_LEAD_TOOL, "Incorrect for Lead Tools");
        add(OTags.Blocks.INCORRECT_FOR_SILVER_TOOL, "Incorrect for Silver Tools");
        add(OTags.Blocks.MINEABLE_WITH_BUSH_HAMMER, "Mineable with Bush Hammer");
        add(OTags.Blocks.MINEABLE_WITH_SCRIBE, "Mineable with Scribe");
        add(OTags.Blocks.SILKTOUCH_WITH_SCRIBE, "Silktouch-able with Scibe");
        add(OTags.Blocks.SILKTOUCH_WITH_SCRIBE_BLACKLIST, "Not Silktouch-able with Scibe");
        add(OTags.Blocks.PREVENTS_LEAD_CLOUD, "Prevents Lead Clouds");

        add(OTags.Enchantments.HEAT_IMMUNITY, "Heat Protective Footwear");
        add(OTags.Enchantments.PREVENTS_LEAD_CLOUD, "Prevents Lead Clouds");

        add(OTags.Entities.BOLT_RESISTANT, "Bolt Resistant");
        add(OTags.Entities.BOLT_SUSCEPTIBLE, "Bolt Susceptible");
        add(OTags.Entities.LIGHTER_THAN_LEAD, "Floats on Lead");
        add(OTags.Entities.SCARED_OF_GARGOYLE, "Scared of Gargoyles");
        add(OTags.Entities.TARNISHABLE, "Can Tarnish");

        add(OTags.Fluids.MOLTEN_LEAD, "Molten Lead");

        /*
            Automatically create translations for blocks and items based on their registry name.

            This must be at the very bottom to avoid overwriting errors. These functions ignore objects
            that have already been translated above.
         */
        // TODO modular add common method
        for (Holder<? extends Block> blocks : OConstants.REGISTRY_HELPER.getBlockSubHelper().getDeferredRegister().getEntries()) {
            tryBlock(blocks);
        }
        for (Holder<? extends Item> items : OConstants.REGISTRY_HELPER.getItemSubHelper().getDeferredRegister().getEntries()) {
            if (!items.equals(OItems.ELECTRUM_UPGRADE_SMITHING_TEMPLATE)) tryItem(items);
        }
        for (Holder<? extends Fluid> fluids : OConstants.REGISTRY_HELPER.getFluidSubHelper().getDeferredRegister().getEntries()) {
            tryFluid(fluids);
        }
        for (Holder<? extends EntityType<?>> entities : OConstants.REGISTRY_HELPER.getEntitySubHelper().getDeferredRegister().getEntries()) {
            tryEntity(entities);
        }
    }

}
