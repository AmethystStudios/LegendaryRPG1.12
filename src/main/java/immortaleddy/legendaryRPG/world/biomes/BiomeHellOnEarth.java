package immortaleddy.legendaryRPG.world.biomes;

import immortaleddy.legendaryRPG.init.BlockInit;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityGiantZombie;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.gen.feature.WorldGenMinable;

public class BiomeHellOnEarth extends Biome {
	/*
	 * Remove rivers and other biomes from interfering with HellOnEarth, remove
	 * water, add more lava generation, add fire generation, tweak mob numbers and
	 * add structure generation. Add patches of soul sand and infinium ore spawning
	 * on the surface, remove coalgen infinium
	 * 
	 * DONE:
	 * 
	 */
	public BiomeHellOnEarth() {

		super(new BiomeProperties("Hell on earth").setRainDisabled().setBaseHeight(0.125f).setHeightVariation(0.15f)
				.setTemperature(7.0f).setWaterColor(11272192));
		topBlock = Blocks.NETHERRACK.getDefaultState();
		fillerBlock = Blocks.NETHERRACK.getDefaultState();
		this.decorator.coalGen = new WorldGenMinable(BlockInit.ORE_INFINIUM.getDefaultState(), 4);
		
		this.decorator.treesPerChunk = 0;
		this.decorator.generateFalls = false;
		getLRPGBiomeDecorator().lavaLakeChance = 0.10f;
		getLRPGBiomeDecorator().minFlamePerChunk = 10;
		getLRPGBiomeDecorator().maxFlamePerChunk = 24;
		getLRPGBiomeDecorator().lavaOceanChance = 1.0f;
		

		this.spawnableCaveCreatureList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableWaterCreatureList.clear();

		this.spawnableMonsterList.add(new SpawnListEntry(EntityWitherSkeleton.class, 4, 1, 3));
		this.spawnableMonsterList.add(new SpawnListEntry(EntitySkeleton.class, 8, 3, 7));
		this.spawnableMonsterList.add(new SpawnListEntry(EntityBlaze.class, 4, 3, 4));
		this.spawnableMonsterList.add(new SpawnListEntry(EntityPigZombie.class, 12, 12, 20));
		this.spawnableMonsterList.add(new SpawnListEntry(EntityGiantZombie.class, 1, 1, 2));
		
	}
	@Override
	public BiomeDecorator createBiomeDecorator() {
		return new LRPGBiomeDecorator();
	}
	
	protected LRPGBiomeDecorator getLRPGBiomeDecorator() {
		return (LRPGBiomeDecorator) this.decorator;
	}


}
