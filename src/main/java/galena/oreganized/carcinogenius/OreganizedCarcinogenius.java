package galena.oreganized.carcinogenius;

import static galena.oreganized.ModCompat.FARMERS_DELIGHT_ID;
import static galena.oreganized.ModCompat.NETHERS_DELIGHT_ID;
import static galena.oreganized.ModCompat.SHIELD_EXPANSION_ID;

import com.mojang.serialization.Codec;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import galena.oreganized.compat.ColorCompat;
import galena.oreganized.compat.create.CreateCompat;
import galena.oreganized.content.entity.LeadBoltEntity;
import galena.oreganized.carcinogenius.data.OBlockStates;
import galena.oreganized.carcinogenius.data.OBlockTags;
import galena.oreganized.carcinogenius.data.OItemModels;
import galena.oreganized.carcinogenius.data.OLang;
import galena.oreganized.carcinogenius.data.OLootTables;
import galena.oreganized.carcinogenius.data.ORecipes;
import galena.oreganized.carcinogenius.index.OBlocks;
import galena.oreganized.carcinogenius.index.OEffects;
import galena.oreganized.carcinogenius.index.OItems;
import galena.oreganized.carcinogenius.index.OParticleTypes;
import galena.oreganized.carcinogenius.index.OPotions;
import galena.oreganized.network.OreganizedNetwork;
import galena.oreganized.world.AddItemLootModifier;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import net.minecraft.DetectedVersion;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.metadata.PackMetadataGenerator;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.util.MutableHashedLinkedMap;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.infernalstudios.shieldexp.init.ItemsInit;
import umpaz.nethersdelight.common.registry.NDItems;
import vectorwing.farmersdelight.common.registry.ModItems;

@Mod(OreganizedCarcinogenius.MOD_ID)
public class OreganizedCarcinogenius {
    public static final Logger LOGGER = LogManager.getLogger();
   // public static final String MOD_ID = "oreganized_carcinogenius";
   public static final String NAMESPACE = Oreganized.MODID;

    public static ResourceLocation modLoc(String location) {
        return new ResourceLocation(MOD_ID, location);
    }

    public static final RegistryHelper REGISTRY_HELPER = new RegistryHelper(NAMESPACE);

    private static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIERS = DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, NAMESPACE);

    public OreganizedCarcinogenius() {
        final IEventBus modBus = Bus.MOD.bus().get();
        final IEventBus forgeBus = MinecraftForge.EVENT_BUS;

        modBus.addListener(this::setup);
        modBus.addListener(this::gatherData);
        modBus.addListener(this::buildCreativeModeTabContents);

        DeferredRegister<?>[] registers = {
                OEffects.EFFECTS,
                OParticleTypes.PARTICLES,
                OPotions.POTIONS,
                LOOT_MODIFIERS,
        };

        for (DeferredRegister<?> register : registers) {
            register.register(modBus);
        }

        REGISTRY_HELPER.register(modBus);
    }

    private void setup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            PotionBrewing.addMix(Potions.WATER, OItems.REFINED_ASBESTOS.get(), OPotions.LUNG_DAMAGE.get());
        });
    }

    public void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> future = event.getLookupProvider();
        ExistingFileHelper helper = event.getExistingFileHelper();
        boolean client = event.includeClient();
        boolean server = event.includeServer();

        var lang = new OLang(output);

        generator.addProvider(client, new OBlockStates(output, helper));
        generator.addProvider(client, new OItemModels(output, helper));
        generator.addProvider(client, lang);

        generator.addProvider(server, new ORecipes(output));
        generator.addProvider(server, new OLootTables(output));
        OBlockTags blockTags = new OBlockTags(output, future, helper);
        generator.addProvider(server, blockTags);

        generator.addProvider(server, new PackMetadataGenerator(output).add(PackMetadataSection.TYPE, new PackMetadataSection(
                Component.literal("Oreganized Carcinogenius resources"),
                DetectedVersion.BUILT_IN.getPackVersion(PackType.CLIENT_RESOURCES),
                Arrays.stream(PackType.values()).collect(Collectors.toMap(Function.identity(), DetectedVersion.BUILT_IN::getPackVersion))
        )));
    }

    @SubscribeEvent
    public void buildCreativeModeTabContents(BuildCreativeModeTabContentsEvent event) {
        MutableHashedLinkedMap<ItemStack, CreativeModeTab.TabVisibility> entries = event.getEntries();

        putAfter(entries, OBlocks.LEAD_BLOCK.get(), OBlocks.ASBESTOS_BLOCK);
        putAfter(entries, OBlocks.ASBESTOS_BLOCK.get(), OBlocks.CUT_LEAD);
        putAfter(entries, OBlocks.CUT_LEAD.get(), OBlocks.LEAD_BRICKS);
        putAfter(entries, OBlocks.LEAD_PILLAR.get(), OBlocks.CUT_LEAD);
        putAfter(entries, OBlocks.LEAD_BRICKS.get(), OBlocks.LEAD_PILLAR);
        putAfter(entries, Blocks.IRON_BARS, OBlocks.LEAD_BARS);
    }

    private static void putAfter(MutableHashedLinkedMap<ItemStack, CreativeModeTab.TabVisibility> entries, ItemLike after, Supplier<? extends ItemLike> supplier) {
        ItemLike key = supplier.get();
        if (!entries.contains(new ItemStack(after))) return;
        entries.putAfter(new ItemStack(after), new ItemStack(key), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
    }

}
