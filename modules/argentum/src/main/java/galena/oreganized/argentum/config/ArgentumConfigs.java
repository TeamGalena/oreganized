package galena.oreganized.argentum.config;

import galena.oreganized.config.OreganizedConfigs;
import net.neoforged.fml.config.ModConfig;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public class ArgentumConfigs {

    public static final ArgentumConfigModule.Common COMMON = OreganizedConfigs.configModule(ModConfig.Type.COMMON, ArgentumConfigModule.CONFIG_KEY);

}
