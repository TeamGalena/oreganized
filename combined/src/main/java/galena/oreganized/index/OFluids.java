package galena.oreganized.index;

import galena.oreganized.Oreganized;
import galena.oreganized.content.fluid.MoltenLeadFluid;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.SoundActions;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class OFluids {

    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(Registries.FLUID, Oreganized.MOD_ID);
    public static final DeferredRegister<FluidType> TYPES = DeferredRegister.create(NeoForgeRegistries.FLUID_TYPES, Oreganized.MOD_ID);

    public static final DeferredHolder<FluidType, FluidType> MOLTEN_LEAD_TYPE = TYPES.register("molten_lead", () -> new FluidType(FluidType.Properties.create()
            .descriptionId("block.oreganized.molten_lead")
            .motionScale(0)
            .canExtinguish(false)
            .supportsBoating(false)
            .lightLevel(8)
            .density(2000)
            .temperature(1300)
            .viscosity(10000)
            .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY_LAVA)
            .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL_LAVA)
    ));

    public static final DeferredHolder<Fluid, FlowingFluid> MOLTEN_LEAD = FLUIDS.register("molten_lead", () -> new MoltenLeadFluid(OFluids.MOLTEN_LEAD_PROPERTIES));

    public static final BaseFlowingFluid.Properties MOLTEN_LEAD_PROPERTIES = new BaseFlowingFluid.Properties(MOLTEN_LEAD_TYPE, MOLTEN_LEAD, MOLTEN_LEAD).bucket(OItems.MOLTEN_LEAD_BUCKET).block(OBlocks.MOLTEN_LEAD).tickRate(30);

    public static void register(IEventBus modBus) {
        FLUIDS.register(modBus);
        TYPES.register(modBus);
    }

}
