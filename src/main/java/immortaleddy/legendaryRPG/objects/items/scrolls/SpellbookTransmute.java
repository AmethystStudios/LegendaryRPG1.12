package immortaleddy.legendaryRPG.objects.items.scrolls;

import java.util.List;

import javax.annotation.Nullable;

import immortaleddy.legendaryRPG.Main;
import immortaleddy.legendaryRPG.init.ItemInit;
import immortaleddy.legendaryRPG.util.interfaces.IHasModel;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemCoal;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class SpellbookTransmute extends Item implements IHasModel {
	private int mode;

	public SpellbookTransmute(String name) {
		setUnlocalizedName(name);
		setRegistryName(name);
		setMaxStackSize(1);
		setMaxDamage(500);
		ItemInit.ITEMS.add(this);
		
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		int DamagetoShow = this.getMaxDamage(stack) - this.getDamage(stack);
		tooltip.add("\u00A72SHIFT + RIGHTCLICK TO CHANGE MODE");
		tooltip.add("Durability: " + DamagetoShow + "/" + this.getMaxDamage(stack));
		switch (mode) {
		case 0:
			tooltip.add("Spell: \u00A78Coal \u00A7r-> \u00A77Iron");
			break;
		case 1:
			tooltip.add("Spell: \u00A77Iron \u00A7r-> \u00A7eGold");
			break;
		case 2:
			tooltip.add("Spell: \u00A7eGold \u00A7r-> \u00A7bDiamond");
			break;
		case 3:
			tooltip.add("Spell: \u00A7bDiamond \u00A7r-> \u00A7eGold");
			break;
		case 4:
			tooltip.add("Spell: \u00A7eGold \u00A7r-> \u00A77Iron");
			break;
		case 5:
			tooltip.add("Spell: \u00A77Iron \u00A7r-> \u00A78Coal");
			break;

		}
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		if (world.isRemote) {
			if (player.isSneaking()) {
				switch (mode) {
				case 0:
					mode = 1;
					player.sendMessage(new TextComponentString("Switched to spell: \u00A77Iron \u00A7r-> \u00A7eGold"));
					break;
				case 1:
					mode = 2;
					player.sendMessage(
							new TextComponentString("Switched to spell: \u00A7eGold \u00A7r-> \u00A7bDiamond"));
					break;
				case 2:
					mode = 3;
					player.sendMessage(
							new TextComponentString("Switched to spell: \u00A7bDiamond \u00A7r-> \u00A7eGold"));
					break;
				case 3:
					player.sendMessage(new TextComponentString("Switched to spell: \u00A7eGold \u00A7r-> \u00A77Iron"));
					mode = 4;
					break;
				case 4:
					player.sendMessage(new TextComponentString("Switched to spell: \u00A77Iron \u00A7r-> \u00A78Coal"));
					mode = 5;
					break;
				case 5:
					mode = 0;
					player.sendMessage(new TextComponentString("Switched to spell: \u00A78Coal \u00A7r-> \u00A77Iron"));
					break;
				}
			} else if (!player.isSneaking()) {
				switch (mode) {
				case 0:
					transmuteItems(player, hand, 4, 1, 7, Items.COAL, Items.IRON_INGOT);
					break;
				case 1:
					transmuteItems(player, hand, 2, 1, 5, Items.IRON_INGOT, Items.GOLD_INGOT);
					break;
				case 2:
					transmuteItems(player, hand, 4, 1, 18, Items.GOLD_INGOT, Items.DIAMOND);
					break;
				case 3:
					transmuteItems(player, hand, 1, 4, 4, Items.DIAMOND, Items.GOLD_INGOT);
					break;
				case 4:
					transmuteItems(player, hand, 1, 2, 8, Items.GOLD_INGOT, Items.IRON_INGOT);
					break;
				case 5:
					transmuteItems(player, hand, 1, 4, 5, Items.IRON_INGOT, Items.COAL);
					break;
				}
			}
		}

		return new ActionResult<ItemStack>(EnumActionResult.PASS, player.getHeldItem(hand));
	}

	public static void transmuteItems(EntityPlayer player, EnumHand hand, int howManyIn, int howManyOut,
			int durabilityCost, Item itemToConsume, Item itemToReturn) {
		int count = countItem(player.inventory, itemToConsume);
		if (count >= howManyIn) {
			consumeItem(player.inventory, itemToConsume, howManyIn);
			player.getHeldItem(hand).damageItem(durabilityCost, player);
			ItemStack stack = new ItemStack(itemToReturn, howManyOut);
			if (!player.inventory.addItemStackToInventory(stack)) {
				player.dropItem(stack, false);
			}
		} else if (howManyIn > count) {
			player.sendMessage(new TextComponentString("Not enough coal! You need " + howManyIn));

		}

	}

	public static int countItem(InventoryPlayer inventory, Item itemtocount) {
		ItemStack returnstack = null;
		int itemCount = 0;
		for (ItemStack s : inventory.mainInventory) {
			if (s != null && s.getItem() == itemtocount) {
				itemCount = itemCount + s.getCount();
			}
		}
		return itemCount;
	}

	public static void consumeItem(InventoryPlayer inventory, Item itemToConsume, int howMany) {
		inventory.clearMatchingItems(itemToConsume, 0, howMany, null);
	}
	@Override
    public boolean isEnchantable(ItemStack stack)
    {
        return false;
    }
    public boolean isBookEnchantable(ItemStack stack, ItemStack book)
    {
        return false;
    }
	@Override
	public CreativeTabs[] getCreativeTabs() {
		return new CreativeTabs[] { CreativeTabs.COMBAT, Main.lrpgmaintab };
	}

	@Override
	public void registerModels() {
		Main.proxy.registerItemRenderer(this, 0, "inventory");

	}

}
// 241