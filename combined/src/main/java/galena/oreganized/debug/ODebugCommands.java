package galena.oreganized.debug;

import galena.oreganized.Oreganized;
import net.minecraft.commands.Commands;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

public class ODebugCommands {

    public static void register(RegisterCommandsEvent event) {
        event.getDispatcher().register(
                Commands.literal(Oreganized.MOD_ID)
                        .then(TarnishCommand.create())
                        .then(PolishCommand.create())
        );
    }

}
