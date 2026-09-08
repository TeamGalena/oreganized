package galena.oreganized;

import net.neoforged.fml.ModList;

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

    public static boolean isLoaded(String id) {
        return ModList.get().isLoaded(id);
    }

}
