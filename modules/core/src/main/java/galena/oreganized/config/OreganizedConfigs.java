package galena.oreganized.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.ServiceLoader;
import java.util.function.Function;
import java.util.stream.Stream;

import net.neoforged.fml.ModContainer;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public class OreganizedConfigs {

    private static final Map<ModConfig.Type, Pair<ModularConfig, ModConfigSpec>> CONFIGS = new HashMap<>();

    public static ModularConfig config(ModConfig.Type type) {
        var pair = Objects.requireNonNull(CONFIGS.get(type));
        return pair.getLeft();
    }

    public static <T> T configModule(ModConfig.Type type, String key) {
        return config(type).get(key);
    }

    private static Stream<ConfigModule> loadConfigModules() {
        return ServiceLoader.load(ConfigModule.class, ModularConfig.class.getClassLoader())
                .stream()
                .map(ServiceLoader.Provider::get);
    }

    private static void create() {
        var map = new HashMap<ModConfig.Type, Map<String, Function<ModConfigSpec.Builder, ?>>>();

        loadConfigModules().forEach(module -> {
            module.create((type, key, factory) -> {
                map.computeIfAbsent(type, $ -> new HashMap<>())
                        .put(key, factory);
            });
        });

        map.forEach((type, modules) -> {
            var modular = new ModConfigSpec.Builder().configure(it -> new ModularConfig(modules, it));
            CONFIGS.put(type, modular);
        });
    }

    static {
        create();
    }

    public static void register(ModContainer container) {
        CONFIGS.forEach((type, pair) -> {
            container.registerConfig(type, pair.getRight());
        });
    }

}
