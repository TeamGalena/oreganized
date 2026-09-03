package galena.oreganized.electrum.world.item;

import galena.oreganized.OConstants;
import galena.oreganized.accessor.GuiAccessor;
import galena.oreganized.electrum.index.ElectrumCriterionTriggers;
import galena.oreganized.electrum.index.ElectrumItems;
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
        if (player.getCooldowns().isOnCooldown(ElectrumItems.SPEEDOMETER.get())) {
            return super.use(level, player, hand);
        }

        if (player instanceof ServerPlayer serverPlayer && player.getDeltaMovement().y < (-3.5)) {
            ElectrumCriterionTriggers.TERMINAL_VELOCITY.get().trigger(serverPlayer);
        }

        player.getCooldowns().addCooldown(ElectrumItems.SPEEDOMETER.get(), 40);
        if (level.isClientSide() && hand == InteractionHand.MAIN_HAND) {
            if (Minecraft.getInstance().gui instanceof GuiAccessor accessor) {
                accessor.oreganized$setToolHighlightTimer(60);
            }
        }

        return super.use(level, player, hand);
    }


}
