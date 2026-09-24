package galena.oreganized.argentum.data;

import static galena.oreganized.data.extensions.OBlockStateExtensions.*;

import galena.oreganized.OConstants;
import galena.oreganized.argentum.index.ArgentumBlocks;
import galena.oreganized.data.ODatagen;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RedstoneLampBlock;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.registries.DeferredHolder;

@Mod(OConstants.MOD_ID)
public class ArgentumBlockStates {

    public ArgentumBlockStates() {
        ODatagen.addBlockStateProvider(this::generate);
    }

    private void generate(BlockStateProvider provider) {
        cubeAll(provider, ArgentumBlocks.SILVER_ORE);
        cubeAll(provider, ArgentumBlocks.DEEPSLATE_SILVER_ORE);
        cubeAll(provider, ArgentumBlocks.RAW_SILVER_BLOCK);

        ArgentumBlocks.SILVER_BULBS.stream().forEach(it -> lamp(provider, it));
        ArgentumBlocks.CUT_SILVERS.stream().forEach(it -> cubeAll(provider, it));
        ArgentumBlocks.SILVER_LATTICES.stream().forEach(it -> cubeAll(provider, it));
        ArgentumBlocks.SILVER_BLOCKS.stream().forEach(it -> cubeAll(provider, it));
        ArgentumBlocks.SILVER_BARS.stream().forEach(it -> bars(provider, it));
        ArgentumBlocks.SILVER_PILLARS.stream().forEach(it -> pillar(provider, it));
        ArgentumBlocks.CHISELED_SILVER.stream().forEach(it -> cubeAll(provider, it));
        ArgentumBlocks.CUT_SILVER_SLABS.indexed().forEach(it -> slab(provider, ArgentumBlocks.CUT_SILVERS.get(it.getSecond()), it.getFirst()));
        ArgentumBlocks.CUT_SILVER_STAIRS.indexed().forEach(it -> stairs(provider, ArgentumBlocks.CUT_SILVERS.get(it.getSecond()), it.getFirst()));
        ArgentumBlocks.SILVER_DOORS.stream().forEach(it -> door(provider, it));
        ArgentumBlocks.SILVER_TRAPDOORS.stream().forEach(it -> trapDoor(provider, it));

        provider.simpleBlockWithItem(
                ArgentumBlocks.GROOVED_ICE.value(),
                provider.models().cubeAll(ArgentumBlocks.GROOVED_ICE.getId().getPath(), blockTexture(ArgentumBlocks.GROOVED_ICE.getId()))
                        .renderType(TRANSLUCENT)
        );
        cubeAll(provider, ArgentumBlocks.GROOVED_PACKED_ICE);
        cubeAll(provider, ArgentumBlocks.GROOVED_BLUE_ICE);
    }

    private static void lamp(BlockStateProvider provider, DeferredHolder<Block, ? extends Block> block) {
        var name = block.getId().getPath();
        provider.getVariantBuilder(block.value()).forAllStates(state -> {
            boolean lit = state.getValue(RedstoneLampBlock.LIT);
            var modelName = lit ? name + "_on" : name;
            var texture = provider.blockTexture(block.value()).withSuffix(lit ? "_on" : "_off");
            var model = provider.models().cubeAll(modelName, texture);
            return ConfiguredModel.builder().modelFile(model).build();
        });

        blockItem(provider, block);
    }

}
