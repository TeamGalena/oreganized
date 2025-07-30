package galena.oreganized.compat.create;

import com.simibubi.create.api.registry.CreateBuiltInRegistries;
import galena.oreganized.Oreganized;
import net.minecraftforge.eventbus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class CreateCompat {

    public static void register() {
        var interactionPointTypes = DeferredRegister.create(CreateBuiltInRegistries.ARM_INTERACTION_POINT_TYPE.key(), Oreganized.MOD_ID);

        interactionPointTypes.register("gargoyle", GargoyleArmPointType::new);

        IEventBus modBus = EventBusSubscriber.Bus.MOD.bus().get();
        interactionPointTypes.register(modBus);
    }

}
