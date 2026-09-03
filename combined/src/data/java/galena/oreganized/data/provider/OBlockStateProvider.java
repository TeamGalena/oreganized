package galena.oreganized.data.provider;

import static net.neoforged.neoforge.client.model.generators.ModelProvider.BLOCK_FOLDER;

import com.teamabnormals.blueprint.core.data.client.BlueprintBlockStateProvider;
import galena.oreganized.compat.ColorCompat;
import galena.oreganized.content.block.CrystalGlassBlock;
import galena.oreganized.content.block.CrystalGlassPaneBlock;
import galena.oreganized.gothic.world.block.GargoyleBlock;
import galena.oreganized.plumbum.world.block.IMeltableBlock;
import galena.oreganized.plumbum.world.block.MoltenLeadCauldronBlock;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.CrossCollisionBlock;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.LeverBlock;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.RedstoneLampBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.Half;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.client.model.generators.MultiPartBlockStateBuilder;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public abstract class OBlockStateProvider extends BlueprintBlockStateProvider {

    protected OBlockStateProvider(PackOutput output, ExistingFileHelper help) {
        super(output, OConstants.MOD_ID, help);
    }

    protected ResourceLocation blockTexture(String name) {
        return modLoc(BLOCK_FOLDER + "/" + name);
    }

    public static String name(DeferredBlock<? extends Block> block) {
        return name(block.get());
    }

    private static String suffixByIndex(int index) {
        switch (index) {
            case CrystalGlassPaneBlock.ROTATED -> {
                return "_rot";
            }
            case CrystalGlassPaneBlock.INNER -> {
                return "_in";
            }
            case CrystalGlassPaneBlock.OUTER -> {
                return "_out";
            }
            default -> {
                return "";
            }
        }
    }

    public void waxedBlock(DeferredBlock<? extends Block> block, Block origin) {
        simpleBlock(block.get(), cubeAll(origin));
        simpleBlockItem(block.get(), new ModelFile.ExistingModelFile(blockTexture(origin), models().existingFileHelper));
    }

    public ModelFile cauldronModel(DeferredBlock<? extends Block> block, ResourceLocation texture, int age) {
        String name = name(block) + age;
        return models().withExistingParent(name, BLOCK_FOLDER + "/template_cauldron_full")
                .texture("content", texture);
    }

    public ModelFile cauldronModel(DeferredBlock<? extends Block> cauldron, DeferredBlock<? extends Block> content, int age) {
        return cauldronModel(cauldron, blockTexture(content.get()), age);
    }

    public void moltenCauldron(DeferredBlock<? extends Block> cauldron, DeferredBlock<? extends Block> content) {
        getVariantBuilder(cauldron.get()).forAllStates(state -> {
            int age = state.getValue(MoltenLeadCauldronBlock.AGE);
            if (age == 0) {
                return ConfiguredModel.builder().modelFile(cauldronModel(cauldron, content, age)).build();
            } else {
                var texture = age == 3
                        ? blockTexture("molten_" + name(content))
                        : blockTexture(content.get()).withSuffix("2");
                return ConfiguredModel.builder().modelFile(cauldronModel(cauldron, texture, age)).build();
            }
        });
    }

    public void crystalGlassBlock(DeferredBlock<? extends Block> block) {
        getVariantBuilder(block.get()).partialState().with(CrystalGlassBlock.TYPE, CrystalGlassBlock.NORMAL).modelForState()
                .modelFile(cubeAll(block.get())).addModel().partialState().with(CrystalGlassBlock.TYPE, CrystalGlassBlock.ROTATED)
                .modelForState().modelFile(models().cubeAll(name(block) + "_rot",
                        OConstants.modLoc("block/" + name(block) + "_rot"))).addModel()
                .partialState().with(CrystalGlassBlock.TYPE, CrystalGlassBlock.INNER)
                .modelForState().modelFile(models().cubeAll(name(block) + "_in",
                        OConstants.modLoc("block/" + name(block) + "_in"))).addModel()
                .partialState().with(CrystalGlassBlock.TYPE, CrystalGlassBlock.OUTER)
                .modelForState().modelFile(models().cubeAll(name(block) + "_out",
                        OConstants.modLoc("block/" + name(block) + "_out"))).addModel();

        blockItem(block);
    }

    public void crystalGlassPaneBlock(DyeColor color, DeferredBlock<? extends Block> pane, DeferredBlock<? extends Block> fullBlock) {
        String baseName = name(fullBlock);
        String paneName = name(pane);
        MultiPartBlockStateBuilder builder = getMultipartBuilder(pane.get());
        for (int i = 0; i < 4; i++) {
            int finalI = i;
            PipeBlock.PROPERTY_BY_DIRECTION.entrySet().forEach(e -> {
                Direction dir = e.getKey();
                if (dir.getAxis().isHorizontal()) {
                    boolean alt = dir == Direction.SOUTH;
                    var topTexture = ResourceLocation.fromNamespaceAndPath(ColorCompat.getNamespace(color), "block/" + color.getSerializedName() + "_stained_glass_pane_top");
                    builder.part().modelFile(models().panePost(paneName + "_post" + suffixByIndex(finalI), OConstants.modLoc("block/" + baseName + suffixByIndex(finalI)), topTexture)).addModel().condition(CrystalGlassPaneBlock.TYPE, finalI).end()
                            .part().modelFile(alt || dir == Direction.WEST ? models().paneSideAlt(paneName + "_side_alt" + suffixByIndex(finalI), OConstants.modLoc("block/" + baseName + suffixByIndex(finalI)), topTexture) :
                                    models().paneSide(paneName + "_side" + suffixByIndex(finalI), OConstants.modLoc("block/" + baseName + suffixByIndex(finalI)), topTexture)).rotationY(dir.getAxis() == Direction.Axis.X ? 90 : 0).addModel()
                            .condition(e.getValue(), true).condition(CrystalGlassPaneBlock.TYPE, finalI).end()
                            .part().modelFile(alt || dir == Direction.EAST ? models().paneNoSideAlt(paneName + "_noside_alt" + suffixByIndex(finalI), OConstants.modLoc("block/" + baseName + suffixByIndex(finalI))) :
                                    models().paneNoSide(paneName + "_noside" + suffixByIndex(finalI), OConstants.modLoc("block/" + baseName + suffixByIndex(finalI)))).rotationY(dir == Direction.WEST ? 270 : dir == Direction.SOUTH ? 90 : 0).addModel()
                            .condition(e.getValue(), false).condition(CrystalGlassPaneBlock.TYPE, finalI);
                }
            });
        }

        generatedItem(pane.get(), fullBlock.get(), "block");
    }

    public <T extends Block & IMeltableBlock> void meltableBlock(DeferredBlock<T> block, BiFunction<String, ResourceLocation, ModelFile> modelBuilder) {
        meltableBlock(block, modelBuilder, (s, it) -> it);
    }

    public <T extends Block & IMeltableBlock> void meltableBlock(DeferredBlock<T> block, BiFunction<String, ResourceLocation, ModelFile> modelBuilder, BiFunction<BlockState, ConfiguredModel.Builder<?>, ConfiguredModel.Builder<?>> modelModifier) {
        var prefixes = List.of("", "goopy_", "red_hot_");
        var redHotModel = models().cubeAll("red_hot_lead", modLoc(BLOCK_FOLDER + "/red_hot_lead"));
        getVariantBuilder(block.get()).forAllStates(state -> {
            int goopyness = block.get().getGoopyness(state);
            var name = prefixes.get(goopyness) + name(block);
            var texture = blockTexture(name);
            var isRedHot = goopyness == 2;
            var model = ConfiguredModel.builder().modelFile(isRedHot ? redHotModel : modelBuilder.apply(name, texture));
            if (isRedHot) return model.build();
            return modelModifier.apply(state, model).build();
        });

        blockItem(block.get());
    }

    public <T extends Block & IMeltableBlock> void meltableLamp(DeferredBlock<T> block) {
        var prefixes = List.of("", "dimmer_", "goopy_", "red_hot_");
        var redHotModel = models().cubeAll("red_hot_lead", modLoc(BLOCK_FOLDER + "/red_hot_lead"));
        getVariantBuilder(block.get()).forAllStates(state -> {
            int goopyness = state.getValue(galena.oreganized.plumbum.world.block.LeadBulbBlock.GOOPYNESS_4);
            var name = prefixes.get(goopyness) + name(block);
            var texture = blockTexture(name);
            var model = goopyness < 3 ? models().cubeAll(name, texture) : redHotModel;

            return ConfiguredModel.builder().modelFile(model).build();
        });

        blockItem(block.get());
    }

    public void lamp(Block block) {
        getVariantBuilder(block).forAllStates(state -> {
            boolean lit = state.getValue(RedstoneLampBlock.LIT);
            String modelName = lit ? name(block) + "_on" : name(block);
            ResourceLocation texture = blockTexture(block).withSuffix(lit ? "_on" : "_off");
            ModelFile model = models().cubeAll(modelName, texture);
            return ConfiguredModel.builder().modelFile(model).build();
        });

        blockItem(block);
    }

    public <T extends RotatedPillarBlock & IMeltableBlock> void meltablePillar(DeferredBlock<T> block) {
        meltableBlock(
                block,
                (n, t) -> models().cubeColumn(n, t.withSuffix("_side"), t.withSuffix("_top")),
                (s, it) -> switch (s.getValue(RotatedPillarBlock.AXIS)) {
                    case X -> it.rotationX(90).rotationY(90);
                    case Y -> it;
                    case Z -> it.rotationX(90);
                }
        );
    }

    public <T extends TrapDoorBlock & IMeltableBlock> void meltableTrapdoor(DeferredBlock<T> block) {
        var baseName = name(block);
        var prefixes = List.of("", "goopy_", "red_hot_");

        getVariantBuilder(block.get()).forAllStatesExcept(state -> {
            int goopyness = block.get().getGoopyness(state);
            var name = prefixes.get(goopyness) + baseName;
            var texture = blockTexture(goopyness < 2 ? name : "red_hot_lead");

            var bottom = models().trapdoorOrientableBottom(name + "_bottom", texture);
            var top = models().trapdoorOrientableTop(name + "_top", texture);
            var open = models().trapdoorOrientableOpen(name + "_open", texture);

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

        itemModels().trapdoorOrientableBottom(name(block), blockTexture(block.get()));
    }

    public <T extends DoorBlock & IMeltableBlock> void meltableDoor(DeferredBlock<T> block) {
        var baseName = name(block);
        var prefixes = List.of("", "goopy_", "red_hot_");

        getVariantBuilder(block.get()).forAllStatesExcept(state -> {
            boolean right = state.getValue(DoorBlock.HINGE) == DoorHingeSide.RIGHT;
            boolean open = state.getValue(DoorBlock.OPEN);
            boolean lower = state.getValue(DoorBlock.HALF) == DoubleBlockHalf.LOWER;

            int goopyness = block.get().getGoopyness(state);
            var name = prefixes.get(goopyness) + baseName;
            var textureSuffix = open ? "_open" : "";
            var bottom = blockTexture(goopyness < 2 ? (name + "_bottom" + textureSuffix) : "red_hot_lead");
            var top = blockTexture(goopyness < 2 ? (name + "_top" + textureSuffix) : "red_hot_lead");

            var bottomLeft = models().doorBottomLeft(name + "_bottom_left" + textureSuffix, bottom, top);
            var bottomRight = models().doorBottomRight(name + "_bottom_right" + textureSuffix, bottom, top);
            var topLeft = models().doorTopLeft(name + "_top_left" + textureSuffix, bottom, top);
            var topRight = models().doorTopRight(name + "_top_right" + textureSuffix, bottom, top);

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

        generatedItem(block.get(), "item");
    }

    public <T extends IronBarsBlock & IMeltableBlock> void meltableBars(DeferredBlock<T> block) {
        var baseName = name(block);
        var prefixes = List.of("", "goopy_", "red_hot_");

        var builder = getMultipartBuilder(block.get());
        var property = block.get().getGoopynessProperty();

        property.getPossibleValues().forEach(goopyness -> {
            var name = prefixes.get(goopyness) + baseName;
            var texture = blockTexture(name);

            Function<String, ModelFile> createModel = suffix -> models()
                    .withExistingParent(name + suffix, mcLoc(BLOCK_FOLDER + "/iron_bars" + suffix))
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

        generatedItem(block.get(), "block");
    }

    public <T extends LeverBlock & IMeltableBlock> void sturdyLever(DeferredBlock<T> block) {
        var baseName = name(block);
        var prefixes = List.of("", "goopy_", "red_hot_");

        getVariantBuilder(block.get()).forAllStates(state -> {
            var facing = state.getValue(LeverBlock.FACING);
            var face = state.getValue(LeverBlock.FACE);
            boolean powered = state.getValue(LeverBlock.POWERED);
            int goopyness = block.get().getGoopyness(state);

            var name = prefixes.get(goopyness) + baseName;
            var poweredSuffix = powered ? "_on" : "";

            ModelFile model = goopyness == 0
                    ? models().getExistingFile(blockTexture(name + poweredSuffix))
                    : models().withExistingParent(name + poweredSuffix, blockTexture(baseName + poweredSuffix))
                            .texture("base", blockTexture(name + "_base"))
                            .texture("lever", blockTexture(name));

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

        generatedItem(block.get(), OConstants.modLoc("block/" + baseName + "_item"));
    }

    public <T extends ButtonBlock & IMeltableBlock> void sturdyButton(DeferredBlock<T> block) {
        var baseName = name(block);
        var prefixes = List.of("", "goopy_", "red_hot_");

        getVariantBuilder(block.get()).forAllStates(state -> {
            var facing = state.getValue(ButtonBlock.FACING);
            var face = state.getValue(ButtonBlock.FACE);
            boolean powered = state.getValue(ButtonBlock.POWERED);
            int goopyness = block.get().getGoopyness(state);

            var name = prefixes.get(goopyness) + baseName;
            var poweredSuffix = powered ? "_pressed" : "";

            ModelFile model = goopyness == 0
                    ? models().getExistingFile(blockTexture(name + poweredSuffix))
                    : models().withExistingParent(name + poweredSuffix, blockTexture(baseName + poweredSuffix))
                            .texture("texture", blockTexture(name));

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

        simpleBlockItem(block.get(), new ModelFile.ExistingModelFile(blockTexture(block.get()).withSuffix("_inventory"), models().existingFileHelper));
    }

    public void gargoyleBlock(DeferredBlock<? extends Block> block) {
        var texture = blockTexture(block.get());
        var floorModel = models().getExistingFile(texture);
        var wallModel = models().getExistingFile(texture.withSuffix("_side"));

        getVariantBuilder(block.get()).forAllStatesExcept(state -> {
            var facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            var attachment = state.getValue(GargoyleBlock.ATTACHMENT);

            return ConfiguredModel.builder()
                    .rotationY((int) (facing.toYRot() % 360))
                    .modelFile(attachment == GargoyleBlock.AttachmentType.WALL ? wallModel : floorModel)
                    .build();
        });

        generatedItem(block.get(), "item");
    }

}
