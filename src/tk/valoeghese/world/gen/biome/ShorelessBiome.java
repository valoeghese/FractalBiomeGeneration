package tk.valoeghese.world.gen.biome;

import javafx.scene.paint.Color;

public class ShorelessBiome extends Biome {

	public ShorelessBiome(int id, Color colour) {
		super(id, colour);
	}
	
	@Override
	public Biome getShore() {
		return this;
	}

}
