package galena.oreganized.index;

import galena.oreganized.plumbum.index.PlumbumAttachmentTypes;
import net.minecraft.network.codec.ByteBufCodecs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class OAttachmentTypes {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, OConstants.MOD_ID);

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredHolder<AttachmentType<?>, AttachmentType<Long>> LAST_PUSH_TIME = PlumbumAttachmentTypes.LAST_PUSH_TIME;

    public static final DeferredHolder<AttachmentType<?>, AttachmentType<Boolean>> TARNISHED =
            ATTACHMENT_TYPES.register("tarnished", () -> AttachmentType.builder(() -> false).sync(ByteBufCodecs.BOOL).build());

    public static void register(IEventBus modBus) {
        ATTACHMENT_TYPES.register(modBus);
    }
}
