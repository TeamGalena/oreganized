package galena.oreganized.index;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;

public class OItemTiers {

    public static final Tier LEAD = new SimpleTier(BlockTags.INCORRECT_FOR_IRON_TOOL, 150, 7F, 1.5F, 8, () -> Ingredient.of(OTags.Items.INGOTS_LEAD));
    public static final Tier ELECTRUM = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1561, 8F, 3.0F, 14, () -> Ingredient.of(OTags.Items.INGOTS_ELECTRUM));

}
