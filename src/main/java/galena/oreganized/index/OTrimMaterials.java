package galena.oreganized.index;

import galena.oreganized.Oreganized;
import java.util.Map;
import net.minecraft.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraftforge.registries.ForgeRegistries;

public class OTrimMaterials {

    public static final ResourceKey<TrimMaterial> LEAD = createKey("lead");
    public static final ResourceKey<TrimMaterial> SILVER = createKey("silver");
    public static final ResourceKey<TrimMaterial> ELECTRUM = createKey("electrum");

    public static void bootstrap(BootstapContext<TrimMaterial> context) {
        register(context, LEAD, OItems.LEAD_INGOT.get(), Style.EMPTY.withColor(6119556), Map.of());
        register(context, SILVER, OItems.SILVER_INGOT.get(), Style.EMPTY.withColor(10663869), Map.of());
        register(context, ELECTRUM, OItems.ELECTRUM_INGOT.get(), Style.EMPTY.withColor(13747326), Map.of());
    }

    private static ResourceKey<TrimMaterial> createKey(String name) {
        return ResourceKey.create(Registries.TRIM_MATERIAL, Oreganized.modLoc(name));
    }

    private static void register(BootstapContext<TrimMaterial> context, ResourceKey<TrimMaterial> key, Item item, Style style, Map<ArmorMaterials, String> overrides) {
        ResourceLocation location = key.location();
        context.register(key, new TrimMaterial(location.getNamespace() + "_" + location.getPath(), ForgeRegistries.ITEMS.getHolder(item).get(), -1.0F, overrides, Component.translatable(Util.makeDescriptionId("trim_material", location)).withStyle(style)));
    }
}
