package galena.oreganized.index;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;

public class OItemTiers {

    public static final Tier LEAD = new SimpleTier(OTags.Blocks.INCORRECT_FOR_LEAD_TOOL, 150, 7F, 1.5F, 8, () -> Ingredient.of(OTags.Items.INGOTS_LEAD));
    public static final Tier ELECTRUM = new SimpleTier(OTags.Blocks.INCORRECT_FOR_ELECTRUM_TOOL, 1561, 8F, 3.0F, 14, () -> Ingredient.of(OTags.Items.INGOTS_ELECTRUM));
    public static final Tier SILVER = new SimpleTier(OTags.Blocks.INCORRECT_FOR_SILVER_TOOL, 191, 5F, 2.0F, 13, () -> Ingredient.of(OTags.Items.INGOTS_SILVER));

}
