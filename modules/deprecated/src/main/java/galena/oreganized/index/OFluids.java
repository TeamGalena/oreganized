package galena.oreganized.index;

import galena.oreganized.plumbum.index.PlumbumFluids;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;

public class OFluids {

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredHolder<FluidType, FluidType> MOLTEN_LEAD_TYPE = PlumbumFluids.MOLTEN_LEAD_TYPE;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredHolder<Fluid, FlowingFluid> MOLTEN_LEAD = PlumbumFluids.MOLTEN_LEAD;

}
