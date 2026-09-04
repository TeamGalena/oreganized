package galena.oreganized.plumbum.data;

import com.tterrag.registrate.providers.ProviderType;
import galena.oreganized.OConstants;
import galena.oreganized.data.provider.ODatagen;
import net.neoforged.fml.common.Mod;

@Mod(OConstants.MOD_ID)
public class PlumbumData {

    public PlumbumData() {
        ODatagen.REGISTRATE.addDataGenerator(ProviderType.RECIPE, PlumbumRecipes::generate);
    }

}
