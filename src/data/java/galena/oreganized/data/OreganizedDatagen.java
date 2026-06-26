package galena.oreganized.data;

import galena.oreganized.Oreganized;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import net.minecraft.DetectedVersion;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.metadata.PackMetadataGenerator;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@EventBusSubscriber(modid = Oreganized.MOD_ID)
public class OreganizedDatagen {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
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
        generator.addProvider(client, new OSoundDefinitions(output, helper));
        generator.addProvider(client, new OSpriteSourceProvider(output, lookup, helper));

        generator.addProvider(server, new ORecipes(output, lookup));
        generator.addProvider(server, new OLootTables(output, lookup));
        OBlockTags blockTags = new OBlockTags(output, lookup, helper);
        generator.addProvider(server, blockTags);
        generator.addProvider(server, new OItemTags(output, lookup, blockTags.contentsGetter(), helper));
        generator.addProvider(server, new OEntityTags(output, lookup, helper));
        generator.addProvider(server, new OAdvancements(output, lookup, helper, lang));
        generator.addProvider(server, new OFluidTags(output, lookup, helper));
        generator.addProvider(server, new OEnchantmentTags(output, lookup, helper));
        DatapackBuiltinEntriesProvider datapackProvider = new ORegistries(output, lookup);
        CompletableFuture<HolderLookup.Provider> lookupProvider = datapackProvider.getRegistryProvider();
        generator.addProvider(server, datapackProvider);
        generator.addProvider(server, new OBiomeTags(output, lookupProvider, helper));
        generator.addProvider(server, new ODamageTypeTags(output, lookupProvider, helper));
        generator.addProvider(server, new OPaintingVariantTags(output, lookupProvider, helper));
        generator.addProvider(server, new ODataMaps(output, lookupProvider));

        generator.addProvider(server, new PackMetadataGenerator(output).add(PackMetadataSection.TYPE, new PackMetadataSection(
                Component.literal("Oreganized resources"),
                DetectedVersion.BUILT_IN.getPackVersion(PackType.CLIENT_RESOURCES),
                Optional.empty()
        )));
    }

}
