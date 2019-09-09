package tk.valoeghese.world.gen.fractal;

import tk.valoeghese.world.gen.biome.Biome;
import tk.valoeghese.world.gen.fractal.type.CrossModifierFractal;
import tk.valoeghese.world.gen.fractal.util.FractalRandom;

public enum FractalModerateEdge implements CrossModifierFractal {
	INSTANCE;
	
	private static final int OCEAN = Biome.OCEAN.id;
	private static final int GRASSLAND = Biome.GRASSLAND.id;
	private static final int SWAMP = Biome.SWAMP.id;
	private static final int DESERT = Biome.DESERT.id;
	private static final int RAINFOREST = Biome.RAINFOREST.id;
	private static final int MARSH = Biome.MARSH.id;
	private static final int SNOW_TAIGA = Biome.SNOW_TAIGA.id;
	private static final int TUNDRA = Biome.TUNDRA.id;
	
	@Override
	public int sample(FractalRandom random, int north, int east, int south, int west, int centre) {
		if (north == RAINFOREST || east == RAINFOREST || south == RAINFOREST || west == RAINFOREST) {
			if (centre == SNOW_TAIGA || centre == TUNDRA) {
				return GRASSLAND;
			}
		}
		if (north == SWAMP || east == SWAMP || south == SWAMP || west == SWAMP) {
			if (centre == DESERT) {
				return GRASSLAND;
			}
			if (centre == TUNDRA) {
				return MARSH;
			}
		}
		if (north == DESERT || east == DESERT || south == DESERT || west == DESERT) {
			if (centre == OCEAN) {
				return GRASSLAND;
			}
		}
		
		return centre;
	}
	
}
