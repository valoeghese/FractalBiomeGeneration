package tk.valoeghese.world.gen.biome;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.paint.Color;

public class Biome {
	private static final Map<Integer, Biome> REVERSE = new HashMap<>();
	
	public Biome(int id, Color colour) {
		REVERSE.put(id, this);
		this.id = id;
		this.colour = colour;
	}
	
	public final int id;
	public final Color colour;
	
	public static Biome getBiome(int id) {
		return REVERSE.get(id);
	}
	
	public Biome getShore() {
		return BEACH;
	}
	
	public static final Biome OCEAN = new Biome(0, Color.BLUE);
	
	// Warm Temperate
	public static final Biome GRASSLAND = new Biome(1, Color.LAWNGREEN);
	public static final Biome WOODLAND = new Biome(2, Color.FORESTGREEN);
	public static final Biome SWAMP = new ShorelessBiome(3, Color.PURPLE);
	
	// Desert
	public static final Biome DESERT = new Biome(4, Color.rgb(230, 220, 100));
	public static final Biome RED_DESERT = new Biome(5, Color.ORANGERED);
	
	// Rainforest
	public static final Biome RAINFOREST = new Biome(6, Color.ORANGE);
	
	// Cool Temperate
	public static final Biome BOREAL_TAIGA = new Biome(7, Color.MEDIUMSEAGREEN);
	public static final Biome MARSH = new Biome(8, Color.HOTPINK);
	public static final Biome MOOR = new Biome(9, Color.MEDIUMSPRINGGREEN);
	
	// Snow
	public static final Biome TUNDRA = new ShorelessBiome(10, Color.WHITE);
	public static final Biome SNOW_TAIGA = new Biome(11, Color.LIGHTGREEN);
	
	// Special
	public static final Biome BEACH = new Biome(12, Color.YELLOW);
	public static final Biome RIVER = new Biome(13, Color.BLUE);
	
	// Variants and other biomes
	public static final Biome PAINTED_DESERT = new Biome(14, Color.ROSYBROWN);
	public static final Biome OASIS = new Biome(15, Color.MEDIUMTURQUOISE);
}
