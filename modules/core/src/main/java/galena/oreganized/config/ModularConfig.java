package galena.oreganized.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Stream;

import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;

public class ModularConfig {

    private final Map<String, Object> modules = new HashMap<>();

    protected ModularConfig(Map<String, Function<ModConfigSpec.Builder, ?>> modules, ModConfigSpec.Builder builder) {
        modules.forEach((key, factory) -> {
            builder.push(key);
            this.modules.put(key, factory.apply(builder));
            builder.pop();
        });
    }

    public Stream<Object> stream() {
        return modules.values().stream();
    }

    public <T> T get(String key) {
        //noinspection unchecked
        return Objects.requireNonNull((T) modules.get(key));
    }

    @FunctionalInterface
    public interface Builder {
        void module(ModConfig.Type type, String key, Function<ModConfigSpec.Builder, Object> factory);
    }

}
