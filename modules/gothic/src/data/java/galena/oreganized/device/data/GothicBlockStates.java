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
import galena.oreganized.gothic.world.block.SpyreBlock;
import net.minecraft.core.Direction;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CrossCollisionBlock;
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

        cubeAll(provider, GothicBlocks.DARK_GRIMSTONE_BRICKS);
        cubeAll(provider, GothicBlocks.PALE_GRIMSTONE_BRICKS);
        cubeAll(provider, GothicBlocks.POLISHED_DARK_GRIMSTONE);
        cubeAll(provider, GothicBlocks.POLISHED_PALE_GRIMSTONE);
        pillar(provider, GothicBlocks.DARK_GRIMSTONE_PILLAR);
        pillar(provider, GothicBlocks.PALE_GRIMSTONE_PILLAR);
        cubeColumn(provider, GothicBlocks.CHISELED_DARK_GRIMSTONE);
        cubeColumn(provider, GothicBlocks.CHISELED_PALE_GRIMSTONE);
        slab(provider, GothicBlocks.POLISHED_DARK_GRIMSTONE, GothicBlocks.DARK_GRIMSTONE_SLAB);
        slab(provider, GothicBlocks.POLISHED_PALE_GRIMSTONE, GothicBlocks.PALE_GRIMSTONE_SLAB);
        cubeAll(provider, GothicBlocks.DARK_GRIMSTONE_SPYRE_BLOCK);
        cubeAll(provider, GothicBlocks.PALE_GRIMSTONE_SPYRE_BLOCK);
        spyre(provider, GothicBlocks.DARK_GRIMSTONE_SPYRE);
        spyre(provider, GothicBlocks.PALE_GRIMSTONE_SPYRE);
        spyreFence(provider, GothicBlocks.DARK_GRIMSTONE_SPYRE_FENCE, false);
        bars(provider, GothicBlocks.PALE_GRIMSTONE_SPYRE_FENCE);
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
        var name = pane.getId().getPath();
        var builder = provider.getMultipartBuilder(pane.value());
        var texture = provider.blockTexture(fullBlock.value());

        for (int i = 0; i < 4; i++) {
            int type = i;
            var suffix = crystalGlassSuffix(type);
            var topTexture = blockTexture(fromNamespaceAndPath(ColorCompat.getNamespace(color), color.getSerializedName() + "_stained_glass_pane_top"));

            var post = provider.models().panePost(name + "_post" + suffix, texture.withSuffix(suffix), topTexture).renderType(TRANSLUCENT);
            var side = provider.models().paneSide(name + "_side" + suffix, texture.withSuffix(suffix), topTexture).renderType(TRANSLUCENT);
            var sideAlt = provider.models().paneSideAlt(name + "_side_alt" + suffix, texture.withSuffix(suffix), topTexture).renderType(TRANSLUCENT);
            var nosideAlt = provider.models().paneNoSideAlt(name + "_noside_alt" + suffix, texture.withSuffix(suffix)).renderType(TRANSLUCENT);
            var noside = provider.models().paneNoSide(name + "_noside" + suffix, texture.withSuffix(suffix)).renderType(TRANSLUCENT);

            builder.
                    part()
                    .modelFile(post)
                    .addModel()
                    .condition(CrystalGlassPaneBlock.TYPE, type)
                    .end();

            CrossCollisionBlock.PROPERTY_BY_DIRECTION.forEach((dir, property) -> {
                var alt = dir == Direction.SOUTH || dir == Direction.EAST;

                builder
                        .part()
                        .modelFile(alt ? sideAlt : side)
                        .rotationY(dir.getAxis() == Direction.Axis.X ? 90 : 0)
                        .addModel()
                        .condition(property, true)
                        .condition(CrystalGlassPaneBlock.TYPE, type)
                        .end()

                        .part().modelFile(alt ? nosideAlt : noside)
                        .rotationY(dir == Direction.WEST ? 270 : dir == Direction.SOUTH ? 90 : 0)
                        .addModel()
                        .condition(property, false)
                        .condition(CrystalGlassPaneBlock.TYPE, type)
                        .end();
            });
        }

        generatedItem(provider.itemModels(), pane.getId(), fullBlock.getId(), BLOCK_FOLDER)
                .renderType(TRANSLUCENT);
    }

    private static String suffix(SpyreBlock.Thickness thickness) {
        return switch (thickness) {
            case BASE -> "_1";
            case FRUSTUM -> "_2";
            case MIDDLE -> "_3";
            case TIP -> "_4";
        };
    }

    public static void spyre(BlockStateProvider provider, DeferredBlock<? extends SpyreBlock> block) {
        var name = block.getId().getPath();

        provider.getVariantBuilder(block.value()).forAllStatesExcept(state -> {
            var thickness = state.getValue(SpyreBlock.THICKNESS);

            var texture = provider.blockTexture(block.value()).withSuffix(suffix(thickness));
            var model = provider.models().withExistingParent(name + "_" + thickness.getSerializedName(), "block/pointed_dripstone")
                    .renderType(CUTOUT)
                    .texture("cross", texture);

            return ConfiguredModel.builder()
                    .modelFile(model)
                    .build();
        }, BlockStateProperties.WATERLOGGED);

        generatedItem(provider.itemModels(), block.getId(), block.getId().withSuffix(suffix(SpyreBlock.Thickness.TIP)), BLOCK_FOLDER);
    }

    public static void spyreFence(BlockStateProvider provider, DeferredBlock<? extends Block> block, boolean rim) {
        var name = block.getId().getPath();
        var builder = provider.getMultipartBuilder(block.value());
        var texture = provider.blockTexture(block.value());

        var crossModel = provider.models().withExistingParent(name + "_cross", blockTexture(OConstants.modLoc("spyre_fence_cross")))
                .texture("texture", texture);
        var cross = builder.part()
                .modelFile(crossModel)
                .addModel();

        var typeSuffix = rim ? "_rimmed" : "";
        var partModel = provider.models().withExistingParent(name + "_part", blockTexture(OConstants.modLoc("spyre_fence_part").withSuffix(typeSuffix)))
                .texture("texture", texture);

        CrossCollisionBlock.PROPERTY_BY_DIRECTION.forEach((dir, property) -> {
            var alt = dir == Direction.SOUTH || dir == Direction.EAST;
            cross.condition(property, false);
            var rotation = (int) dir.getCounterClockWise().toYRot();

            if (rim) {
                var rimModel = provider.models().withExistingParent(name + "_rim", blockTexture(OConstants.modLoc("spyre_fence_rim")))
                        .texture("texture", texture);

                var capBuidler = builder.
                        part()
                        .modelFile(rimModel)
                        .rotationY(rotation)
                        .addModel()
                        .condition(property, true);

                CrossCollisionBlock.PROPERTY_BY_DIRECTION.forEach((otherDir, otherProperty) -> {
                    if (otherDir != dir) capBuidler.condition(otherProperty, false);
                });
            }

            builder.
                    part()
                    .modelFile(partModel)
                    .rotationY(rotation)
                    .addModel()
                    .condition(property, true)
                    .end();
        });

        generatedItem(provider.itemModels(), block.getId(), block.getId(), BLOCK_FOLDER);
    }

}
