package galena.oreganized.data.extensions;

import static net.minecraft.resources.ResourceLocation.withDefaultNamespace;
import static net.neoforged.neoforge.client.model.generators.ModelProvider.BLOCK_FOLDER;
import static net.neoforged.neoforge.client.model.generators.ModelProvider.ITEM_FOLDER;

import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.client.model.generators.*;
import net.neoforged.neoforge.registries.DeferredBlock;

public class OBlockStateExtensions {

    public static final ResourceLocation CUTOUT = withDefaultNamespace("cutout");
    public static final ResourceLocation TRANSLUCENT = withDefaultNamespace("translucent");

    public static void cubeAll(BlockStateProvider provider, DeferredBlock<? extends Block> block) {
        var model = provider.cubeAll(block.value());
        provider.simpleBlockWithItem(block.value(), model);
    }

    public static void slab(BlockStateProvider provider, DeferredBlock<? extends Block> block, DeferredBlock<? extends SlabBlock> slab) {
        var texture = provider.blockTexture(block.value());
        provider.slabBlock(slab.value(), texture, texture);
        blockItem(provider, slab);
    }

    public static void stairs(BlockStateProvider provider, DeferredBlock<? extends Block> block, DeferredBlock<? extends StairBlock> stairs) {
        var texture = provider.blockTexture(block.value());
        provider.stairsBlock(stairs.value(), texture);
        blockItem(provider, stairs);
    }

    public static void wall(BlockStateProvider provider, DeferredBlock<? extends Block> block, DeferredBlock<? extends WallBlock> wall) {
        var texture = provider.blockTexture(block.value());
        provider.wallBlock(wall.value(), texture);
        provider.itemModels()
                .getBuilder(wall.getId().getPath())
                .parent(provider.models().wallInventory(wall.getId().getPath() + "_inventory", texture));
    }

    public static void waxed(BlockStateProvider provider, DeferredBlock<? extends Block> block, Block origin) {
        var parent = provider.blockTexture(origin);
        var model = provider.models().getExistingFile(parent);
        provider.simpleBlockWithItem(block.value(), model);
    }

    public static void trapDoor(BlockStateProvider provider, DeferredBlock<? extends TrapDoorBlock> block) {
        var texture = provider.blockTexture(block.value());
        provider.trapdoorBlock(block.value(), texture, true);
        provider.simpleBlockItem(block.value(), provider.models().getExistingFile(texture.withSuffix("_bottom")));
    }

    public static void door(BlockStateProvider provider, DeferredBlock<? extends DoorBlock> block) {
        var texture = provider.blockTexture(block.value());
        provider.doorBlock(block.value(), texture.withSuffix("_bottom"), texture.withSuffix("_top"));
        generatedItem(provider, block, ITEM_FOLDER);
    }

    /**
     * copied from {@link com.teamabnormals.blueprint.core.data.client.BlueprintBlockStateProvider}
     */
    private static void pane(BlockStateProvider provider, Block block, ModelFile post, ModelFile postEnds, ModelFile side, ModelFile sideAlt, ModelFile cap, ModelFile capAlt) {
        MultiPartBlockStateBuilder builder = provider.getMultipartBuilder(block).part().modelFile(postEnds).addModel().end();
        builder.part().modelFile(post).addModel().condition(BlockStateProperties.NORTH, false).condition(BlockStateProperties.WEST, false).condition(BlockStateProperties.SOUTH, false).condition(BlockStateProperties.EAST, false).end();

        for (Direction direction : Direction.Plane.HORIZONTAL.stream().toList()) {
            builder.part().modelFile(direction == Direction.SOUTH || direction == Direction.WEST ? capAlt : cap).rotationY(direction.getAxis() == Direction.Axis.X ? 90 : 0).addModel()
                    .condition(BlockStateProperties.NORTH, PipeBlock.PROPERTY_BY_DIRECTION.get(direction) == BlockStateProperties.NORTH)
                    .condition(BlockStateProperties.WEST, PipeBlock.PROPERTY_BY_DIRECTION.get(direction) == BlockStateProperties.WEST)
                    .condition(BlockStateProperties.SOUTH, PipeBlock.PROPERTY_BY_DIRECTION.get(direction) == BlockStateProperties.SOUTH)
                    .condition(BlockStateProperties.EAST, PipeBlock.PROPERTY_BY_DIRECTION.get(direction) == BlockStateProperties.EAST)
                    .end();

        }

        PipeBlock.PROPERTY_BY_DIRECTION.forEach((dir, value) -> {
            if (dir.getAxis().isHorizontal()) {
                builder.part().modelFile(dir == Direction.SOUTH || dir == Direction.WEST ? sideAlt : side).rotationY(dir.getAxis() == Direction.Axis.X ? 90 : 0).addModel().condition(value, true).end();
            }
        });
    }

    /**
     * copied from {@link com.teamabnormals.blueprint.core.data.client.BlueprintBlockStateProvider}
     */
    public static void bars(BlockStateProvider provider, DeferredBlock<? extends IronBarsBlock> block) {
        var texture = provider.blockTexture(block.value());
        var name = block.getId().getPath();
        var edgeTexture = texture.withSuffix("_edge");

        var post = ironBarsModel(provider, name, "post", texture).texture("bars", edgeTexture);
        var postEnds = ironBarsModel(provider, name, "post_ends", texture).texture("edge", edgeTexture);
        var side = ironBarsModel(provider, name, "side", texture).texture("bars", texture).texture("edge", edgeTexture);
        var sideAlt = ironBarsModel(provider, name, "side_alt", texture).texture("bars", texture).texture("edge", edgeTexture);
        var cap = ironBarsModel(provider, name, "cap", texture).texture("bars", texture).texture("edge", edgeTexture);
        var capAlt = ironBarsModel(provider, name, "cap_alt", texture).texture("bars", texture).texture("edge", edgeTexture);

        pane(provider, block.value(), post, postEnds, side, sideAlt, cap, capAlt);
        generatedItem(provider, block, BLOCK_FOLDER);
    }

    public static BlockModelBuilder ironBarsModel(BlockStateProvider provider, String name, String suffix, ResourceLocation texture) {
        return provider.models()
                .withExistingParent(name + "_" + suffix, withDefaultNamespace("block/iron_bars_" + suffix))
                .renderType(CUTOUT)
                .texture("particle", texture);
    }

    public static void pillar(BlockStateProvider provider, DeferredBlock<? extends RotatedPillarBlock> block) {
        provider.logBlock(block.value());
        blockItem(provider, block);
    }

    public static void crossWithPot(BlockStateProvider provider, DeferredBlock<? extends Block> cross, DeferredBlock<? extends FlowerPotBlock> potted) {
        var texture = provider.blockTexture(cross.value());
        crossBlock(provider, cross);
        var pottedModel = provider.models().singleTexture(potted.getId().getPath(), withDefaultNamespace("block/flower_pot_cross"), "plant", texture).renderType(CUTOUT);
        provider.simpleBlock(potted.value(), pottedModel);
    }

    public static void crossBlock(BlockStateProvider provider, DeferredBlock<? extends Block> cross) {
        var texture = provider.blockTexture(cross.value());
        provider.simpleBlock(cross.value(), provider.models().cross(cross.getId().getPath(), texture).renderType(CUTOUT));
        generatedItem(provider, cross, BLOCK_FOLDER);
    }

    public static ItemModelBuilder generatedItem(BlockStateProvider provider, DeferredBlock<? extends Block> block, String type) {
        return OItemModelExtensions.generatedItem(provider.itemModels(), block.getId(), block.getId(), type);
    }

    public static ModelFile cauldronModel(BlockStateProvider provider, DeferredBlock<? extends Block> block, ResourceLocation texture, int age) {
        var name = block.getId().getPath() + "_" + age;
        return provider.models().withExistingParent(name, BLOCK_FOLDER + "/template_cauldron_full")
                .texture("content", texture);
    }

    public static void cubeBottomTopBlock(BlockStateProvider provider, DeferredBlock<? extends Block> block) {
        var texture = provider.blockTexture(block.value());
        cubeBottomTopBlock(provider, block, texture.withSuffix("_side"), texture.withSuffix("_bottom"), texture.withSuffix("_top"));
    }

    public static void cubeBottomTopBlock(BlockStateProvider provider, DeferredBlock<? extends Block> block, ResourceLocation sideTexture, ResourceLocation bottomTexture, ResourceLocation topTexture) {
        provider.simpleBlock(block.value(), provider.models().cubeBottomTop(block.getId().getPath(), sideTexture, bottomTexture, topTexture));
        blockItem(provider, block);
    }

    public static void blockItem(BlockStateProvider provider, DeferredBlock<? extends Block> block) {
        blockItem(provider, block, provider.blockTexture(block.value()));
    }

    public static void blockItem(BlockStateProvider provider, DeferredBlock<? extends Block> block, ResourceLocation model) {
        var existing = provider.models().getExistingFile(model);
        provider.simpleBlockItem(block.value(), existing);
    }

    public static ResourceLocation blockTexture(ResourceLocation id) {
        return id.withPrefix(BLOCK_FOLDER + "/");
    }

}
