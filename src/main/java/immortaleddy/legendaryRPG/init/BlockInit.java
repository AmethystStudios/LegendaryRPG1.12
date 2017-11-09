package immortaleddy.legendaryRPG.init;

import java.util.ArrayList;
import java.util.List;

import immortaleddy.legendaryRPG.objects.blocks.BlockBase;
import immortaleddy.legendaryRPG.objects.blocks.BlockOreInfinium;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockInit {
	
	public static final List<Block> BLOCKS = new ArrayList<Block>();

	public static final Block BLOCK_INFINIUM = new BlockBase("block_infinium", Material.IRON);

	public static final Block ORE_INFINIUM = new BlockOreInfinium("block_ore_infinium");
	
}
