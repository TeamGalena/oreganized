package galena.oreganized.argentum.compat;

import galena.oreganized.ModCompat;
import galena.oreganized.OConstants;
import galena.oreganized.argentum.compat.create.CreateCompat;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(OConstants.MOD_ID)
public class ArgentumCompat {

    public ArgentumCompat(IEventBus modBus) {
        if (ModCompat.CREATE_LOADED) {
            CreateCompat.register(modBus);
        }
    }

}
