package galena.oreganized.carcinogenius.index;

import static net.minecraft.world.level.block.state.BlockBehaviour.Properties.ofFullCopy;

import com.teamabnormals.blueprint.core.util.registry.BlockSubRegistryHelper;
import galena.oreganized.carcinogenius.OreganizedCarcinogenius;
import galena.oreganized.carcinogenius.content.block.AsbestosBlock;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.neoforged.neoforge.registries.DeferredBlock;

public class OCBlocks {
    public static final BlockSubRegistryHelper HELPER = OreganizedCarcinogenius.REGISTRY_HELPER.getBlockSubHelper();

    public static final DeferredBlock<Block> ASBESTOS_ORE = register("asbestos_ore", () -> new DropExperienceBlock(ConstantInt.of(0), ofFullCopy(Blocks.GOLD_ORE).strength(3.0F, 3.0F)));
    public static final DeferredBlock<Block> DEEPSLATE_ASBESTOS_ORE = register("deepslate_asbestos_ore", () -> new DropExperienceBlock(ConstantInt.of(0), ofFullCopy(Blocks.DEEPSLATE_GOLD_ORE)));

    // Storage Blocks
    public static final DeferredBlock<Block> ASBESTOS_BLOCK = register("asbestos_block", () -> new AsbestosBlock(ofFullCopy(Blocks.RAW_IRON_BLOCK)));
    public static final DeferredBlock<Block> RAW_ASBESTOS_BLOCK = register("raw_asbestos_block", () -> new AsbestosBlock(ofFullCopy(Blocks.RAW_IRON_BLOCK)));

    public static <T extends Block> DeferredBlock<T> baseRegister(String name, Supplier<? extends T> block, Function<DeferredBlock<T>, Supplier<? extends Item>> item) {
        DeferredBlock<T> register = HELPER.createBlockNoItem(name, block);
        OCItems.HELPER.createItem(name, item.apply(register));
        return register;
    }

    public static <B extends Block> DeferredBlock<B> register(String name, Supplier<? extends B> block) {
        return baseRegister(name, block, OCBlocks::registerBlockItem);
    }

    private static <T extends Block> Supplier<BlockItem> registerBlockItem(final DeferredBlock<T> block) {
        return () -> new BlockItem(Objects.requireNonNull(block.get()), new Item.Properties());
    }

    public static void register() {
        // Load this class
    }

}
