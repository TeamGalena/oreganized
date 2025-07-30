package galena.oreganized.index;

import galena.oreganized.Oreganized;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.util.ExtraCodecs;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@EventBusSubscriber(modid = Oreganized.MOD_ID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class OComponents {

    private static final DeferredRegister<DataComponentType<?>> DATA_COMPONENTS = DeferredRegister.create(Registries.DATA_COMPONENT_TYPE, Oreganized.MOD_ID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> LEVEL = DATA_COMPONENTS.register("level", () ->
        DataComponentType.<Integer>builder()
                .persistent(ExtraCodecs.NON_NEGATIVE_INT)
                .networkSynchronized(ByteBufCodecs.VAR_INT)
                .build()
    );

}
