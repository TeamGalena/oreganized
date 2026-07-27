package galena.oreganized.data;

import galena.oreganized.Oreganized;
import galena.oreganized.index.OTags;
import java.util.concurrent.CompletableFuture;
import javax.annotation.Nullable;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.EntityType;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class OEntityTags extends EntityTypeTagsProvider {

    public OEntityTags(PackOutput output, CompletableFuture<HolderLookup.Provider> future, @Nullable ExistingFileHelper help) {
        super(output, future, Oreganized.MOD_ID, help);
    }

    @Override
    public String getName() {
        return "Oreganized Entity Type Tags";
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(OTags.Entities.LIGHTER_THAN_LEAD).add(EntityType.IRON_GOLEM);
        tag(OTags.Entities.BOLT_SUSCEPTIBLE)
                .add(EntityType.IRON_GOLEM)
                .addOptional(ResourceLocation.fromNamespaceAndPath("caverns_and_chasms", "copper_golem"));
        tag(OTags.Entities.BOLT_RESISTANT)
                .addOptional(ResourceLocation.fromNamespaceAndPath("alexsmobs", "mimicube"))
                .addOptional(ResourceLocation.fromNamespaceAndPath("caverns_and_chasms", "mime"));
        tag(OTags.Entities.SCARED_OF_GARGOYLE).addTags(EntityTypeTags.UNDEAD);
    }
}
