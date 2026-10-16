package galena.oreganized.argentum.index;

import java.util.stream.Stream;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public class ArgentumSets {

    public static Stream<galena.oreganized.argentum.index.TarnishedBlocks<?>> tarnishedBlocks() {
        return Stream.of(
                ArgentumBlocks.SILVER_BLOCKS,
                ArgentumBlocks.SILVER_BULBS,
                ArgentumBlocks.SILVER_BARS,
                ArgentumBlocks.SILVER_DOORS,
                ArgentumBlocks.SILVER_TRAPDOORS,
                ArgentumBlocks.SILVER_LATTICES,
                ArgentumBlocks.SILVER_PILLARS,
                ArgentumBlocks.CHISELED_SILVER,
                ArgentumBlocks.CUT_SILVERS,
                ArgentumBlocks.CUT_SILVER_SLABS,
                ArgentumBlocks.CUT_SILVER_STAIRS
        );
    }

    public static Stream<DeferredItem<ArmorItem>> silverArmor() {
        return Stream.of(
                ArgentumItems.SILVER_BOOTS,
                ArgentumItems.SILVER_LEGGINGS,
                ArgentumItems.SILVER_CHESTPLATE,
                ArgentumItems.SILVER_HELMET
        );
    }

    public static Stream<DeferredItem<? extends Item>> silverTools() {
        return Stream.of(
                ArgentumItems.SILVER_SWORD,
                ArgentumItems.SILVER_SHOVEL,
                ArgentumItems.SILVER_PICKAXE,
                ArgentumItems.SILVER_AXE,
                ArgentumItems.SILVER_HOE
        );
    }

}
