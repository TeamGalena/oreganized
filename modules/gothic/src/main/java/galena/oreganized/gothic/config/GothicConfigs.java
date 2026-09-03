package galena.oreganized.gothic.config;

import galena.oreganized.config.OreganizedConfigs;
import net.neoforged.fml.config.ModConfig;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public class GothicConfigs {

    public static GothicConfigModule.Common COMMON = OreganizedConfigs.configModule(ModConfig.Type.COMMON, GothicConfigModule.CONFIG_KEY);

}
