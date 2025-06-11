package galena.oreganized.carcinogenius.index;

import com.teamabnormals.blueprint.core.util.registry.BlockSubRegistryHelper;
import galena.oreganized.carcinogenius.OreganizedCarcinogenius;
import galena.oreganized.carcinogenius.content.block.AsbestosBlock;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = OreganizedCarcinogenius.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class OCBlocks {
    public static final BlockSubRegistryHelper HELPER = OreganizedCarcinogenius.REGISTRY_HELPER.getBlockSubHelper();

    public static final RegistryObject<Block> ASBESTOS_ORE = register("asbestos_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.GOLD_ORE).strength(3.0F, 3.0F)));
    public static final RegistryObject<Block> DEEPSLATE_ASBESTOS_ORE = register("deepslate_asbestos_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_GOLD_ORE)));

    // Storage Blocks
    public static final RegistryObject<Block> ASBESTOS_BLOCK = register("asbestos_block", () -> new AsbestosBlock(BlockBehaviour.Properties.copy(Blocks.RAW_IRON_BLOCK)));
    public static final RegistryObject<Block> RAW_ASBESTOS_BLOCK = register("raw_asbestos_block", () -> new AsbestosBlock(BlockBehaviour.Properties.copy(Blocks.RAW_IRON_BLOCK)));

    public static <T extends Block> RegistryObject<T> baseRegister(String name, Supplier<? extends T> block, Function<RegistryObject<T>, Supplier<? extends Item>> item) {
        RegistryObject<T> register = HELPER.createBlockNoItem(name, block);
        OCItems.HELPER.createItem(name, item.apply(register));
        return register;
    }

    public static <B extends Block> RegistryObject<B> register(String name, Supplier<? extends Block> block) {
        return (RegistryObject<B>) baseRegister(name, block, OCBlocks::registerBlockItem);
    }

    private static <T extends Block> Supplier<BlockItem> registerBlockItem(final RegistryObject<T> block) {
        return () -> {
            return new BlockItem(Objects.requireNonNull(block.get()), new Item.Properties());
        };
    }
}
