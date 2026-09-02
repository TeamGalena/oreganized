package galena.oreganized.compat.create;

import com.simibubi.create.content.equipment.armor.BacktankUtil;
import com.simibubi.create.content.equipment.armor.DivingHelmetItem;
import java.util.function.Predicate;
import net.minecraft.world.entity.LivingEntity;

public class CreateArmorProtection implements Predicate<LivingEntity> {

    @Override
    public boolean test(LivingEntity entity) {
        if (!DivingHelmetItem.isWornBy(entity)) return false;

        var backtanks = BacktankUtil.getAllWithAir(entity);
        if (backtanks.isEmpty()) return false;

        if (entity.level().getGameTime() % 20 == 0L) {
            BacktankUtil.consumeAir(entity, backtanks.getFirst(), 1);
        }

        return true;
    }

}
