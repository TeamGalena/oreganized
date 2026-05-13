package galena.oreganized.data;

import com.possible_triangle.multikulti.datagen.conditions.Condition;
import com.possible_triangle.multikulti.datagen.conditions.Conditional;
import com.possible_triangle.multikulti.datagen.conditions.ModLoaded;
import galena.oreganized.compat.ColorCompat;
import java.util.List;
import java.util.Optional;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;

public class ConditionalData {

    private static Optional<Condition> dyeCondition(DyeColor color) {
        var namespace = ColorCompat.getNamespace(color);
        if (namespace.equals(ResourceLocation.DEFAULT_NAMESPACE)) return Optional.empty();
        return Optional.of(new ModLoaded(namespace));
    }

    public static <T> T dyed(DyeColor color, T value) {
        return dyeCondition(color)
                .map(condition -> Conditional.with(value, condition))
                .orElse(value);
    }

    public static <T> void dyed(DyeColor color, T value, Runnable block) {
        dyeCondition(color).ifPresentOrElse(
                condition -> Conditional.with(value, List.of(condition), block),
                block
        );
    }

}
