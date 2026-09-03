package galena.oreganized.armament.index;

import galena.oreganized.OConstants;
import galena.oreganized.armament.world.item.FlintAndPewterItem;
import galena.oreganized.armament.world.item.LeadBoltItem;
import galena.oreganized.armament.world.item.MinecartShrapnelBombItem;
import galena.oreganized.register.ItemRegistryHelper;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.item.Item;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredItem;

@Mod(OConstants.MOD_ID)
public class ArmamentItems {

    private static final ItemRegistryHelper ITEMS = OConstants.REGISTRY_HELPER.getItemSubHelper();

    public static final DeferredItem<Item> LEAD_BOLT = ITEMS.createItem("lead_bolt",
            () -> new LeadBoltItem(new Item.Properties()));

    public static final DeferredItem<Item> FLINT_AND_PEWTER = ITEMS.createItem("flint_and_pewter",
            () -> new FlintAndPewterItem(new Item.Properties().durability(64)));

    public static final DeferredItem<Item> SHRAPNEL_BOMB_MINECART = ITEMS.createItem("shrapnel_bomb_minecart",
            () -> new MinecartShrapnelBombItem(AbstractMinecart.Type.TNT, ArmamentEntities.SHRAPNEL_BOMB_MINECART));



}
