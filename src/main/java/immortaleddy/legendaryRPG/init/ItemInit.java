package immortaleddy.legendaryRPG.init;

import java.util.ArrayList;
import java.util.List;

import immortaleddy.legendaryRPG.objects.armor.ArmorBase;
import immortaleddy.legendaryRPG.objects.items.ItemBase;
import immortaleddy.legendaryRPG.objects.items.ItemBaseFood;
import immortaleddy.legendaryRPG.objects.items.scrolls.ScrollIron;
import immortaleddy.legendaryRPG.objects.items.scrolls.ScrollRecall;
import immortaleddy.legendaryRPG.objects.items.scrolls.ScrollSpawnpoint;
import immortaleddy.legendaryRPG.objects.items.scrolls.SpellbookTransmute;
import immortaleddy.legendaryRPG.objects.tools.ToolAxe;
import immortaleddy.legendaryRPG.objects.tools.ToolHoe;
import immortaleddy.legendaryRPG.objects.tools.ToolPickaxe;
import immortaleddy.legendaryRPG.objects.tools.ToolShovel;
import immortaleddy.legendaryRPG.objects.tools.ToolSword;
import immortaleddy.legendaryRPG.util.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.common.util.EnumHelper;

public class ItemInit {
	
	public static final List<Item> ITEMS = new ArrayList<Item>();
	
	//Materials
	public static final ToolMaterial TOOL_INFINIUM = EnumHelper.addToolMaterial("tool_infinium", 4, 900000000, 8.0f, 5.0f, 35);
	public static final ArmorMaterial ARMOR_INFINIUM = EnumHelper.addArmorMaterial("armor_infinium", Reference.MODID + ":infinium", 900000000, new int[] { 4, 9, 7, 6 }, 20, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.0F);

	//Items
	public static final Item INGOT_UNREFINED_INFINIUM = new ItemBase("ingot_unrefined_infinium");
	public static final Item INGOT_REFINED_INFINIUM = new ItemBase("ingot_refined_infinium").setCreativeTab(CreativeTabs.BREWING);
	public static final Item NUGGET_INFINIUM = new ItemBase("nugget_infinium");
	public static final Item ITEM_RENNET = new ItemBase("item_rennet");
	public static final Item ITEM_BLAZE_HEART = new ItemBaseFood("item_blaze_heart", 4, 4, false, new PotionEffect(Potion.getPotionById(12), 1200, 0)).setAlwaysEdible();
	public static final Item ITEM_CREEPER_BRAIN = new ItemBase("item_creeper_brain");
	
	//Spellbooks and scrolls
	public static final Item SPELLBOOK_TRANSMUTE = new SpellbookTransmute("spellbook_transmute");
	public static final Item SCROLL_SPAWNPOINT = new ScrollSpawnpoint("scroll_spawnpoint");
	public static final Item SCROLL_RECALL = new ScrollRecall("scroll_recall");
	
	public static final Item SCROLL_IRON_TIER_1 = new ScrollIron("scroll_iron_tier_1", 1);
	public static final Item SCROLL_IRON_TIER_2 = new ScrollIron("scroll_iron_tier_2", 2);
	public static final Item SCROLL_IRON_TIER_3 = new ScrollIron("scroll_iron_tier_3", 3);
	
	//Tools
	public static final Item PICKAXE_INFINIUM = new ToolPickaxe("pickaxe_infinium", TOOL_INFINIUM);
	public static final Item AXE_INFINIUM = new ToolAxe("axe_infinium", TOOL_INFINIUM);
	public static final Item SHOVEL_INFINIUM = new ToolShovel("shovel_infinium", TOOL_INFINIUM);
	public static final Item HOE_INFINIUM = new ToolHoe("hoe_infinium", TOOL_INFINIUM);
	public static final Item SWORD_INFINIUM = new ToolSword("sword_infinium", TOOL_INFINIUM);
	
	//Armor
	public static final Item HELMET_INFINIUM = new ArmorBase("helmet_infinium", ARMOR_INFINIUM, 1, EntityEquipmentSlot.HEAD);
	public static final Item CHESTPLATE_INFINIUM = new ArmorBase("chestplate_infinium", ARMOR_INFINIUM, 1, EntityEquipmentSlot.CHEST);
	public static final Item LEGGINGS_INFINIUM = new ArmorBase("leggings_infinium", ARMOR_INFINIUM, 2, EntityEquipmentSlot.LEGS);
	public static final Item BOOTS_INFINIUM = new ArmorBase("boots_infinium", ARMOR_INFINIUM, 1, EntityEquipmentSlot.FEET);

}
