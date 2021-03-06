package immortaleddy.legendaryRPG.world.gen;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenFlame extends WorldGenerator {

	@Override
	public boolean generate(World world, Random rand, BlockPos pos) {
		BlockPos genPos = pos.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4),
				rand.nextInt(8) - rand.nextInt(8));
		BlockPos blockUnderPos = genPos.down();

		if (world.isAirBlock(genPos) && (world.getBlockState(blockUnderPos).getBlock() == Blocks.NETHERRACK)) {
			world.setBlockState(genPos, Blocks.FIRE.getDefaultState());
		}
		return false;
	}

}