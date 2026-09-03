package galena.oreganized.argentum.index;

import galena.oreganized.OConstants;
import galena.oreganized.register.SimpleRegistryHelper;
import net.minecraft.network.codec.ByteBufCodecs;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredHolder;

@Mod(OConstants.MOD_ID)
public class ArgentumAttachmentTypes {

    private static final SimpleRegistryHelper<AttachmentType<?>> ATTACHMENT_TYPES = OConstants.REGISTRY_HELPER.getAttachmentTypeSubHelper();

    public static final DeferredHolder<AttachmentType<?>, AttachmentType<Boolean>> TARNISHED =
            ATTACHMENT_TYPES.create("tarnished", $ -> AttachmentType.builder(() -> false).sync(ByteBufCodecs.BOOL).build());

}
