package galena.oreganized.antiques.data;

import static galena.oreganized.data.extensions.OBlockStateExtensions.*;
import static galena.oreganized.data.extensions.OItemModelExtensions.normalItem;

import com.tterrag.registrate.providers.RegistrateItemModelProvider;
import galena.oreganized.OConstants;
import galena.oreganized.antiques.index.AntiquesItems;
import galena.oreganized.data.ODatagen;
import net.neoforged.fml.common.Mod;

@Mod(OConstants.MOD_ID)
public class AntiquesItemModels {

    public AntiquesItemModels() {
        ODatagen.addItemModelProvider(this::generate);
    }

    private void generate(RegistrateItemModelProvider provider) {
        normalItem(provider, AntiquesItems.MUSIC_DISC_RADIO);
    }

}
