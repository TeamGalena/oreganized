package galena.oreganized.index;

import galena.oreganized.argentum.index.ArgentumDataComponents;
import galena.oreganized.device.index.DeviceDataComponents;
import galena.oreganized.plumbum.index.PlumbumDataComponents;
import net.minecraft.core.component.DataComponentType;
import net.neoforged.neoforge.registries.DeferredHolder;

public class ODataComponents {

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> DEVICE_VALUE = DeviceDataComponents.DEVICE_VALUE;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> MIRROR_LEVEL = ArgentumDataComponents.MIRROR_LEVEL;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> HEAT_LEVEL = PlumbumDataComponents.HEAT_LEVEL;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Boolean>> LOCKED = PlumbumDataComponents.LOCKED;


}
