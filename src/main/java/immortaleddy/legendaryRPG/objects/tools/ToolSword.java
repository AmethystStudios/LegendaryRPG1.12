package immortaleddy.legendaryRPG.objects.tools;

import immortaleddy.legendaryRPG.Main;
import immortaleddy.legendaryRPG.init.ItemInit;
import immortaleddy.legendaryRPG.util.interfaces.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemSword;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ToolSword extends ItemSword implements IHasModel {

	public ToolSword(String name, ToolMaterial material) {
		super(material);
		setUnlocalizedName(name);
		setRegistryName(name);

		ItemInit.ITEMS.add(this);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean isFull3D() {
		return true;
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
