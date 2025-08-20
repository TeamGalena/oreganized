package galena.oreganized.content.item;

import galena.oreganized.content.entity.LeadBoltEntity;
import galena.oreganized.index.OEntityTypes;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class LeadBoltItem extends ArrowItem {

    public LeadBoltItem(Properties properties) {
        super(properties);
    }


    @Override
    public AbstractArrow createArrow(Level level, ItemStack stack, LivingEntity user, @Nullable ItemStack weapon) {
        return new LeadBoltEntity(OEntityTypes.LEAD_BOLT.get(), level, user, stack, weapon);
    }

    @Override
    public Projectile asProjectile(Level level, Position pos, ItemStack stack, Direction direction) {
        var bolt = new LeadBoltEntity(OEntityTypes.LEAD_BOLT.get(), level, pos, stack.copyWithCount(1));
        bolt.pickup = AbstractArrow.Pickup.ALLOWED;
        return bolt;
    }

}
