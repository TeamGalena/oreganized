package galena.oreganized.index;

import java.util.Map;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;

public class OTrimMaterials {

    public static final ResourceKey<TrimMaterial> LEAD = createKey("lead");
    public static final ResourceKey<TrimMaterial> SILVER = createKey("silver");
    public static final ResourceKey<TrimMaterial> ELECTRUM = createKey("electrum");

    public static void bootstrap(BootstrapContext<TrimMaterial> context) {
        register(context, LEAD, OItems.LEAD_INGOT, Style.EMPTY.withColor(6119556), Map.of());
        register(context, SILVER, OItems.SILVER_INGOT, Style.EMPTY.withColor(10663869), Map.of());
        register(context, ELECTRUM, OItems.ELECTRUM_INGOT, Style.EMPTY.withColor(13747326), Map.of());
    }

    private static ResourceKey<TrimMaterial> createKey(String name) {
        return ResourceKey.create(Registries.TRIM_MATERIAL, OConstants.modLoc(name));
    }

    private static void register(BootstrapContext<TrimMaterial> context, ResourceKey<TrimMaterial> key, Holder<Item> item, Style style, Map<Holder<ArmorMaterial>, String> overrides) {
        ResourceLocation location = key.location();
        context.register(key, new TrimMaterial(location.getNamespace() + "_" + location.getPath(), item, -1.0F, overrides, Component.translatable(Util.makeDescriptionId("trim_material", location)).withStyle(style)));
    }
}
