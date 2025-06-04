package galena.oreganized.compat.farmers_delight;

import galena.oreganized.index.OItemTiers;
import java.util.function.Function;
import net.minecraft.world.item.Item;
import vectorwing.farmersdelight.common.item.KnifeItem;

public class FarmersDelightCompat {

    public static final Function<Item.Properties, ? extends Item> KNIFE_FACTORY = (it) ->
            new KnifeItem(OItemTiers.ELECTRUM, 0.5F, -1.8F, it);

}
