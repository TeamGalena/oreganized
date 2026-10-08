package galena.oreganized.antiques.index;

import galena.oreganized.OConstants;
import galena.oreganized.register.ItemRegistryHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredItem;

@Mod(OConstants.MOD_ID)
public class AntiquesItems {

    private static final ItemRegistryHelper ITEMS = OConstants.REGISTRY_HELPER.getItemSubHelper();

    public static final DeferredItem<Item> MUSIC_DISC_RADIO = ITEMS.createItem("music_disc_radio",
            () -> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.RARE).jukeboxPlayable(AntiquesSongs.RADIO)));

}
