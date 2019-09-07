package tk.valoeghese.world.gen;

public final class WorldArea {
	
	public int[] biomes = new int[256];
	
	public final int x, z;
	
	public WorldArea(int x, int z) {
		this.x = x;
		this.z = z;
	}
	
	public int trueX() {
		return x << 4;
	}
	
	public int trueZ() {
		return z << 4;
	}
}
