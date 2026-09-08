package galena.oreganized.electrum.world.item;

import galena.oreganized.OConstants;
import galena.oreganized.client.render.AdditionalHighlightEvent;
import galena.oreganized.electrum.accessor.IMotionHolder;
import galena.oreganized.electrum.index.ElectrumCriterionTriggers;
import galena.oreganized.electrum.index.ElectrumItems;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;

@EventBusSubscriber
public class SpeedometerItem extends Item {

    public static final ResourceLocation PROPERTY_KEY = OConstants.modLoc("level");

    public SpeedometerItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (player.getCooldowns().isOnCooldown(ElectrumItems.SPEEDOMETER.get())) {
            return super.use(level, player, hand);
        }

        if (player instanceof ServerPlayer serverPlayer && player.getDeltaMovement().y < (-3.5)) {
            ElectrumCriterionTriggers.TERMINAL_VELOCITY.get().trigger(serverPlayer);
        }

        player.getCooldowns().addCooldown(ElectrumItems.SPEEDOMETER.get(), 40);
        if (hand == InteractionHand.MAIN_HAND) {
            AdditionalHighlightEvent.resetHighlightTimer(level);
        }

        return super.use(level, player, hand);
    }

    @SubscribeEvent
    public static void renderHighlight(AdditionalHighlightEvent event) {
        if (event.getStack().is(ElectrumItems.SPEEDOMETER.value()) && event.getPlayer().getRootVehicle() instanceof IMotionHolder motionHolder) {
            long time = event.getLevel().getGameTime();
            double speed = motionHolder.oreganised$getMotion();
            var formattedSpeed = String.format("%.2f", speed);
            var tooltip = Component.empty().append(Component.translatable("tooltip.oreganized.speed", formattedSpeed))
                    .withStyle((style) -> style.withColor(0xc7bf81));
            var arrow = Component.empty().append("->")
                    .withStyle((style) -> style.withColor(0xebe198));

            var x = event.offsetCentered(tooltip);
            event.draw(tooltip, x);
            event.draw(arrow, x - 20 + (int) (Math.cos(time / 10f) * 5));
            // var x = (screenWidth - font.width(tooltip)) / 2;
            // graphics.drawString(font, tooltip, x, y - 12, color);
            // graphics.drawString(font, arrow, x - 20 + (int) (Math.cos(time / 10f) * 5), y - 12, color);
        }
    }

}
