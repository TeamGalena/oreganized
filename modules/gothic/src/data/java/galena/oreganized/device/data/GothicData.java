package galena.oreganized.device.data;

import com.tterrag.registrate.providers.ProviderType;
import galena.oreganized.OConstants;
import galena.oreganized.data.provider.ODatagen;
import net.neoforged.fml.common.Mod;

@Mod(OConstants.MOD_ID)
public class GothicData {

    public GothicData() {
        ODatagen.REGISTRATE.addDataGenerator(ProviderType.RECIPE, GothicRecipes::generate);
    }

}
