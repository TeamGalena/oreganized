package galena.oreganized.data;

import galena.oreganized.OreganizedCarcinogenius;
import galena.oreganized.data.provider.OLangProvider;
import galena.oreganized.index.OBlocks;
import galena.oreganized.index.OEffects;
import galena.oreganized.index.OPotions;
import net.minecraft.data.PackOutput;

public class OLang extends OLangProvider {

    public OLang(PackOutput output) {
        super(output, OreganizedCarcinogenius.NAMESPACE, "en_us");
    }

    @Override
    protected void addTranslations() {
        addBlock(OBlocks.DEEPSLATE_ASBESTOS_ORE, "Deepslate Asbestos Ore");
        addBlock(OBlocks.ASBESTOS_ORE, "Asbestos Ore");
        addBlock(OBlocks.ASBESTOS_BLOCK, "Block of Asbestos");
        addBlock(OBlocks.RAW_ASBESTOS_BLOCK, "Block of Raw Asbestos");

        addEffect(OEffects.LUNG_DAMAGE, "Lung Damage");
        addPotion(OPotions.LUNG_DAMAGE, "Lung Damage");
    }

}
