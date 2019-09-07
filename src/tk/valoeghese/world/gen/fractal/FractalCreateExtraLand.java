package tk.valoeghese.world.gen.fractal;

import tk.valoeghese.world.gen.fractal.type.CrossModifierFractal;
import tk.valoeghese.world.gen.fractal.util.FractalRandom;

public enum FractalCreateExtraLand implements CrossModifierFractal {
	PREPARE {
		@Override
		protected int create(FractalRandom random) {
			return -1;
		}
	},
	CREATE;
	
	@Override
	public int sample(FractalRandom random, int north, int east, int south, int west, int centre) {
		if (centre == -1) {
			return 0;
		}
		
		if (centre != 0) {
			return centre;
		}
		
		// is it surrounded by ocean?
		if (north == 0 && east == 0 && west == 0 && south == 0) {
			// one in ten chance for new land
			return this.create(random);
		}
		
		return 0;
	}
	
	protected int create(FractalRandom random) {
		return random.nextInt(10) == 0 ? 1 : 0;
	}

}
