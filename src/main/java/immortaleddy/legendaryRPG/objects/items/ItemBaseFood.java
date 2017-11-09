package immortaleddy.legendaryRPG.objects.items;

import java.lang.ref.Reference;

import immortaleddy.legendaryRPG.Main;
import immortaleddy.legendaryRPG.init.ItemInit;
import immortaleddy.legendaryRPG.util.interfaces.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ItemBaseFood extends ItemFood implements IHasModel{
	private PotionEffect[] effects;

	public ItemBaseFood(String name, int amount, boolean isWolfFood, PotionEffect... potionEffects) {
		super(amount, isWolfFood);
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		
		ItemInit.ITEMS.add(this);

		this.effects = potionEffects;
	}

	public ItemBaseFood(String name, int amount, float saturation, boolean isWolfFood, PotionEffect... potionEffects) {
		super(amount, saturation, isWolfFood);
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		
		ItemInit.ITEMS.add(this);

		this.effects = potionEffects;
	}

	@Override
	protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
		for (PotionEffect effect : effects) {
			player.addPotionEffect(new PotionEffect(effect));
		}
	}
	
    @Override
	public CreativeTabs[] getCreativeTabs() {
	 return new CreativeTabs[] {CreativeTabs.FOOD, Main.lrpgmaintab};
	}
	@Override
	public void registerModels() {
		Main.proxy.registerItemRenderer(this, 0, "inventory");
		
	}

}



