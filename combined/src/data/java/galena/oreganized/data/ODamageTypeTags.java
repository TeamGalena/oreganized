package galena.oreganized.data;

import galena.oreganized.Oreganized;
import galena.oreganized.index.ODamageSources;
import java.util.concurrent.CompletableFuture;
import javax.annotation.Nullable;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageType;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

public class ODamageTypeTags extends TagsProvider<DamageType> {

    public ODamageTypeTags(PackOutput output, CompletableFuture<HolderLookup.Provider> future, @Nullable ExistingFileHelper helper) {
        super(output, Registries.DAMAGE_TYPE, future, Oreganized.MOD_ID, helper);
    }

    @Override
    public @NotNull String getName() {
        return "Oreganized DamageType Tags";
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
      tag(DamageTypeTags.IS_FIRE).add(ODamageSources.MOLTEN_LEAD);
      tag(DamageTypeTags.NO_KNOCKBACK).add(ODamageSources.MOLTEN_LEAD);
    }
}
