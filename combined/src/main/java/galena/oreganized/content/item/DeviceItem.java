package galena.oreganized.content.item;

import galena.oreganized.Oreganized;
import galena.oreganized.client.accessors.GuiAccessor;
import galena.oreganized.client.tooltips.DeviceTooltip;
import galena.oreganized.index.ODataComponents;
import galena.oreganized.index.OItems;
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

@EventBusSubscriber(modid = Oreganized.MOD_ID)
public class DeviceItem extends Item {

    public static final int FRAMES = 10;
    public static final int TOOLTIP_COLOR = 0x8c2115;

    public DeviceItem(Properties properties) {
        super(properties);
    }

    private static void generateValue(ItemStack stack, RandomSource random) {
        stack.set(ODataComponents.DEVICE_VALUE, random.nextInt(999999));
    }

    private static void clearValue(ItemStack stack) {
        stack.remove(ODataComponents.DEVICE_VALUE);
    }

    public static Optional<Integer> getValue(ItemStack stack) {
        return Optional.ofNullable(stack.get(ODataComponents.DEVICE_VALUE));
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
        if (!stack.is(OItems.UNKNOWN_DEVICE.get())) return;
        event.getEntity().playSound(SoundEvents.AMETHYST_BLOCK_RESONATE, 1F, 1.5F);
        clearValue(stack);
    }

}
