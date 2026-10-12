package galena.oreganized.data;

import com.tterrag.registrate.providers.RegistrateLangProvider;
import galena.oreganized.core.CoreModule;
import galena.oreganized.index.CoreTags;
import net.neoforged.fml.common.Mod;

@Mod(CoreModule.MOD_ID)
public class CoreLang {

    public CoreLang() {
        ODatagen.addLangProvider(this::generate);
    }

    private void generate(RegistrateLangProvider provider) {
        provider.add("tooltip.oreganized.wip.title", "Work In Progress");
        provider.add("tooltip.oreganized.wip.description", "Usages for this item will be available in a future release");

        provider.add(CoreTags.Blocks.AMETHYST_CLUSTERS, "Amethyst Clusters");
    }

}
