package galena.oreganized.index;

import galena.oreganized.argentum.index.ArgentumItems;
import galena.oreganized.electrum.index.ElectrumItems;
import net.minecraft.core.Holder;
import net.minecraft.world.item.ArmorMaterial;

public class OArmorMaterials {

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final Holder<ArmorMaterial> SILVER = ArgentumItems.SILVER_MATERIAL;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final Holder<ArmorMaterial> ELECTRUM = ElectrumItems.ELECTRUM_MATERIAL;

}
