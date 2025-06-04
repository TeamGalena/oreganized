package galena.oreganized.data;

import galena.oreganized.OreganizedCarcinogenius;
import galena.oreganized.data.provider.OBlockStateProvider;
import galena.oreganized.index.OBlocks;
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
