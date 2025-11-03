package galena.oreganized;


import com.google.common.collect.ImmutableBiMap;
import com.mojang.serialization.Codec;
import com.teamabnormals.blueprint.common.dispenser.FishBucketDispenseItemBehavior;
import com.teamabnormals.blueprint.core.util.DataUtil;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import galena.oreganized.api.LeadProtections;
import galena.oreganized.compat.ColorCompat;
import galena.oreganized.compat.create.CreateCompat;
import galena.oreganized.content.block.LeadOreBlock;
import galena.oreganized.content.block.MoltenLeadCauldronBlock;
import galena.oreganized.content.entity.LeadBoltEntity;
import galena.oreganized.index.OAttributes;
import galena.oreganized.index.OBlockEntities;
import galena.oreganized.index.OBlocks;
import galena.oreganized.index.OCriteriaTriggers;
import galena.oreganized.index.OEffects;
import galena.oreganized.index.OEntityTypes;
import galena.oreganized.index.OFluids;
import galena.oreganized.index.OItems;
import galena.oreganized.index.OPaintingVariants;
import galena.oreganized.index.OParticleTypes;
import galena.oreganized.index.OPotions;
import galena.oreganized.index.ORecipeTypes;
import galena.oreganized.index.OSoundEvents;
import galena.oreganized.index.OStructures;
import galena.oreganized.index.OTags;
import galena.oreganized.network.OreganizedNetwork;
import galena.oreganized.world.AddItemLootModifier;
import java.util.stream.Stream;
import net.minecraft.core.Position;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.core.dispenser.AbstractProjectileDispenseBehavior;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraftforge.common.BasicItemListing;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidInteractionRegistry;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Oreganized.MOD_ID)
public class Oreganized {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "oreganized";

    public static ResourceLocation modLoc(String location) {
        return new ResourceLocation(MOD_ID, location);
    }

    public static final RegistryHelper REGISTRY_HELPER = new RegistryHelper(MOD_ID);

    private static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIERS = DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, Oreganized.MOD_ID);

    public Oreganized() {
        final IEventBus modBus = Bus.MOD.bus().get();
        final IEventBus forgeBus = MinecraftForge.EVENT_BUS;

        OreganizedConfig.register();

        modBus.addListener(this::setup);
        forgeBus.addListener(this::injectVillagerTrades);

        LOOT_MODIFIERS.register("add_item", () -> AddItemLootModifier.CODEC);

        OEffects.register(modBus);
        OEntityTypes.register(modBus);
        OFluids.register(modBus);
        OParticleTypes.register(modBus);
        OPotions.register(modBus);
        OStructures.register(modBus);
        OAttributes.register(modBus);
        LOOT_MODIFIERS.register(modBus);
        OBlockEntities.register();
        OBlocks.register();
        OItems.register();
        OCriteriaTriggers.register();
        OSoundEvents.register();
        ORecipeTypes.register(modBus);
        OPaintingVariants.register(modBus);

        REGISTRY_HELPER.register(modBus);

        OreganizedNetwork.register();

        var createLoaded = ModList.get().getModContainerById("create")
                .filter(it -> it.getModInfo().getVersion().getMajorVersion() >= 6)
                .isPresent();

        if (createLoaded) {
            CreateCompat.register();
        }

        LeadProtections.register(entity -> entity.getItemBySlot(EquipmentSlot.HEAD).is(OTags.Items.PROTECTIVE_HELMET));
        LeadProtections.register(entity -> {
            for (var slot : entity.getArmorSlots()) {
                if (!slot.is(OTags.Items.PROTECTIVE_ARMOR_PART)) return false;
            }
            return true;
        });
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
            var EMPTY = CauldronInteraction.EMPTY;
            var WATER = CauldronInteraction.WATER;
            var LAVA = CauldronInteraction.LAVA;
            var POWDER_SNOW = CauldronInteraction.POWDER_SNOW;
            var LEAD = MoltenLeadCauldronBlock.INTERACTION_MAP;

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

            CauldronInteraction.addDefaultInteractions(MoltenLeadCauldronBlock.INTERACTION_MAP);

            PotionBrewing.addMix(Potions.WATER, OItems.LEAD_INGOT.get(), OPotions.STUNNING.get());
            PotionBrewing.addMix(OPotions.STUNNING.get(), Items.REDSTONE, OPotions.LONG_STUNNING.get());

            FireBlock fire = (FireBlock) Blocks.FIRE;
            fire.setFlammable(OBlocks.SHRAPNEL_BOMB.get(), 15, 100);

            DispenserBlock.registerBehavior(OItems.LEAD_BOLT.get(), new AbstractProjectileDispenseBehavior() {
                protected Projectile getProjectile(Level level, Position pos, ItemStack stack) {
                    var entity = new LeadBoltEntity(OEntityTypes.LEAD_BOLT.get(), level, pos);
                    entity.pickup = AbstractArrow.Pickup.ALLOWED;
                    return entity;
                }
            });

            DispenserBlock.registerBehavior(OItems.MOLTEN_LEAD_BUCKET.get(), new FishBucketDispenseItemBehavior());

            Stream.of("lead_bolt_crates1", "lead_bolt_crates2").forEach(name -> {
                DataUtil.addToJigsawPattern(new ResourceLocation("pillager_outpost/features"), $ -> {
                    return StructurePoolElement.legacy(Oreganized.MOD_ID + ":pillager_outpost/" + name).apply(StructureTemplatePool.Projection.RIGID);
                }, 1);
            });
        });

        var waxedBlocks = new ImmutableBiMap.Builder<Block, Block>();
        waxedBlocks.put(OBlocks.WAXED_SPOTTED_GLANCE.get(), OBlocks.SPOTTED_GLANCE.get());
        OBlocks.WAXED_CONCRETE_POWDER.forEach((color, waxed) -> {
            var unwaxed = ColorCompat.getColoredBlock("concrete_powder", color);
            waxedBlocks.put(waxed.get(), unwaxed);
        });
        OBlocks.WAXED_BLOCKS = waxedBlocks.build();
    }

}
