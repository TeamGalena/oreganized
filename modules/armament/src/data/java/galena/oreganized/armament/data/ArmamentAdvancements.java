package galena.oreganized.armament.data;

import static galena.oreganized.data.extensions.OAdvancementExtensions.*;

import com.tterrag.registrate.providers.RegistrateAdvancementProvider;
import galena.oreganized.OConstants;
import galena.oreganized.armament.index.ArmamentCriterionTriggers;
import galena.oreganized.data.ODatagen;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.world.item.Items;
import net.neoforged.fml.common.Mod;

@Mod(OConstants.MOD_ID)
public class ArmamentAdvancements {

    public ArmamentAdvancements() {
        ODatagen.addAdvancementProvider(this::generate);
    }

    private void generate(RegistrateAdvancementProvider provider) {
        Advancement.Builder.advancement()
                .parent(getAdv("adventure/whos_the_pillager_now"))
                .display(info(
                        provider,
                        Items.CROSSBOW,
                        "demoted",
                        AdvancementType.CHALLENGE,
                        "Demoted",
                        "Use lead bolts in a crossbow to strip a pillager of his Ominous Banner"
                ))
                .addCriterion("see_gargoyle_gargle", ArmamentCriterionTriggers.KNOCKED_BANNER_OFF.value().createCriterion())
                .save(provider, "oreganized:adventure/demoted");


    }

}
