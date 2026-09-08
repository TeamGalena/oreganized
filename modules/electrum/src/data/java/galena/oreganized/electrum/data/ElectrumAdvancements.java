package galena.oreganized.electrum.data;

import static galena.oreganized.data.extensions.OAdvancementExtensions.getAdv;
import static galena.oreganized.data.extensions.OAdvancementExtensions.info;

import com.tterrag.registrate.providers.RegistrateAdvancementProvider;
import galena.oreganized.OConstants;
import galena.oreganized.data.ODatagen;
import galena.oreganized.electrum.index.ElectrumCriterionTriggers;
import galena.oreganized.electrum.index.ElectrumItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.critereon.*;
import net.neoforged.fml.common.Mod;

@Mod(OConstants.MOD_ID)
public class ElectrumAdvancements {

    public ElectrumAdvancements() {
        ODatagen.addAdvancementProvider(this::generate);
    }

    private void generate(RegistrateAdvancementProvider provider) {
        // TODO modular this will need to be conditional if shipped as modules
        var obtainSilver = getAdv("oreganized:story/obtain_silver");

        Advancement.Builder.advancement()
                .parent(obtainSilver)
                .display(info(
                        provider,
                        ElectrumItems.ELECTRUM_CHESTPLATE,
                        "electrum_gear",
                        AdvancementType.CHALLENGE,
                        "Cover me in... Wings?",
                        "Obtain a full set of electrum armor"
                ))
                .addCriterion("has_all_electrum_armor", InventoryChangeTrigger.TriggerInstance.hasItems(
                        ElectrumItems.ELECTRUM_HELMET, ElectrumItems.ELECTRUM_CHESTPLATE, ElectrumItems.ELECTRUM_LEGGINGS, ElectrumItems.ELECTRUM_BOOTS
                ))
                .save(provider, "oreganized:story/electrum_gear");

        Advancement.Builder.advancement()
                .parent(obtainSilver)
                .display(info(
                        provider,
                        ElectrumItems.SPEEDOMETER,
                        "terminal_velocity",
                        AdvancementType.TASK,
                        "Got some fallin' To do",
                        "Achieve Terminal Velocity by falling"
                ))
                .addCriterion("terminal_velocity", ElectrumCriterionTriggers.TERMINAL_VELOCITY.value().createCriterion())
                .save(provider, "oreganized:adventure/terminal_velocity");

    }

}
