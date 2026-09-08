package galena.oreganized.electrum.config;

import galena.oreganized.config.ConfigModule;
import galena.oreganized.config.ModularConfig;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;

public class ElectrumConfigModule implements ConfigModule {

    static final String CONFIG_KEY = "electrum";

    @Override
    public void create(ModularConfig.Builder builder) {
        builder.module(ModConfig.Type.COMMON, CONFIG_KEY, Common::new);
    }

    public static class Common {

        public final ModConfigSpec.ConfigValue<Double> electrumSpeedBoost;

        private Common(ModConfigSpec.Builder builder) {
            electrumSpeedBoost = builder.comment("the speed boost granted by electrum armor")
                    .defineInRange("speedBoost", 0.05, 0, 1);
        }
    }

}
