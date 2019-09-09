package tk.valoeghese.world.gen.fractal;

import tk.valoeghese.world.gen.IntSampler;
import tk.valoeghese.world.gen.biome.Biome;
import tk.valoeghese.world.gen.fractal.type.BiParentedFractal;
import tk.valoeghese.world.gen.fractal.util.FractalRandom;

public enum FractalAddRiver implements BiParentedFractal {
	INSTANCE;
	
	private static final int OCEAN = Biome.OCEAN.id;
	private static final int RIVER = Biome.RIVER.id;
	
	@Override
	public int sample(FractalRandom random, IntSampler mainSampler, IntSampler riverSampler, int x, int z) {
		int centre = mainSampler.sample(x, z);
		int river = riverSampler.sample(x, z);
		
		if (centre != OCEAN && river == RIVER) {
			return RIVER;
		}
		
		return centre;
	}

}
