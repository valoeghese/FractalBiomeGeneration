package tk.valoeghese.world.gen.fractal;

import tk.valoeghese.world.gen.biome.Biome;
import tk.valoeghese.world.gen.fractal.type.CrossModifierFractal;
import tk.valoeghese.world.gen.fractal.util.FractalRandom;

public enum FractalAddVariants implements CrossModifierFractal {
	INSTANCE;
	
	private static final int GRASSLAND = Biome.GRASSLAND.id;
	private static final int SWAMP = Biome.SWAMP.id;
	private static final int WOODLAND = Biome.WOODLAND.id;
	private static final int RED_DESERT = Biome.RED_DESERT.id;
	private static final int PAINTED_DESERT = Biome.PAINTED_DESERT.id;
	
	@Override
	public int sample(FractalRandom random, int north, int east, int south, int west, int centre) {
		int similarity = 0;
		if (north == centre) {
			++similarity;
		}
		if (east == centre) {
			++similarity;
		}
		if (south == centre) {
			++similarity;
		}
		if (west == centre) {
			++similarity;
		}
		if (similarity >= 3 && random.nextInt(4) == 0) {
			int result = centre;
			
			if (centre == RED_DESERT) {
				result = PAINTED_DESERT;
			} else if (centre == SWAMP) {
				result = GRASSLAND;
			} else if (centre == GRASSLAND) {
				result = WOODLAND;
			}
			
			return result;
		}
		
		return centre;
	}
}
