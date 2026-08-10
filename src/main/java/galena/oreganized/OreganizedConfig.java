package galena.oreganized;

import com.teamabnormals.blueprint.core.annotations.ConfigKey;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.ModConfigSpec.ConfigValue;
import org.apache.commons.lang3.tuple.Pair;

public class OreganizedConfig {
    public static final Common COMMON;
    public static final Client CLIENT;
    private static final ModConfigSpec COMMON_SPEC;
    private static final ModConfigSpec CLIENT_SPEC;

    public static class Common {
        public final ConfigValue<Boolean> poisonInsteadOfStunning;
        public final ConfigValue<Boolean> leadDustCloud;
        public final ConfigValue<Boolean> pillagerSpawnWithBolts;
        public final ConfigValue<Boolean> scribeSilkTouchStone;
        public final ConfigValue<Integer> moltenLeadDelay;
        public final ConfigValue<Boolean> cauldronLeadMelting;
        public final ConfigValue<Integer> tarnishRadius;
        public final ConfigValue<Double> tarnishChance;
        public final ConfigValue<Integer> tarnishChecksPerMob;
        @ConfigKey("cleric_windows")
        public final ConfigValue<Boolean> replaceClericWindows;


        private Common(ModConfigSpec.Builder builder) {
            builder.comment("Common");
            builder.push("common");

            poisonInsteadOfStunning = builder.comment("Should lead poisoning events give just Poison instead of Brain Damage?").define("poisonInsteadOfBrainDamage", false);
            leadDustCloud = builder.comment("Should lead ore spawn dust clouds when broken without adjacent water?").define("leadDustCloud", true);
            pillagerSpawnWithBolts = builder.comment("Pillagers have a chance to spawn with a lead bolt in their offhand").define("pillagerSpawnWithBolts", true);
            scribeSilkTouchStone = builder.comment("The scribe is able to silk-touch pickaxe-related blocks").define("scribeSilkTouchStone", true);
            moltenLeadDelay = builder.comment("Time in ticks molten lead waits until flowing downwards").defineInRange("moltenLeadDelay", 20 * 10, 0, 20 * 100);
            cauldronLeadMelting = builder.comment("Can lead blocks be placed into a cauldron to melt?").define("cauldronLeadMelting", true);
            replaceClericWindows = builder.comment("Replace the stained glass windows in cleric temples with crystal glass")
                    .define("replaceClericWindows", true);
            builder.push("silver");
            tarnishRadius = builder.comment("The radius in blocks for the tarnishing effect of undead mobs")
                    .defineInRange("tarnishRadius", 4, 1, 20);
            tarnishChance = builder.comment("The chance per block check for tarnishing to occur (1.0 = 100%, 0.0 = 0%). Note that this only applies to the first tarnish stage. other nextStage are this /2")
                    .defineInRange("tarnishChance", 0.5D, 0.0D, 1.0D);
            tarnishChecksPerMob = builder.comment("The number of blocks around an undead mob to check every times a mob dies")
                    .defineInRange("tarnishChecksPerMob", 40, 1, 100);
            builder.pop();
            builder.pop();
        }
    }

    public static class Client {

        public final ConfigValue<Boolean> renderStunningOverlay;

        public Client(ModConfigSpec.Builder builder) {
            builder.comment("Client");
            builder.push("client");

            renderStunningOverlay = builder.comment("Should the custom overlay for the brain damage effect be rendered?").define("renderBrainDamageOverlay", true);

            builder.pop();
        }
    }

    static {
        final Pair<Common, ModConfigSpec> commonSpecPair = new ModConfigSpec.Builder().configure(Common::new);
        final Pair<Client, ModConfigSpec> clientSpecPair = new ModConfigSpec.Builder().configure(Client::new);

        COMMON = commonSpecPair.getLeft();
        CLIENT = clientSpecPair.getLeft();
        COMMON_SPEC = commonSpecPair.getRight();
        CLIENT_SPEC = clientSpecPair.getRight();
    }

    public static void register(ModContainer container) {
        container.registerConfig(ModConfig.Type.COMMON, COMMON_SPEC);
        container.registerConfig(ModConfig.Type.CLIENT, CLIENT_SPEC);
    }

}
