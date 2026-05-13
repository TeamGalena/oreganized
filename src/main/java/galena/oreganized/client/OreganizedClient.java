package galena.oreganized.client;

import com.mojang.math.Axis;
import galena.oreganized.Oreganized;
import galena.oreganized.client.extensions.ElectrumArmorClientExtensions;
import galena.oreganized.client.extensions.MoltenLeadClientExtensions;
import galena.oreganized.client.render.entity.LeadBoltRender;
import galena.oreganized.client.render.entity.ShrapnelBombMinecartRender;
import galena.oreganized.client.render.entity.ShrapnelBombRender;
import galena.oreganized.client.render.gui.StunningOverlay;
import galena.oreganized.client.tooltips.ClientDeviceTooltip;
import galena.oreganized.client.tooltips.ClientThermometerTooltip;
import galena.oreganized.client.tooltips.DeviceTooltip;
import galena.oreganized.client.tooltips.ThermometerTooltip;
import galena.oreganized.content.item.SpeedometerItem;
import galena.oreganized.content.item.ThermometerItem;
import galena.oreganized.index.*;
import galena.oreganized.world.IDoorProgressHolder;
import galena.oreganized.world.IMotionHolder;
import java.util.List;
import java.util.function.Supplier;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.ChargedProjectiles;
import net.minecraft.world.level.block.Block;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterClientTooltipComponentFactoriesEvent;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.neoforged.neoforge.client.event.RenderHandEvent;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

@EventBusSubscriber(modid = Oreganized.MOD_ID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class OreganizedClient {

    private static void render(Supplier<? extends Block> block, RenderType render) {
        ItemBlockRenderTypes.setRenderLayer(block.get(), render);
    }

    @SubscribeEvent
    public static void setup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            OreganizedClient.registerBlockRenderers();
            OreganizedClient.registerItemProperties();
        });
    }

    private static void registerItemProperties() {
        ItemProperties.register(OItems.SILVER_MIRROR.get(), ODataComponents.MIRROR_LEVEL.getId(), (stack, world, entity, seed) ->
                stack.getOrDefault(ODataComponents.MIRROR_LEVEL.get(), 8)
        );

        ItemProperties.register(OItems.SPEEDOMETER.get(), SpeedometerItem.PROPERTY_KEY, (stack, world, entity, seed) -> {
            if (entity == null) return 0;
            var vehicle = entity.getRootVehicle();
            if (!(vehicle instanceof IMotionHolder motionHolder)) return 0;
            return Mth.clamp(Math.round(motionHolder.oreganised$getMotion() * 100), 0, 16);
        });

        ItemProperties.register(OItems.THERMOMETER.get(), ODataComponents.HEAT_LEVEL.getId(), (stack, world, entity, seed) -> {
            return ThermometerItem.getHeatLevel(stack);
        });

        ItemProperties.register(Items.CROSSBOW, Oreganized.modLoc("lead_bolt"), (stack, level, user, i) ->
                stack.getOrDefault(DataComponents.CHARGED_PROJECTILES, ChargedProjectiles.EMPTY).contains(OItems.LEAD_BOLT.get()) ? 1.0F : 0.0F
        );

        ItemProperties.register(OItems.ELECTRUM_SHIELD.get(), ResourceLocation.withDefaultNamespace("blocking"), (stack, level, user, i) ->
                user != null && user.isUsingItem() && user.getUseItem() == stack ? 1.0F : 0.0F
        );

        ItemProperties.register(OItems.UNKNOWN_DEVICE.get(), ODataComponents.DEVICE_VALUE.getId(), new DevicePropertyFunction());
    }

    private static void registerBlockRenderers() {
        RenderType cutout = RenderType.cutout();
        RenderType translucent = RenderType.translucent();

        OBlocks.SILVER_DOORS.all().forEach(deferredBlock -> render(deferredBlock, cutout));
        OBlocks.SILVER_TRAPDOORS.all().forEach(deferredBlock -> render(deferredBlock, cutout));
        render(OBlocks.LEAD_DOOR, cutout);
        render(OBlocks.LEAD_TRAPDOOR, cutout);
        render(OBlocks.LEAD_BARS, cutout);
        render(OBlocks.GARGOYLE, cutout);
        render(OBlocks.WHITE_DATURA, cutout);
        render(OBlocks.PURPLE_DATURA, cutout);
        render(OBlocks.POTTED_WHITE_DATURA, cutout);
        render(OBlocks.POTTED_PURPLE_DATURA, cutout);
        OBlocks.CRYSTAL_GLASS.forEach((c, b) -> render(b, translucent));
        OBlocks.CRYSTAL_GLASS_PANES.forEach((c, b) -> render(b, translucent));
        OBlocks.SILVER_BARS.all().forEach((b) -> render(b, translucent));

        render(OBlocks.GROOVED_ICE, translucent);
    }

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(OEntityTypes.SHRAPNEL_BOMB.value(), ShrapnelBombRender::new);
        event.registerEntityRenderer(OEntityTypes.SHRAPNEL_BOMB_MINECART.get(), ShrapnelBombMinecartRender::new);
        event.registerEntityRenderer(OEntityTypes.LEAD_BOLT.get(), LeadBoltRender::new);
    }

    @SubscribeEvent
    public static void registerGuiOverlays(RegisterGuiLayersEvent event) {
        event.registerAbove(VanillaGuiLayers.EFFECTS, Oreganized.modLoc("stunning"), new StunningOverlay());
    }

    public static void renderThirdPersonArm(ModelPart arm, boolean rightArm) {
        arm.xRot = -1.7F;
        arm.yRot = rightArm ? -0.1F : 0.2F;
    }

    @SubscribeEvent
    public static void registerClientTooltips(RegisterClientTooltipComponentFactoriesEvent event) {
        event.register(ThermometerTooltip.class, ClientThermometerTooltip::new);
        event.register(DeviceTooltip.class, ClientDeviceTooltip::new);
    }

    @SubscribeEvent
    public static void registerClientExtensions(RegisterClientExtensionsEvent event) {
        event.registerFluidType(new MoltenLeadClientExtensions(), OFluids.MOLTEN_LEAD_TYPE);
        event.registerItem(new ElectrumArmorClientExtensions(), OItems.ELECTRUM_HELMET, OItems.ELECTRUM_CHESTPLATE, OItems.ELECTRUM_LEGGINGS, OItems.ELECTRUM_BOOTS);
    }

    @EventBusSubscriber(modid = Oreganized.MOD_ID, value = Dist.CLIENT)
    public static class ForgeBusEvents {

        @SubscribeEvent
        public static void addTooltips(ItemTooltipEvent event) {
            if (event.getItemStack().is(OItems.BUSH_HAMMER.get())) {
                List<Component> tooltip = event.getToolTip();
                MutableComponent wipTitle = Component.translatable("tooltip.oreganized.wip.title");
                MutableComponent wipDesc = Component.translatable("tooltip.oreganized.wip.description");

                tooltip.add(wipTitle.withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.BOLD));
                tooltip.add(wipDesc.withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC));
            }
        }

        @SubscribeEvent
        public static void renderHand(RenderHandEvent event) {
            var player = Minecraft.getInstance().player;
            //TODO: might want to use attachments here instead
            if (!(player instanceof IDoorProgressHolder progressHolder)) return;
            var progress = progressHolder.oreganised$getOpeningProgress();
            if (progress == 0) return;
            if (event.getHand() == InteractionHand.OFF_HAND) return;

            var poseStack = event.getPoseStack();

            poseStack.pushPose();

            //TODO: also would be nice if the hand was slightly animate to indicate opening progress
            var rightArm = player.getMainArm() == HumanoidArm.RIGHT;
            float factor = rightArm ? 1.0F : -1.0F;
            poseStack.translate(factor * 0.84000005F, -0.4F, -0.4F);
            poseStack.mulPose(Axis.YP.rotationDegrees(factor * -20F - event.getSwingProgress()));
            poseStack.mulPose(Axis.ZP.rotationDegrees(factor * 45F));
            poseStack.mulPose(Axis.XP.rotationDegrees(-45F));

            var renderer = (PlayerRenderer) Minecraft.getInstance().getEntityRenderDispatcher().getRenderer(player);

            if (rightArm) {
                renderer.renderRightHand(poseStack, event.getMultiBufferSource(), event.getPackedLight(), player);
            } else {
                renderer.renderLeftHand(poseStack, event.getMultiBufferSource(), event.getPackedLight(), player);
            }

            poseStack.popPose();

            event.setCanceled(true);
        }

    }

}
