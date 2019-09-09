package tk.valoeghese.world.gen.fractal;

import tk.valoeghese.world.gen.fractal.type.ModifierFractal;
import tk.valoeghese.world.gen.fractal.util.FractalRandom;

public enum FractalRiverNoise implements ModifierFractal {
	DENSE(4),
	SPARSE(3);
	
	private FractalRiverNoise(int density) {
		this.bound = density;
	}
	
	private final int bound;
	
	@Override
	public int sample(FractalRandom random, int value) {
		return value == 0 ? 0 : 1 + random.nextInt(bound);
	}

}
