package galena.oreganized.api;

import com.google.common.base.Suppliers;
import galena.oreganized.ModCompat;
import galena.oreganized.OConstants;
import galena.oreganized.index.OTags;
import galena.oreganized.plumbum.world.CreateArmorProtection;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.fml.common.Mod;

@Mod(OConstants.MOD_ID)
public class LeadProtections {

    private static final List<Predicate<LivingEntity>> PROTECTIONS = new ArrayList<>();

    public static void register(Predicate<LivingEntity> protection) {
        PROTECTIONS.add(protection);
    }

    public static boolean isProtected(LivingEntity entity) {
        return PREDICATE.get().test(entity);
    }

    public static boolean isNotProtected(LivingEntity entity) {
        return !isProtected(entity);
    }

    private static final Supplier<Predicate<LivingEntity>> PREDICATE = Suppliers.memoize(() -> PROTECTIONS.stream().reduce($ -> false, Predicate::or));

    public LeadProtections() {
        LeadProtections.register(entity -> entity.getItemBySlot(EquipmentSlot.HEAD).is(OTags.Items.PROTECTIVE_HELMET));
        LeadProtections.register(entity -> {
            for (var slot : entity.getArmorSlots()) {
                if (!slot.is(OTags.Items.PROTECTIVE_ARMOR_PART)) return false;
            }
            return true;
        });

        if(ModCompat.CREATE_LOADED) {
            LeadProtections.register(new CreateArmorProtection());
        }
    }

}
