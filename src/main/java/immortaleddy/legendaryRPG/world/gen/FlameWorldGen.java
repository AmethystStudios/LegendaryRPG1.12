package immortaleddy.legendaryRPG.world.gen;

import java.util.Random;

import immortaleddy.legendaryRPG.world.biomes.BiomeHellOnEarth;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class FlameWorldGen implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		// these are important!
		int blockX = chunkX * 16;
		int blockZ = chunkZ * 16;
		// generate differently based on dimension
		switch (world.provider.getDimension()) {
		case -1: // generateNether(world, random, blockX, blockZ);
			break;
		case 0:
			generateOverworld(world, random, blockX, blockZ);
			break;
		case 1: // generateEnd(world, random, blockX, blockZ);
			break;
		}

	}

	public static int getGroundFromAbove(World world, int x, int z) {
		int y = 255;
		boolean foundGround = false;
		while (!foundGround && y-- >= 0) {
			Block blockAt = world.getBlockState(new BlockPos(x, y, z)).getBlock();

			foundGround = blockAt == Blocks.NETHERRACK;
		}
		return y;
	}

	private void generateOverworld(World world, Random rand, int blockX, int blockZ) {
		WorldGenerator genFlames = new WorldGenFlame();
		int y = getGroundFromAbove(world, blockX, blockZ);
		Biome biome = world.getBiome(new BlockPos(blockX, y, blockZ));

	/*	if (biome instanceof BiomeHellOnEarth) {
			// how many we want to make per chunk
			// let's make it random between MIN and MAX
			int MIN = 10;
			int MAX = 24;
			int numFlames = MIN + rand.nextInt(MAX - MIN);
			// Generate Flames
			for (int i = 0; i < numFlames; i++) {
				// get a random position in the chunk
				int randX = blockX + rand.nextInt(16);
				int randZ = blockZ + rand.nextInt(16);
				// the y-value we pass here will be used as minimum spawn height (in our
				// generator, anyway)
				genFlames.generate(world, rand, new BlockPos(randX, y + 1, randZ));
			}
		}
*/
	}

}