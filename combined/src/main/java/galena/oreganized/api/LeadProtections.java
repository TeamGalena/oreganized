package galena.oreganized.api;

import com.google.common.base.Suppliers;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;
import net.minecraft.world.entity.LivingEntity;

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

}
