package galena.oreganized.antiques.data;

import static galena.oreganized.data.extensions.OBlockStateExtensions.*;
import static galena.oreganized.data.extensions.OItemModelExtensions.generatedItem;
import static net.neoforged.neoforge.client.model.generators.ModelProvider.ITEM_FOLDER;

import com.tterrag.registrate.providers.RegistrateBlockstateProvider;
import galena.oreganized.OConstants;
import galena.oreganized.antiques.index.AntiquesBlocks;
import galena.oreganized.data.ODatagen;
import net.minecraft.world.level.block.Block;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.registries.DeferredBlock;

@Mod(OConstants.MOD_ID)
public class AntiquesBlockStates {

    public AntiquesBlockStates() {
        ODatagen.addBlockStateProvider(this::generate);
    }

    private void generate(RegistrateBlockstateProvider provider) {
        antiquesPile(provider, AntiquesBlocks.ANTIQUES_PILE);
    }

    private void antiquesPile(RegistrateBlockstateProvider provider, DeferredBlock<Block> block) {
        var builder = provider.getVariantBuilder(block.value()).partialState();
        var texture = blockTexture(block.getId());

        for (int i = 1; i <= 5; i++) {
            var suffix = "_" + i;
            var model = provider.models().withExistingParent(
                    block.getId().getPath() + suffix,
                    blockTexture(block.getId().withPrefix("template/")).withSuffix(suffix)
            )
                    .texture("particle", texture.withSuffix("_particle"))
                    .texture("texture", texture)
                    .renderType(CUTOUT);

            horizontalDirections((direction, $) -> {
                builder.addModels(ConfiguredModel.builder()
                        .modelFile(model)
                        .rotationY((int) direction.toYRot())
                        .build());
            });
        }

        generatedItem(provider.itemModels(), block.getId(), block.getId(), ITEM_FOLDER);
    }

}
