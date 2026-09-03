package galena.oreganized.plumbum.index;

import galena.oreganized.OConstants;
import galena.oreganized.register.SimpleRegistryHelper;
import net.minecraft.network.codec.ByteBufCodecs;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredHolder;

public class PlumbumAttachmentTypes {

    private static final SimpleRegistryHelper<AttachmentType<?>> ATTACHMENT_TYPES = OConstants.REGISTRY_HELPER.getAttachmentTypeSubHelper();

    public static final DeferredHolder<AttachmentType<?>, AttachmentType<Long>> LAST_PUSH_TIME =
            ATTACHMENT_TYPES.create("last_push_time", $ -> AttachmentType.builder(() -> 0L).sync(ByteBufCodecs.VAR_LONG).build());

    public static void register() {
        // Load this class
    }

}
