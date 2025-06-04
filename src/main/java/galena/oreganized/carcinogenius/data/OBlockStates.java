package galena.oreganized.carcinogenius.data;

import galena.oreganized.carcinogenius.OreganizedCarcinogenius;
import galena.oreganized.carcinogenius.data.provider.OBlockStateProvider;
import galena.oreganized.carcinogenius.index.OBlocks;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;

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
        simpleBlock(OBlocks.ASBESTOS_ORE);
        simpleBlock(OBlocks.DEEPSLATE_ASBESTOS_ORE);

        simpleBlock(OBlocks.ASBESTOS_BLOCK.get(), cubeBottomTop(OBlocks.ASBESTOS_BLOCK));
        simpleBlock(OBlocks.RAW_ASBESTOS_BLOCK);
    }

}
