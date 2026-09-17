package galena.oreganized.register;

import static net.minecraft.resources.ResourceLocation.fromNamespaceAndPath;

import galena.oreganized.OConstants;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;

public class TagHelper<T> {

    private final ResourceKey<Registry<T>> registry;

    public TagHelper(ResourceKey<Registry<T>> registry) {
        this.registry = registry;
    }

    public TagKey<T> tag(ResourceLocation id) {
        return TagKey.create(registry, id);
    }

    public TagKey<T> tag(String namespace, String path) {
        return tag(fromNamespaceAndPath(namespace, path));
    }

    public TagKey<T> modTag(String path) {
        return tag(OConstants.modLoc(path));
    }

    public TagKey<T> commonTag(String path) {
        return tag("c", path);
    }

    public TagKey<T> copy(TagKey<?> from) {
        return tag(from.location());
    }

}
