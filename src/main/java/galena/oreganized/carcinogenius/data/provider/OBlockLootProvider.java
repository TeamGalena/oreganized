package galena.oreganized.carcinogenius.data.provider;

import java.util.Set;
import java.util.function.Supplier;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public abstract class OBlockLootProvider extends BlockLootSubProvider {

    protected OBlockLootProvider(HolderLookup.Provider lookup) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), lookup);
    }

    public void dropSelf(Supplier<? extends Block> block) {
        super.dropSelf(block.get());
    }

    public void ore(Supplier<? extends Block> block, Supplier<? extends Item> drop) {
        super.add(block.get(), (result) -> createOreDrop(result, drop.get()));
    }

}
