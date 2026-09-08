package galena.oreganized.device.index;

import galena.oreganized.OConstants;
import galena.oreganized.register.DataComponentRegistryHelper;
import net.minecraft.core.component.DataComponentType;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredHolder;

@Mod(OConstants.MOD_ID)
public class DeviceDataComponents {

    private static final DataComponentRegistryHelper DATA_COMPONENTS = OConstants.REGISTRY_HELPER.getDataComponentSubHelper();

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> DEVICE_VALUE = DATA_COMPONENTS.positiveInteger("value");


}
