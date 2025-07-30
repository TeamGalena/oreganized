package galena.oreganized.content.item;

import galena.oreganized.Oreganized;
import galena.oreganized.client.accessors.GuiAccessor;
import galena.oreganized.client.tooltips.DeviceTooltip;
import galena.oreganized.index.OItems;
import java.util.Optional;
import java.util.OptionalInt;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

@EventBusSubscriber(modid = Oreganized.MOD_ID)
public class DeviceItem extends Item {

    public static final int FRAMES = 10;
    public static final ResourceLocation PROPERTY_KEY = Oreganized.modLoc("device_value");
    public static final int TOOLTIP_COLOR = 0x8c2115;

    public DeviceItem(Properties properties) {
        super(properties);
    }

    private static void generateValue(ItemStack stack, RandomSource random) {
        stack.getOrCreateTag().putInt("Value", random.nextInt(999999));
    }

    private static void clearValue(ItemStack stack) {
        stack.getOrCreateTag().remove("Value");
    }

    public static OptionalInt getValue(ItemStack stack) {
        if (!stack.hasTag() || !stack.getOrCreateTag().contains("Value")) return OptionalInt.empty();
        return OptionalInt.of(stack.getOrCreateTag().getInt("Value"));
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
                .<TooltipComponent>mapToObj(DeviceTooltip::new)
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
