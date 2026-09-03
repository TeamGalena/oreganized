package galena.oreganized.plumbum.world.item;

import galena.oreganized.OConstants;
import galena.oreganized.accessor.GuiAccessor;
import galena.oreganized.index.OTags;
import galena.oreganized.plumbum.client.tooltip.ThermometerTooltip;
import galena.oreganized.plumbum.index.PlumbumCriterionTriggers;
import galena.oreganized.plumbum.index.PlumbumDataComponents;
import galena.oreganized.plumbum.index.PlumbumItems;
import galena.oreganized.plumbum.world.block.IMeltableBlock;

import java.util.Optional;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import org.jetbrains.annotations.Nullable;

@EventBusSubscriber
public class ThermometerItem extends Item {

    public static final ResourceKey<LootTable> BREAK_LOOT_TABLE = ResourceKey.create(Registries.LOOT_TABLE, OConstants.modLoc("gameplay/thermometer_breaking"));
    private static final int AMBIENT_RANGE = 5;
    public static final int HEAT_LEVELS = 9;

    public ThermometerItem(Properties properties) {
        super(properties);
    }

    @Override
    public Optional<TooltipComponent> getTooltipImage(ItemStack stack) {
        int heat = getHeatLevel(stack);
        return Optional.of(new ThermometerTooltip(heat));
    }

    private static int heatLevel(BlockState state, LevelAccessor level, BlockPos pos) {
        if (state.is(OTags.Blocks.LAVA_HEAT_LEVEL)) return 8;
        if (state.is(OTags.Blocks.FIRE_HEAT_LEVEL)) return 7;
        if (state.getLightEmission(level, pos) > 2) return 4;
        if (state.getBlock() instanceof IMeltableBlock block) {
            var goopyness = block.getGoopyness(state);
            if (goopyness > 1) return 7;
            if (goopyness > 0) return 5;
        }
        return 2;
    }

    public static int ambientMeasurement(Player player) {
        var level = player.level();
        var pos = player.blockPosition();

        if (player.getBlockStateOn().is(OTags.Blocks.LAVA_HEAT_LEVEL)) return 8;
        if (player.getBlockStateOn().is(OTags.Blocks.FIRE_HEAT_LEVEL)) return 7;
        if (player.isOnFire()) return 5;
        if (player.isFreezing()) return 0;

        var biome = level.getBiome(pos).value();
        var temperature = biome.getBaseTemperature();
        var weather = level.isRaining() ? 0 : 1;

        return (int) (Math.max(0, Math.min(2, temperature) / 2) * 3) + weather;
    }

    public static int activeMeasurement(Level level, BlockPos pos) {
        var box = new AABB(pos).inflate(AMBIENT_RANGE);
        var lavaDistance = BlockPos.betweenClosedStream(box)
                .filter(it -> level.getBlockState(it).is(OTags.Blocks.LAVA_HEAT_LEVEL))
                .mapToInt(it -> it.distManhattan(pos))
                .min()
                .orElse(Integer.MAX_VALUE);

        var state = level.getBlockState(pos);
        var blockHeatLevel = heatLevel(state, level, pos);

        if (lavaDistance < HEAT_LEVELS) {
            var lavaHeatLevel = (HEAT_LEVELS - 1) - lavaDistance;
            if (lavaHeatLevel > blockHeatLevel) return lavaHeatLevel;
        }

        return blockHeatLevel;
    }

    private static int heatLevel(LivingEntity entity) {
        if (entity.getType() == EntityType.MAGMA_CUBE) return 8;
        if (entity.getType() == EntityType.BLAZE) return 7;
        if (entity.isOnFire()) return 5;

        if (entity.isInvertedHealAndHarm()) return 1;

        if (entity.getActiveEffects().stream().anyMatch(it -> !it.getEffect().value().isBeneficial())) return 3;

        return 2;
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity target, InteractionHand hand) {
        var held = player.getItemInHand(hand);
        var heatLevel = heatLevel(target);
        setHeatLevel(held, player.level(), heatLevel);
        player.getCooldowns().addCooldown(held.getItem(), 60);
        setLocked(player, held, true);
        return InteractionResult.sidedSuccess(player.level().isClientSide());
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        var hit = getPlayerPOVHitResult(level, player, ClipContext.Fluid.ANY);

        if (hit.getType() != HitResult.Type.BLOCK) return super.use(level, player, hand);

        var pos = hit.getBlockPos();
        var state = level.getBlockState(pos);
        var stack = player.getItemInHand(hand);

        if (state.getFluidState().is(FluidTags.WATER)) {
            var result = extinguish(player, stack, hit.getLocation());
            if (result.getResult() != InteractionResult.PASS) return result;
        }

        var heatLevel = activeMeasurement(level, pos);
        setHeatLevel(stack, level, heatLevel);
        player.getCooldowns().addCooldown(stack.getItem(), 60);
        setLocked(player, stack, true);
        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
    }

    private static InteractionResultHolder<ItemStack> extinguish(Player player, ItemStack stack, Vec3 pos) {
        var heatLevel = getHeatLevel(stack);
        if (heatLevel < 4) return InteractionResultHolder.pass(stack);
        player.playSound(SoundEvents.FIRE_EXTINGUISH, 1F, 0.9F);
        player.level().addParticle(ParticleTypes.CLOUD, pos.x(), pos.y(), pos.z(), 0.0, 0.05, 0.0);

        if (heatLevel > 6) {
            stack.shrink(1);
            if (player.level() instanceof ServerLevel level) {
                var lootTable = level.getServer().reloadableRegistries().getLootTable(BREAK_LOOT_TABLE);
                var lootParams = new LootParams.Builder(level)
                        .withParameter(LootContextParams.ORIGIN, pos)
                        .withParameter(LootContextParams.THIS_ENTITY, player)
                        .withParameter(LootContextParams.TOOL, stack)
                        .create(LootContextParamSets.GIFT);

                var drops = lootTable.getRandomItems(lootParams);
                drops.forEach(drop -> {
                    if (player.addItem(drop)) return;
                    Containers.dropItemStack(level, pos.x(), pos.y(), pos.z(), drop);
                });

                PlumbumCriterionTriggers.BROKEN_THERMOMETER.get().trigger((ServerPlayer) player);
            }
        } else {
            setLocked(player, stack, false);
        }

        return InteractionResultHolder.sidedSuccess(stack, player.level().isClientSide());
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        return InteractionResult.PASS;
    }

    public static int getHeatLevel(ItemStack stack) {
        return stack.getOrDefault(PlumbumDataComponents.HEAT_LEVEL, 0);
    }

    public static void setHeatLevel(ItemStack stack, @Nullable Level level, int heatLevel) {
        if (getHeatLevel(stack) == heatLevel) return;
        stack.set(PlumbumDataComponents.HEAT_LEVEL, heatLevel);
        if (level != null && level.isClientSide()) {

            // TODO modules use interface data
            if (Minecraft.getInstance().gui instanceof GuiAccessor accessor) {
                accessor.oreganized$setToolHighlightTimer(60);
            }
        }
    }

    public static boolean isLocked(ItemStack stack) {
        return stack.getOrDefault(PlumbumDataComponents.LOCKED, false);
    }

    private static void setLocked(@Nullable Entity user, ItemStack stack, boolean locked) {
        if (isLocked(stack) == locked) return;
        stack.set(PlumbumDataComponents.LOCKED, locked);
        var sound = locked ? SoundEvents.LODESTONE_COMPASS_LOCK : SoundEvents.AMETHYST_BLOCK_RESONATE;
        if (user != null) {
            user.level().playSound(user, user.blockPosition(), sound, SoundSource.PLAYERS, 1.0F, 1.2F);
        }
    }

    @SubscribeEvent
    public static void onHitAir(PlayerInteractEvent.LeftClickEmpty event) {
        var stack = event.getItemStack();
        if (!stack.is(PlumbumItems.THERMOMETER.get())) return;
        setLocked(event.getEntity(), stack, false);

        if (event.getEntity() instanceof ServerPlayer player) {
            PlumbumCriterionTriggers.SHAKEN_THERMOMETER.get().trigger(player);
        }
    }

    @SubscribeEvent
    public static void tickPlayer(final PlayerTickEvent.Post event) {
        if (event.getEntity().level().getGameTime() % 20L != 0) return;

        var stack = event.getEntity().getItemInHand(InteractionHand.MAIN_HAND);

        if (stack.getItem() instanceof ThermometerItem && !ThermometerItem.isLocked(stack)) {
            var heatLevel = ThermometerItem.ambientMeasurement(event.getEntity());
            ThermometerItem.setHeatLevel(stack, event.getEntity().level(), heatLevel);
        }
    }

}
