package tk.valoeghese.world.gen.fractal;

import tk.valoeghese.world.gen.IntSampler;
import tk.valoeghese.world.gen.fractal.type.ParentedFractal;
import tk.valoeghese.world.gen.fractal.util.FractalRandom;

public enum FractalScale implements ParentedFractal {
	BASIC {
		@Override
		public int sample(FractalRandom random, IntSampler sampler, int x, int z) {
			return sampler.sample(x >> 1, z >> 1);
		}
	},
	SHAPING,
	NOISY {
		@Override
		protected int chooseCentre(FractalRandom random, int directParent, int s, int e, int se) {
			return random.chooseInt(directParent, s, e, se);
		}
	},
	REVERSE_BASIC {
		@Override
		public int sample(FractalRandom random, IntSampler sampler, int x, int z) {
			return sampler.sample(x >> 1 << 2, z >> 1 << 2);
		}
	};

	@Override
	public int sample(FractalRandom random, IntSampler sampler, int x, int z) {
		int xMod = x & 1;
		int zMod = z & 1;
		int transformX = x >> 1;
		int transformZ = z >> 1;

		int directParent = sampler.sample(transformX, transformZ);

		if (xMod == 0 && zMod == 0) {
			return directParent;
		} else {
			if (xMod == 0) {
				return random.chooseInt(directParent, sampler.sample(transformX, transformZ + 1));
			} else if (zMod == 0) {
				return random.chooseInt(directParent, sampler.sample(transformX + 1, transformZ));
			} else {
				int s = sampler.sample(transformX, transformZ + 1);
				int e = sampler.sample(transformX + 1, transformZ);
				int se = sampler.sample(transformX + 1, transformZ + 1);
				return chooseCentre(random, directParent, s, e, se);
			}
		}
	}

	protected int chooseCentre(FractalRandom random, int directParent, int s, int e, int se) {
		if (s == e && e == se) {
			return s;
		} else if (directParent == s) {
			if (directParent == e) {
				return directParent;
			} else if (e == se) {
				return random.chooseInt(directParent, e);
			} else {
				return directParent;
			}
		} else if (directParent == e) {
			if (s == se) {
				return random.chooseInt(directParent, s);
			} else {
				return directParent;
			}
		} else if (s == se) {
			return s;
		} else if (e == se) {
			return e;
		} else {
			return random.chooseInt(directParent, s, e, se);
		}
	}
}
