package tk.valoeghese.world.gen.fractal;

import tk.valoeghese.world.gen.biome.Biome;
import tk.valoeghese.world.gen.fractal.type.CrossModifierFractal;
import tk.valoeghese.world.gen.fractal.util.FractalRandom;

public enum FractalRiver implements CrossModifierFractal {
	PREPARE {
		@Override
		public int sample(FractalRandom random, int north, int east, int south, int west, int centre) {
			int dissimilarity = 0;
			if (north != centre) {
				++dissimilarity;
			}
			if (east != centre) {
				++dissimilarity;
			}
			if (south != centre) {
				++dissimilarity;
			}
			if (west != centre) {
				++dissimilarity;
			}
			
			return dissimilarity;
		}
	},
	CREATE {
		@Override
		public int sample(FractalRandom random, int north, int east, int south, int west, int centre) {
			if (centre >= 1 || north >= 2 || east >= 2 || south >= 2 || west >= 2) {
				return RIVER;
			} else {
				return 0;
			}
		}
	};
	
	private static final int RIVER = Biome.RIVER.id;
	
	@Override
	public abstract int sample(FractalRandom random, int north, int east, int south, int west, int centre);

}
