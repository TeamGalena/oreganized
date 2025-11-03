package galena.oreganized.data;

import galena.oreganized.Oreganized;
import galena.oreganized.content.item.ThermometerItem;
import galena.oreganized.data.provider.OLangProvider;
import galena.oreganized.index.OBlocks;
import galena.oreganized.index.OCriteriaTriggers;
import galena.oreganized.index.OEffects;
import galena.oreganized.index.OItems;
import galena.oreganized.index.OTags;
import it.unimi.dsi.fastutil.ints.Int2ObjectFunction;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.DisplayInfo;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.critereon.BlockPredicate;
import net.minecraft.advancements.critereon.EffectsChangedTrigger;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.ItemUsedOnLocationTrigger;
import net.minecraft.advancements.critereon.LocationPredicate;
import net.minecraft.advancements.critereon.MobEffectsPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;

public class OAdvancements extends ForgeAdvancementProvider {

    public OAdvancements(PackOutput output, CompletableFuture<HolderLookup.Provider> future, ExistingFileHelper helper, OLangProvider lang) {
        super(output, future, helper, List.of(new OreganizedAdvancements(lang)));
    }

    static class OreganizedAdvancements implements ForgeAdvancementProvider.AdvancementGenerator {

        private final OLangProvider lang;

        OreganizedAdvancements(OLangProvider lang) {
            this.lang = lang;
            lang.addSubProvider(() -> this.generate(null, $ -> {
            }, null));
        }

        @Override
        public void generate(@Nullable HolderLookup.Provider provider, Consumer<Advancement> consumer, @Nullable ExistingFileHelper helper) {
            Advancement.Builder.advancement()
                    .parent(getAdv("minecraft:adventure/root"))
                    .display(info(OItems.SILVER_MIRROR.get(), "mirror_mirror", FrameType.TASK,
                            "Mirror, Mirror who is the fairest?", "Obtain a Silver Mirror"))
                    .addCriterion("has_silver_mirror", InventoryChangeTrigger.TriggerInstance.hasItems(OItems.SILVER_MIRROR.get()))
                    .save(consumer, "oreganized:adventure/mirror_mirror");



            var likeTheRomans = Advancement.Builder.advancement()
                    .parent(getAdv("minecraft:story/upgrade_tools"))
                    .display(info(OItems.LEAD_INGOT.get(), "like_the_romans", FrameType.TASK,
                            "Like the Romans", "Get Brain Damage from interacting with lead"))
                    .addCriterion("has_brain_damage", EffectsChangedTrigger.TriggerInstance.hasEffects(MobEffectsPredicate.effects().and(OEffects.STUNNING.get())))
                    .save(consumer, "oreganized:story/like_the_romans");

            Advancement.Builder.advancement()
                    .parent(likeTheRomans)
                    .display(info(OItems.MOLTEN_LEAD_BUCKET.get(), "profound_brain_damage", FrameType.TASK,
                            "Profound Brain Damage", "Let your health reach half a heart while having the Brain Damage effect"))
                    .addCriterion("stunned", OCriteriaTriggers.PROFOUND_BRAIN_DAMAGE.instance())
                    .save(consumer, "oreganized:story/profound_brain_damage");

            var obtainSilver = Advancement.Builder.advancement()
                    .parent(getAdv("minecraft:story/iron_tools"))
                    .display(info(OItems.SILVER_INGOT.get(), "obtain_silver", FrameType.TASK,
                            "Every Stone has a Silver Lining", "Smelt Raw Silver"))
                    .addCriterion("has_silver_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(OTags.Items.INGOTS_SILVER).build()))
                    .save(consumer, "oreganized:story/obtain_silver");

            Advancement.Builder.advancement()
                    .parent(obtainSilver)
                    .display(info(OItems.ELECTRUM_CHESTPLATE.get(), "electrum_gear", FrameType.CHALLENGE,
                            "Cover me in... Wings?", "Obtain a full set of electrum armor"))
                    .addCriterion("has_all_electrum_armor", InventoryChangeTrigger.TriggerInstance.hasItems(
                            OItems.ELECTRUM_HELMET.get(), OItems.ELECTRUM_CHESTPLATE.get(), OItems.ELECTRUM_LEGGINGS.get(), OItems.ELECTRUM_BOOTS.get()
                    ))
                    .save(consumer, "oreganized:story/electrum_gear");
            Advancement.Builder.advancement()
                    .parent(obtainSilver)
                    .display(info(OItems.SPEEDOMETER.get(), "terminal_velocity", FrameType.TASK,
                            "Got some fallin' To do", "Achieve Terminal Velocity by falling"))
                    .addCriterion("terminal_velocity", OCriteriaTriggers.TERMINAL_VELOCITY.instance())
                    .save(consumer, "oreganized:adventure/terminal_velocity");
            var meltingPoint = Advancement.Builder.advancement()
                    .parent(getAdv("minecraft:story/upgrade_tools"))
                    .display(info(OItems.MOLTEN_LEAD_BUCKET.get(), "melting_point", FrameType.TASK,
                            "Melting Point", "Pick up Molten Lead from a cauldron"))
                    .addCriterion("has_molten_lead_bucket", InventoryChangeTrigger.TriggerInstance.hasItems(OItems.MOLTEN_LEAD_BUCKET.get()))
                    //.addCriterion("item_used_on_cauldron", ItemInteractWithBlockTrigger.TriggerInstance.itemUsedOnBlock()))
                    .save(consumer, "oreganized:story/melting_point");

            Advancement.Builder.advancement()
                    .parent(meltingPoint)
                    .display(info(OItems.MUSIC_DISC_STRUCTURE.get(), "disc_smith", FrameType.TASK,
                            "Disc Smith", "Submerge a broken music disc into molten lead"))
                    //.addCriterion("use_disc_on_lead_cauldron", ItemInteractWithBlockTrigger.TriggerInstance.itemUsedOnBlock(new LocationPredicate.Builder().setBlock(new BlockPredicate(null, Set.of(OBlocks.MOLTEN_LEAD_CAULDRON.get()), StatePropertiesPredicate.ANY, NbtPredicate.ANY)), ItemPredicate.Builder.item().of(Items.MUSIC_DISC_11)))
                    .addCriterion("has_structure_disc", InventoryChangeTrigger.TriggerInstance.hasItems(OItems.MUSIC_DISC_STRUCTURE.get()))
                    .save(consumer, "oreganized:story/disc_smith");

            var weepingDevil = Advancement.Builder.advancement()
                    .parent(getAdv("minecraft:adventure/root"))
                    .display(info(OBlocks.GARGOYLE.get(), "weeping_devil", FrameType.TASK,
                            "Weeping Devil", "Exchange a Silver Ingot for a hellish growl of the Gargoyle"))
                    .addCriterion("activated_gargoyle", ItemUsedOnLocationTrigger.TriggerInstance.itemUsedOnBlock(
                            LocationPredicate.Builder.location()
                                    .setBlock(BlockPredicate.Builder.block().of(OBlocks.GARGOYLE.get()).build()),
                            ItemPredicate.Builder.item().of(OTags.Items.INGOTS_SILVER)
                    ))
                    .save(consumer, "oreganized:adventure/weeping_devil");

            Advancement.Builder.advancement()
                    .parent(weepingDevil)
                    .display(info(Items.WATER_BUCKET, "garglin_water", FrameType.TASK,
                            "Garglin' Water", "Provide Gargoyle with a source of water to spew even without rain"))
                    .addCriterion("see_gargoyle_gargle", OCriteriaTriggers.SEE_GARGOYLE_GARGLE.instance())
                    .save(consumer, "oreganized:adventure/garglin_water");

            Advancement.Builder.advancement()
                    .parent(getAdv("adventure/whos_the_pillager_now"))
                    .display(info(new ItemStack(Items.CROSSBOW), "demoted", FrameType.CHALLENGE,
                            "Demoted", "Use lead bolts in a crossbow to strip a pillager of his Ominous Banner"))
                    .addCriterion("see_gargoyle_gargle", OCriteriaTriggers.KNOCKED_BANNER_OFF.instance())
                    .save(consumer, "oreganized:adventure/demoted");

            Advancement.Builder.advancement()
                    .parent(getAdv("husbandry/root"))
                    .display(info(OBlocks.GROOVED_ICE.get(), "groovy", FrameType.TASK,
                            "Groovy", "Use the scribe to make grooves on Ice!"))
                    .addCriterion("use_scribe_on_ice", ItemUsedOnLocationTrigger.TriggerInstance.itemUsedOnBlock(
                            LocationPredicate.Builder.location()
                                    .setBlock(BlockPredicate.Builder.block()
                                            .of(BlockTags.ICE)
                                            .build()),
                            ItemPredicate.Builder.item().of(OItems.SCRIBE.get())
                    ))
                    .save(consumer, "oreganized:husbandry/groovy");

            Advancement.Builder.advancement()
                    .parent(getAdv("story/root"))
                    .display(info(OBlocks.LEAD_ORE.get(), "lead_to_dust", FrameType.TASK,
                            "Well... That leads to dust", "Mine lead ore without adjacent water to block the toxic dust cloud from forming"))
                    .addCriterion("in_lead_cloud", OCriteriaTriggers.IN_LEAD_CLOUD.instance())
                    .save(consumer, "oreganized:story/lead_to_dust");

            Int2ObjectFunction<ItemStack> thermometer = heat -> {
                var stack = new ItemStack(OItems.THERMOMETER.get());
                ThermometerItem.setHeatLevel(stack, null, heat);
                return stack;
            };

            var shakeItOff = Advancement.Builder.advancement()
                    .parent(getAdv("husbandry/root"))
                    .display(info(thermometer.get(1), "shake_it_off", FrameType.TASK,
                            "Shake it off!", "Shake the Thermometer and recalibrate its value"))
                    .addCriterion("shaken_thermometer", OCriteriaTriggers.SHAKEN_THERMOMETER.instance())
                    .save(consumer, "oreganized:husbandry/shake_it_off");

            Advancement.Builder.advancement()
                    .parent(shakeItOff)
                    .display(info(thermometer.apply(7), "thermal_shock", FrameType.TASK,
                            "Thermal Shock", "Cool your Thermometer too quickly in water, destroying it"))
                    .addCriterion("broken_thermometer", OCriteriaTriggers.BROKEN_THERMOMETER.instance())
                    .save(consumer, "oreganized:husbandry/thermal_shock");
        }

        protected Advancement getAdv(String loc) {
            return Advancement.Builder.advancement().build(new ResourceLocation(loc));
        }

        protected DisplayInfo info(ItemLike icon, String id, FrameType type, String title, String description) {
            return info(new ItemStack(icon), id, type, title, description);
        }

        protected DisplayInfo info(ItemStack icon, String id, FrameType type, String title, String description) {
            var advancementId = Oreganized.MOD_ID + "." + id;
            lang.addAdvTitle(advancementId, title);
            lang.addAdvDesc(advancementId, description);
            return new DisplayInfo(
                    icon,
                    Component.translatable("advancements.%s.title".formatted(advancementId)),
                    Component.translatable("advancements.%s.description".formatted(advancementId)),
                    null,
                    type,
                    true,
                    true,
                    false
            );
        }
    }

}
