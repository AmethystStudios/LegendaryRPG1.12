package immortaleddy.legendaryRPG.objects.items.scrolls;

import java.util.Random;

import immortaleddy.legendaryRPG.Main;
import immortaleddy.legendaryRPG.init.ItemInit;
import immortaleddy.legendaryRPG.util.interfaces.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class ScrollSpawnpoint extends Item implements IHasModel{

	public ScrollSpawnpoint(String name) 
	{
		setUnlocalizedName(name);
		setRegistryName(name);
		
		ItemInit.ITEMS.add(this);
	}
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		if (world.isRemote) {
            BlockPos chunkcoordinates = player.getPosition();
            player.setSpawnChunk(chunkcoordinates, true, world.provider.getDimension());
            player.inventory.clearMatchingItems(ItemInit.SCROLL_SPAWNPOINT, 0, 1, null);
            player.sendMessage(new TextComponentString("You feel bound to this place..."));
		}

		Random rand = new Random();
		for (int countparticles = 0; countparticles <= 20; ++countparticles) {
			world.spawnParticle
			(EnumParticleTypes.PORTAL, player.posX + (rand.nextDouble() - 0.5D) * (double) player.width, player.posY + rand.nextDouble() * (double) player.height - (double) player.getYOffset(), player.posZ + (rand.nextDouble() - 0.5D) * (double) player.width, 0.0D, 0.0D, 0.0D);
		}
		return  new ActionResult<ItemStack>(EnumActionResult.PASS, player.getHeldItem(hand));
	}

	@Override
	public CreativeTabs[] getCreativeTabs() {
		return new CreativeTabs[] { CreativeTabs.TOOLS, Main.lrpgmaintab };
	}
	@Override
	public void registerModels() {
		Main.proxy.registerItemRenderer(this, 0, "inventory");
		
	}
}

