package immortaleddy.legendaryRPG.util.handlers;

import java.util.Random;

import immortaleddy.legendaryRPG.init.ItemInit;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.item.Item;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class DropHandler {

	public static Random random = new Random();
	public static int dropped;

	private void dropItem(int[] chance, LivingDropsEvent event, int amount, Item item) {
		int rand = random.nextInt(100);
		for (int val : chance)
			if (val == rand)
				event.getEntityLiving().dropItem(item, amount);
	}
	// 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
	// 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38,
	// 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56,
	// 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74,
	// 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92,
	// 93, 94, 95, 96, 97, 98, 99, 100

	@SubscribeEvent
	public void onEntityDrop(LivingDropsEvent event) {
		if (event.getEntityLiving() instanceof EntityCow && event.getEntityLiving().isChild() == true) {

			int j = random.nextInt(2);

			int k;

			for (k = 0; k < j; ++k) {
				event.getEntityLiving().dropItem(ItemInit.ITEM_RENNET, 1);
			}

			/*****************************************************************
			 * Keeping this as an example
			 * 
			 * j = this.random.nextInt(3);
			 * 
			 * for (k = 0; k < j; ++k) {
			 * event.entityLiving.dropItem(Items.nether_star, 10); }
			 * 
			 * }
			 ***************************************************************/
		}

		if (event.getEntityLiving() instanceof EntityCreeper) {
			dropItem(new int[] { 1, 88, 32, 64, 16 }, event, 1, ItemInit.ITEM_CREEPER_BRAIN);
		}

		if (event.getEntityLiving() instanceof EntityBlaze) {

			dropItem(new int[] { 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 30, 31, 32, 33, 34,
					35 }, event, 1, ItemInit.ITEM_BLAZE_HEART);

		}

	}

}
