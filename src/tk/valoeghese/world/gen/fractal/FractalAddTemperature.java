package tk.valoeghese.world.gen.fractal;

import tk.valoeghese.world.gen.IntSampler;
import tk.valoeghese.world.gen.fractal.type.ParentedFractal;
import tk.valoeghese.world.gen.fractal.util.FractalRandom;
import tk.valoeghese.world.util.ValueNoise;

public enum FractalAddTemperature implements ParentedFractal {
	INSTANCE;

	@Override
	public int sample(FractalRandom random, IntSampler sampler, int x, int z) {
		int parent = sampler.sample(x, z);
		if (parent != 0) {
			double noise = ValueNoise.noise((double) x * 0.1D, (double) z * 0.1D);
			
			if (noise < 0.25D) {
				return 1;
			} else if (noise < 0.75D) {
				return 2;
			} else {
				return 3;
			}
		}
		
		return 0;
	}

}
