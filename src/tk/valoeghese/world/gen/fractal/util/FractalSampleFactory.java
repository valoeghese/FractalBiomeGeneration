package tk.valoeghese.world.gen.fractal.util;

import tk.valoeghese.world.gen.IntSampler;
import tk.valoeghese.world.gen.CachingIntSampler;

public class FractalSampleFactory {
	
	public FractalSampleFactory (IntSampler sampler) {
		this.sampler = sampler;
	}
	
	private final IntSampler sampler;
	
	public CachingIntSampler buildCaching(int capacity) {
		return new CachingIntSampler(capacity, sampler);
	}
}
