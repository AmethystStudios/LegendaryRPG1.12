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
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
    	int DamagetoShow = this.getMaxDamage(stack) - this.getDamage(stack);
    	tooltip.add("\u00A72SHIFT + RIGHTCLICK TO CHANGE MODE");
    	tooltip.add("Durability: " + DamagetoShow + "/" + this.getMaxDamage(stack));
    	switch (mode){
    	case 0:
    		tooltip.add("Mode: \u00A78Coal \u00A7r-> \u00A77Iron");
    		break;
    	case 1:
    		tooltip.add("Mode: \u00A77Iron \u00A7r-> \u00A7eGold");
    		break;
    	case 2:
    		tooltip.add("Mode: \u00A7eGold \u00A7r-> \u00A7bDiamond");
    		break;
    		
    	}
    }

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		if (world.isRemote) {
			if (player.isSneaking()) {
				switch (mode) {
				case 0:
					player.sendMessage(new TextComponentString(
							"Switched to Mode: \u00A77Iron \u00A7r-> \u00A7eGold"));
					mode = 1;
					break;
				case 1:
					player.sendMessage(new TextComponentString(
							"Switched to Mode: \u00A7eGold \u00A7r-> \u00A7bDiamond"));
					mode = 2;
					break;
				case 2:
					mode = 0;
					player.sendMessage(new TextComponentString(
							"Switched to Mode: \u00A78Coal \u00A7r-> \u00A77Iron"));
					break;
				}
			} else if (!player.isSneaking()) {
				switch (mode) {
				case 0:
					int countCoal = countItem(player.inventory, Items.COAL);
					int howManyCoal = 2;
					if (countCoal >= howManyCoal) {
						consumeItem(player.inventory, Items.COAL, howManyCoal);
						player.sendMessage(new TextComponentString(
								"With a puff of smoke, you transmute " + howManyCoal + " coal into 1 Iron!"));
						player.getHeldItem(hand).damageItem(7, player);
						ItemStack stack = new ItemStack(Items.IRON_INGOT, 2);
						if (!player.inventory.addItemStackToInventory(stack)) {
							player.dropItem(stack, false);
						}
					} else if (howManyCoal > countCoal) {
						player.sendMessage(new TextComponentString("Not enough coal! You need " + howManyCoal + "!"));

					}
					break;
				case 1:
					int countIron = countItem(player.inventory, Items.IRON_INGOT);
					int howManyIron = 2;
					if (countIron >= howManyIron) {
						consumeItem(player.inventory, Items.IRON_INGOT, howManyIron);
						player.sendMessage(new TextComponentString(
								"With a puff of smoke, you transmute " + howManyIron + " iron into 1 Gold!"));
						player.getHeldItem(hand).damageItem(5, player);
						ItemStack stack = new ItemStack(Items.GOLD_INGOT, 1);
						if (!player.inventory.addItemStackToInventory(stack)) {
							player.dropItem(stack, false);
						}
					} else if (howManyIron > countIron) {
						player.sendMessage(new TextComponentString("Not enough iron! You need " + howManyIron + "!"));

					}
					break;
				case 2:
					int countGold = countItem(player.inventory, Items.GOLD_INGOT);
					int howManyGold = 4;
					if (countGold >= howManyGold) {
						consumeItem(player.inventory, Items.GOLD_INGOT, howManyGold);
						player.sendMessage(new TextComponentString(
								"With a puff of smoke, you transmute " + howManyGold + " gold into a diamond!"));
						player.getHeldItem(hand).damageItem(18, player);
						ItemStack stack = new ItemStack(Items.DIAMOND, 1);
						if (!player.inventory.addItemStackToInventory(stack)) {
							player.dropItem(stack, false);
						}
					} else if (howManyGold > countGold) {
						player.sendMessage(new TextComponentString("Not enough gold! You need " + howManyGold + "!"));
					}
					break;
				}
			}
		}

		return new ActionResult<ItemStack>(EnumActionResult.PASS, player.getHeldItem(hand));
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
	public CreativeTabs[] getCreativeTabs() {
		return new CreativeTabs[] { CreativeTabs.COMBAT, Main.lrpgmaintab };
	}

	@Override
	public void registerModels() {
		Main.proxy.registerItemRenderer(this, 0, "inventory");

	}

}
