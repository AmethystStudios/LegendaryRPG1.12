package immortaleddy.legendaryRPG.world.gen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenPools extends WorldGenerator {

	private final Block block;

	public WorldGenPools(Block blockIn) {
		// The kind of Pool we want to generate (Water or Lava)
		this.block = blockIn;
	}

	public boolean generate(World worldIn, Random rand, BlockPos position) {
		// Initialize the loop with Position being 8 less. As long as the Y is greater
		// than 5 and the block in question
		// air, move down one.
		for (position = position.add(-8, 0, -8); position.getY() > 5
				&& worldIn.isAirBlock(position); position = position.down()) {
			// Apparently do nothing and close the loop. I don't understand why this is
			// here.
			;
		}

		// If Y is 4 or less, return false to generate. I assume this means the
		// generator got out of bounds,
		// and it cancels the generation
		if (position.getY() <= 4) {
			return false;
		}
		// Otherwise
		else {
			// Move our Pos down 4
			position = position.down(4);
			// Make a new Boolean Array names aboolean. Not sure what a boolean array does.
			// Possibly just stores 2048 true/falses
			boolean[] aboolean = new boolean[2048];

			// Get a random number between 0-4, Add 4. This is the number of times the below
			// for loop will run
			int i = rand.nextInt(4) + 4;

			for (int j = 0; j < i; ++j) {
				// get a Random double 0.0-1.0, and multiply it times 6, then add 3. For this
				// case, we will say the random is 0.4
				double d0 = rand.nextDouble() * 6.0D + 3.0D; // Test Case Result: d0 == 5.4D
				// Test Case: Rand is 0.7
				double d1 = rand.nextDouble() * 4.0D + 2.0D;// Test Case Result: d1 == 4.8D
				// Test Case: Rand is 0.1
				double d2 = rand.nextDouble() * 6.0D + 3.0D;// Test Case Result: d2 == 3.6D
				// Test Case: Rand is 0.9
				double d3 = rand.nextDouble() * (16.0D - d0 - 2.0D) + 1.0D + d0 / 2.0D;// Test Case Result: d3 11.44D
				// Test Case: Rand is 1.0
				double d4 = rand.nextDouble() * (8.0D - d1 - 4.0D) + 2.0D + d1 / 2.0D;// Test Case Result: d4 3.6D
				// Test Case: Rand is 0.3
				double d5 = rand.nextDouble() * (16.0D - d2 - 2.0D) + 1.0D + d2 / 2.0D;// Test Case Result: d5 5.92D

				// Initialize for loop at 1, as long as it is less than 15, keep running.
				// I believe this means the below loop should run 14 times total
				for (int l = 1; l < 15; ++l) {
					// Identical to the above loop. This means that for each time the above loop above
					//runs, the loop below run 14 times as well. If each loop runs 13 times, then the
					// loop below will run 196 times
					for (int i1 = 1; i1 < 15; ++i1) {
						// This loop should run 6 times for each time the above loop runs, for a total of 1176 times 
						for (int j1 = 1; j1 < 7; ++j1) {
							// This for loop will run for a total of 1176 times, re-running the math each time with the new numbers.
							//Test Case: We are on the 8th loop of "l"
							double d6 = ((double) l - d3) / (d0 / 2.0D);//Test Case Result: d6 1.274074074074074D
							//Test Case: We are on the 5th loop of "j1"
							double d7 = ((double) j1 - d4) / (d1 / 2.0D);//Test Case Result: d7 0.5833333333333333D
							//Test Case: We are on the 4th loop of "i1"
							double d8 = ((double) i1 - d5) / (d2 / 2.0D);//Test Case Result: d8 -1.066666666666667D
							double d9 = d6 * d6 + d7 * d7 + d8 * d8; //Test Case Result: d9 3.101320301783265
							
							// This means that in this case, this if statement never happens
							// If it did, pretending that l = 1, i1 = 2, and j1 =1
							if (d9 < 1.0D) {
								
								aboolean[(l * 16 + i1) * 8 + j1] = true;
								//then the array inside would end up looking like this
							}
						}
					}
				}
			}

			for (int k1 = 0; k1 < 16; ++k1) {
				for (int l2 = 0; l2 < 16; ++l2) {
					for (int k = 0; k < 8; ++k) {
						boolean flag = !aboolean[(k1 * 16 + l2) * 8 + k]
								&& (k1 < 15 && aboolean[((k1 + 1) * 16 + l2) * 8 + k]
										|| k1 > 0 && aboolean[((k1 - 1) * 16 + l2) * 8 + k]
										|| l2 < 15 && aboolean[(k1 * 16 + l2 + 1) * 8 + k]
										|| l2 > 0 && aboolean[(k1 * 16 + (l2 - 1)) * 8 + k]
										|| k < 7 && aboolean[(k1 * 16 + l2) * 8 + k + 1]
										|| k > 0 && aboolean[(k1 * 16 + l2) * 8 + (k - 1)]);

						if (flag) {
							Material material = worldIn.getBlockState(position.add(k1, k, l2)).getMaterial();

							if (k >= 4 && material.isLiquid()) {
								return false;
							}

							if (k < 4 && !material.isSolid()
									&& worldIn.getBlockState(position.add(k1, k, l2)).getBlock() != this.block) {
								return false;
							}
						}
					}
				}
			}

			for (int l1 = 0; l1 < 16; ++l1) {
				for (int i3 = 0; i3 < 16; ++i3) {
					for (int i4 = 0; i4 < 8; ++i4) {
						if (aboolean[(l1 * 16 + i3) * 8 + i4]) {
							worldIn.setBlockState(position.add(l1, i4, i3),
									i4 >= 4 ? Blocks.AIR.getDefaultState() : this.block.getDefaultState(), 2);
						}
					}
				}
			}

			for (int i2 = 0; i2 < 16; ++i2) {
				for (int j3 = 0; j3 < 16; ++j3) {
					for (int j4 = 4; j4 < 8; ++j4) {
						if (aboolean[(i2 * 16 + j3) * 8 + j4]) {
							BlockPos blockpos = position.add(i2, j4 - 1, j3);

							if (worldIn.getBlockState(blockpos).getBlock() == Blocks.DIRT
									&& worldIn.getLightFor(EnumSkyBlock.SKY, position.add(i2, j4, j3)) > 0) {
								Biome biome = worldIn.getBiome(blockpos);

								if (biome.topBlock.getBlock() == Blocks.MYCELIUM) {
									worldIn.setBlockState(blockpos, Blocks.MYCELIUM.getDefaultState(), 2);
								} else {
									worldIn.setBlockState(blockpos, Blocks.GRASS.getDefaultState(), 2);
								}
							}
						}
					}
				}
			}

			if (this.block.getDefaultState().getMaterial() == Material.LAVA) {
				for (int j2 = 0; j2 < 16; ++j2) {
					for (int k3 = 0; k3 < 16; ++k3) {
						for (int k4 = 0; k4 < 8; ++k4) {
							boolean flag1 = !aboolean[(j2 * 16 + k3) * 8 + k4]
									&& (j2 < 15 && aboolean[((j2 + 1) * 16 + k3) * 8 + k4]
											|| j2 > 0 && aboolean[((j2 - 1) * 16 + k3) * 8 + k4]
											|| k3 < 15 && aboolean[(j2 * 16 + k3 + 1) * 8 + k4]
											|| k3 > 0 && aboolean[(j2 * 16 + (k3 - 1)) * 8 + k4]
											|| k4 < 7 && aboolean[(j2 * 16 + k3) * 8 + k4 + 1]
											|| k4 > 0 && aboolean[(j2 * 16 + k3) * 8 + (k4 - 1)]);

							if (flag1 && (k4 < 4 || rand.nextInt(2) != 0)
									&& worldIn.getBlockState(position.add(j2, k4, k3)).getMaterial().isSolid()) {
								worldIn.setBlockState(position.add(j2, k4, k3), Blocks.STONE.getDefaultState(), 2);
							}
						}
					}
				}
			}

			if (this.block.getDefaultState().getMaterial() == Material.WATER) {
				for (int k2 = 0; k2 < 16; ++k2) {
					for (int l3 = 0; l3 < 16; ++l3) {
						int l4 = 4;

						if (worldIn.canBlockFreezeWater(position.add(k2, 4, l3))) {
							int flag = net.minecraftforge.common.ForgeModContainer.fixVanillaCascading ? 2 | 16 : 2; // Forge:
																														// With
																														// bit
																														// 5
																														// unset,
																														// it
																														// will
																														// notify
																														// neighbors
																														// and
																														// load
																														// adjacent
																														// chunks.
							worldIn.setBlockState(position.add(k2, 4, l3), Blocks.ICE.getDefaultState(), flag); // Forge
						}
					}
				}
			}

			return true;
		}
	}
}