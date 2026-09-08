package galena.oreganized.compat.ponder;

import galena.oreganized.ponder.OPonderPlugin;

import java.util.ServiceLoader;
import java.util.stream.Stream;

import net.createmod.ponder.foundation.PonderIndex;

public class PonderCompat {

    private static Stream<OPonderPlugin> loadPlugins() {
        return ServiceLoader.load(OPonderPlugin.class, PonderCompat.class.getClassLoader())
                .stream()
                .map(ServiceLoader.Provider::get);
    }

    public static void register() {
        var plugins = loadPlugins();
        PonderIndex.addPlugin(new OreganizedPonderPlugin(plugins));
    }

}
