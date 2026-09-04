package galena.oreganized.armament.data;


import static com.tterrag.registrate.providers.RegistrateRecipeProvider.has;
import static galena.oreganized.data.provider.ORecipeProvider.compact;
import static galena.oreganized.data.provider.ORecipeProvider.unCompact;
import static net.minecraft.data.recipes.ShapedRecipeBuilder.shaped;
import static net.minecraft.data.recipes.ShapelessRecipeBuilder.shapeless;

import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import galena.oreganized.OConstants;
import galena.oreganized.armament.index.ArmamentBlocks;
import galena.oreganized.armament.index.ArmamentItems;
import galena.oreganized.index.OTags;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.Tags;

public class ArmamentRecipes {

    static void generate(RegistrateRecipeProvider consumer) {
        shaped(RecipeCategory.REDSTONE, ArmamentBlocks.SHRAPNEL_BOMB.get())
                .pattern("ABA")
                .pattern("BAB")
                .pattern("ABA")
                .define('A', Tags.Items.GUNPOWDERS)
                .define('B', OTags.Items.NUGGETS_LEAD)
                .unlockedBy("has_gunpowder", has(Tags.Items.GUNPOWDERS))
                .unlockedBy("has_lead_nugget", has(OTags.Items.NUGGETS_LEAD))
                .save(consumer);

        shapeless(RecipeCategory.TRANSPORTATION, ArmamentItems.SHRAPNEL_BOMB_MINECART.get())
                .requires(ArmamentBlocks.SHRAPNEL_BOMB.get())
                .requires(Items.MINECART)
                .unlockedBy("has_shrapnel_bomb", has(ArmamentBlocks.SHRAPNEL_BOMB.get()))
                .save(consumer);

        shaped(RecipeCategory.COMBAT, ArmamentItems.LEAD_BOLT.get(), 1)
                .pattern("A")
                .pattern("A")
                .define('A', OTags.Items.INGOTS_LEAD)
                .unlockedBy("has_lead", has(OTags.Items.INGOTS_LEAD))
                .save(consumer);

        shapeless(RecipeCategory.TOOLS, ArmamentItems.FLINT_AND_PEWTER.get(), 1)
                .requires(OTags.Items.INGOTS_LEAD)
                .requires(Items.FLINT)
                .unlockedBy("has_lead", has(OTags.Items.INGOTS_LEAD))
                .save(consumer);

        compact(ArmamentBlocks.LEAD_BOLT_CRATE.get().asItem(), ArmamentItems.LEAD_BOLT.get()).save(consumer);
        unCompact(ArmamentItems.LEAD_BOLT.get(), ArmamentBlocks.LEAD_BOLT_CRATE.get().asItem()).save(consumer, OConstants.modLoc("lead_bolt_from_crate"));
    }

}
