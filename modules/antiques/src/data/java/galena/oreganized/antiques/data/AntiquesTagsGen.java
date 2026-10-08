package galena.oreganized.antiques.data;

import com.tterrag.registrate.providers.RegistrateItemTagsProvider;
import galena.oreganized.OConstants;
import galena.oreganized.antiques.index.AntiquesItems;
import galena.oreganized.data.ODatagen;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.Tags;

@Mod(OConstants.MOD_ID)
public class AntiquesTagsGen {

    public AntiquesTagsGen() {
        ODatagen.addItemTagProvider(this::items);
    }

    private void items(RegistrateItemTagsProvider provider) {
        provider.addTag(Tags.Items.MUSIC_DISCS).add(AntiquesItems.MUSIC_DISC_RADIO.getKey());
    }

}
