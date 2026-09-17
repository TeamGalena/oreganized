package galena.oreganized.device.data;

import static galena.oreganized.data.extensions.OBlockStateExtensions.*;
import static galena.oreganized.data.extensions.OItemModelExtensions.generatedItem;
import static net.minecraft.resources.ResourceLocation.fromNamespaceAndPath;
import static net.neoforged.neoforge.client.model.generators.ModelProvider.BLOCK_FOLDER;
import static net.neoforged.neoforge.client.model.generators.ModelProvider.ITEM_FOLDER;

import galena.oreganized.OConstants;
import galena.oreganized.data.ColorCompat;
import galena.oreganized.data.ODatagen;
import galena.oreganized.gothic.index.GothicBlocks;
import galena.oreganized.gothic.world.block.CrystalGlassBlock;
import galena.oreganized.gothic.world.block.CrystalGlassPaneBlock;
import galena.oreganized.gothic.world.block.GargoyleBlock;
import net.minecraft.core.Direction;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.registries.DeferredBlock;

@Mod(OConstants.MOD_ID)
public class GothicBlockStates {

    public GothicBlockStates() {
        ODatagen.addBlockStateProvider(this::generate);
    }

    private void generate(BlockStateProvider provider) {
        gargoyleBlock(provider, GothicBlocks.GARGOYLE);

        GothicBlocks.CRYSTAL_GLASS.forEach((color, block) -> crystalGlassBlock(provider, block));
        GothicBlocks.CRYSTAL_GLASS_PANES.forEach((color, block) -> crystalGlassPaneBlock(provider, color, block, GothicBlocks.CRYSTAL_GLASS.get(color)));
    }

    private static void gargoyleBlock(BlockStateProvider provider, DeferredBlock<? extends Block> block) {
        var texture = provider.blockTexture(block.value());
        var floorModel = provider.models().getExistingFile(texture);
        var wallModel = provider.models().getExistingFile(texture.withSuffix("_side"));

        provider.getVariantBuilder(block.value()).forAllStatesExcept(state -> {
            var facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            var attachment = state.getValue(GargoyleBlock.ATTACHMENT);

            return ConfiguredModel.builder()
                    .rotationY((int) (facing.toYRot() % 360))
                    .modelFile(attachment == GargoyleBlock.AttachmentType.WALL ? wallModel : floorModel)
                    .build();
        });

        generatedItem(provider.itemModels(), block.getId(), block.getId(), ITEM_FOLDER);
    }

    private static String crystalGlassSuffix(int index) {
        return switch (index) {
            case CrystalGlassPaneBlock.ROTATED -> "_rot";
            case CrystalGlassPaneBlock.INNER -> "_in";
            case CrystalGlassPaneBlock.OUTER -> "_out";
            default -> "";
        };
    }

    public static void crystalGlassBlock(BlockStateProvider provider, DeferredBlock<? extends Block> block) {
        var name = block.getId().getPath();
        var texture = provider.blockTexture(block.value());

        provider.getVariantBuilder(block.value()).forAllStatesExcept(state -> {
            var type = state.getValue(CrystalGlassBlock.TYPE);
            var suffix = crystalGlassSuffix(type);

            var model = provider.models().cubeAll(name + suffix, texture.withSuffix(suffix))
                    .renderType(TRANSLUCENT);

            return ConfiguredModel.builder()
                    .modelFile(model)
                    .build();
        });

        blockItem(provider, block);
    }

    public static void crystalGlassPaneBlock(BlockStateProvider provider, DyeColor color, DeferredBlock<? extends Block> pane, DeferredBlock<? extends Block> fullBlock) {
        var baseName = fullBlock.getId().getPath();
        var paneName = pane.getId().getPath();
        var builder = provider.getMultipartBuilder(pane.value());
        var texture = provider.blockTexture(fullBlock.value());

        for (int i = 0; i < 4; i++) {
            int type = i;
            PipeBlock.PROPERTY_BY_DIRECTION.entrySet().forEach(e -> {
                Direction dir = e.getKey();
                if (dir.getAxis().isHorizontal()) {
                    boolean alt = dir == Direction.SOUTH;
                    var suffix = crystalGlassSuffix(type);
                    var topTexture = blockTexture(fromNamespaceAndPath(ColorCompat.getNamespace(color), color.getSerializedName() + "_stained_glass_pane_top"));

                    var post = provider.models().panePost(paneName + "_post" + suffix, texture.withSuffix(suffix), topTexture).renderType(TRANSLUCENT);
                    var side = provider.models().paneSide(paneName + "_side" + suffix, texture.withSuffix(suffix), topTexture).renderType(TRANSLUCENT);
                    var sideAlt = provider.models().paneSideAlt(paneName + "_side_alt" + suffix, texture.withSuffix(suffix), topTexture).renderType(TRANSLUCENT);
                    var nosideAlt = provider.models().paneNoSideAlt(paneName + "_noside_alt" + suffix, texture.withSuffix(suffix)).renderType(TRANSLUCENT);
                    var noside = provider.models().paneNoSide(paneName + "_noside" + suffix, texture.withSuffix(suffix)).renderType(TRANSLUCENT);

                    builder.
                            part()
                            .modelFile(post)
                            .addModel()
                            .condition(CrystalGlassPaneBlock.TYPE, type)
                            .end()

                            .part()
                            .modelFile(alt || dir == Direction.WEST ? sideAlt : side)
                            .rotationY(dir.getAxis() == Direction.Axis.X ? 90 : 0)
                            .addModel()
                            .condition(e.getValue(), true)
                            .condition(CrystalGlassPaneBlock.TYPE, type)
                            .end()

                            .part().modelFile(alt || dir == Direction.EAST ? nosideAlt : noside)
                            .rotationY(dir == Direction.WEST ? 270 : dir == Direction.SOUTH ? 90 : 0)
                            .addModel()
                            .condition(e.getValue(), false)
                            .condition(CrystalGlassPaneBlock.TYPE, type)
                            .end();
                }
            });
        }

        generatedItem(provider.itemModels(), pane.getId(), fullBlock.getId(), BLOCK_FOLDER)
                .renderType(TRANSLUCENT);
    }

}
