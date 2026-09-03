package galena.oreganized.plumbum.config;

import galena.oreganized.config.ConfigModule;
import galena.oreganized.config.ModularConfig;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;

public class PlumbumConfigModule implements ConfigModule {

    static final String CONFIG_KEY = "lead";

    @Override
    public void create(ModularConfig.Builder builder) {
        builder.module(ModConfig.Type.COMMON, CONFIG_KEY, Common::new);
        builder.module(ModConfig.Type.CLIENT, CONFIG_KEY, Client::new);
    }

    public static class Common {

        public final ModConfigSpec.ConfigValue<Boolean> poisonInsteadOfStunning;
        public final ModConfigSpec.ConfigValue<Boolean> leadDustCloud;
        public final ModConfigSpec.ConfigValue<Boolean> pillagerSpawnWithBolts;
        public final ModConfigSpec.ConfigValue<Integer> moltenLeadDelay;
        public final ModConfigSpec.ConfigValue<Boolean> cauldronLeadMelting;

        private Common(ModConfigSpec.Builder builder) {
            poisonInsteadOfStunning = builder.comment("Should lead poisoning events give just Poison instead of Brain Damage?")
                    .define("poisonInsteadOfBrainDamage", false);
            leadDustCloud = builder.comment("Should lead ore spawn dust clouds when broken without adjacent water?")
                    .define("leadDustCloud", true);
            pillagerSpawnWithBolts = builder.comment("Pillagers have a chance to spawn with a lead bolt in their offhand")
                    .define("pillagerSpawnWithBolts", true);
            moltenLeadDelay = builder.comment("Time in ticks molten lead waits until flowing downwards")
                    .defineInRange("moltenLeadDelay", 20 * 10, 0, 20 * 100);
            cauldronLeadMelting = builder.comment("Can lead blocks be placed into a cauldron to melt?")
                    .define("cauldronLeadMelting", true);
        }
    }

    public static class Client {

        public final ModConfigSpec.ConfigValue<Boolean> renderStunningOverlay;

        public Client(ModConfigSpec.Builder builder) {
            renderStunningOverlay = builder.comment("Should the custom overlay for the brain damage effect be rendered?")
                    .define("renderBrainDamageOverlay", true);
        }
    }

}
