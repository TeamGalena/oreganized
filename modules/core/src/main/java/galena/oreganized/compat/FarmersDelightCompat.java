package galena.oreganized.compat;

import java.util.function.BiFunction;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;
import vectorwing.farmersdelight.common.item.KnifeItem;

public class FarmersDelightCompat {

    public static final BiFunction<Tier, Item.Properties, ? extends Item> KNIFE_FACTORY = KnifeItem::new;

}
