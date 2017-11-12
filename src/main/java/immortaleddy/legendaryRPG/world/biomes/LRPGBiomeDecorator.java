package immortaleddy.legendaryRPG.world.biomes;

import java.util.Random;

import immortaleddy.legendaryRPG.world.gen.WorldGenFlame;
import immortaleddy.legendaryRPG.world.gen.WorldGenPatch;
import immortaleddy.legendaryRPG.world.gen.WorldGenPools;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.gen.feature.WorldGenFire;
import net.minecraft.world.gen.feature.WorldGenHellLava;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.feature.WorldGenerator;

public class LRPGBiomeDecorator extends BiomeDecorator {
	// Set default chances for something to trigger
	public float lavaLakeChance = 0;
	public int minFlamePerChunk = 0;
	public int maxFlamePerChunk = 0;
	public int soulSandPatchSize = 0;
	public float soulSandPatchChance = 0;
	// Register generators
	//private WorldGenLakes moreLavaLakeGen = new WorldGenLakes(Blocks.LAVA);
	private WorldGenPools moreLavaLakeGen = new WorldGenPools(Blocks.LAVA);
	private WorldGenerator flameGen = new WorldGenFlame();
	//TODO change the material to something that works with netherrack
	private WorldGenerator soulSandGen = new WorldGenPatch(Blocks.SOUL_SAND, soulSandPatchSize, Material.LAVA, Blocks.NETHERRACK);


	@Override
	public void decorate(World world, Random rand, Biome biome, BlockPos pos) {
		//genDecorations(biome, world, rand);
		
		super.decorate(world, rand, biome, pos);
	}

	@Override
	protected void genDecorations(Biome biome, World world, Random randGen) {
		
		// Generate Default Lava Lakes
		if(randGen.nextFloat() < lavaLakeChance) {
			int randX = chunkPos.getX() + randGen.nextInt(16) + 8;
			int randZ = chunkPos.getZ() + randGen.nextInt(16) + 8;
			
			//world.getheight gets the top block of the area to use as our Y POS
			moreLavaLakeGen.generate(world, randGen, world.getHeight(new BlockPos(randX, 1, randZ)));
		}
		// Generate Soul Sand Patch
		if(randGen.nextFloat() < soulSandPatchChance) {
			int randX = chunkPos.getX() + randGen.nextInt(16) + 8;
			int randZ = chunkPos.getZ() + randGen.nextInt(16) + 8;
			
			//world.getheight gets the top block of the area to use as our Y POS
			soulSandGen.generate(world, randGen, world.getHeight(new BlockPos(randX, 0, randZ)));
		}
		

		//Generate Flames
		if(minFlamePerChunk > 0) {
			int numFlames = minFlamePerChunk + randGen.nextInt(maxFlamePerChunk - minFlamePerChunk);
			for (int i = 0; i < numFlames; i++) {
				//Get a random position in the chunk
				//System.out.println("Tried to generate a flame");
				int randX = chunkPos.getX() + randGen.nextInt(16) + 8;
				int randZ = chunkPos.getZ() + randGen.nextInt(16) + 8;
				
				//world.getheight gets the top block of the area to use as our Y POS
				flameGen.generate(world, randGen, world.getHeight(new BlockPos(randX, 0, randZ)));
			}
			
		}
		
		
		super.genDecorations(biome, world, randGen);
	}
}
