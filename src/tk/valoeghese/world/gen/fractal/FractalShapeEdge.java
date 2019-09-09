package tk.valoeghese.world.gen.fractal;

import tk.valoeghese.world.gen.fractal.type.CrossModifierFractal;
import tk.valoeghese.world.gen.fractal.util.FractalRandom;

public enum FractalShapeEdge implements CrossModifierFractal {
	INSTANCE;

	@Override
	public int sample(FractalRandom random, int north, int east, int south, int west, int centre) {
		if (centre != 0) {
			if (north == 0 || east == 0 || south == 0 || west == 0) {
				if (random.nextInt(6) == 0) {
					return 0;
				}
			}
			return centre;
		} else {
			int chance = 1; // pick equally among all non-ocean biomes by incrementing this number after each successful non-ocean detection.
			//                 (one non-ocean = certain, two non-oceans = pick first one (100% chance for all)
			//                 1/2 chance to be second one (overall, 50% chance for all).
			//                 And so on.
			int landResult = 1; // temporary initialisation to something else to fix compile errors

			if (north != 0 && random.nextInt(chance++) == 0) {
				landResult = north;
			}
			if (east != 0 && random.nextInt(chance++) == 0) {
				landResult = east;
			}
			if (south != 0 && random.nextInt(chance++) == 0) {
				landResult = south;
			}
			if (west != 0 && random.nextInt(chance++) == 0) {
				landResult = west;
			}

			// chance to be replace ocean with land here is dependent on how much land borders this tile
			// 100% chance if surrounded by land
			return random.nextInt(4) < (chance - 1) ? landResult : 0;
		}
	}
}
