package galena.oreganized.engraved.index;

import galena.oreganized.OConstants;
import galena.oreganized.engraved.world.item.BushHammerItem;
import galena.oreganized.index.OTags;
import galena.oreganized.register.ItemRegistryHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.SimpleTier;
import net.neoforged.neoforge.registries.DeferredItem;

@Mod(OConstants.MOD_ID)
public class EngravedItems {

    private static final ItemRegistryHelper ITEMS = OConstants.REGISTRY_HELPER.getItemSubHelper();

    private static final Tier HAMMER_TIER = new SimpleTier(OTags.Blocks.INCORRECT_FOR_LEAD_TOOL, 150, 7F, 1.5F, 8, () -> Ingredient.of(OTags.Items.INGOTS_LEAD));

    public static final DeferredItem<Item> BUSH_HAMMER = ITEMS.createItem("bush_hammer",
            () -> new BushHammerItem(HAMMER_TIER, new Item.Properties().stacksTo(1)));

}
