package galena.oreganized.client;

import galena.oreganized.OConstants;

import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.AddPackFindersEvent;

@EventBusSubscriber(Dist.CLIENT)
public class OResourcePacks {

    public static final String CREATE_COMPAT = "resourcepacks/create_compat";

    @SubscribeEvent
    private static void addResourcePacks(AddPackFindersEvent event) {
        event.addPackFinders(
                OConstants.modLoc(CREATE_COMPAT),
                PackType.CLIENT_RESOURCES,
                Component.literal("Create Compat"),
                PackSource.BUILT_IN,
                false,
                Pack.Position.TOP
        );
    }

}
