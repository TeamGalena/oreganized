package galena.oreganized.world.recipe;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.level.block.state.pattern.BlockInWorld;

public record BlockRecipeInput(BlockInWorld block) implements RecipeInput {

    @Override
    public ItemStack getItem(int i) {
        return block.getState().getBlock().asItem().getDefaultInstance();
    }

    @Override
    public int size() {
        return 1;
    }

}
