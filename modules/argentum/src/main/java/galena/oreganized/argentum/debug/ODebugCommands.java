package galena.oreganized.argentum.debug;

import galena.oreganized.OConstants;
import net.minecraft.commands.Commands;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

@EventBusSubscriber
public class ODebugCommands {

    @SubscribeEvent
    private static void register(RegisterCommandsEvent event) {
        event.getDispatcher().register(
                Commands.literal(OConstants.MOD_ID)
                        .then(TarnishCommand.create())
                        .then(PolishCommand.create())
        );
    }

}
