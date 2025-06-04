package galena.oreganized.index;

import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Stream;
import net.minecraft.world.item.DyeColor;
import net.minecraftforge.fml.ModList;

public class DyeColors {

    private static Stream<DyeColor> vanillaColors() {
        return Stream.of(
                DyeColor.WHITE,
                DyeColor.LIGHT_GRAY,
                DyeColor.GRAY,
                DyeColor.BLACK,
                DyeColor.BROWN,
                DyeColor.RED,
                DyeColor.ORANGE,
                DyeColor.YELLOW,
                DyeColor.LIME,
                DyeColor.GREEN,
                DyeColor.CYAN,
                DyeColor.LIGHT_BLUE,
                DyeColor.BLUE,
                DyeColor.PURPLE,
                DyeColor.MAGENTA,
                DyeColor.PINK
        );
    }

    private static Stream<DyeColor> depotColors() {
        if (!ModList.get().isLoaded("dye_depot")) return Stream.empty();
        return Stream.of(
                        "amber",
                        "aqua",
                        "beige",
                        "coral",
                        "forest",
                        "ginger",
                        "indigo",
                        "maroon",
                        "mint",
                        "navy",
                        "olive",
                        "rose",
                        "slate",
                        "tan",
                        "teal",
                        "verdant"
                )
                .map(it -> DyeColor.byName(it, null))
                .filter(Objects::nonNull);
    }

    public static Stream<DyeColor> supported() {
        return Stream.of(
                vanillaColors(),
                depotColors()
        ).flatMap(Function.identity());
    }

}
