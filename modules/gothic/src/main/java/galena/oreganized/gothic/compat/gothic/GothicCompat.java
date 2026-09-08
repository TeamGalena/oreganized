package galena.oreganized.gothic.compat.gothic;

import galena.oreganized.ModCompat;
import galena.oreganized.OConstants;
import galena.oreganized.gothic.compat.create.CreateCompat;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(OConstants.MOD_ID)
public class GothicCompat {

    public GothicCompat(IEventBus modBus) {
        if (ModCompat.CREATE_LOADED) {
            CreateCompat.register(modBus);
        }
    }

}
