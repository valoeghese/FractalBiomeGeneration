package tk.valoeghese.world.gen.fractal;

import tk.valoeghese.world.gen.IntSampler;
import tk.valoeghese.world.gen.fractal.util.FractalRandom;
import tk.valoeghese.world.gen.fractal.util.FractalSampleFactory;
import tk.valoeghese.world.gen.fractal.util.FractalSamplerInfo;

@Deprecated
public enum FractalVoronoi {
	INSTANCE;
	
	private static final int
	nw = 0,
	ne = 1,
	sw = 2,
	se = 3;
	
	public int sample(FractalSamplerInfo<?> info, IntSampler sampler, int x, int z) {
		int sampleX = x >> 2;
		int sampleZ = z >> 2;
		
		FractalRandom random = info.create(sampleX, sampleZ);
		double nwOffsetX = random.nextDouble();
		double nwOffsetZ = random.nextDouble();
		
		random = info.create(sampleX + 1, sampleZ);
		double neOffsetX = random.nextDouble() + 4;
		double neOffsetZ = random.nextDouble();
		
		random = info.create(sampleX, sampleZ + 1);
		double swOffsetX = random.nextDouble();
		double swOffsetZ = random.nextDouble() + 4;
		
		random = info.create(sampleX + 1, sampleZ + 1);
		double seOffsetX = random.nextDouble() + 4;
		double seOffsetZ = random.nextDouble() + 4;
		
		double localX = 0.5 + (double) (x - (sampleX << 2));
		double localZ = 0.5 + (double) (z - (sampleZ << 2));
		
		int direction = nw;
		double sqDistCache = squaredDistanceTo(localX, localZ, nwOffsetX, nwOffsetZ);
		
		double sqDistNE = squaredDistanceTo(localX, localZ, neOffsetX, neOffsetZ);
		if (sqDistCache > sqDistNE) {
			sqDistCache = sqDistNE;
			direction = ne;
		}
		
		double sqDistSW = squaredDistanceTo(localX, localZ, swOffsetX, swOffsetZ);
		if (sqDistCache > sqDistSW) {
			sqDistCache = sqDistSW;
			direction = sw;
		}
		
		if (sqDistCache > squaredDistanceTo(localX, localZ, seOffsetX, seOffsetZ)) {
			direction = se;
		}
		
		if (direction == nw) {
			return sampler.sample(sampleX, sampleZ);
		} else if (direction == ne) {
			return sampler.sample(sampleX + 1, sampleZ);
		} else if (direction == sw) {
			return sampler.sample(sampleX, sampleZ + 1);
		} else {
			return sampler.sample(sampleX + 1, sampleZ + 1);
		}
	}
	
	public FractalSampleFactory create(FractalSamplerInfo<?> info, FractalSampleFactory parent) {
		IntSampler sampler = (parent.buildCaching(info.getSamplerCapacity()))::sample;
		
		return new FractalSampleFactory((x, z) -> {
			return this.sample(info, sampler, x, z);
		});
	}
	
	private double squaredDistanceTo(double x1, double z1, double x2, double z2) {
		double dx = x2 - x1;
		double dz = z2 - z2;
		
		return (dx * dx) + (dz + dz);
	}
}
