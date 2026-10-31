package galena.oreganized;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.fml.ModList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OConstants {

    public static final String MOD_ID = "oreganized";

    public static final Logger LOGGER = LogManager.getLogger("Oreganized");

    public static ResourceLocation modLoc(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }

    public static final String PROTOCOL_VERSION = "3";

    public static boolean moduleLoaded(String module) {
        return ModList.get().isLoaded(MOD_ID + "_" + module);
    }

}
