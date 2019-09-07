package tk.valoeghese.world.gen.fractal;

import tk.valoeghese.world.gen.biome.Region;
import tk.valoeghese.world.gen.fractal.type.ModifierFractal;
import tk.valoeghese.world.gen.fractal.util.FractalRandom;

public enum FractalAddClimate implements ModifierFractal {
	INSTANCE;
	
	private static final int WARM_TEMPERATE = Region.WARM_TEMPERATE.id;
	private static final int DESERT = Region.DESERT.id;
	private static final int COOL_TEMPERATE = Region.COOL_TEMPERATE.id;
	private static final int SNOW = Region.SNOW.id;
	
	@Override
	public int sample(FractalRandom random, int value) {
		if (value == 0) {
			return 0;
		} else if (value == 1) {
			return random.nextInt(4) == 0 ? COOL_TEMPERATE : SNOW;
		} else if (value == 2) {
			return random.nextInt(3) == 0 ? COOL_TEMPERATE : WARM_TEMPERATE;
		} else {
			return random.nextInt(3) == 0 ? WARM_TEMPERATE : DESERT;
		}
	}

}
