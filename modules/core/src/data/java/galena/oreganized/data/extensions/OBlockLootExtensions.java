package galena.oreganized.data.extensions;

import com.tterrag.registrate.providers.loot.RegistrateBlockLootTables;

import galena.oreganized.index.sets.StoneSet;
import net.minecraft.core.Holder;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class OBlockLootExtensions {

    public static void dropSelf(RegistrateBlockLootTables provider, Holder<? extends Block> block) {
        provider.dropSelf(block.value());
    }

    public static void dropSlab(RegistrateBlockLootTables provider, Holder<? extends Block> slab) {
        provider.add(slab.value(), provider.createSlabItemTable(slab.value()));
    }

    public static void dropOther(RegistrateBlockLootTables provider, Holder<? extends Block> brokenBlock, ItemLike droppedBlock) {
        provider.dropOther(brokenBlock.value(), droppedBlock);
    }

    public static void dropAsSilk(RegistrateBlockLootTables provider, Holder<? extends Block> block) {
        provider.dropWhenSilkTouch(block.value());
    }

    public static void dropWithSilk(RegistrateBlockLootTables provider, Holder<? extends Block> block, Holder<? extends ItemLike> drop) {
        provider.add(block.value(), provider.createSingleItemTableWithSilkTouch(block.value(), drop.value()));
    }

    public static void dropOre(RegistrateBlockLootTables provider, Holder<? extends Block> block, Holder<? extends Item> drop) {
        provider.add(block.value(), provider.createOreDrop(block.value(), drop.value()));
    }

    public static void dropOre(RegistrateBlockLootTables provider, Holder<? extends Block> block, Item drop) {
        provider.add(block.value(), provider.createOreDrop(block.value(), drop));
    }

    public static void dropCauldron(RegistrateBlockLootTables provider, Holder<? extends Block> block) {
        dropOther(provider, block, Blocks.CAULDRON);
    }

    public static void dropNothing(RegistrateBlockLootTables provider, Holder<? extends Block> block) {
        dropOther(provider, block, Blocks.AIR);
    }

    public static void dropPottedPlant(RegistrateBlockLootTables provider, Holder<? extends Block> block) {
        provider.dropPottedContents(block.value());
    }

    public static void dropStoneSet(RegistrateBlockLootTables provider, StoneSet<?, ?, ?, ?> set) {
        dropSelf(provider, set.block());
        dropSlab(provider, set.slab());
        dropSelf(provider, set.stairs());
        dropSelf(provider, set.wall());
    }

}
