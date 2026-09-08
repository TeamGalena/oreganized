package galena.oreganized.plumbum.data;

import galena.oreganized.OConstants;
import galena.oreganized.data.ColorCompat;
import galena.oreganized.data.ODatagen;
import galena.oreganized.waxed.index.WaxedBlocks;
import java.util.Optional;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.common.conditions.ModLoadedCondition;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;
import net.neoforged.neoforge.registries.datamaps.builtin.Waxable;

@Mod(OConstants.MOD_ID)
public class WaxedDataMaps {

    public WaxedDataMaps() {
        ODatagen.addDataMapProvider(this::generate);
    }

    private void generate(DataMapProvider provider) {
        var waxables = provider.builder(NeoForgeDataMaps.WAXABLES);

        WaxedBlocks.WAXED_CONCRETE_POWDER.forEach((color, waxed) -> {
            var unwaxed = ColorCompat.createBlockKey("concrete_powder", color);
            var conditions = Optional.of(unwaxed.location().getNamespace())
                    .filter(it -> !it.equals(ResourceLocation.DEFAULT_NAMESPACE))
                    .map(ModLoadedCondition::new)
                    .stream()
                    .toArray(ICondition[]::new);

            waxables.add(unwaxed, new Waxable(waxed.get()), false, conditions);
        });
    }

}
