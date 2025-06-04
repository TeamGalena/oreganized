package galena.oreganized.carcinogenius.data;

import galena.oreganized.carcinogenius.OreganizedCarcinogenius;
import galena.oreganized.carcinogenius.data.provider.OItemModelProvider;
import galena.oreganized.carcinogenius.index.OCBlocks;
import galena.oreganized.carcinogenius.index.OCItems;
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

        normalItem(OCItems.RAW_ASBESTOS);
        normalItem(OCItems.REFINED_ASBESTOS);

        block(OCBlocks.ASBESTOS_BLOCK);
        block(OCBlocks.RAW_ASBESTOS_BLOCK);
        block(OCBlocks.ASBESTOS_ORE);
        block(OCBlocks.DEEPSLATE_ASBESTOS_ORE);
    }

}
