package galena.oreganized.gothic.data;

import static galena.oreganized.data.extensions.OAdvancementExtensions.getAdv;
import static galena.oreganized.data.extensions.OAdvancementExtensions.info;

import com.tterrag.registrate.providers.RegistrateAdvancementProvider;
import galena.oreganized.OConstants;
import galena.oreganized.data.ODatagen;
import galena.oreganized.gothic.index.GothicBlocks;
import galena.oreganized.gothic.index.GothicCriterionTriggers;
import galena.oreganized.index.CoreTags;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.critereon.*;
import net.minecraft.world.item.Items;
import net.neoforged.fml.common.Mod;

@Mod(OConstants.MOD_ID)
public class GothicAdvancements {

    public GothicAdvancements() {
        ODatagen.addAdvancementProvider(this::generate);
    }

    private void generate(RegistrateAdvancementProvider provider) {
        var weepingDevil = Advancement.Builder.advancement()
                .parent(getAdv("minecraft:adventure/root"))
                .display(info(
                        provider,
                        GothicBlocks.GARGOYLE,
                        "weeping_devil",
                        AdvancementType.TASK,
                        "Weeping Devil",
                        "Exchange a Silver Ingot for a hellish growl of the Gargoyle"
                ))
                .addCriterion("activated_gargoyle", ItemUsedOnLocationTrigger.TriggerInstance.itemUsedOnBlock(
                        LocationPredicate.Builder.location()
                                .setBlock(BlockPredicate.Builder.block().of(GothicBlocks.GARGOYLE.value())),
                        ItemPredicate.Builder.item().of(CoreTags.Items.INGOTS_SILVER)
                ))
                .save(provider, "oreganized:adventure/weeping_devil");

        Advancement.Builder.advancement()
                .parent(weepingDevil)
                .display(info(
                        provider,
                        Items.WATER_BUCKET,
                        "garglin_water",
                        AdvancementType.TASK,
                        "Garglin' Water",
                        "Provide Gargoyle with a source of water to spew even without rain"
                ))
                .addCriterion("see_gargoyle_gargle", GothicCriterionTriggers.SEE_GARGOYLE_GARGLE.value().createCriterion())
                .save(provider, "oreganized:adventure/garglin_water");
    }

}
