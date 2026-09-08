package galena.oreganized.plumbum.world.block;

import galena.oreganized.plumbum.index.PlumbumAttachmentTypes;
import galena.oreganized.plumbum.index.PlumbumBlockEntities;
import galena.oreganized.plumbum.index.PlumbumDamageTypes;
import java.util.Optional;
import java.util.UUID;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class PushableBlockEntity extends BlockEntity {
    private static final String PRESSURE_NBT_KEY = "Pressure";
    private static final String RESET_TIMER_NBT_KEY = "ResetTimer";
    private static final int REQUIRED_PRESSURE = 16;
    private static final int RESET_TIME = REQUIRED_PRESSURE;
    private int pressure = 0;
    private int resetTimer = 0;
    private long lastInteractionTime = 0;
    private UUID lastInteractingPlayerUUID = null;

    public PushableBlockEntity(BlockPos pos, BlockState state) {
        super(PlumbumBlockEntities.PUSHABLE.get(), pos, state);
    }

    public static Optional<PushableBlockEntity> getAt(LevelAccessor level, BlockPos pos) {
        if (level.getBlockEntity(pos) instanceof PushableBlockEntity blockEntity) {
            return Optional.of(blockEntity);
        }
        return Optional.empty();
    }

    public static boolean isPushing(Entity entity) {
        if (!(entity instanceof Player player)) {
            return false;
        }
        return player.level().getGameTime() - player.getData(PlumbumAttachmentTypes.LAST_PUSH_TIME.get()) < 2;
    }

    public void tick(Level level, BlockPos pos, BlockState state) {
        if (!(state.getBlock() instanceof IPushableBlock pushable)) return;

        if (lastInteractingPlayerUUID != null) {
            Player player = level.getPlayerByUUID(lastInteractingPlayerUUID);
            boolean pushing = level.getGameTime() - lastInteractionTime < 5;
            if (player != null && pushing && !player.swinging) {
                player.setData(PlumbumAttachmentTypes.LAST_PUSH_TIME.get(), level.getGameTime());
                if (!player.hasEffect(MobEffects.WEAKNESS)) {
                    if (pressure < REQUIRED_PRESSURE) {
                        pressure++;
                        setChanged();
                    }
                    if (resetTimer < RESET_TIME) {
                        resetTimer++;
                        setChanged();
                    }
                }
                return;
            }
        }

        if (!pushable.isToggleable(state) && resetTimer > 0) {
            resetTimer--;
            setChanged();
            if (resetTimer <= 0) {
                pushable.reset(level, pos, state);
                level.sendBlockUpdated(pos, state, state, Block.UPDATE_CLIENTS);
            }
        }
        if (pressure != 0) {
            pressure = 0;
            setChanged();
        }
    }

    @Override
    protected void saveAdditional(CompoundTag nbt, HolderLookup.Provider lookup) {
        super.saveAdditional(nbt, lookup);
        nbt.putInt(PRESSURE_NBT_KEY, pressure);
        nbt.putInt(RESET_TIMER_NBT_KEY, resetTimer);
    }

    @Override
    public void loadAdditional(CompoundTag nbt, HolderLookup.Provider lookup) {
        super.loadAdditional(nbt, lookup);
        if (nbt.contains(PRESSURE_NBT_KEY, 99)) {
            pressure = nbt.getInt(PRESSURE_NBT_KEY);
        }
        if (nbt.contains(RESET_TIMER_NBT_KEY, 99)) {
            resetTimer = nbt.getInt(RESET_TIMER_NBT_KEY);
        }
    }

    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        var nbt = super.getUpdateTag(registries);
        nbt.putInt(PRESSURE_NBT_KEY, pressure);
        nbt.putInt(RESET_TIMER_NBT_KEY, resetTimer);
        return nbt;
    }

    public ItemInteractionResult use(BlockState state, Level level, BlockPos pos, Player player) {
        if (state.getBlock() instanceof IMeltableBlock meltable) {
            int goopyness = meltable.getGoopyness(state);
            if (goopyness > 0) {
                player.hurt(level.damageSources().source(PlumbumDamageTypes.MOLTEN_LEAD), 1F);
                if (goopyness > 1) {
                    return ItemInteractionResult.sidedSuccess(level.isClientSide());
                }
            }
        }

        if (!player.swinging) {
            lastInteractionTime = level.getGameTime();
        }

        level.sendBlockUpdated(pos, state, state, Block.UPDATE_CLIENTS);

        int offset = level.isClientSide() ? 1 : 0;
        if (player.hasEffect(MobEffects.DAMAGE_BOOST)
                || pressure >= REQUIRED_PRESSURE - offset && player.getUUID().equals(lastInteractingPlayerUUID)) {
            var pushable = (IPushableBlock) state.getBlock();
            pushable.onFullyPushed(player, level, pos, state);
            pressure = 0;
            resetTimer = RESET_TIME;
            setChanged();
            level.sendBlockUpdated(pos, state, state, Block.UPDATE_CLIENTS);
            if (player.hasEffect(MobEffects.DAMAGE_BOOST) || pushable.isToggleable(state)) {
                return ItemInteractionResult.sidedSuccess(level.isClientSide());
            }
        }
        lastInteractingPlayerUUID = player.getUUID();
        return ItemInteractionResult.CONSUME;
    }
}
