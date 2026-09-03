package galena.oreganized.plumbum.index;

import galena.oreganized.OConstants;
import galena.oreganized.plumbum.client.extensions.MoltenLeadClientExtensions;
import galena.oreganized.plumbum.world.fluid.MoltenLeadFluid;
import galena.oreganized.register.SimpleRegistryHelper;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.common.SoundActions;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;

@Mod(OConstants.MOD_ID)
@EventBusSubscriber(Dist.CLIENT)
public class PlumbumFluids {

    private static final SimpleRegistryHelper<Fluid> FLUIDS = OConstants.REGISTRY_HELPER.getFluidSubHelper();
    private static final SimpleRegistryHelper<FluidType> TYPES = OConstants.REGISTRY_HELPER.getFluidTypeSubHelper();

    public static final DeferredHolder<FluidType, FluidType> MOLTEN_LEAD_TYPE = TYPES.create("molten_lead", id -> new FluidType(FluidType.Properties.create()
            .descriptionId(id.toLanguageKey("block"))
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

    public static final DeferredHolder<Fluid, FlowingFluid> MOLTEN_LEAD = FLUIDS.create("molten_lead", $ -> new MoltenLeadFluid(PlumbumFluids.MOLTEN_LEAD_PROPERTIES));

    private static final BaseFlowingFluid.Properties MOLTEN_LEAD_PROPERTIES = new BaseFlowingFluid.Properties(MOLTEN_LEAD_TYPE, MOLTEN_LEAD, MOLTEN_LEAD)
            .bucket(PlumbumItems.MOLTEN_LEAD_BUCKET)
            .block(PlumbumBlocks.MOLTEN_LEAD)
            .tickRate(30);

    @SubscribeEvent
    public static void registerClientExtensions(RegisterClientExtensionsEvent event) {
        event.registerFluidType(new MoltenLeadClientExtensions(), MOLTEN_LEAD_TYPE);
    }

}
