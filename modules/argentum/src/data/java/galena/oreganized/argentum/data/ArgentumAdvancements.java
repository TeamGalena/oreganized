package galena.oreganized.argentum.data;

import static galena.oreganized.data.extensions.OAdvancementExtensions.getAdv;
import static galena.oreganized.data.extensions.OAdvancementExtensions.info;

import com.tterrag.registrate.providers.RegistrateAdvancementProvider;
import galena.oreganized.OConstants;
import galena.oreganized.argentum.index.ArgentumBlocks;
import galena.oreganized.argentum.index.ArgentumItems;
import galena.oreganized.data.ODatagen;
import galena.oreganized.index.CoreTags;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.critereon.*;
import net.minecraft.tags.BlockTags;
import net.neoforged.fml.common.Mod;

@Mod(OConstants.MOD_ID)
public class ArgentumAdvancements {

    public ArgentumAdvancements() {
        ODatagen.addAdvancementProvider(this::generate);
    }

    private void generate(RegistrateAdvancementProvider provider) {
        Advancement.Builder.advancement()
                .parent(getAdv("minecraft:adventure/root"))
                .display(info(
                        provider,
                        ArgentumItems.SILVER_MIRROR,
                        "mirror_mirror",
                        AdvancementType.TASK,
                        "Mirror, Mirror who is the fairest?",
                        "Obtain a Silver Mirror"
                ))
                .addCriterion("has_silver_mirror", InventoryChangeTrigger.TriggerInstance.hasItems(ArgentumItems.SILVER_MIRROR))
                .save(provider, "oreganized:adventure/mirror_mirror");

        Advancement.Builder.advancement()
                .parent(getAdv("minecraft:story/iron_tools"))
                .display(info(
                        provider,
                        ArgentumItems.SILVER_INGOT,
                        "obtain_silver",
                        AdvancementType.TASK,
                        "Every Stone has a Silver Lining",
                        "Smelt Raw Silver"
                ))
                .addCriterion("has_silver_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(CoreTags.Items.INGOTS_SILVER).build()))
                .save(provider, "oreganized:story/obtain_silver");

        Advancement.Builder.advancement()
                .parent(getAdv("husbandry/root"))
                .display(info(
                        provider,
                        ArgentumBlocks.GROOVED_ICE,
                        "groovy",
                        AdvancementType.TASK,
                        "Groovy",
                        "Use the scribe to make grooves on Ice!"
                ))
                .addCriterion("use_scribe_on_ice", ItemUsedOnLocationTrigger.TriggerInstance.itemUsedOnBlock(
                        LocationPredicate.Builder.location()
                                .setBlock(BlockPredicate.Builder.block()
                                        .of(BlockTags.ICE)),
                        ItemPredicate.Builder.item().of(ArgentumItems.SCRIBE)
                ))
                .save(provider, "oreganized:husbandry/groovy");
    }

}
