package galena.oreganized.gothic.data;

import static galena.oreganized.data.extensions.OItemModelExtensions.*;

import com.tterrag.registrate.providers.RegistrateItemModelProvider;
import galena.oreganized.OConstants;
import galena.oreganized.data.ODatagen;
import galena.oreganized.engraved.index.EngravedItems;
import net.neoforged.fml.common.Mod;

@Mod(OConstants.MOD_ID)
public class EngravedItemModels {

    public EngravedItemModels() {
        ODatagen.addItemModelProvider(this::generate);
    }

    private void generate(RegistrateItemModelProvider provider) {
        toolItem(provider, EngravedItems.BUSH_HAMMER);
    }

}
