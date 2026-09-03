package galena.oreganized.index;

import galena.oreganized.argentum.index.ArgentumItems;
import galena.oreganized.electrum.index.ElectrumItems;
import net.minecraft.world.item.Tier;

public class OItemTiers {

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final Tier SILVER = ElectrumItems.ELECTRUM_TIER;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final Tier ELECTRUM = ArgentumItems.SILVER_TIER;

}
