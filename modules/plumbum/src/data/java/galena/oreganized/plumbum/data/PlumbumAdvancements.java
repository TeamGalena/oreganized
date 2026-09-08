package galena.oreganized.plumbum.data;

import static galena.oreganized.data.extensions.OAdvancementExtensions.*;

import com.tterrag.registrate.providers.RegistrateAdvancementProvider;
import galena.oreganized.OConstants;
import galena.oreganized.data.ODatagen;
import galena.oreganized.plumbum.index.PlumbumBlocks;
import galena.oreganized.plumbum.index.PlumbumCriterionTriggers;
import galena.oreganized.plumbum.index.PlumbumEffects;
import galena.oreganized.plumbum.index.PlumbumItems;
import galena.oreganized.plumbum.world.item.ThermometerItem;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.critereon.EffectsChangedTrigger;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.MobEffectsPredicate;
import net.minecraft.world.item.ItemStack;
import net.neoforged.fml.common.Mod;

@Mod(OConstants.MOD_ID)
public class PlumbumAdvancements {

    public PlumbumAdvancements() {
        ODatagen.addAdvancementProvider(this::generate);
    }

    private void generate(RegistrateAdvancementProvider provider) {
        var likeTheRomans = Advancement.Builder.advancement()
                .parent(getAdv("story/upgrade_tools"))
                .display(info(
                        provider,
                        PlumbumItems.LEAD_INGOT,
                        "like_the_romans",
                        AdvancementType.TASK,
                        "Like the Romans",
                        "Get Brain Damage from interacting with lead"
                ))
                .addCriterion("has_brain_damage", EffectsChangedTrigger.TriggerInstance.hasEffects(MobEffectsPredicate.Builder.effects().and(PlumbumEffects.STUNNING)))
                .save(provider, "oreganized:story/like_the_romans");

        Advancement.Builder.advancement()
                .parent(likeTheRomans)
                .display(info(provider,
                        PlumbumItems.MOLTEN_LEAD_BUCKET,
                        "profound_brain_damage",
                        AdvancementType.TASK,
                        "Profound Brain Damage",
                        "Let your health reach half a heart while having the Brain Damage effect"
                ))
                .addCriterion("stunned", PlumbumCriterionTriggers.PROFOUND_BRAIN_DAMAGE.value().createCriterion())
                .save(provider, "oreganized:story/profound_brain_damage");

        var meltingPoint = Advancement.Builder.advancement()
                .parent(getAdv("minecraft:story/upgrade_tools"))
                .display(info(
                        provider,
                        PlumbumItems.MOLTEN_LEAD_BUCKET,
                        "melting_point",
                        AdvancementType.TASK,
                        "Melting Point",
                        "Pick up Molten Lead from a cauldron"
                ))
                .addCriterion("has_molten_lead_bucket", InventoryChangeTrigger.TriggerInstance.hasItems(PlumbumItems.MOLTEN_LEAD_BUCKET))
                .save(provider, "oreganized:story/melting_point");

        Advancement.Builder.advancement()
                .parent(meltingPoint)
                .display(info(
                        provider,
                        PlumbumItems.MUSIC_DISC_STRUCTURE,
                        "disc_smith",
                        AdvancementType.TASK,
                        "Disc Smith",
                        "Submerge a broken music disc into molten lead"
                ))
                .addCriterion("has_structure_disc", InventoryChangeTrigger.TriggerInstance.hasItems(PlumbumItems.MUSIC_DISC_STRUCTURE))
                .save(provider, "oreganized:story/disc_smith");

        Advancement.Builder.advancement()
                .parent(getAdv("story/root"))
                .display(info(
                        provider,
                        PlumbumBlocks.LEAD_ORE,
                        "lead_to_dust",
                        AdvancementType.TASK,
                        "Well... That leads to dust",
                        "Mine lead ore without adjacent water to block the toxic dust cloud from forming"
                ))
                .addCriterion("in_lead_cloud", PlumbumCriterionTriggers.IN_LEAD_CLOUD.value().createCriterion())
                .save(provider, "oreganized:story/lead_to_dust");

        var shakeItOff = Advancement.Builder.advancement()
                .parent(getAdv("husbandry/root"))
                .display(info(
                        provider,
                        thermometerStack(1),
                        "shake_it_off",
                        AdvancementType.TASK,
                        "Shake it off!",
                        "Shake the Thermometer and recalibrate its value"
                ))
                .addCriterion("shaken_thermometer", PlumbumCriterionTriggers.SHAKEN_THERMOMETER.value().createCriterion())
                .save(provider, "oreganized:husbandry/shake_it_off");

        Advancement.Builder.advancement()
                .parent(shakeItOff)
                .display(info(
                        provider,
                        thermometerStack(7),
                        "thermal_shock",
                        AdvancementType.TASK,
                        "Thermal Shock",
                        "Cool your Thermometer too quickly in water, destroying it"
                ))
                .addCriterion("broken_thermometer", PlumbumCriterionTriggers.BROKEN_THERMOMETER.value().createCriterion())
                .save(provider, "oreganized:husbandry/thermal_shock");
    }

    private static ItemStack thermometerStack(int heat) {
        var stack = PlumbumItems.THERMOMETER.toStack();
        ThermometerItem.setHeatLevel(stack, null, heat);
        return stack;
    }

}
