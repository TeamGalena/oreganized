package galena.oreganized.index;

import galena.oreganized.Oreganized;
import net.minecraft.network.codec.ByteBufCodecs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class OAttachmentTypes {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, Oreganized.MOD_ID);

    public static final DeferredHolder<AttachmentType<?>, AttachmentType<Long>> LAST_PUSH_TIME =
            ATTACHMENT_TYPES.register("last_push_time", () -> AttachmentType.builder(() -> 0L).sync(ByteBufCodecs.VAR_LONG).build());

    public static void register(IEventBus modBus) {
        ATTACHMENT_TYPES.register(modBus);
    }
}
