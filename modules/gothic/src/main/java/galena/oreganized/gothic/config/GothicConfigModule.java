package galena.oreganized.gothic.config;

import com.teamabnormals.blueprint.core.annotations.ConfigKey;
import galena.oreganized.config.ConfigModule;
import galena.oreganized.config.ModularConfig;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;

public class GothicConfigModule implements ConfigModule {

    static final String CONFIG_KEY = "gothic";

    @Override
    public void create(ModularConfig.Builder builder) {
        builder.module(ModConfig.Type.COMMON, CONFIG_KEY, Common::new);
    }

    public static class Common {
        @ConfigKey("cleric_windows")
        public final ModConfigSpec.ConfigValue<Boolean> replaceClericWindows;

        private Common(ModConfigSpec.Builder builder) {
            replaceClericWindows = builder.comment("Replace the stained glass windows in cleric temples with crystal glass")
                    .define("replaceClericWindows", true);
        }
    }

}
