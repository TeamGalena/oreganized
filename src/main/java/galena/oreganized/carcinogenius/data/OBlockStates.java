package galena.oreganized.carcinogenius.data;

import galena.oreganized.carcinogenius.OreganizedCarcinogenius;
import galena.oreganized.carcinogenius.data.provider.OBlockStateProvider;
import galena.oreganized.carcinogenius.index.OCBlocks;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class OBlockStates extends OBlockStateProvider {

    public OBlockStates(PackOutput output, ExistingFileHelper helper) {
        super(output, helper);
    }

    @Override
    public String getName() {
        return OreganizedCarcinogenius.NAMESPACE + " Block States";
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(OCBlocks.ASBESTOS_ORE);
        simpleBlock(OCBlocks.DEEPSLATE_ASBESTOS_ORE);

        simpleBlock(OCBlocks.ASBESTOS_BLOCK.get(), cubeBottomTop(OCBlocks.ASBESTOS_BLOCK));
        simpleBlock(OCBlocks.RAW_ASBESTOS_BLOCK);
    }

}
