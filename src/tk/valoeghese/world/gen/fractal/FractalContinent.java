package tk.valoeghese.world.gen.fractal;

import tk.valoeghese.world.gen.fractal.type.InitFractal;
import tk.valoeghese.world.gen.fractal.util.FractalRandom;

public enum FractalContinent implements InitFractal {
	INSTANCE;

	@Override
	public int sample(FractalRandom random, int x, int z) {
		if (x == 0 && z == 0) {
			return 1;
		} else {
			return random.nextInt(8) == 0 ? 1 : 0;
		}
	}

}
