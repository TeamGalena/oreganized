package galena.oreganized.content.item;

import galena.oreganized.client.accessors.GuiAccessor;
import galena.oreganized.index.OCriteriaTriggers;
import galena.oreganized.index.OItems;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class SpeedometerItem extends Item {

    public static final ResourceLocation PROPERTY_KEY = OConstants.modLoc("level");

    public SpeedometerItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (player.getCooldowns().isOnCooldown(OItems.SPEEDOMETER.get())) {
            return super.use(level, player, hand);
        }

        if (player instanceof ServerPlayer serverPlayer && player.getDeltaMovement().y < (-3.5)) {
            OCriteriaTriggers.TERMINAL_VELOCITY.get().trigger(serverPlayer);
        }

        player.getCooldowns().addCooldown(OItems.SPEEDOMETER.get(), 40);
        if (level.isClientSide() && hand == InteractionHand.MAIN_HAND) {
            if (Minecraft.getInstance().gui instanceof GuiAccessor accessor) {
                accessor.oreganized$setToolHighlightTimer(60);
            }
        }

        return super.use(level, player, hand);
    }


}
