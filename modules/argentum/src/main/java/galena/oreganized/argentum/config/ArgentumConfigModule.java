package galena.oreganized.argentum.config;

import galena.oreganized.config.ConfigModule;
import galena.oreganized.config.ModularConfig;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;

public class ArgentumConfigModule implements ConfigModule {

    static final String CONFIG_KEY = "silver";

    @Override
    public void create(ModularConfig.Builder builder) {
        builder.module(ModConfig.Type.COMMON, CONFIG_KEY, Common::new);
    }

    public static class Common {

        public final ModConfigSpec.ConfigValue<Boolean> scribeSilkTouchStone;

        public final ModConfigSpec.ConfigValue<Integer> tarnishRadius;
        public final ModConfigSpec.ConfigValue<Double> tarnishChance;
        public final ModConfigSpec.ConfigValue<Integer> tarnishChecksPerMob;

        private Common(ModConfigSpec.Builder builder) {
            scribeSilkTouchStone = builder.comment("The scribe is able to silk-touch pickaxe-related blocks")
                    .define("scribeSilkTouchStone", true);

            tarnishRadius = builder.comment("The radius in blocks for the tarnishing effect of undead mobs")
                    .defineInRange("tarnishRadius", 4, 1, 20);
            tarnishChance = builder.comment("The chance per block check for tarnishing to occur (1.0 = 100%, 0.0 = 0%). Note that this only applies to the first tarnish stage. other nextStage are this /2")
                    .defineInRange("tarnishChance", 0.5D, 0.0D, 1.0D);
            tarnishChecksPerMob = builder.comment("The number of blocks around an undead mob to check every times a mob dies")
                    .defineInRange("tarnishChecksPerMob", 40, 1, 100);
        }
    }

}
