package immortaleddy.legendaryRPG.objects.blocks;

import immortaleddy.legendaryRPG.Main;
import immortaleddy.legendaryRPG.init.BlockInit;
import immortaleddy.legendaryRPG.init.ItemInit;
import immortaleddy.legendaryRPG.util.interfaces.IHasModel;
import net.minecraft.block.BlockSoulSand;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BlockOreInfinium extends BlockSoulSand implements IHasModel{
	
	public BlockOreInfinium(String name) {
		setUnlocalizedName(name);
		setRegistryName(name);
		setLightLevel(1.0f);
		setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		
		
		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}
	
	@Override
	public void registerModels() {
		Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
		
	}
	



}
