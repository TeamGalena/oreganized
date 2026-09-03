package galena.oreganized.client;

import galena.oreganized.OConstants;
import galena.oreganized.compat.ponder.PonderCompat;
import galena.oreganized.device.client.DevicePropertyFunction;
import galena.oreganized.electrum.accessor.IMotionHolder;
import galena.oreganized.electrum.world.item.SpeedometerItem;
import galena.oreganized.index.OBlocks;
import galena.oreganized.index.ODataComponents;
import galena.oreganized.index.OItems;
import galena.oreganized.plumbum.world.item.ThermometerItem;
import java.util.function.Supplier;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.util.Mth;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.ChargedProjectiles;
import net.minecraft.world.level.block.Block;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.event.AddPackFindersEvent;

@EventBusSubscriber(Dist.CLIENT)
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

        if (ModList.get().isLoaded("ponder")) {
            PonderCompat.register();
        }
    }

    @SubscribeEvent
    public static void addResourcePacks(AddPackFindersEvent event) {
        event.addPackFinders(
                OConstants.modLoc("resourcepacks/create_compat"),
                PackType.CLIENT_RESOURCES,
                Component.literal("Create Compat"),
                PackSource.BUILT_IN,
                false,
                Pack.Position.TOP
        );
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

        ItemProperties.register(Items.CROSSBOW, OConstants.modLoc("lead_bolt"), (stack, level, user, i) ->
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

}
