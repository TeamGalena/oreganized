package galena.oreganized.compat.ponder;

import galena.oreganized.ModCompat;
import galena.oreganized.ponder.OPonderPlugin;

import java.util.ServiceLoader;
import java.util.stream.Stream;

import net.createmod.ponder.foundation.PonderIndex;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(Dist.CLIENT)
public class PonderCompat {

    private static Stream<OPonderPlugin> loadPlugins() {
        return ServiceLoader.load(OPonderPlugin.class, PonderCompat.class.getClassLoader())
                .stream()
                .map(ServiceLoader.Provider::get);
    }

    @SubscribeEvent
    private static void setup(FMLClientSetupEvent event) {
        if (ModCompat.PONDER_LOADED) {
            PonderCompat.register();
        }
    }

    public static void register() {
        var plugins = loadPlugins();
        PonderIndex.addPlugin(new OreganizedPonderPlugin(plugins));
    }

}
