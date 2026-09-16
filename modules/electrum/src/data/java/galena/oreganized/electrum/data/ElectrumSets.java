package galena.oreganized.electrum.data;

import galena.oreganized.electrum.index.ElectrumItems;
import java.util.stream.Stream;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;

public class ElectrumSets {

    public static Stream<DeferredItem<? extends Item>> electrumTools() {
        return Stream.of(
                ElectrumItems.ELECTRUM_SWORD,
                ElectrumItems.ELECTRUM_SHOVEL,
                ElectrumItems.ELECTRUM_PICKAXE,
                ElectrumItems.ELECTRUM_AXE,
                ElectrumItems.ELECTRUM_HOE,
                ElectrumItems.ELECTRUM_KNIFE,
                ElectrumItems.ELECTRUM_MACHETE
        );
    }

}
