package galena.oreganized.armament.data;

import static galena.oreganized.data.extensions.OItemModelExtensions.*;

import com.tterrag.registrate.providers.RegistrateItemModelProvider;
import galena.oreganized.OConstants;
import galena.oreganized.armament.index.ArmamentItems;
import galena.oreganized.data.ODatagen;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;

@Mod(OConstants.MOD_ID)
public class ArmamentItemModels {

    public ArmamentItemModels() {
        ODatagen.addItemModelProvider(this::generate);
    }

    private void generate(RegistrateItemModelProvider provider) {
        normalItem(provider, ArmamentItems.SHRAPNEL_BOMB_MINECART);
        normalItem(provider, ArmamentItems.SHRAPNEL_BOMB_MINECART);

        normalItem(provider, ArmamentItems.LEAD_BOLT);
        normalItem(provider, ArmamentItems.FLINT_AND_PEWTER);

        crossbowOverwrite(provider, "crossbow_lead_bolt");
    }

    // TODO modular do using blueprint
    public ItemModelBuilder crossbowOverwrite(RegistrateItemModelProvider provider, String name) {
        return provider.withExistingParent(name, "item/crossbow")
                .texture("layer0", itemTexture(OConstants.modLoc(name)));
    }

}
