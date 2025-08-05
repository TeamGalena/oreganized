package galena.oreganized.data;

import galena.oreganized.Oreganized;
import galena.oreganized.index.OTags;
import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EnchantmentTagsProvider;
import net.minecraft.world.item.enchantment.Enchantments;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class OEnchantmentTags extends EnchantmentTagsProvider {

    public OEnchantmentTags(PackOutput output, CompletableFuture<HolderLookup.Provider> future, @Nullable ExistingFileHelper helper) {
        super(output, future, Oreganized.MOD_ID, helper);
    }

    @Override
    public @NotNull String getName() {
        return "Oreganized Enchantment Tags";
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(OTags.Enchantments.PREVENTS_LEAD_CLOUD).add(Enchantments.SILK_TOUCH);
        tag(OTags.Enchantments.HEAD_IMMUNITY).add(Enchantments.FROST_WALKER);
    }
}
