package galena.oreganized.core.data;

import galena.oreganized.client.OResourcePacks;
import java.util.Optional;
import net.minecraft.DetectedVersion;
import net.minecraft.data.PackOutput;
import net.minecraft.data.metadata.PackMetadataGenerator;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@EventBusSubscriber
public class CompatPacks {

    @SubscribeEvent(priority = EventPriority.HIGH)
    static void generatePackMetadata(GatherDataEvent event) {
        var createCompat = event.getGenerator().getBuiltinDatapack(true, "create_compat");
        var createCompatOutput = new PackOutput(event.getGenerator().getPackOutput().getOutputFolder().resolve(OResourcePacks.CREATE_COMPAT));
        createCompat.addProvider($ -> new PackMetadataGenerator(createCompatOutput).add(PackMetadataSection.TYPE, new PackMetadataSection(
                Component.literal("Textures for other mods to fit Oreganized's color palettes for its materials"),
                DetectedVersion.BUILT_IN.getPackVersion(PackType.CLIENT_RESOURCES),
                Optional.empty()
        )));
    }

}
