package galena.oreganized.data;

import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@EventBusSubscriber
public class OreganizedDatagen {

    @SubscribeEvent(priority = EventPriority.HIGH)
    private static void gatherData(GatherDataEvent event) {
        var generator = event.getGenerator();
        var output = generator.getPackOutput();
        var lookup = event.getLookupProvider();
        var helper = event.getExistingFileHelper();
        boolean client = event.includeClient();
        boolean server = event.includeServer();

        ODatagen.addLangProvider(OLang::generate);

        OBlockTags blockTags = new OBlockTags(output, lookup, helper);
        generator.addProvider(server, blockTags);
        generator.addProvider(server, new OItemTags(output, lookup, blockTags.contentsGetter(), helper));
        generator.addProvider(server, new OEntityTags(output, lookup, helper));
        generator.addProvider(server, new OFluidTags(output, lookup, helper));
        generator.addProvider(server, new OEnchantmentTags(output, lookup, helper));
        DatapackBuiltinEntriesProvider datapackProvider = new ORegistries(output, lookup);
        CompletableFuture<HolderLookup.Provider> lookupProvider = datapackProvider.getRegistryProvider();
        generator.addProvider(server, datapackProvider);
        generator.addProvider(server, new OBiomeTags(output, lookupProvider, helper));
        generator.addProvider(server, new ODamageTypeTags(output, lookupProvider, helper));
        generator.addProvider(server, new OPaintingVariantTags(output, lookupProvider, helper));

        generator.addProvider(server, new ODataMaps(output, lookupProvider));
        generator.addProvider(server, new OLootModifiers(output, lookupProvider));

        generator.addProvider(client, new OSpriteSourceProvider(output, lookup, helper));
    }

}
