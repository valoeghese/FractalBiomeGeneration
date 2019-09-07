package tk.valoeghese.world.gen.fractal;

import tk.valoeghese.world.gen.biome.Region;
import tk.valoeghese.world.gen.fractal.type.CrossModifierFractal;
import tk.valoeghese.world.gen.fractal.util.FractalRandom;

public enum FractalAddSpecial implements CrossModifierFractal {
	INSTANCE;

	private static final int WARM_TEMPERATE = Region.WARM_TEMPERATE.id;
	private static final int DESERT = Region.DESERT.id;
	private static final int RAINFOREST = Region.RAINFOREST.id;
	private static final int SNOW = Region.SNOW.id;

	@Override
	public int sample(FractalRandom random, int north, int east, int south, int west, int centre) {
		if (centre == 0) {
			return 0;
		}

		if (north == WARM_TEMPERATE || east == WARM_TEMPERATE || south == WARM_TEMPERATE || west == WARM_TEMPERATE) {
			if (random.nextInt(5) == 0) {
				if (canSpawnNextTo(north) && canSpawnNextTo(east) && canSpawnNextTo(south) && canSpawnNextTo(west)) {
					return RAINFOREST;
				}
			}
		}

		return centre;
	}
	
	private boolean canSpawnNextTo(int value) {
		return value != DESERT && value != SNOW;
	}

}
