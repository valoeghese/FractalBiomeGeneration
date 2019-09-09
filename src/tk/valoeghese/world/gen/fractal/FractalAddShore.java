package tk.valoeghese.world.gen.fractal;

import tk.valoeghese.world.gen.biome.Biome;
import tk.valoeghese.world.gen.fractal.type.CrossModifierFractal;
import tk.valoeghese.world.gen.fractal.util.FractalRandom;

public enum FractalAddShore implements CrossModifierFractal {
	INSTANCE;
	
	private static final int OCEAN = Biome.OCEAN.id;
	
	@Override
	public int sample(FractalRandom random, int north, int east, int south, int west, int centre) {
		if ((centre != OCEAN) && (north == OCEAN || east == OCEAN || south == OCEAN || west == OCEAN)) {
			return Biome.getBiome(centre).getShore().id;
		}
		
		return centre;
	}

}
