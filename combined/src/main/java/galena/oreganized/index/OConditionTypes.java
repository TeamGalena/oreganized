package galena.oreganized.index;

import com.mojang.serialization.MapCodec;
import com.teamabnormals.blueprint.core.api.conditions.ConfigValueCondition;
import com.teamabnormals.blueprint.core.util.DataUtil;
import galena.oreganized.Oreganized;
import galena.oreganized.config.OreganizedConfigs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class OConditionTypes {
    private static final DeferredRegister<MapCodec<? extends ICondition>> HELPER = DeferredRegister.create(NeoForgeRegistries.CONDITION_SERIALIZERS, Oreganized.MOD_ID);

    public static final DeferredHolder<MapCodec<? extends ICondition>, ConfigValueCondition.Serializer> CONFIG = HELPER.register("config",
            () -> new ConfigValueCondition.Serializer(DataUtil.getConfigValues(
                    OreganizedConfigs.config(ModConfig.Type.COMMON).stream().toArray()
            ))
    );

    public static void register(IEventBus modBus) {
        HELPER.register(modBus);
    }

}
