package galena.oreganized.antiques.data;


import com.tterrag.registrate.providers.RegistrateLangProvider;
import galena.oreganized.OConstants;
import galena.oreganized.antiques.index.AntiquesBlocks;
import galena.oreganized.data.ODatagen;
import net.neoforged.fml.common.Mod;

@Mod(OConstants.MOD_ID)
public class AntiquesLang {

    public AntiquesLang() {
        ODatagen.addLangProvider(this::generate);
    }

    private void generate(RegistrateLangProvider provider) {
        provider.addBlock(AntiquesBlocks.ANTIQUES_PILE, "Tarnished Antiques");
    }

}
