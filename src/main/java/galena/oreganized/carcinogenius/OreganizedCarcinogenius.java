package galena.oreganized.carcinogenius;

import com.mojang.serialization.Codec;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import galena.oreganized.Oreganized;
import galena.oreganized.carcinogenius.data.OBlockStates;
import galena.oreganized.carcinogenius.data.OBlockTags;
import galena.oreganized.carcinogenius.data.OItemModels;
import galena.oreganized.carcinogenius.data.OLang;
import galena.oreganized.carcinogenius.data.OLootTables;
import galena.oreganized.carcinogenius.data.ORecipes;
import galena.oreganized.carcinogenius.index.OCBlocks;
import galena.oreganized.carcinogenius.index.OCEffects;
import galena.oreganized.carcinogenius.index.OCItems;
import galena.oreganized.carcinogenius.index.OCParticleTypes;
import galena.oreganized.carcinogenius.index.OCPotions;
import galena.oreganized.index.OBlocks;
import galena.oreganized.index.OItems;
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

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Mod(OreganizedCarcinogenius.MOD_ID)
public class OreganizedCarcinogenius {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String NAMESPACE = Oreganized.MOD_ID;
    public static final String MOD_ID = Oreganized.MOD_ID + "_carcinogenius";

    public static ResourceLocation modLoc(String location) {
        return new ResourceLocation(NAMESPACE, location);
    }

    public static final RegistryHelper REGISTRY_HELPER = new RegistryHelper(NAMESPACE);

    private static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIERS = DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, NAMESPACE);

    public OreganizedCarcinogenius() {
        final IEventBus modBus = Bus.MOD.bus().get();

        modBus.addListener(this::setup);
        modBus.addListener(this::gatherData);
        modBus.addListener(this::buildCreativeModeTabContents);

        DeferredRegister<?>[] registers = {
                OCEffects.EFFECTS,
                OCParticleTypes.PARTICLES,
                OCPotions.POTIONS,
                LOOT_MODIFIERS,
        };

        for (DeferredRegister<?> register : registers) {
            register.register(modBus);
        }

        REGISTRY_HELPER.register(modBus);
    }

    private void setup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            PotionBrewing.addMix(Potions.WATER, OCItems.REFINED_ASBESTOS.get(), OCPotions.LUNG_DAMAGE.get());
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

        putAfter(entries, OBlocks.LEAD_BLOCK.get(), OCBlocks.ASBESTOS_BLOCK);
        putAfter(entries, OBlocks.RAW_SILVER_BLOCK.get(), OCBlocks.RAW_ASBESTOS_BLOCK);
        putAfter(entries, OBlocks.DEEPSLATE_SILVER_ORE.get(), OCBlocks.ASBESTOS_ORE);
        putAfter(entries, OCBlocks.ASBESTOS_ORE.get(), OCBlocks.DEEPSLATE_ASBESTOS_ORE);
        putAfter(entries, OItems.RAW_SILVER.get(), OCItems.RAW_ASBESTOS);
        putAfter(entries, OCItems.RAW_ASBESTOS.get(), OCItems.REFINED_ASBESTOS);
    }

    private static void putAfter(MutableHashedLinkedMap<ItemStack, CreativeModeTab.TabVisibility> entries, ItemLike after, Supplier<? extends ItemLike> supplier) {
        ItemLike key = supplier.get();
        if (!entries.contains(new ItemStack(after))) return;
        entries.putAfter(new ItemStack(after), new ItemStack(key), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
    }

}
