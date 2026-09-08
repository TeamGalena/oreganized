package galena.oreganized.plumbum.config;

import galena.oreganized.config.OreganizedConfigs;
import net.neoforged.fml.config.ModConfig;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public class PlumbumConfigs {

    public static final PlumbumConfigModule.Common COMMON = OreganizedConfigs.configModule(ModConfig.Type.COMMON, PlumbumConfigModule.CONFIG_KEY);
    public static final PlumbumConfigModule.Client CLIENT = OreganizedConfigs.configModule(ModConfig.Type.CLIENT, PlumbumConfigModule.CONFIG_KEY);

}
