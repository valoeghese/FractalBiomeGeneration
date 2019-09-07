package tk.valoeghese.world.gen.fractal;

import tk.valoeghese.world.gen.fractal.type.CrossModifierFractal;
import tk.valoeghese.world.gen.fractal.util.FractalRandom;

public enum FractalCorrectPatches implements CrossModifierFractal {
	INSTANCE;

	@Override
	public int sample(FractalRandom random, int north, int east, int south, int west, int centre) {
		if (centre != north && centre != east && centre != south && centre != west) {
			return random.chooseInt(north, east, south, west);
		}
		return centre;
	}
	
}
