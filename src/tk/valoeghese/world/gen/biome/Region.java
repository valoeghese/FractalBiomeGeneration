package tk.valoeghese.world.gen.biome;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tk.valoeghese.world.gen.fractal.util.FractalRandom;
import tk.valoeghese.world.util.WeightedInt;

public class Region {
	private static final Map<Integer, Region> REVERSE = new HashMap<>();
	
	public Region(int id) {
		REVERSE.put(id, this);
		this.id = id;
	}
	
	public final int id;
	private final List<WeightedInt> biomes = new ArrayList<>();
	private double weightTotal = 0D;
	
	public void addBiome(Biome biome, double weight) {
		this.biomes.add(new WeightedInt(biome.id, weight));
		weightTotal += weight;
	}
	
	public int pickBiome(FractalRandom random) {
		double randVal = random.nextDouble() * weightTotal;
		
		int i = -1;
		while (randVal >= 0) {
			++i;
			randVal -= biomes.get(i).weight;
		}
		
		return biomes.get(i).value;
	}
	
	public static final Region OCEAN = new Region(0);
	
	public static final Region WARM_TEMPERATE = new Region(1);
	public static final Region DESERT = new Region(2);
	public static final Region RAINFOREST = new Region(3);
	public static final Region COOL_TEMPERATE = new Region(4);
	public static final Region SNOW = new Region(5);
	
	public static Region getRegion(int id) {
		return REVERSE.get(id);
	}
}
