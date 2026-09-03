package galena.oreganized.device.world.item;

import galena.oreganized.accessor.GuiAccessor;
import galena.oreganized.device.client.DeviceTooltip;
import galena.oreganized.device.index.DeviceDataComponents;
import galena.oreganized.device.index.DeviceItems;

import java.util.Optional;

import net.minecraft.client.Minecraft;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

@EventBusSubscriber
public class DeviceItem extends Item {

    public static final int FRAMES = 10;
    public static final int TOOLTIP_COLOR = 0x8c2115;

    public DeviceItem(Properties properties) {
        super(properties);
    }

    private static void generateValue(ItemStack stack, RandomSource random) {
        stack.set(DeviceDataComponents.DEVICE_VALUE, random.nextInt(999999));
    }

    private static void clearValue(ItemStack stack) {
        stack.remove(DeviceDataComponents.DEVICE_VALUE);
    }

    public static Optional<Integer> getValue(ItemStack stack) {
        return Optional.ofNullable(stack.get(DeviceDataComponents.DEVICE_VALUE));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        var stack = player.getItemInHand(hand);
        if (getValue(stack).isPresent()) return super.use(level, player, hand);
        generateValue(stack, player.getRandom());
        player.playSound(SoundEvents.LODESTONE_COMPASS_LOCK, 1F, 1.5F);
        if (level.isClientSide()) {
            if (Minecraft.getInstance().gui instanceof GuiAccessor accessor) {
                accessor.oreganized$setToolHighlightTimer(60);
            }
        }
        return InteractionResultHolder.success(stack);
    }

    @Override
    public Optional<TooltipComponent> getTooltipImage(ItemStack stack) {
        return getValue(stack).stream()
                .<TooltipComponent>map(DeviceTooltip::new)
                .findAny();
    }

    @SubscribeEvent
    public static void onHitAir(PlayerInteractEvent.LeftClickEmpty event) {
        var stack = event.getItemStack();
        if (!stack.is(DeviceItems.UNKNOWN_DEVICE.get())) return;
        event.getEntity().playSound(SoundEvents.AMETHYST_BLOCK_RESONATE, 1F, 1.5F);
        clearValue(stack);
    }

}
