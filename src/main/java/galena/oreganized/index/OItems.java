package galena.oreganized.index;

import static galena.oreganized.ModCompat.FARMERS_DELIGHT_ID;

import com.teamabnormals.blueprint.common.item.BlueprintRecordItem;
import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
import galena.oreganized.OreganizedCarcinogenius;
import galena.oreganized.compat.farmers_delight.FarmersDelightCompat;
import galena.oreganized.content.item.BushHammerItem;
import galena.oreganized.content.item.ElectrumArmorItem;
import galena.oreganized.content.item.FlintAndPewterItem;
import galena.oreganized.content.item.LeadBoltItem;
import galena.oreganized.content.item.MinecartShrapnelBombItem;
import galena.oreganized.content.item.OSmithingTemplateItem;
import galena.oreganized.content.item.ScribeItem;
import galena.oreganized.content.item.SilverMirrorItem;
import java.util.function.Function;
import java.util.function.Supplier;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.RecordItem;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.SwordItem;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = OreganizedCarcinogenius.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class OItems {
    public static final ItemSubRegistryHelper HELPER = OreganizedCarcinogenius.REGISTRY_HELPER.getItemSubHelper();

    public static final RegistryObject<Item> RAW_ASBESTOS = HELPER.createItem("raw_asbestos", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> REFINED_ASBESTOS = HELPER.createItem("refined_asbestos", () -> new Item(new Item.Properties()));
}