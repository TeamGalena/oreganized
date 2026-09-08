package galena.oreganized.register;

import com.mojang.serialization.Codec;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ExtraCodecs;
import net.neoforged.neoforge.registries.DeferredHolder;

public class DataComponentRegistryHelper extends SimpleRegistryHelper<DataComponentType<?>> {

    public DataComponentRegistryHelper(RegistryHelper parent) {
        super(parent, Registries.DATA_COMPONENT_TYPE);
    }

    public <T> DeferredHolder<DataComponentType<?>, DataComponentType<T>> create(String name, Codec<T> persistent, StreamCodec<? super RegistryFriendlyByteBuf, T> network) {
        return create(name, $ -> DataComponentType.<T>builder()
                .networkSynchronized(network)
                .persistent(persistent)
                .build()
        );
    }

    public DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> integer(String name) {
        return create(name, Codec.INT, ByteBufCodecs.INT);
    }

    public DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> positiveInteger(String name) {
        return create(name, ExtraCodecs.NON_NEGATIVE_INT, ByteBufCodecs.VAR_INT);
    }

    public DeferredHolder<DataComponentType<?>, DataComponentType<Boolean>> bool(String name) {
        return create(name, Codec.BOOL, ByteBufCodecs.BOOL);
    }

}
