package galena.oreganized.client.render;

import net.minecraft.network.chat.Component;

public interface ComponentRenderer {
    void draw(Component component, int x);

    int offsetCentered(Component component);
}
