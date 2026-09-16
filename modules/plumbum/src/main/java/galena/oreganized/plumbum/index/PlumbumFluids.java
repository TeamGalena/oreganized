package galena.oreganized.plumbum.index;

import galena.oreganized.OConstants;
import galena.oreganized.plumbum.client.extensions.MoltenLeadClientExtensions;
import galena.oreganized.plumbum.config.PlumbumConfigs;
import galena.oreganized.plumbum.world.MeltingCauldronInteractions;
import galena.oreganized.plumbum.world.block.LeadOreBlock;
import galena.oreganized.plumbum.world.block.MoltenLeadCauldronBlock;
import galena.oreganized.plumbum.world.fluid.MoltenLeadFluid;
import galena.oreganized.register.SimpleRegistryHelper;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.common.SoundActions;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.fluids.FluidInteractionRegistry;
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
    private static void registerClientExtensions(RegisterClientExtensionsEvent event) {
        event.registerFluidType(new MoltenLeadClientExtensions(), MOLTEN_LEAD_TYPE);
    }

    @SubscribeEvent
    private static void registerFluidInteractions(FMLCommonSetupEvent event) {
        FluidInteractionRegistry.addInteraction(PlumbumFluids.MOLTEN_LEAD_TYPE.get(), new FluidInteractionRegistry.InteractionInformation(
                (level, pos, relativePos, fluidState) -> level.getFluidState(relativePos).is(FluidTags.WATER) && fluidState.isSource(),
                fluidState -> PlumbumBlocks.LEAD_BLOCK.get().defaultBlockState()
        ));

        FluidInteractionRegistry.addInteraction(PlumbumFluids.MOLTEN_LEAD_TYPE.get(), new FluidInteractionRegistry.InteractionInformation(
                (level, blockPos, relativePos, fluidState) -> level.getFluidState(relativePos).is(FluidTags.LAVA) && fluidState.isSource(),
                (level, pos, relativePos, fluidState) -> {
                    LeadOreBlock.spawnCloud(level, pos, 2F);
                    level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
                    level.levelEvent(1501, pos, 0);
                }
        ));
    }

    @SubscribeEvent
    private static void registerCauldronInteractions(FMLCommonSetupEvent event) {
        var EMPTY = CauldronInteraction.EMPTY.map();
        var WATER = CauldronInteraction.WATER.map();
        var LAVA = CauldronInteraction.LAVA.map();
        var POWDER_SNOW = CauldronInteraction.POWDER_SNOW.map();
        var LEAD = MoltenLeadCauldronBlock.INTERACTION_MAP.map();

        EMPTY.put(PlumbumItems.MOLTEN_LEAD_BUCKET.get(), MeltingCauldronInteractions.fillMoltenLead());
        WATER.put(PlumbumItems.MOLTEN_LEAD_BUCKET.get(), MeltingCauldronInteractions.fillMoltenLead());
        LAVA.put(PlumbumItems.MOLTEN_LEAD_BUCKET.get(), MeltingCauldronInteractions.fillMoltenLead());
        POWDER_SNOW.put(PlumbumItems.MOLTEN_LEAD_BUCKET.get(), MeltingCauldronInteractions.fillMoltenLead());
        LEAD.put(PlumbumItems.MOLTEN_LEAD_BUCKET.get(), MeltingCauldronInteractions.fillMoltenLead());

        if (PlumbumConfigs.COMMON.cauldronLeadMelting.get()) {
            EMPTY.put(PlumbumBlocks.LEAD_BLOCK.get().asItem(), MeltingCauldronInteractions.placeLeadBlock());
            WATER.put(PlumbumBlocks.LEAD_BLOCK.get().asItem(), MeltingCauldronInteractions.placeLeadBlock());
            LAVA.put(PlumbumBlocks.LEAD_BLOCK.get().asItem(), MeltingCauldronInteractions.placeLeadBlock());
            POWDER_SNOW.put(PlumbumBlocks.LEAD_BLOCK.get().asItem(), MeltingCauldronInteractions.placeLeadBlock());
        }

        LEAD.put(Items.AIR, MeltingCauldronInteractions.dropResource(PlumbumBlocks.LEAD_BLOCK.toStack(), state -> state.getValue(MoltenLeadCauldronBlock.AGE) == 0, SoundEvents.ITEM_FRAME_REMOVE_ITEM));
        LEAD.put(Items.BUCKET, (state, world, pos, player, hand, stack) ->
                CauldronInteraction.fillBucket(state, world, pos, player, hand, stack, PlumbumItems.MOLTEN_LEAD_BUCKET.toStack(), blockState -> state.getValue(MoltenLeadCauldronBlock.AGE).equals(3), SoundEvents.BUCKET_FILL_LAVA)
        );
        LEAD.put(Items.MUSIC_DISC_11, MeltingCauldronInteractions.convertItem(PlumbumItems.MUSIC_DISC_STRUCTURE.toStack(), it -> it.getValue(MoltenLeadCauldronBlock.AGE) == 3));

        CauldronInteraction.addDefaultInteractions(LEAD);
    }

}
