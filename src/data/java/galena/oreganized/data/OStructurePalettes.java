package galena.oreganized.data;

import com.teamabnormals.blueprint.common.world.modification.structure.SimpleStructureRepaletter;
import com.teamabnormals.blueprint.common.world.modification.structure.StructureRepaletter;
import com.teamabnormals.blueprint.common.world.modification.structure.StructureRepaletterEntry;
import com.teamabnormals.blueprint.core.registry.BlueprintDataPackRegistries;
import galena.oreganized.Oreganized;
import galena.oreganized.compat.ColorCompat;
import galena.oreganized.index.OBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.StructureTags;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;

import java.util.Map;
import java.util.function.Predicate;

public class OStructurePalettes {

    private static StructureRepaletter[] replaceColored(Map<DyeColor, ? extends Holder<Block>> to, String from, Predicate<DyeColor> filter) {
        return to.entrySet().stream()
                .filter(it -> filter.test(it.getKey()))
                .map(entry ->
                        new SimpleStructureRepaletter(
                                ColorCompat.getColoredBlock(from, entry.getKey()),
                                entry.getValue().value()
                        )
                ).toArray(StructureRepaletter[]::new);
    }

    public static void bootstrap(BootstrapContext<StructureRepaletterEntry> context) {
        var structures = context.lookup(Registries.STRUCTURE);
        var villages = structures.getOrThrow(StructureTags.VILLAGE);

        // TODO dye depot condition
        context.register(
                ResourceKey.create(BlueprintDataPackRegistries.STRUCTURE_REPALETTERS, Oreganized.modLoc("replace_cleric_windows")),
                new StructureRepaletterEntry.Builder()
                        .repaletters(replaceColored(OBlocks.CRYSTAL_GLASS, "stained_glass", it -> !ColorCompat.isModded(it)))
                        .repaletters(replaceColored(OBlocks.CRYSTAL_GLASS_PANES, "stained_glass_pane", it -> !ColorCompat.isModded(it)))
                        .select(villages)
        );
    }

}
