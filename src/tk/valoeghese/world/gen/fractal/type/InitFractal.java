package tk.valoeghese.world.gen.fractal.type;

import tk.valoeghese.world.gen.fractal.util.FractalRandom;
import tk.valoeghese.world.gen.fractal.util.FractalSampleFactory;
import tk.valoeghese.world.gen.fractal.util.FractalSamplerInfo;

public interface InitFractal {
	default public FractalSampleFactory create(FractalSamplerInfo<?> info) {
		return new FractalSampleFactory((x, z) -> this.sample(info.create(x, z), x, z));
	}
	
	public int sample(FractalRandom random, int x, int z);
}
