package tk.valoeghese.world.gen.fractal.type;

import tk.valoeghese.world.gen.IntSampler;
import tk.valoeghese.world.gen.fractal.util.FractalRandom;

public interface ModifierFractal extends ParentedFractal {
	
	@Override
	default public int sample(FractalRandom random, IntSampler sampler, int x, int z) {
		int value = sampler.sample(x, z);
		
		return this.sample(random, value);
	}

	public int sample(FractalRandom random, int value);
}
