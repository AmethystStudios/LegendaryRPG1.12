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
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class SpellbookTransmute2 extends Item implements IHasModel {
	private int mode;

	public SpellbookTransmute2(String name) {
		setUnlocalizedName(name);
		setRegistryName(name);
		setMaxStackSize(1);

		ItemInit.ITEMS.add(this);
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {

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

				ItemStack itemstack = getItem(player.inventory, null);
				if (itemstack == null) {
					System.out.println("player doesn't appear to have any coal");
					player.sendMessage(new TextComponentString("You don't have any coal!"));
				}

				if (itemstack != null) {
					if (itemstack.getCount() >= 4) {
						System.out.println("Player appears to have coal.");
						itemstack.shrink(4);

						ItemStack stack = new ItemStack(Items.IRON_INGOT, 1);
						if (!player.inventory.addItemStackToInventory(stack)) {
							player.dropItem(stack, false);
						}
						player.sendMessage(new TextComponentString("You magically transmuted coal into iron!"));
					} else if (itemstack.getCount() < 4) {
						ItemStack secondaryStack = getItem(player.inventory, itemstack);
						if (secondaryStack.getCount() + itemstack.getCount() >= 4) {
							for (int toShrink = 4; toShrink > 0; --toShrink) {
								if (itemstack.getCount() > 0) {
									itemstack.shrink(1);
								} else if (secondaryStack.getCount() > 0) {
									secondaryStack.shrink(1);
								}
								else if (secondaryStack.getCount() + itemstack.getCount() < 4) {
									player.sendMessage(new TextComponentString("You don't have enough coal!"));
								}
							}

							ItemStack stack = new ItemStack(Items.IRON_INGOT, 1);
							if (!player.inventory.addItemStackToInventory(stack)) {
								player.dropItem(stack, false);

							} 

						}
					}
				}
				break;
			case 1:
				break;
			case 2:
				mode = 0;
				break;
			}
		}
		return EnumActionResult.PASS;

	}

	public static ItemStack getItem(InventoryPlayer inventory, ItemStack itemstack) {
		ItemStack returnstack = null;
		for (ItemStack s : inventory.mainInventory) {
			if (s != null && s.getItem() instanceof ItemCoal && s != itemstack) {
				returnstack = s;
				break;
			}
		}
		return returnstack;
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
