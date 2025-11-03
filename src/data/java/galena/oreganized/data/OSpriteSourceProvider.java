package galena.oreganized.data;

import com.teamabnormals.blueprint.core.api.BlueprintTrims;
import galena.oreganized.Oreganized;
import galena.oreganized.index.OTrimMaterials;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.SpriteSourceProvider;

public class OSpriteSourceProvider extends SpriteSourceProvider {

    public OSpriteSourceProvider(PackOutput output, ExistingFileHelper helper) {
        super(output, helper, Oreganized.MOD_ID);
    }

    @Override
    protected void addSources() {
        this.atlas(BlueprintTrims.ARMOR_TRIMS_ATLAS)
                .addSource(BlueprintTrims.materialPatternPermutations(
                OTrimMaterials.LEAD,
                OTrimMaterials.SILVER,
                OTrimMaterials.ELECTRUM
        ));
    }
}
