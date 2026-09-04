package galena.oreganized.data;

import galena.oreganized.OConstants;
import galena.oreganized.index.OTags;
import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EnchantmentTagsProvider;
import net.minecraft.world.item.enchantment.Enchantments;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class OEnchantmentTags extends EnchantmentTagsProvider {

    public OEnchantmentTags(PackOutput output, CompletableFuture<HolderLookup.Provider> future, @Nullable ExistingFileHelper helper) {
        super(output, future, OConstants.MOD_ID, helper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(OTags.Enchantments.PREVENTS_LEAD_CLOUD).add(Enchantments.SILK_TOUCH);
        tag(OTags.Enchantments.HEAT_IMMUNITY).add(Enchantments.FROST_WALKER);
    }
}
