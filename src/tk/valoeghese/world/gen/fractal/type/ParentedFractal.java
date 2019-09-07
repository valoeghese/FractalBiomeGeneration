package tk.valoeghese.world.gen.fractal.type;

import tk.valoeghese.world.gen.IntSampler;
import tk.valoeghese.world.gen.fractal.util.FractalRandom;
import tk.valoeghese.world.gen.fractal.util.FractalSampleFactory;
import tk.valoeghese.world.gen.fractal.util.FractalSamplerInfo;

public interface ParentedFractal {
	
	default public FractalSampleFactory create(FractalSamplerInfo<?> info, FractalSampleFactory parent) {
		IntSampler sampler = (parent.buildCaching(info.getSamplerCapacity()))::sample;
		
		return new FractalSampleFactory((x, z) -> {
			FractalRandom random = info.create(x, z);
			
			return this.sample(random, sampler, x, z);
		});
	}
	
	public int sample(FractalRandom random, IntSampler sampler, int x, int z);
}
