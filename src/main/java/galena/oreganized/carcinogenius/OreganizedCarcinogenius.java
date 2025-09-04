package galena.oreganized.carcinogenius;

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
import java.util.Optional;
import java.util.function.Supplier;
import net.minecraft.DetectedVersion;
import net.minecraft.data.metadata.PackMetadataGenerator;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.ItemLike;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(OreganizedCarcinogenius.MOD_ID)
public class OreganizedCarcinogenius {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String NAMESPACE = Oreganized.MOD_ID;
    public static final String MOD_ID = Oreganized.MOD_ID + "_carcinogenius";

    public static ResourceLocation modLoc(String location) {
        return ResourceLocation.fromNamespaceAndPath(NAMESPACE, location);
    }

    public static final RegistryHelper REGISTRY_HELPER = new RegistryHelper(NAMESPACE);

    public OreganizedCarcinogenius(IEventBus modBus) {
        NeoForge.EVENT_BUS.addListener(this::registerPotions);
        modBus.addListener(this::gatherData);
        modBus.addListener(this::buildCreativeModeTabContents);

        OCItems.register();
        OCBlocks.register();
        OCEffects.register(modBus);
        OCParticleTypes.register(modBus);
        OCPotions.register(modBus);

        REGISTRY_HELPER.register(modBus);
    }

    private void registerPotions(RegisterBrewingRecipesEvent event) {
        event.getBuilder().addMix(Potions.WATER, OCItems.REFINED_ASBESTOS.get(), OCPotions.LUNG_DAMAGE);
    }

    public void gatherData(GatherDataEvent event) {
        var generator = event.getGenerator();
        var output = generator.getPackOutput();
        var lookup = event.getLookupProvider();
        var helper = event.getExistingFileHelper();
        boolean client = event.includeClient();
        boolean server = event.includeServer();

        var lang = new OLang(output);

        generator.addProvider(client, new OBlockStates(output, helper));
        generator.addProvider(client, new OItemModels(output, helper));
        generator.addProvider(client, lang);

        generator.addProvider(server, new ORecipes(output, lookup));
        generator.addProvider(server, new OLootTables(output, lookup));
        OBlockTags blockTags = new OBlockTags(output, lookup, helper);
        generator.addProvider(server, blockTags);

        generator.addProvider(server, new PackMetadataGenerator(output).add(PackMetadataSection.TYPE, new PackMetadataSection(
                Component.literal("Oreganized Carcinogenius resources"),
                DetectedVersion.BUILT_IN.getPackVersion(PackType.CLIENT_RESOURCES),
                Optional.empty()
        )));
    }

    @SubscribeEvent
    public void buildCreativeModeTabContents(BuildCreativeModeTabContentsEvent event) {
        var tab = event.getTabKey();

        if (tab == CreativeModeTabs.BUILDING_BLOCKS) {
            putAfter(event, OBlocks.LEAD_BLOCK, OCBlocks.ASBESTOS_BLOCK);
        }

        if (tab == CreativeModeTabs.NATURAL_BLOCKS) {
            putAfter(event, OBlocks.RAW_SILVER_BLOCK, OCBlocks.RAW_ASBESTOS_BLOCK);
            putAfter(event, OBlocks.DEEPSLATE_SILVER_ORE, OCBlocks.ASBESTOS_ORE);
            putAfter(event, OCBlocks.ASBESTOS_ORE, OCBlocks.DEEPSLATE_ASBESTOS_ORE);
        }

        if (tab == CreativeModeTabs.INGREDIENTS) {
            putAfter(event, OItems.RAW_SILVER, OCItems.RAW_ASBESTOS);
            putAfter(event, OCItems.RAW_ASBESTOS, OCItems.REFINED_ASBESTOS);
        }
    }

    private static void putAfter(BuildCreativeModeTabContentsEvent event, ItemLike after, Supplier<? extends ItemLike> item) {
        event.insertAfter(new ItemStack(after), new ItemStack(item.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
    }

}
