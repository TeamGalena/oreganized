package galena.oreganized.debug;

import static net.minecraft.commands.Commands.argument;
import static net.minecraft.commands.Commands.literal;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import galena.oreganized.argentum.world.TarnishBlockManager;
import galena.oreganized.argentum.world.TarnishEntityManager;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.arguments.coordinates.BlockPosArgument;

public class TarnishCommand {

    public static LiteralArgumentBuilder<CommandSourceStack> create() {
        return literal("tarnish")
                .then(literal("block")
                        .then(argument("pos", BlockPosArgument.blockPos())
                                .executes(TarnishCommand::executeOnBlock)
                        )
                ).then(literal("entity")
                        .then(argument("selector", EntityArgument.entities())
                                .executes(TarnishCommand::executeOnEntity)
                        )
                );
    }

    private static int executeOnBlock(CommandContext<CommandSourceStack> context) {
        var pos = BlockPosArgument.getBlockPos(context, "pos");
        var level = context.getSource().getLevel();

        var success = TarnishBlockManager.tryTarnishing(pos, level);

        return success ? 1 : 0;
    }

    private static int executeOnEntity(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        var entities = EntityArgument.getEntities(context, "selector");

        var successes = entities.stream()
                .filter(TarnishEntityManager::tryTarnish)
                .count();

        return Math.toIntExact(successes);
    }

}
