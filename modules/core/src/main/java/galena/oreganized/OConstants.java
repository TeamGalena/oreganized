package galena.oreganized;

import galena.oreganized.register.ORegistryHelper;
import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OConstants {

    public static final String MOD_ID = "oreganized";

    public static final Logger LOGGER = LogManager.getLogger("Oreganized");

    public static ResourceLocation modLoc(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }

    public static final ORegistryHelper REGISTRY_HELPER = ORegistryHelper.createRegistryHelper();

}
