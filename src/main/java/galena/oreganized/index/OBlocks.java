package galena.oreganized.index;

import com.google.common.collect.ImmutableBiMap;
import com.teamabnormals.blueprint.core.util.registry.BlockSubRegistryHelper;
import galena.oreganized.OreganizedCarcinogenius;
import galena.oreganized.content.block.*;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DoubleHighBlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.IceBlock;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = OreganizedCarcinogenius.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class OBlocks {
    public static final BlockSubRegistryHelper HELPER = OreganizedCarcinogenius.REGISTRY_HELPER.getBlockSubHelper();

    public static final RegistryObject<Block> ASBESTOS_ORE = register("asbestos_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.GOLD_ORE).strength(3.0F, 3.0F)));
    public static final RegistryObject<Block> DEEPSLATE_ASBESTOS_ORE = register("deepslate_asbestos_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_GOLD_ORE)));

    // Storage Blocks
    public static final RegistryObject<Block> ASBESTOS_BLOCK = register("asbestos_block", () -> new AsbestosBlock(BlockBehaviour.Properties.copy(Blocks.RAW_IRON_BLOCK)));
    public static final RegistryObject<Block> RAW_ASBESTOS_BLOCK = register("raw_asbestos_block", () -> new AsbestosBlock(BlockBehaviour.Properties.copy(Blocks.RAW_IRON_BLOCK)));

    public static <T extends Block> RegistryObject<T> baseRegister(String name, Supplier<? extends T> block, Function<RegistryObject<T>, Supplier<? extends Item>> item) {
        RegistryObject<T> register = HELPER.createBlockNoItem(name, block);
        OItems.HELPER.createItem(name, item.apply(register));
        return register;
    }

    public static <B extends Block> RegistryObject<B> register(String name, Supplier<? extends Block> block) {
        return (RegistryObject<B>) baseRegister(name, block, OBlocks::registerBlockItem);
    }

    private static <T extends Block> Supplier<BlockItem> registerBlockItem(final RegistryObject<T> block) {
        return () -> {
            return new BlockItem(Objects.requireNonNull(block.get()), new Item.Properties());
        };
    }
}
