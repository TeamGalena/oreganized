package galena.oreganized.data;

import galena.oreganized.api.Tarnishable;
import galena.oreganized.compat.ColorCompat;
import galena.oreganized.index.OBlocks;
import galena.oreganized.index.ODataMapTypes;
import galena.oreganized.index.TarnishedBlocks;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.common.conditions.ModLoadedCondition;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.datamaps.builtin.Compostable;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;
import net.neoforged.neoforge.registries.datamaps.builtin.Waxable;

public class ODataMaps extends DataMapProvider {

    public ODataMaps(PackOutput output, CompletableFuture<HolderLookup.Provider> lookup) {
        super(output, lookup);
    }

    private static ResourceKey<Item> itemKey(DeferredBlock<?> holder) {
        return ResourceKey.create(Registries.ITEM, holder.getKey().location());
    }

    @Override
    protected void gather(HolderLookup.Provider provider) {
        var compostable = builder(NeoForgeDataMaps.COMPOSTABLES);

        compostable.add(itemKey(OBlocks.WHITE_DATURA), new Compostable(0.65F, true), false);
        compostable.add(itemKey(OBlocks.PURPLE_DATURA), new Compostable(0.65F, true), false);

        var waxables = builder(NeoForgeDataMaps.WAXABLES);

        waxables.add(OBlocks.SPOTTED_GLANCE, new Waxable(OBlocks.WAXED_SPOTTED_GLANCE.get()), false);

        OBlocks.WAXED_CONCRETE_POWDER.forEach((color, waxed) -> {
            var unwaxed = ColorCompat.createBlockKey("concrete_powder", color);
            var conditions = Optional.of(unwaxed.location().getNamespace())
                    .filter(it -> !it.equals(ResourceLocation.DEFAULT_NAMESPACE))
                    .map(ModLoadedCondition::new)
                    .stream()
                    .toArray(ICondition[]::new);
            waxables.add(unwaxed, new Waxable(waxed.get()), false, conditions);
        });

        registerTarnishable(OBlocks.SILVER_BLOCKS);
        registerTarnishable(OBlocks.SILVER_BULBS);
        registerTarnishable(OBlocks.SILVER_BARS);
        registerTarnishable(OBlocks.SILVER_DOORS);
        registerTarnishable(OBlocks.SILVER_TRAPDOORS);
        registerTarnishable(OBlocks.SILVER_LATTICES);
        registerTarnishable(OBlocks.SILVER_PILLARS);
        registerTarnishable(OBlocks.CHISELED_SILVER);
        registerTarnishable(OBlocks.CUT_SILVERS);
        registerTarnishable(OBlocks.CUT_SILVER_SLABS);
        registerTarnishable(OBlocks.CUT_SILVER_STAIRS);
    }

    private void registerTarnishable(TarnishedBlocks<?> blocks) {
        var tarnishables = builder(ODataMapTypes.TARNISHABLES);
        tarnishables.add(blocks.base(), new Tarnishable(blocks.blemished().get()), false);
        tarnishables.add(blocks.blemished(), new Tarnishable(blocks.tarnished().get()), false);
    }

}
