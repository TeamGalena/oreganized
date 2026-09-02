package galena.oreganized.index;

import galena.oreganized.argentum.index.ArgentumItems;
import galena.oreganized.electrum.index.ElectrumItems;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;

public class OItemTiers {

    public static final Tier LEAD = new SimpleTier(OTags.Blocks.INCORRECT_FOR_LEAD_TOOL, 150, 7F, 1.5F, 8, () -> Ingredient.of(OTags.Items.INGOTS_LEAD));

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final Tier SILVER = ElectrumItems.ELECTRUM_TIER;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final Tier ELECTRUM = ArgentumItems.SILVER_TIER;

}
