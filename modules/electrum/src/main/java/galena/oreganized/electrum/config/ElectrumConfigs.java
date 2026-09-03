package galena.oreganized.electrum.config;

import galena.oreganized.config.OreganizedConfigs;
import net.neoforged.fml.config.ModConfig;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public class ElectrumConfigs {

    public static ElectrumConfigModule.Common COMMON = OreganizedConfigs.configModule(ModConfig.Type.COMMON, ElectrumConfigModule.CONFIG_KEY);

}
