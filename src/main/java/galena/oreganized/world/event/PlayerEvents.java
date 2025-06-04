package galena.oreganized.world.event;

import galena.oreganized.OreganizedCarcinogenius;
import galena.oreganized.content.entity.GargoyleBlockEntity;
import galena.oreganized.content.item.ScribeItem;
import galena.oreganized.index.*;
import galena.oreganized.index.OBlocks;
import galena.oreganized.index.OItems;

import java.util.UUID;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import net.minecraftforge.event.ItemAttributeModifierEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = OreganizedCarcinogenius.MOD_ID)
public class PlayerEvents {

    @SubscribeEvent
    public static void onBlockBreak(final BlockEvent.BreakEvent event) {
        if(event.getState().getBlock() == OBlocks.RAW_ASBESTOS_BLOCK.get() ||
                event.getState().getBlock() == OBlocks.ASBESTOS_BLOCK.get() ||
                event.getState().getBlock() == OBlocks.DEEPSLATE_ASBESTOS_ORE.get() ||
                event.getState().getBlock() == OBlocks.ASBESTOS_ORE.get() ){
            var vec = Vec3.atCenterOf(event.getPos());
            Level level = event.getPlayer().level();
            var cloud = new AreaEffectCloud(level, vec.x, vec.y, vec.z);

            cloud.addEffect(new MobEffectInstance(OEffects.LUNG_DAMAGE.get(),50));
            cloud.setParticle(OParticleTypes.ASBESTOS_CLOUD.get());
            cloud.setRadius(4F);
            cloud.setRadiusPerTick(-0.02F);
            cloud.setDuration((int) (120));
            level.addFreshEntity(cloud);

        }
    }

}
