package tk.valoeghese.world.gen.fractal.type;

import tk.valoeghese.world.gen.IntSampler;
import tk.valoeghese.world.gen.fractal.util.FractalRandom;
import tk.valoeghese.world.gen.fractal.util.FractalSampleFactory;
import tk.valoeghese.world.gen.fractal.util.FractalSamplerInfo;

public interface BiParentedFractal {
	
	default public FractalSampleFactory create(FractalSamplerInfo<?> info, FractalSampleFactory parent_1, FractalSampleFactory parent_2) {
		IntSampler sampler_1 = (parent_1.buildCaching(info.getSamplerCapacity()))::sample;
		IntSampler sampler_2 = (parent_2.buildCaching(info.getSamplerCapacity()))::sample;
		
		return new FractalSampleFactory((x, z) -> {
			FractalRandom random = info.create(x, z);
			
			return this.sample(random, sampler_1, sampler_2, x, z);
		});
	}
	
	public int sample(FractalRandom random, IntSampler mainSampler, IntSampler secondarySampler, int x, int z);
}
