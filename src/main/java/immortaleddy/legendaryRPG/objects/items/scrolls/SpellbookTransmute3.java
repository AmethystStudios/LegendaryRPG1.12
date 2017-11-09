package immortaleddy.legendaryRPG.objects.items.scrolls;

import immortaleddy.legendaryRPG.Main;
import immortaleddy.legendaryRPG.init.ItemInit;
import immortaleddy.legendaryRPG.util.interfaces.IHasModel;
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

public class SpellbookTransmute3 extends Item implements IHasModel {
	private int mode;

	public SpellbookTransmute3(String name) {
		setUnlocalizedName(name);
		setRegistryName(name);
		setMaxStackSize(1);

		ItemInit.ITEMS.add(this);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		if (world.isRemote) {
			if (player.getHeldItem(hand).getItem() == ItemInit.SPELLBOOK_TRANSMUTE) {
				if (player.isSneaking()) {
					switch (mode) {
					case 0:
						mode = 1;
						break;

					case 1:
						mode = 2;
						break;
					case 2:
						mode = 0;
						break;
					}
				} else if (!player.isSneaking()) {
					switch (mode) {
					case 0:
						int count = countItem(player.inventory, Items.COAL);
						player.sendMessage(new TextComponentString("" + count + " coal found in inventory"));
						int howMany = 4;
						if (count >= howMany) {
							int consumed = consumeItem(player.inventory, Items.COAL, howMany);
							if (consumed == howMany) {
								player.sendMessage(new TextComponentString(
										"With a puff of smoke, you transmute " + howMany + " coal into 2 Iron!"));
								ItemStack stack = new ItemStack(Items.IRON_INGOT, 2);
								if (!player.inventory.addItemStackToInventory(stack)) {
									player.dropItem(stack, false);

								}
							}
						} else if (howMany > count) {
							player.sendMessage(new TextComponentString("Not enough coal! You need " + howMany + "!"));

						}
						break;
					case 1:
						break;
					case 2:
						mode = 0;
						break;
					}
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

	public static int consumeItem(InventoryPlayer inventory, Item itemToConsume, int howMany) {

		int itemsConsumedCount = 0;
		int howManyLeft = howMany - itemsConsumedCount;
		for (ItemStack s : inventory.mainInventory) {
			if (s != null && s.getItem() == itemToConsume) {
				if (itemsConsumedCount < howMany) {
					
					if (s.getCount() < howManyLeft ) {
						System.out.println("how many so far?! 1:" + itemsConsumedCount);
						System.out.println("how many left?! 1:" + howManyLeft);
						int stackSize = s.getCount();
						itemsConsumedCount = itemsConsumedCount + stackSize;
						s.shrink(stackSize);
						
						
						System.out.println("Feed me More!:" + itemsConsumedCount);

					}
					if (s.getCount() >= howManyLeft) {
						System.out.println("how many so far?!:" + itemsConsumedCount);
						System.out.println("how many left?!:" + howManyLeft);
						itemsConsumedCount = itemsConsumedCount + (howMany - itemsConsumedCount);
						s.shrink(howManyLeft);

					System.out.println("I'm Full:" + itemsConsumedCount);

				}
			}
		}}
		return itemsConsumedCount;
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
