package galena.oreganized.debug;

import static net.minecraft.commands.Commands.argument;
import static net.minecraft.commands.Commands.literal;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import galena.oreganized.world.TarnishManager;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.coordinates.BlockPosArgument;

public class PolishCommand {

    public static LiteralArgumentBuilder<CommandSourceStack> create() {
        return literal("polish")
                .then(literal("block")
                        .then(argument("pos", BlockPosArgument.blockPos())
                                .executes(PolishCommand::executeOnBlock)
                        )
                );
    }

    private static int executeOnBlock(CommandContext<CommandSourceStack> context) {
        var pos = BlockPosArgument.getBlockPos(context, "pos");
        var level = context.getSource().getLevel();

        var success = TarnishManager.tryPolishing(pos, level);

        return success ? 1 : 0;
    }

}
