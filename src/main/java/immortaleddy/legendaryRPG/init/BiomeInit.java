package immortaleddy.legendaryRPG.init;

import immortaleddy.legendaryRPG.world.biomes.BiomeHellOnEarth;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class BiomeInit {
	public static final Biome HELLONEARTH = new BiomeHellOnEarth();

	public static void registerBiomes() {
		initBiome(HELLONEARTH, "HellOnEarth", BiomeType.DESERT, Type.DEAD, Type.HOT, Type.WASTELAND);

	}

	// TODO Change weight to realistic number, 
	private static Biome initBiome(Biome biome, String name, BiomeType biomeType, Type... types) {
		biome.setRegistryName(name);
		ForgeRegistries.BIOMES.register(biome);
		System.out.println("BiomeRegistered");
		BiomeDictionary.addTypes(biome, types);
		BiomeManager.addBiome(biomeType, new BiomeEntry(biome, 999999999));
		BiomeManager.addSpawnBiome(biome);
		System.out.println("BiomeAdded");
		
		return biome;

	}

}
