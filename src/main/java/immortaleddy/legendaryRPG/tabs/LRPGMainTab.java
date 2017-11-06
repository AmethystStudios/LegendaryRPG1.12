package immortaleddy.legendaryRPG.tabs;

import immortaleddy.legendaryRPG.init.ItemInit;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class LRPGMainTab extends CreativeTabs{
	
	public LRPGMainTab(String label) { 
	super("lrpgmaintab");
	
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(ItemInit.PICKAXE_INFINIUM);
	}

}
