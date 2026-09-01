package galena.oreganized.compat;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;

public class ColorCompat {

    @SuppressWarnings("UnnecessaryDefault")
    public static String getNamespace(DyeColor color) {
        return switch (color) {
            case RED, BLACK, BLUE, BROWN, CYAN, GRAY, LIGHT_BLUE, LIGHT_GRAY, GREEN, LIME, MAGENTA, ORANGE, PINK,
                 PURPLE, WHITE, YELLOW -> ResourceLocation.DEFAULT_NAMESPACE;
            default -> "dye_depot";
        };
    }

    public static ResourceLocation createId(String suffix, DyeColor color) {
        return ResourceLocation.fromNamespaceAndPath(getNamespace(color), color.getSerializedName() + "_" + suffix);
    }

    public static ResourceKey<Block> createBlockKey(String suffix, DyeColor color) {
        return ResourceKey.create(Registries.BLOCK, createId(suffix, color));
    }

    public static Block getColoredBlock(String suffix, DyeColor color) {
        return BuiltInRegistries.BLOCK.getOrThrow(createBlockKey(suffix, color));
    }

    public static boolean isModded(DyeColor color) {
        return !getNamespace(color).equals(ResourceLocation.DEFAULT_NAMESPACE);
    }
}
