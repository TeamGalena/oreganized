package galena.oreganized.data.provider;

import galena.oreganized.data.ConditionalData;
import java.util.Set;
import java.util.function.Supplier;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public abstract class OBlockLootProvider extends BlockLootSubProvider {

    protected OBlockLootProvider(HolderLookup.Provider lookup) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), lookup);
    }

    public void dropSelf(Supplier<? extends Block> block) {
        dropSelf(block.get());
    }

    public void slab(Supplier<? extends Block> slab) {
        this.add(slab.get(), this::createSlabItemTable);
    }

    public void dropOther(Supplier<? extends Block> brokenBlock, ItemLike droppedBlock) {
        dropOther(brokenBlock.get(), droppedBlock);
    }

    public void dropAsSilk(Supplier<? extends Block> block) {
        dropWhenSilkTouch(block.get());
    }

    public void dropWithSilk(Supplier<? extends Block> block, Supplier<? extends ItemLike> drop) {
        add(block.get(), (result) -> createSingleItemTableWithSilkTouch(result, drop.get()));
    }

    public void ore(Supplier<? extends Block> block, Supplier<? extends Item> drop) {
        add(block.get(), (result) -> createOreDrop(result, drop.get()));
    }

    public void ore(Supplier<? extends Block> block, Item drop) {
        add(block.get(), (result) -> createOreDrop(result, drop));
    }

    public void cauldron(Supplier<? extends Block> block) {
        dropOther(block, Blocks.CAULDRON);
    }

    public void dropNothing(Supplier<? extends Block> block) {
        dropOther(block, Blocks.AIR);
    }

    public void dyed(DyeColor color, Runnable block) {
        ConditionalData.dyed(color, this, block);
    }

}
