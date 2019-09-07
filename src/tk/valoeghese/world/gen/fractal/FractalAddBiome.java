package tk.valoeghese.world.gen.fractal;

import tk.valoeghese.world.gen.biome.Region;
import tk.valoeghese.world.gen.fractal.type.ModifierFractal;
import tk.valoeghese.world.gen.fractal.util.FractalRandom;

public enum FractalAddBiome implements ModifierFractal {
	INSTANCE;

	@Override
	public int sample(FractalRandom random, int value) {
		return Region.getRegion(value).pickBiome(random);
	}

}
