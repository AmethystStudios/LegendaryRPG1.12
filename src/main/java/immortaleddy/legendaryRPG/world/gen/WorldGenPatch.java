package immortaleddy.legendaryRPG.world.gen;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenPatch extends WorldGenerator
{
	// Copy/Pasted from sand patches, I'm going to see if I can bend it to my will in a way that works.
    private final Block block;
    /** The maximum radius used when generating a patch of blocks. */
    private final int radius;
    private final Material replaceMaterial;
    private final Block replaceBlock;

    public WorldGenPatch(Block blockToGenerate, int patchRadius, Material generateIn, Block replaceBlock)
    {
        this.block = blockToGenerate;
        this.radius = 17;
        this.replaceMaterial = generateIn;
        this.replaceBlock = replaceBlock;
        
    }

    public boolean generate(World worldIn, Random rand, BlockPos position)
    {
        if (worldIn.getBlockState(position).getMaterial() != replaceMaterial)
        {
            return false;
        }
        else
        {
        	// I have hardcoded radius because I was getting a bouds must be positive, which means radius was evaluating to 0.
        	//TODO fix this. Change the generation so it's a bit more randomized and not circular
        	System.out.println("generating Patch");
            int i = rand.nextInt(this.radius - 2) + 2;
            int j = 2;

            for (int k = position.getX() - i; k <= position.getX() + i; ++k)
            {
                for (int l = position.getZ() - i; l <= position.getZ() + i; ++l)
                {
                    int i1 = k - position.getX();
                    int j1 = l - position.getZ();

                    if (i1 * i1 + j1 * j1 <= i * i)
                    {
                        for (int k1 = position.getY() - 2; k1 <= position.getY() + 2; ++k1)
                        {
                            BlockPos blockpos = new BlockPos(k, k1, l);
                            Block block = worldIn.getBlockState(blockpos).getBlock();

                            if (block == replaceBlock)
                            {
                                worldIn.setBlockState(blockpos, this.block.getDefaultState(), 2);
                            }
                        }
                    }
                }
            }

            return true;
        }
    }
}