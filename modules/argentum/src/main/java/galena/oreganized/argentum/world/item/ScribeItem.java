package galena.oreganized.argentum.world.item;

import static galena.oreganized.index.OTags.Blocks.MINEABLE_WITH_SCRIBE;
import static galena.oreganized.index.OTags.Blocks.SILKTOUCH_WITH_SCRIBE;
import static galena.oreganized.index.OTags.Blocks.SILKTOUCH_WITH_SCRIBE_BLACKLIST;

import galena.oreganized.argentum.config.ArgentumConfigs;
import galena.oreganized.argentum.index.ArgentumRecipeTypes;
import galena.oreganized.argentum.world.recipe.BlockRecipeInput;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.pattern.BlockInWorld;
import net.minecraft.world.level.gameevent.GameEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.BlockDropsEvent;

@EventBusSubscriber
public class ScribeItem extends Item {

    public ScribeItem(Properties properties) {
        super(properties);
    }

    public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity user) {
        if (!level.isClientSide && state.getDestroySpeed(level, pos) != 0F) {
            stack.hurtAndBreak(1, user, LivingEntity.getSlotForHand(user.getUsedItemHand()));
        }

        return true;
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        if (state.is(MINEABLE_WITH_SCRIBE)) return 32F;
        else if (isCorrectToolForDrops(stack, state)) return 0.3F;
        return super.getDestroySpeed(stack, state);
    }

    public boolean dropsLikeSilktouch(ItemStack stack, BlockState state) {
        return isCorrectToolForDrops(stack, state) && !shouldNotSilktouch(stack, state);
    }

    private boolean shouldNotSilktouch(ItemStack stack, BlockState state) {
        return state.is(SILKTOUCH_WITH_SCRIBE_BLACKLIST);
    }

    @Override
    public boolean isCorrectToolForDrops(ItemStack stack, BlockState state) {
        if (ArgentumConfigs.COMMON.scribeSilkTouchStone.get()) {
            return state.is(SILKTOUCH_WITH_SCRIBE);
        } else {
            return state.is(MINEABLE_WITH_SCRIBE);
        }
    }

    @Override
    public boolean isValidRepairItem(ItemStack stack, ItemStack repairStack) {
        return repairStack.is(Items.AMETHYST_SHARD);
    }

    @Override
    public boolean supportsEnchantment(ItemStack stack, Holder<Enchantment> enchantment) {
        if (enchantment.is(EnchantmentTags.MINING_EXCLUSIVE)) return true;
        return super.supportsEnchantment(stack, enchantment);
    }

    private InteractionResult replaceBlock(UseOnContext context, BlockState to, boolean particles) {
        var level = context.getLevel();
        var pos = context.getClickedPos();
        var from = level.getBlockState(pos);

        level.setBlockAndUpdate(pos, to);
        level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(context.getPlayer(), from));

        if (particles) {
            level.addDestroyBlockEffect(pos, from);
        }

        if (context.getPlayer() != null) {
            context.getPlayer().playSound(SoundEvents.GRINDSTONE_USE, 1F, 1.5F);

            context.getItemInHand().hurtAndBreak(1, context.getPlayer(), LivingEntity.getSlotForHand(context.getHand()));
        }

        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        var state = context.getLevel().getBlockState(context.getClickedPos());

        // TODO modular move to attachment or something similar
        // if (state.hasProperty(ICrystalGlass.TYPE)) {
        //     return replaceBlock(context, state.cycle(ICrystalGlass.TYPE), true);
        // }

        var input = new BlockRecipeInput(new BlockInWorld(context.getLevel(), context.getClickedPos(), false));
        var recipe = context.getLevel().getRecipeManager().getRecipeFor(ArgentumRecipeTypes.SCRIBE_RECIPE.get(), input, context.getLevel())
                .map(RecipeHolder::value)
                .orElse(null);
        if (recipe != null) {
            if (recipe.dropResources()) {
                var tool = new ItemStack(Items.IRON_PICKAXE);
                tool.applyComponents(context.getItemInHand().getComponents());
                Block.dropResources(state, context.getLevel(), context.getClickedPos(), null, context.getPlayer(), tool);
            }
            return replaceBlock(context, recipe.to().withPropertiesOf(state), !recipe.dropResources());
        }

        return super.useOn(context);
    }

    @SubscribeEvent
    public static void onBlockBreak(final BlockDropsEvent event) {
        var stack = event.getTool();

        if (stack.getItem() instanceof ScribeItem scribe && scribe.dropsLikeSilktouch(stack, event.getState())) {
            event.setDroppedExperience(0);
        }
    }
}
