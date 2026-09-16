package galena.oreganized.client.render;

import galena.oreganized.accessor.GuiAccessor;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.ICancellableEvent;

public class AdditionalHighlightEvent extends Event implements ComponentRenderer, ICancellableEvent {

    public static void resetHighlightTimer(LevelReader level) {
        if (level != null && level.isClientSide()) {
            if (Minecraft.getInstance().gui instanceof GuiAccessor accessor) {
                accessor.oreganized$setToolHighlightTimer(60);
            }
        }
    }

    private final Level level;
    private final Player player;
    private final ComponentRenderer renderer;
    private final ItemStack stack;

    public AdditionalHighlightEvent(Level level, Player player, ItemStack stack, ComponentRenderer renderer) {
        this.level = level;
        this.player = player;
        this.renderer = renderer;
        this.stack = stack;
    }

    @Override
    public int offsetCentered(Component component) {
        return this.renderer.offsetCentered(component);
    }

    @Override
    public void draw(Component component, int x) {
        this.renderer.draw(component, x);
    }

    public void drawCentered(Component component, int xOffset) {
        draw(component, xOffset + offsetCentered(component));
    }

    public void drawCentered(Component component) {
        drawCentered(component, 0);
    }

    public ItemStack getStack() {
        return stack;
    }

    public Player getPlayer() {
        return player;
    }

    public Level getLevel() {
        return level;
    }
}
