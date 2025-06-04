package galena.oreganized.carcinogenius.data;

import galena.oreganized.carcinogenius.OreganizedCarcinogenius;
import galena.oreganized.carcinogenius.data.provider.OLangProvider;
import galena.oreganized.carcinogenius.index.OCBlocks;
import galena.oreganized.carcinogenius.index.OCEffects;
import galena.oreganized.carcinogenius.index.OCPotions;
import net.minecraft.data.PackOutput;

public class OLang extends OLangProvider {

    public OLang(PackOutput output) {
        super(output, OreganizedCarcinogenius.NAMESPACE, "en_us");
    }

    @Override
    protected void addTranslations() {
        addBlock(OCBlocks.DEEPSLATE_ASBESTOS_ORE, "Deepslate Asbestos Ore");
        addBlock(OCBlocks.ASBESTOS_ORE, "Asbestos Ore");
        addBlock(OCBlocks.ASBESTOS_BLOCK, "Block of Asbestos");
        addBlock(OCBlocks.RAW_ASBESTOS_BLOCK, "Block of Raw Asbestos");

        addEffect(OCEffects.LUNG_DAMAGE, "Lung Damage");
        addPotion(OCPotions.LUNG_DAMAGE, "Lung Damage");
    }

}
