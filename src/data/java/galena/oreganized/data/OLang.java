package galena.oreganized.data;

import galena.oreganized.Oreganized;
import galena.oreganized.client.tooltips.ClientThermometerTooltip;
import galena.oreganized.data.provider.OLangProvider;
import galena.oreganized.index.OBlocks;
import galena.oreganized.index.OEffects;
import galena.oreganized.index.OEntityTypes;
import galena.oreganized.index.OFluids;
import galena.oreganized.index.OItems;
import galena.oreganized.index.OPaintingVariants;
import galena.oreganized.index.OPotions;
import net.minecraft.core.Holder;
import net.minecraft.data.PackOutput;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

public class OLang extends OLangProvider {

    public OLang(PackOutput output) {
        super(output, Oreganized.MOD_ID, "en_us");
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

        add("death.attack.lead_bolt", "%1$s was shot %2$s");
        add("death.attack.lead_bolt.item", "%1$s was shot %2$s using %3$s");
        add("death.attack.molten_lead", "%1$s refused to let go of the soaring hot metal");

        add("attribute.oreganized.kinetic_damage", "Kinetic Damage");

        add("item.oreganized.flint_and_pewter", "Flint and Pewter");

        addPainting(OPaintingVariants.VINDICATING_BAD, "Vindicating Bad", "Xaidee");

        /*
            Automatically create translations for blocks and items based on their registry name.

            This must be at the very bottom to avoid overwriting errors. These functions ignore objects
            that have already been translated above.
         */
        for (Holder<? extends Block> blocks : Oreganized.REGISTRY_HELPER.getBlockSubHelper().getDeferredRegister().getEntries()) {
            tryBlock(blocks);
        }
        for (Holder<? extends Item> items : Oreganized.REGISTRY_HELPER.getItemSubHelper().getDeferredRegister().getEntries()) {
            if (!items.equals(OItems.ELECTRUM_UPGRADE_SMITHING_TEMPLATE)) tryItem(items);
        }
        for (Holder<? extends Fluid> fluids : OFluids.FLUIDS.getEntries()) {
            tryFluid(fluids);
        }
        for (Holder<? extends EntityType<?>> entities : OEntityTypes.ENTITIES.getEntries()) {
            tryEntity(entities);
        }
    }

}
