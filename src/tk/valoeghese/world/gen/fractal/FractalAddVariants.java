package tk.valoeghese.world.gen.fractal;

import tk.valoeghese.world.gen.fractal.type.CrossModifierFractal;
import tk.valoeghese.world.gen.fractal.util.FractalRandom;

public enum FractalAddVariants implements CrossModifierFractal {
	INSTANCE;
	
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
		if (similarity >= 3 && random.nextInt(3) == 0) {
			
		}
		
		return centre;
	}
}
