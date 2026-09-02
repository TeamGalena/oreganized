package galena.oreganized.compat.ponder;

import net.createmod.ponder.foundation.PonderIndex;

public class PonderCompat {

    public static void register() {
        PonderIndex.addPlugin(new OreganizedPonderPlugin());
    }

}
