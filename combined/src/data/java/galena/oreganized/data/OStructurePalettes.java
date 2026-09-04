package galena.oreganized.data;

import com.teamabnormals.blueprint.common.world.modification.structure.SimpleStructureRepaletter;
import com.teamabnormals.blueprint.common.world.modification.structure.StructureRepaletter;
import com.teamabnormals.blueprint.common.world.modification.structure.StructureRepaletterEntry;
import com.teamabnormals.blueprint.core.api.conditions.ConfigValueCondition;
import com.teamabnormals.blueprint.core.registry.BlueprintDataPackRegistries;
import galena.oreganized.OConstants;
import galena.oreganized.compat.ColorCompat;
import galena.oreganized.gothic.config.GothicConfigs;
import galena.oreganized.index.OBlocks;
import galena.oreganized.index.OConditionTypes;

import java.util.Comparator;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.StructureTags;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.conditions.ICondition;

public class OStructurePalettes {

    private static final ResourceKey<StructureRepaletterEntry> CLERIC_WINDOWS = ResourceKey.create(BlueprintDataPackRegistries.STRUCTURE_REPALETTERS, OConstants.modLoc("replace_cleric_windows"));

    private static StructureRepaletter[] replaceColored(Map<DyeColor, ? extends Holder<Block>> to, String from, Predicate<DyeColor> filter) {
        return to.entrySet().stream()
                .filter(it -> filter.test(it.getKey()))
                .sorted(Comparator.comparing(it -> it.getKey().getId()))
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

        context.register(
                CLERIC_WINDOWS,
                new StructureRepaletterEntry.Builder()
                        .repaletters(replaceColored(OBlocks.CRYSTAL_GLASS, "stained_glass", it -> !ColorCompat.isModded(it)))
                        .repaletters(replaceColored(OBlocks.CRYSTAL_GLASS_PANES, "stained_glass_pane", it -> !ColorCompat.isModded(it)))
                        .select(villages)
        );
    }

    public static void conditions(BiConsumer<ResourceKey<?>, ICondition> consumer) {
        consumer.accept(CLERIC_WINDOWS, new ConfigValueCondition(OConditionTypes.CONFIG.get(), GothicConfigs.COMMON.replaceClericWindows, "cleric_windows", Map.of(), false));
    }
}
