package galena.oreganized.data;

import com.teamabnormals.blueprint.core.api.BlueprintTrims;
import galena.oreganized.Oreganized;
import galena.oreganized.index.OTrimMaterials;
import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.SpriteSourceProvider;

public class OSpriteSourceProvider extends SpriteSourceProvider {

    public OSpriteSourceProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookup, ExistingFileHelper helper) {
        super(output, lookup, Oreganized.MOD_ID, helper);
    }

    @Override
    protected void gather() {
        atlas(BlueprintTrims.ARMOR_TRIMS_ATLAS)
                .addSource(BlueprintTrims.materialPatternPermutations(
                OTrimMaterials.LEAD,
                OTrimMaterials.SILVER,
                OTrimMaterials.ELECTRUM
        ));
    }
}
