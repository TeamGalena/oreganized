package galena.oreganized.plumbum.data;

import static galena.oreganized.data.extensions.OBlockStateExtensions.*;
import static net.minecraft.resources.ResourceLocation.withDefaultNamespace;
import static net.neoforged.neoforge.client.model.generators.ModelProvider.BLOCK_FOLDER;
import static net.neoforged.neoforge.client.model.generators.ModelProvider.ITEM_FOLDER;

import galena.oreganized.OConstants;
import galena.oreganized.data.ODatagen;
import galena.oreganized.plumbum.index.PlumbumBlocks;
import galena.oreganized.plumbum.world.block.IMeltableBlock;
import galena.oreganized.plumbum.world.block.MoltenLeadCauldronBlock;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.Half;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.registries.DeferredBlock;

@Mod(OConstants.MOD_ID)
public class PlumbumBlockStates {

    public PlumbumBlockStates() {
        ODatagen.addBlockStateProvider(this::generate);
    }

    private void generate(BlockStateProvider provider) {
        provider.models().cubeAll(RED_HOT.getPath(), RED_HOT);

        cubeAll(provider, PlumbumBlocks.LEAD_ORE);
        cubeAll(provider, PlumbumBlocks.DEEPSLATE_LEAD_ORE);
        cubeAll(provider, PlumbumBlocks.RAW_LEAD_BLOCK);

        meltable(provider, PlumbumBlocks.LEAD_BLOCK, (n, t) -> provider.models().cubeAll(n, t));
        meltable(provider, PlumbumBlocks.LEAD_BRICKS, (n, t) -> provider.models().cubeAll(n, t));
        meltablePillar(provider, PlumbumBlocks.LEAD_PILLAR);
        meltablePillar(provider, PlumbumBlocks.CUT_LEAD);
        meltableLamp(provider, PlumbumBlocks.LEAD_BULB);
        meltableDoor(provider, PlumbumBlocks.LEAD_DOOR);
        meltableTrapdoor(provider, PlumbumBlocks.LEAD_TRAPDOOR);
        meltableBars(provider, PlumbumBlocks.LEAD_BARS);

        sturdyLever(provider, PlumbumBlocks.STURDY_LEVER);
        sturdyButton(provider, PlumbumBlocks.STURDY_BUTTON);

        moltenCauldron(provider, PlumbumBlocks.MOLTEN_LEAD_CAULDRON, PlumbumBlocks.LEAD_BLOCK);

        crossWithPot(provider, PlumbumBlocks.PURPLE_DATURA, PlumbumBlocks.POTTED_PURPLE_DATURA);
        crossWithPot(provider, PlumbumBlocks.WHITE_DATURA, PlumbumBlocks.POTTED_WHITE_DATURA);
    }

    public static void moltenCauldron(BlockStateProvider provider, DeferredBlock<? extends Block> cauldron, DeferredBlock<? extends Block> content) {
        provider.getVariantBuilder(cauldron.get()).forAllStates(state -> {
            int age = state.getValue(MoltenLeadCauldronBlock.AGE);
            var prefix = age == 0
                    ? ""
                    : age == 3
                    ? "molten_"
                    : "melting_";
            var texture = blockTexture(content.getId().withPrefix(prefix));
            return ConfiguredModel.builder().modelFile(cauldronModel(provider, cauldron, texture, age)).build();
        });
    }

    private static final ResourceLocation RED_HOT = blockTexture(OConstants.modLoc("red_hot_lead"));

    private static ModelFile redHotModel(BlockStateProvider provider) {
        return provider.models().getExistingFile(RED_HOT);
    }

    public static <T extends Block & IMeltableBlock> void meltable(BlockStateProvider provider, DeferredBlock<T> block, BiFunction<String, ResourceLocation, ModelFile> modelBuilder) {
        meltable(provider, block, modelBuilder, (s, it) -> it);
    }

    public static <T extends Block & IMeltableBlock> void meltable(BlockStateProvider provider, DeferredBlock<T> block, BiFunction<String, ResourceLocation, ModelFile> modelBuilder, BiFunction<BlockState, ConfiguredModel.Builder<?>, ConfiguredModel.Builder<?>> modelModifier) {
        var prefixes = List.of("", "goopy_", "red_hot_");
        provider.getVariantBuilder(block.get()).forAllStates(state -> {
            int goopyness = block.get().getGoopyness(state);
            var name = prefixes.get(goopyness) + block.getId().getPath();
            var texture = blockTexture(block.getId().withPrefix(prefixes.get(goopyness)));
            var isRedHot = goopyness == 2;
            var model = ConfiguredModel.builder().modelFile(isRedHot ? redHotModel(provider) : modelBuilder.apply(name, texture));
            if (isRedHot) return model.build();
            return modelModifier.apply(state, model).build();
        });

        blockItem(provider, block);
    }

    public static <T extends Block & IMeltableBlock> void meltableLamp(BlockStateProvider provider, DeferredBlock<T> block) {
        var prefixes = List.of("", "dimmer_", "goopy_", "red_hot_");
        provider.getVariantBuilder(block.get()).forAllStates(state -> {
            int goopyness = state.getValue(galena.oreganized.plumbum.world.block.LeadBulbBlock.GOOPYNESS_4);
            var name = prefixes.get(goopyness) + block.getId().getPath();
            var texture = blockTexture(block.getId().withPrefix(prefixes.get(goopyness)));
            var model = goopyness < 3 ? provider.models().cubeAll(name, texture) : redHotModel(provider);

            return ConfiguredModel.builder().modelFile(model).build();
        });

        blockItem(provider, block);
    }

    public static <T extends RotatedPillarBlock & IMeltableBlock> void meltablePillar(BlockStateProvider provider, DeferredBlock<T> block) {
        meltable(
                provider,
                block,
                (n, t) -> provider.models().cubeColumn(n, t.withSuffix("_side"), t.withSuffix("_top")),
                (s, it) -> switch (s.getValue(RotatedPillarBlock.AXIS)) {
                    case X -> it.rotationX(90).rotationY(90);
                    case Y -> it;
                    case Z -> it.rotationX(90);
                }
        );
    }

    public static <T extends TrapDoorBlock & IMeltableBlock> void meltableTrapdoor(BlockStateProvider provider, DeferredBlock<T> block) {
        var prefixes = List.of("", "goopy_", "red_hot_");

        provider.getVariantBuilder(block.get()).forAllStatesExcept(state -> {
            int goopyness = block.get().getGoopyness(state);
            var key = block.getId().withPrefix(prefixes.get(goopyness));
            var name = key.getPath();
            var texture = goopyness < 2 ?blockTexture(key) : RED_HOT;

            var bottom = provider.models().trapdoorOrientableBottom(name + "_bottom", texture);
            var top = provider.models().trapdoorOrientableTop(name + "_top", texture);
            var open = provider.models().trapdoorOrientableOpen(name + "_open", texture);

            int xRot = 0;
            int yRot = ((int) state.getValue(TrapDoorBlock.FACING).toYRot()) + 180;
            boolean isOpen = state.getValue(TrapDoorBlock.OPEN);
            if (isOpen && state.getValue(TrapDoorBlock.HALF) == Half.TOP) {
                xRot += 180;
                yRot += 180;
            }
            yRot %= 360;
            return ConfiguredModel.builder().modelFile(isOpen ? open : state.getValue(TrapDoorBlock.HALF) == Half.TOP ? top : bottom)
                    .rotationX(xRot)
                    .rotationY(yRot)
                    .build();
        }, TrapDoorBlock.POWERED, TrapDoorBlock.WATERLOGGED);

        provider.itemModels().trapdoorOrientableBottom(block.getId().getPath(), provider.blockTexture(block.value()));
    }

    public static <T extends DoorBlock & IMeltableBlock> void meltableDoor(BlockStateProvider provider, DeferredBlock<T> block) {
        var prefixes = List.of("", "goopy_", "red_hot_");

        provider.getVariantBuilder(block.get()).forAllStatesExcept(state -> {
            boolean right = state.getValue(DoorBlock.HINGE) == DoorHingeSide.RIGHT;
            boolean open = state.getValue(DoorBlock.OPEN);
            boolean lower = state.getValue(DoorBlock.HALF) == DoubleBlockHalf.LOWER;

            int goopyness = block.get().getGoopyness(state);
            var key = block.getId().withPrefix(prefixes.get(goopyness));
            var name = key.getPath();
            var texture = blockTexture(key);

            var textureSuffix = open ? "_open" : "";
            var bottom = goopyness < 2 ? texture.withSuffix("_bottom" + textureSuffix) : RED_HOT;
            var top = goopyness < 2 ? texture.withSuffix("_top" + textureSuffix) : RED_HOT;

            var bottomLeft = provider.models().doorBottomLeft(name + "_bottom_left" + textureSuffix, bottom, top);
            var bottomRight = provider.models().doorBottomRight(name + "_bottom_right" + textureSuffix, bottom, top);
            var topLeft = provider.models().doorTopLeft(name + "_top_left" + textureSuffix, bottom, top);
            var topRight = provider.models().doorTopRight(name + "_top_right" + textureSuffix, bottom, top);

            int yRot = (int) state.getValue(DoorBlock.FACING).toYRot() + 90;
            if (open) {
                yRot += 90;
            }

            if (right && open) {
                yRot += 180;
            }

            yRot %= 360;
            ModelFile model = null;

            if (lower) {
                if (right) {
                    model = bottomRight;
                } else {
                    model = bottomLeft;
                }
            } else {
                if (right) {
                    model = topRight;
                } else {
                    model = topLeft;
                }
            }

            return ConfiguredModel.builder().modelFile(model).rotationY(yRot).build();
        }, DoorBlock.POWERED);

        generatedItem(provider, block, ITEM_FOLDER);
    }

    public static <T extends IronBarsBlock & IMeltableBlock> void meltableBars(BlockStateProvider provider, DeferredBlock<T> block) {
        var prefixes = List.of("", "goopy_", "red_hot_");

        var builder = provider.getMultipartBuilder(block.get());
        var property = block.get().getGoopynessProperty();

        property.getPossibleValues().forEach(goopyness -> {
            var key = block.getId().withPrefix(prefixes.get(goopyness));
            var name = key.getPath();
            var texture = blockTexture(key);

            Function<String, ModelFile> createModel = suffix -> provider.models()
                    .withExistingParent(name + suffix, blockTexture(withDefaultNamespace("iron_bars" + suffix)))
                    .texture("bars", texture)
                    .texture("edge", texture)
                    .texture("particle", texture);

            builder.part().modelFile(createModel.apply("_post_ends")).addModel()
                    .condition(property, goopyness);

            builder.part()
                    .modelFile(createModel.apply("_post")).addModel()
                    .condition(property, goopyness)
                    .condition(IronBarsBlock.NORTH, false)
                    .condition(IronBarsBlock.EAST, false)
                    .condition(IronBarsBlock.SOUTH, false)
                    .condition(IronBarsBlock.WEST, false);

            Arrays.stream(Direction.values()).filter(it -> it.getAxis().isHorizontal()).forEach(direction -> {
                var suffix = switch (direction) {
                    case SOUTH, WEST -> "_alt";
                    default -> "";
                };

                var yRotation = switch (direction) {
                    case EAST, WEST -> 90;
                    default -> 0;
                };

                builder.part()
                        .modelFile(createModel.apply("_cap" + suffix)).rotationY(yRotation).addModel()
                        .condition(property, goopyness)
                        .condition(CrossCollisionBlock.NORTH, direction == Direction.NORTH)
                        .condition(CrossCollisionBlock.EAST, direction == Direction.EAST)
                        .condition(CrossCollisionBlock.SOUTH, direction == Direction.SOUTH)
                        .condition(CrossCollisionBlock.WEST, direction == Direction.WEST);

                builder.part()
                        .modelFile(createModel.apply("_side" + suffix)).rotationY(yRotation).addModel()
                        .condition(property, goopyness)
                        .condition(PipeBlock.PROPERTY_BY_DIRECTION.get(direction), true);
            });
        });

        generatedItem(provider, block, BLOCK_FOLDER);
    }

    public static <T extends LeverBlock & IMeltableBlock> void sturdyLever(BlockStateProvider provider, DeferredBlock<T> block) {
        var prefixes = List.of("", "goopy_", "red_hot_");

        provider.getVariantBuilder(block.get()).forAllStates(state -> {
            var facing = state.getValue(LeverBlock.FACING);
            var face = state.getValue(LeverBlock.FACE);
            boolean powered = state.getValue(LeverBlock.POWERED);
            int goopyness = block.get().getGoopyness(state);

            var key = block.getId().withPrefix(prefixes.get(goopyness));
            var name = key.getPath();
            var texture = blockTexture(key);
            var poweredSuffix = powered ? "_on" : "";
            var template = provider.blockTexture(block.value()).withSuffix(poweredSuffix);

            ModelFile model = goopyness == 0
                    ? provider.models().getExistingFile(template)
                    : provider.models().withExistingParent(name + poweredSuffix, template)
                    .texture("base", texture.withSuffix("_base"))
                    .texture("lever", texture);

            return ConfiguredModel.builder()
                    .modelFile(model)
                    .rotationX(switch (face) {
                        case FLOOR -> 0;
                        case WALL -> 270;
                        case CEILING -> 180;
                    })
                    .rotationY((int) (switch (face) {
                        case FLOOR, WALL -> facing;
                        case CEILING -> facing.getOpposite();
                    }).toYRot())
                    .build();
        });

        generatedItem(provider, block, ITEM_FOLDER);
    }

    public static <T extends ButtonBlock & IMeltableBlock> void sturdyButton(BlockStateProvider provider, DeferredBlock<T> block) {
        var prefixes = List.of("", "goopy_", "red_hot_");

        provider.getVariantBuilder(block.get()).forAllStates(state -> {
            var facing = state.getValue(ButtonBlock.FACING);
            var face = state.getValue(ButtonBlock.FACE);
            boolean powered = state.getValue(ButtonBlock.POWERED);
            int goopyness = block.get().getGoopyness(state);

            var key = block.getId().withPrefix(prefixes.get(goopyness));
            var name = key.getPath();
            var texture = blockTexture(key);
            var poweredSuffix = powered ? "_pressed" : "";
            var template = provider.blockTexture(block.value()).withSuffix(poweredSuffix);

            ModelFile model = goopyness == 0
                    ? provider.models().getExistingFile(template)
                    : provider.models().withExistingParent(name + poweredSuffix, template)
                    .texture("texture", texture);

            return ConfiguredModel.builder()
                    .modelFile(model)
                    .rotationX(switch (face) {
                        case FLOOR -> 0;
                        case WALL -> 270;
                        case CEILING -> 180;
                    })
                    .rotationY((int) (switch (face) {
                        case FLOOR -> facing.getOpposite();
                        case WALL, CEILING -> facing;
                    }).toYRot())
                    .build();
        });

        provider.itemModels().withExistingParent(
                block.getId().getPath(),
                blockTexture(block.getId().withSuffix("_inventory"))
        );
    }

}
