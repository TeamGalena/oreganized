package galena.oreganized.data.provider;

import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.providers.ProviderType;
import com.tterrag.registrate.providers.RegistrateLangProvider;import galena.oreganized.OConstants;
import net.createmod.ponder.foundation.PonderIndex;
import net.minecraft.data.recipes.RecipeOutput;import net.neoforged.fml.common.Mod;import java.util.function.Consumer;

@Mod(OConstants.MOD_ID)
public class ODatagen {

    private static final Registrate REGISTRATE = Registrate.create(OConstants.MOD_ID);

    public ODatagen() {
        PonderIndex.getLangAccess().provideLang(OConstants.MOD_ID, REGISTRATE::addRawLang);
    }

    public static void addLangProvider(Consumer<RegistrateLangProvider> consumer) {
        REGISTRATE.addDataGenerator(ProviderType.LANG, consumer::accept);
    }

    public static void addRecipeProvider(Consumer<RecipeOutput> consumer) {
        REGISTRATE.addDataGenerator(ProviderType.RECIPE, consumer::accept);
    }
}
