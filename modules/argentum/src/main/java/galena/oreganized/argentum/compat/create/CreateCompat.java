package galena.oreganized.argentum.compat.create;

import com.simibubi.create.api.registry.CreateBuiltInRegistries;
import galena.oreganized.OConstants;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class CreateCompat {

    public static void register(IEventBus modBus) {
        var fanProcessingTypes = DeferredRegister.create(CreateBuiltInRegistries.FAN_PROCESSING_TYPE.key(), OConstants.MOD_ID);

        fanProcessingTypes.register("tarnishing", TarnishingFanProcessingType::new);

        fanProcessingTypes.register(modBus);
    }

}
