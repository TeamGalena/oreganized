package galena.oreganized;

import com.farcr.nomansland.common.registry.NMLParticleTypes;
import java.util.function.Supplier;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

@EventBusSubscriber
public class ModCompat {

    public static final String SHIELD_EXPANSION = "shieldexp";
    public static final String FARMERS_DELIGHT = "farmersdelight";
    public static final String NETHERS_DELIGHT = "nethersdelight";
    public static final String NO_MANS_LAND = "nomansland";
    public static final String CREATE = "create";
    public static final String QUARK = "quark";

    public static final boolean CREATE_LOADED = isLoaded(CREATE);
    public static final boolean PONDER_LOADED = isLoaded("ponder");
    public static final boolean DYE_DEPOT_LOADED = isLoaded("dye_depot");
    public static final boolean FARMERS_DELIGHT_LOADED = isLoaded(FARMERS_DELIGHT);
    public static final boolean NETHERS_DELIGHT_LOADED = isLoaded(NETHERS_DELIGHT);
    public static final boolean SHIELD_EXPANSION_LOADED = isLoaded(SHIELD_EXPANSION);
    public static final boolean NO_MANS_LAND_LOADED = isLoaded(NO_MANS_LAND);

    public static boolean isLoaded(String id) {
        return ModList.get().isLoaded(id);
    }

    private static Supplier<? extends ParticleOptions> spawnerFlameParticle = () -> ParticleTypes.FLAME;

    public static ParticleOptions createSpawnerFlame() {
        return spawnerFlameParticle.get();
    }

    @SubscribeEvent
    private static void setup(FMLCommonSetupEvent event) {
        if (NO_MANS_LAND_LOADED) {
            noMansLandCompat();
        }
    }

    private static void noMansLandCompat() {
        spawnerFlameParticle = NMLParticleTypes.MALEVOLENT_FLAME;
    }

}
