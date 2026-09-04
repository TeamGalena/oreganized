package galena.oreganized.device.data;

import com.tterrag.registrate.providers.ProviderType;
import galena.oreganized.OConstants;
import galena.oreganized.data.provider.ODatagen;
import net.neoforged.fml.common.Mod;

@Mod(OConstants.MOD_ID)
public class EngravedData {

    public EngravedData() {
        ODatagen.REGISTRATE.addDataGenerator(ProviderType.RECIPE, EngravedRecipes::generate);
    }

}
