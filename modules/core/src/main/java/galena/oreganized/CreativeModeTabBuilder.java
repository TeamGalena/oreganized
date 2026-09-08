package galena.oreganized;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Consumer;
import java.util.stream.Stream;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTab.TabVisibility;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public final class CreativeModeTabBuilder {

    private final BuildCreativeModeTabContentsEvent event;

    public CreativeModeTabBuilder(BuildCreativeModeTabContentsEvent event) {
        this.event = event;
    }

    public void add(ResourceKey<CreativeModeTab> tab, Consumer<Scoped> factory) {
        if (event.getTabKey() == tab) factory.accept(new Scoped());
    }

    public void add(Collection<ResourceKey<CreativeModeTab>> tabs, Consumer<Scoped> factory) {
        if (tabs.contains(event.getTabKey())) factory.accept(new Scoped());
    }

    public class Scoped {
        public final void putAfter(ItemLike after, Stream<? extends ItemLike> suppliers) {
            var reference = new ItemStack(after);
            suppliers
                    .map(ItemStack::new)
                    .forEach(it -> event.insertAfter(reference, it, TabVisibility.PARENT_AND_SEARCH_TABS));
        }

        public final void putAfter(ItemLike after, ItemLike... suppliers) {
            putAfter(after, Arrays.stream(suppliers));
        }

        public final void putBefore(ItemLike after, Stream<? extends ItemLike> suppliers) {
            var reference = new ItemStack(after);
            suppliers
                    .map(ItemStack::new)
                    .forEach(it -> event.insertBefore(reference, it, TabVisibility.PARENT_AND_SEARCH_TABS));
        }

        public final void putBefore(ItemLike before, ItemLike... suppliers) {
            putBefore(before, Arrays.stream(suppliers));
        }
    }

}
