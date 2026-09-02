package galena.oreganized.index;

import galena.oreganized.Oreganized;
import java.util.List;
import net.minecraft.client.gui.Gui;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.asm.enumextension.EnumProxy;
import net.neoforged.neoforge.event.entity.player.PlayerHeartTypeEvent;

@EventBusSubscriber(modid = Oreganized.MOD_ID, value = Dist.CLIENT)
public class OHeartTypes {

    public static final EnumProxy<Gui.HeartType> STUNNED = new EnumProxy<>(
            Gui.HeartType.class, parameters("stunned")
    );

    public static final EnumProxy<Gui.HeartType> STUNNED_POISONED = new EnumProxy<>(
            Gui.HeartType.class, parameters("stunned_poisoned")
    );

    private static List<Object> parameters(String type) {
        return List.of(
                Oreganized.modLoc("hud/heart/" + type + "_full"),
                Oreganized.modLoc("hud/heart/" + type + "_full_blinking"),
                Oreganized.modLoc("hud/heart/" + type + "_half"),
                Oreganized.modLoc("hud/heart/" + type + "_half_blinking"),
                Oreganized.modLoc("hud/heart/" + type + "_full_hardcore"),
                Oreganized.modLoc("hud/heart/" + type + "_full_hardcore_blinking"),
                Oreganized.modLoc("hud/heart/" + type + "_half_hardcore"),
                Oreganized.modLoc("hud/heart/" + type + "_half_hardcore_blinking")
        );
    }

    @SubscribeEvent
    public static void modifyHeartType(PlayerHeartTypeEvent event) {
        if (event.getEntity().hasEffect(OEffects.STUNNING)) {
            if (event.getOriginalType() == Gui.HeartType.NORMAL) event.setType(STUNNED.getValue());
            if (event.getOriginalType() == Gui.HeartType.POISIONED) event.setType(STUNNED_POISONED.getValue());
        }
    }

}
