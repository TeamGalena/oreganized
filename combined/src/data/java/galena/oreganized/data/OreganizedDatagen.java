package galena.oreganized.data;

import com.teamabnormals.blueprint.core.registry.BlueprintDataPackRegistries;
import galena.oreganized.index.ODamageSources;
import galena.oreganized.index.ORecords;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
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

        ODatagen.addDataRegistryEntries(Registries.CONFIGURED_FEATURE, OFeatures.Configured::bootstrap);
        ODatagen.addDataRegistryEntries(Registries.PLACED_FEATURE, OFeatures.Placed::bootstrap);
        ODatagen.addDataRegistryEntries(Registries.DAMAGE_TYPE, ODamageSources::bootStrap);
        ODatagen.addDataRegistryEntries(Registries.TRIM_MATERIAL, OTrimMaterials::bootstrap);
        ODatagen.addDataRegistryEntries(Registries.PAINTING_VARIANT, OPaintingVariants::bootstrap);
        ODatagen.addDataRegistryEntries(Registries.JUKEBOX_SONG, ORecords::bootstrap);
        ODatagen.addDataRegistryEntries(BlueprintDataPackRegistries.STRUCTURE_REPALETTERS, OStructurePalettes::bootstrap);

        generator.addProvider(client, new OSpriteSourceProvider(output, lookup, helper));
    }

}
