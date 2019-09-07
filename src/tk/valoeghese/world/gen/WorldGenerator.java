package tk.valoeghese.world.gen;

public abstract class WorldGenerator {
	
	public WorldGenerator(String name) {
		this.name = name;
	}
	
	public final String name;
	
	protected abstract void populateBiomes(WorldArea chunk);
	
	public WorldArea populate(WorldArea chunk) {
		this.populateBiomes(chunk);
		return chunk;
	}
	
	@Override
	public String toString() {
		return "populator." + name;
	}
	
	@Override
	public boolean equals(Object other) {
		if (other instanceof WorldGenerator) {
			return other.toString().equals(this.toString());
		} else {
			return false;
		}
	}
}
