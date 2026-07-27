package galena.oreganized;

import com.mojang.serialization.MapCodec;
import com.teamabnormals.blueprint.common.dispenser.FishBucketDispenseItemBehavior;
import com.teamabnormals.blueprint.core.util.DataUtil;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import galena.oreganized.api.LeadProtections;
import galena.oreganized.compat.create.CreateCompat;
import galena.oreganized.content.block.LeadOreBlock;
import galena.oreganized.content.block.MoltenLeadCauldronBlock;
import galena.oreganized.debug.ODebugCommands;
import galena.oreganized.index.OArmorMaterials;
import galena.oreganized.index.OAttachmentTypes;
import galena.oreganized.index.OAttributes;
import galena.oreganized.index.OBlockEntities;
import galena.oreganized.index.OBlocks;
import galena.oreganized.index.OCriteriaTriggers;
import galena.oreganized.index.ODataComponents;
import galena.oreganized.index.OEffects;
import galena.oreganized.index.OEntityTypes;
import galena.oreganized.index.OFluids;
import galena.oreganized.index.OItems;
import galena.oreganized.index.OParticleTypes;
import galena.oreganized.index.OPotions;
import galena.oreganized.index.ORecipeTypes;
import galena.oreganized.index.OSoundEvents;
import galena.oreganized.index.OStructures;
import galena.oreganized.index.OTags;
import galena.oreganized.network.OreganizedNetwork;
import galena.oreganized.world.AddItemLootModifier;
import java.util.stream.Stream;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.core.dispenser.ProjectileDispenseBehavior;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.neoforge.common.BasicItemListing;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import net.neoforged.neoforge.fluids.FluidInteractionRegistry;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Oreganized.MOD_ID)
public class Oreganized {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "oreganized";

    public static ResourceLocation modLoc(String location) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, location);
    }

    public static final RegistryHelper REGISTRY_HELPER = new RegistryHelper(MOD_ID);

    private static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> LOOT_MODIFIERS = DeferredRegister.create(NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, Oreganized.MOD_ID);

    public Oreganized(IEventBus modBus, ModContainer container) {
        final IEventBus forgeBus = NeoForge.EVENT_BUS;

        OreganizedConfig.register(container);

        modBus.addListener(this::setup);
        forgeBus.addListener(this::injectVillagerTrades);
        forgeBus.addListener(this::registerPotionMixes);

        LOOT_MODIFIERS.register("add_item", () -> AddItemLootModifier.CODEC);

        OEffects.register(modBus);
        OEntityTypes.register(modBus);
        OFluids.register(modBus);
        OParticleTypes.register(modBus);
        OPotions.register(modBus);
        OStructures.register(modBus);
        OAttributes.register(modBus);
        OArmorMaterials.register(modBus);
        ODataComponents.register(modBus);
        LOOT_MODIFIERS.register(modBus);
        OBlockEntities.register();
        OBlocks.register();
        OItems.register();
        OCriteriaTriggers.register(modBus);
        OSoundEvents.register();
        ORecipeTypes.register(modBus);
        OAttachmentTypes.register(modBus);

        REGISTRY_HELPER.register(modBus);

        modBus.addListener(OreganizedNetwork::register);

        var createLoaded = ModList.get().getModContainerById("create")
                .filter(it -> it.getModInfo().getVersion().getMajorVersion() >= 6)
                .isPresent();

        if (createLoaded) {
            CreateCompat.register(modBus);
        }

        LeadProtections.register(entity -> entity.getItemBySlot(EquipmentSlot.HEAD).is(OTags.Items.PROTECTIVE_HELMET));
        LeadProtections.register(entity -> {
            for (var slot : entity.getArmorSlots()) {
                if (!slot.is(OTags.Items.PROTECTIVE_ARMOR_PART)) return false;
            }
            return true;
        });

        if (!FMLLoader.isProduction()) {
            forgeBus.addListener(ODebugCommands::register);
        }
    }

    private void injectVillagerTrades(VillagerTradesEvent event) {
        if (event.getType() == VillagerProfession.MASON) {
            event.getTrades().get(5).add(new BasicItemListing(14, new ItemStack(OBlocks.GARGOYLE.get()), 5, 30, 0.05F));
        }
    }

    private void setup(FMLCommonSetupEvent event) {
        FluidInteractionRegistry.addInteraction(OFluids.MOLTEN_LEAD_TYPE.get(), new FluidInteractionRegistry.InteractionInformation(
                (level, pos, relativePos, fluidState) -> level.getFluidState(relativePos).is(FluidTags.WATER) && fluidState.isSource(),
                fluidState -> OBlocks.LEAD_BLOCK.get().defaultBlockState()
        ));

        FluidInteractionRegistry.addInteraction(OFluids.MOLTEN_LEAD_TYPE.get(), new FluidInteractionRegistry.InteractionInformation(
                (level, blockPos, relativePos, fluidState) -> level.getFluidState(relativePos).is(FluidTags.LAVA) && fluidState.isSource(),
                (level, pos, relativePos, fluidState) -> {
                    LeadOreBlock.spawnCloud(level, pos, 2F);
                    level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
                    level.levelEvent(1501, pos, 0);
                }
        ));

        event.enqueueWork(() -> {
            var EMPTY = CauldronInteraction.EMPTY.map();
            var WATER = CauldronInteraction.WATER.map();
            var LAVA = CauldronInteraction.LAVA.map();
            var POWDER_SNOW = CauldronInteraction.POWDER_SNOW.map();
            var LEAD = MoltenLeadCauldronBlock.INTERACTION_MAP.map();

            EMPTY.put(OItems.MOLTEN_LEAD_BUCKET.get(), MoltenLeadCauldronBlock.FILL_MOLTEN_LEAD);
            WATER.put(OItems.MOLTEN_LEAD_BUCKET.get(), MoltenLeadCauldronBlock.FILL_MOLTEN_LEAD);
            LAVA.put(OItems.MOLTEN_LEAD_BUCKET.get(), MoltenLeadCauldronBlock.FILL_MOLTEN_LEAD);
            POWDER_SNOW.put(OItems.MOLTEN_LEAD_BUCKET.get(), MoltenLeadCauldronBlock.FILL_MOLTEN_LEAD);
            LEAD.put(OItems.MOLTEN_LEAD_BUCKET.get(), MoltenLeadCauldronBlock.FILL_MOLTEN_LEAD);

            if (OreganizedConfig.COMMON.cauldronLeadMelting.get()) {
                EMPTY.put(OBlocks.LEAD_BLOCK.get().asItem(), MoltenLeadCauldronBlock.FILL_LEAD_BLOCK);
                WATER.put(OBlocks.LEAD_BLOCK.get().asItem(), MoltenLeadCauldronBlock.FILL_LEAD_BLOCK);
                LAVA.put(OBlocks.LEAD_BLOCK.get().asItem(), MoltenLeadCauldronBlock.FILL_LEAD_BLOCK);
                POWDER_SNOW.put(OBlocks.LEAD_BLOCK.get().asItem(), MoltenLeadCauldronBlock.FILL_LEAD_BLOCK);
            }

            LEAD.put(Items.AIR, MoltenLeadCauldronBlock.EMPTY_LEAD_BLOCK);
            LEAD.put(Items.BUCKET, MoltenLeadCauldronBlock.EMPTY_MOLTEN_LEAD);

            CauldronInteraction.addDefaultInteractions(MoltenLeadCauldronBlock.INTERACTION_MAP.map());

            FireBlock fire = (FireBlock) Blocks.FIRE;
            fire.setFlammable(OBlocks.SHRAPNEL_BOMB.get(), 15, 100);

            DispenserBlock.registerBehavior(OItems.LEAD_BOLT.get(), new ProjectileDispenseBehavior(OItems.LEAD_BOLT.get()));

            DispenserBlock.registerBehavior(OItems.MOLTEN_LEAD_BUCKET.get(), new FishBucketDispenseItemBehavior());

            Stream.of("lead_bolt_crates1", "lead_bolt_crates2").forEach(name -> {
                DataUtil.addToJigsawPattern(ResourceLocation.withDefaultNamespace("pillager_outpost/features"), $ -> {
                    return StructurePoolElement.legacy(Oreganized.MOD_ID + ":pillager_outpost/" + name).apply(StructureTemplatePool.Projection.RIGID);
                }, 1);
            });
        });
    }

    private void registerPotionMixes(RegisterBrewingRecipesEvent event) {
        event.getBuilder().addMix(Potions.WATER, OItems.LEAD_INGOT.get(), OPotions.STUNNING);
        event.getBuilder().addMix(OPotions.STUNNING, Items.REDSTONE, OPotions.LONG_STUNNING);
    }

}
