package tk.valoeghese.world.gen;

import tk.valoeghese.world.gen.fractal.util.BiomeFractals;

public class OverworldChunkPopulator extends WorldGenerator {
	
	private IntSampler provider;
	private final long seed;
	
	public OverworldChunkPopulator(long seed) {
		super("overworld");
		
		this.seed = seed;
		
		IntSampler[] providers = BiomeFractals.build(seed);
		this.provider = providers[0];
	}

	@Override
	protected void populateBiomes(WorldArea chunk) {
		for (int x = 0; x < 16; ++x) {
			for (int z = 0; z < 16; ++z) {
				chunk.biomes[(x << 4) + z] = provider.sample((chunk.x << 4) + x, (chunk.z << 4) + z);
			}
		}
	}

	@Override
	public void buildBiomes() {
		IntSampler[] providers = BiomeFractals.build(seed);
		this.provider = providers[0];
	}
	
}
