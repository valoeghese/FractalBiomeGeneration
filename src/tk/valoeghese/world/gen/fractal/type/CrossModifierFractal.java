package tk.valoeghese.world.gen.fractal.type;

import tk.valoeghese.world.gen.IntSampler;
import tk.valoeghese.world.gen.fractal.util.FractalRandom;

public interface CrossModifierFractal extends ParentedFractal {
	
	@Override
	default public int sample(FractalRandom random, IntSampler sampler, int x, int z) {
		
		int north = sampler.sample(x, z - 1);
		int east = sampler.sample(x + 1, z);
		int south = sampler.sample(x, z + 1);
		int west = sampler.sample(x - 1, z);
		int centre = sampler.sample(x, z);
		
		return this.sample(random, north, east, south, west, centre);
	}

	public int sample(FractalRandom random, int north, int east, int south, int west, int centre);
}
