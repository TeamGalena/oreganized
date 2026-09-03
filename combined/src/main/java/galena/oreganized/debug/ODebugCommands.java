package galena.oreganized.debug;

import net.minecraft.commands.Commands;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

public class ODebugCommands {

    public static void register(RegisterCommandsEvent event) {
        event.getDispatcher().register(
                Commands.literal(OConstants.MOD_ID)
                        .then(TarnishCommand.create())
                        .then(PolishCommand.create())
        );
    }

}
