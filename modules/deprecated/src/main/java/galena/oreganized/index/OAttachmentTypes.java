package galena.oreganized.index;

import galena.oreganized.argentum.index.ArgentumAttachmentTypes;
import galena.oreganized.plumbum.index.PlumbumAttachmentTypes;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredHolder;

public class OAttachmentTypes {

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredHolder<AttachmentType<?>, AttachmentType<Long>> LAST_PUSH_TIME = PlumbumAttachmentTypes.LAST_PUSH_TIME;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredHolder<AttachmentType<?>, AttachmentType<Boolean>> TARNISHED = ArgentumAttachmentTypes.TARNISHED;

}
