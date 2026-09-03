package galena.oreganized.index;

import com.mojang.serialization.Codec;
import galena.oreganized.OConstants;
import galena.oreganized.plumbum.index.PlumbumDataComponents;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.util.ExtraCodecs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ODataComponents {

    private static final DeferredRegister<DataComponentType<?>> DATA_COMPONENTS = DeferredRegister.create(Registries.DATA_COMPONENT_TYPE, OConstants.MOD_ID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> DEVICE_VALUE = DATA_COMPONENTS.register("value", () ->
            DataComponentType.<Integer>builder()
                    .networkSynchronized(ByteBufCodecs.VAR_INT)
                    .persistent(Codec.INT)
                    .build()
    );

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> MIRROR_LEVEL = DATA_COMPONENTS.register("mirror_level", () ->
            DataComponentType.<Integer>builder()
                    .persistent(ExtraCodecs.NON_NEGATIVE_INT)
                    .networkSynchronized(ByteBufCodecs.VAR_INT)
                    .build()
    );

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> HEAT_LEVEL = PlumbumDataComponents.HEAT_LEVEL;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Boolean>> LOCKED = PlumbumDataComponents.LOCKED;

    public static void register(IEventBus modBus) {
        DATA_COMPONENTS.register(modBus);
    }

}
