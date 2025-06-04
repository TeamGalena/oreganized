package galena.oreganized.data;

import galena.oreganized.Oreganized;
import galena.oreganized.data.provider.OLangProvider;
import galena.oreganized.index.OBlocks;
import galena.oreganized.index.OEffects;
import galena.oreganized.index.OEntityTypes;
import galena.oreganized.index.OFluids;
import galena.oreganized.index.OItems;
import galena.oreganized.index.OPaintingVariants;
import galena.oreganized.index.OPotions;
import java.util.function.Supplier;
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

        addBlock(OBlocks.RAW_SILVER_BLOCK, "Block of Raw Silver");
        addBlock(OBlocks.RAW_LEAD_BLOCK, "Block of Raw Lead");
        addBlock(OBlocks.SILVER_BLOCK, "Block of Silver");

        addBlock(OBlocks.DEEPSLATE_ASBESTOS_ORE, "Deepslate Asbestos Ore");
        addBlock(OBlocks.ASBESTOS_ORE, "Asbestos Ore");
        addBlock(OBlocks.ASBESTOS_BLOCK, "Block of Asbestos");
        addBlock(OBlocks.RAW_ASBESTOS_BLOCK, "Block of Raw Asbestos");
        addBlock(OBlocks.LEAD_BLOCK, "Block of Lead");
        addBlock(OBlocks.LEAD_BRICKS, "Lead Bricks");
        addBlock(OBlocks.LEAD_PILLAR, "Lead Pillar");
        addBlock(OBlocks.LEAD_BULB, "Lead Bulb");
        addBlock(OBlocks.CUT_LEAD, "Cut Lead");
        addBlock(OBlocks.ELECTRUM_BLOCK, "Block of Electrum");
        addBlock(OBlocks.LEAD_BOLT_CRATE, "Crate of Lead Bolts");
        addEffect(OEffects.STUNNING, "Brain Damage");
        addEffect(OEffects.LUNG_DAMAGE, "Lung Damage");
        addPotion(OPotions.LUNG_DAMAGE, "Lung Damage");
        addPotion(OPotions.STUNNING, "Brain Damage");
        add("trim_material.oreganized.lead", "Lead material");
        add("trim_material.oreganized.silver", "Silver material");
        add("trim_material.oreganized.electrum", "Electrum material");
        add("upgrade.oreganized.electrum_upgrade", "Electrum Upgrade");
        add("item.oreganized.smithing_template.electrum_upgrade.applies_to", "Diamond Equipment");
        add("item.oreganized.smithing_template.electrum_upgrade.ingredients", "Electrum Ingot");

        // JEED compat
        add("effect.oreganized.stunning.description", "Paralyzes the victim periodically with random intervals");

        addSubtitle("entity", "shrapnel_bomb.primed", "Shrapnel Bomb fizzes");
        addSubtitle("entity", "bolt_hit", "Bolt hits");
        addSubtitle("block", "gargoyle.growl", "Gargoyle growls");

        add("tooltip.oreganized.wip.title", "Work In Progress");
        add("tooltip.oreganized.wip.description", "Usages for this item will be available in a future release");

        add("item.oreganized.smithing_template.electrum_upgrade.additions_slot_description", "Add Electrum Ingot");

        add("death.attack.lead_bolt", "%1$s was shot %2$s");
        add("death.attack.lead_bolt.item", "%1$s was shot %2$s using %3$s");
        add("death.attack.molten_lead", "%1$s refused to let go of the soaring hot metal");

        add("attribute.oreganized.kinetic_damage", "Kinetic Damage");
        add("item.oreganized.raw_asbestos", "Raw Asbestos");
        add("item.oreganized.refined_asbestos", "Asbestos");
        add("item.oreganized.flint_and_pewter", "Flint and Pewter");
        
        addPainting(OPaintingVariants.VINDICATING_BAD, "Vidicating Bad", "Xaidee");

        /*
            Automatically create translations for blocks and items based on their registry name.

            This must be at the very bottom to avoid overwriting errors. These functions ignore objects
            that have already been translated above.
         */
        for (Supplier<? extends Block> blocks : Oreganized.REGISTRY_HELPER.getBlockSubHelper().getDeferredRegister().getEntries()) {
            tryBlock(blocks);
        }
        for (Supplier<? extends Item> items : Oreganized.REGISTRY_HELPER.getItemSubHelper().getDeferredRegister().getEntries()) {
            if (!items.equals(OItems.ELECTRUM_UPGRADE_SMITHING_TEMPLATE)) tryItem(items);
        }
        for (Supplier<? extends Fluid> fluids : OFluids.FLUIDS.getEntries()) {
            tryFluid(fluids);
        }
        for (Supplier<? extends EntityType<?>> entities : OEntityTypes.ENTITIES.getEntries()) {
            tryEntity(entities);
        }
    }

}
