package galena.oreganized.compat.create;

import com.simibubi.create.api.registry.CreateBuiltInRegistries;
import galena.oreganized.Oreganized;
import galena.oreganized.api.LeadProtections;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;

public class CreateCompat {

    public static void register() {
        var interactionPointTypes = DeferredRegister.create(CreateBuiltInRegistries.ARM_INTERACTION_POINT_TYPE.key(), Oreganized.MOD_ID);

        interactionPointTypes.register("gargoyle", GargoyleArmPointType::new);

        IEventBus modBus = Mod.EventBusSubscriber.Bus.MOD.bus().get();
        interactionPointTypes.register(modBus);

        LeadProtections.register(new CreateArmorProtection());
    }

}
