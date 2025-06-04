package galena.oreganized.data;

import galena.oreganized.OreganizedCarcinogenius;
import galena.oreganized.data.provider.OItemModelProvider;
import galena.oreganized.index.OBlocks;
import galena.oreganized.index.OItems;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;

public class OItemModels extends OItemModelProvider {

    public OItemModels(PackOutput output, ExistingFileHelper helper) {
        super(output, helper);
    }

    @Override
    public String getName() {
        return OreganizedCarcinogenius.NAMESPACE + " Item Models";
    }

    @Override
    protected void registerModels() {

        normalItem(OItems.RAW_ASBESTOS);
        normalItem(OItems.REFINED_ASBESTOS);

        block(OBlocks.ASBESTOS_BLOCK);
        block(OBlocks.RAW_ASBESTOS_BLOCK);
        block(OBlocks.ASBESTOS_ORE);
        block(OBlocks.DEEPSLATE_ASBESTOS_ORE);
    }

}
