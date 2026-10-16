package galena.oreganized.gothic.compat;

import galena.oreganized.ModCompat;
import galena.oreganized.gothic.GothicModule;
import galena.oreganized.gothic.compat.create.CreateCompat;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(GothicModule.MOD_ID)
public class GothicCompat {

    public GothicCompat(IEventBus modBus) {
        if (ModCompat.CREATE_LOADED) {
            CreateCompat.register(modBus);
        }
    }

}
