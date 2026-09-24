package galena.oreganized.index;

import galena.oreganized.ModCompat;

import java.util.function.Consumer;
import java.util.stream.Stream;

import net.minecraft.world.item.DyeColor;

public class DyeColors {

    /**
     * these are sorted the way they appear in the creative mode tabs
     * this sorting is persisted all the way through the IDyedSets.stream() method
     */
    public static Stream<DyeColor> supported() {
        var builder = Stream.<DyeColor>builder();

        builder.add(DyeColor.WHITE);
        builder.add(DyeColor.LIGHT_GRAY);
        builder.add(DyeColor.GRAY);
        builder.add(DyeColor.BLACK);
        builder.add(DyeColor.BROWN);
        addDepotDye(builder, "maroon");
        addDepotDye(builder, "rose");
        builder.add(DyeColor.RED);
        addDepotDye(builder, "coral");
        addDepotDye(builder, "ginger");
        builder.add(DyeColor.ORANGE);
        addDepotDye(builder, "tan");
        addDepotDye(builder, "beige");
        builder.add(DyeColor.YELLOW);
        addDepotDye(builder, "amber");
        addDepotDye(builder, "olive");
        builder.add(DyeColor.LIME);
        addDepotDye(builder, "forest");
        builder.add(DyeColor.GREEN);
        addDepotDye(builder, "verdant");
        addDepotDye(builder, "teal");
        builder.add(DyeColor.CYAN);
        addDepotDye(builder, "mint");
        addDepotDye(builder, "aqua");
        builder.add(DyeColor.LIGHT_BLUE);
        builder.add(DyeColor.BLUE);
        addDepotDye(builder, "slate");
        addDepotDye(builder, "navy");
        addDepotDye(builder, "indigo");
        builder.add(DyeColor.PURPLE);
        builder.add(DyeColor.MAGENTA);
        builder.add(DyeColor.PINK);

        return builder.build();
    }

    private static void addDepotDye(Consumer<DyeColor> builder, String name) {
        if (ModCompat.DYE_DEPOT_LOADED) {
            builder.accept(DyeColor.byName(name, null));
        }
    }

}
