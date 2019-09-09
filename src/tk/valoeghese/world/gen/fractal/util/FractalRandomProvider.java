package tk.valoeghese.world.gen.fractal.util;

public final class FractalRandomProvider implements FractalSamplerInfo<FractalRandomProvider.Instance> {
	
	private long initSeed;
	private int capacity;
	
	public FractalRandomProvider(int capacity, long seed, long salt) {
		initSeed = salt;
		initSeed *= 3412375462423L * initSeed + 834672456235L;
		initSeed += salt;
		initSeed *= 3412375462423L * initSeed + 834672456235L;
		initSeed += salt;
		initSeed *= 3412375462423L * initSeed + 834672456235L; 
		
		this.capacity = capacity;
		
		this.setSeed(seed);
	}
	
	public int getSamplerCapacity() {
		return capacity;
	}
	
	public void setSeed(long seed) {
		initSeed = seed;
		initSeed *= 3412375462423L * initSeed + 834672456235L;
		initSeed += seed;
		initSeed *= 3412375462423L * initSeed + 834672456235L;
		initSeed += seed;
		initSeed *= 3412375462423L * initSeed + 834672456235L;
	}
	
	@Override
	public Instance create(int x, int z) {
		long localSeed = initSeed;
		localSeed += x;
		localSeed *= 3412375462423L * localSeed + 834672456235L;
		localSeed += z;
		localSeed *= 3412375462423L * localSeed + 834672456235L;
		
		return new Instance(localSeed, initSeed);
	}
	
	public static class Instance implements FractalRandom {
		
		private long localSeed;
		private final long parentSeed;
		
		private Instance(long seed, long parentSeed) {
			this.localSeed = seed;
			this.parentSeed = parentSeed;
		}
		
		public int nextInt(int bound) {
			int result = (int) ((localSeed >> 24) % bound);
			if (result < 0) {
				result += (bound - 1);
			}
			
			localSeed += parentSeed;
			localSeed *= 3412375462423L * localSeed + 834672456235L;
			
			return result;
		}
		
		public double nextDouble() {
			return (double) this.nextInt(Integer.MAX_VALUE) / (double) Integer.MAX_VALUE;
		}

		@Override
		public int chooseInt(int... items) {
			if (items.length == 0) {
				new ArrayIndexOutOfBoundsException("Empty array passed to FractalRandom chooseInt method!").printStackTrace();
				return 0;
			}
			return items[nextInt(items.length)];
		}
	}
}
