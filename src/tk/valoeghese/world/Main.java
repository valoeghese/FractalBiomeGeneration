package tk.valoeghese.world;

import java.util.Random;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import tk.valoeghese.world.gen.OverworldChunkPopulator;
import tk.valoeghese.world.gen.WorldArea;
import tk.valoeghese.world.gen.WorldGenerator;
import tk.valoeghese.world.gen.biome.Biome;
import tk.valoeghese.world.gen.biome.Region;

public class Main extends Application {

	public static int WIDTH = 512;
	public static int HEIGHT = 512;
	public static WorldGenerator populator;
	
	private int xOff = 0;
	private int zOff = 0;
	public static int zoom = 0;
	
	public static void main(String[] args) {
		setup();

		populator = new OverworldChunkPopulator(new Random().nextLong());

		launch(new String[0]);
	}

	private static void setup() {
		Region.OCEAN.addBiome(Biome.OCEAN, 1D);

		Region.WARM_TEMPERATE.addBiome(Biome.GRASSLAND, 1.5D);
		Region.WARM_TEMPERATE.addBiome(Biome.WOODLAND, 1D);
		Region.WARM_TEMPERATE.addBiome(Biome.SWAMP, 0.5D);

		Region.DESERT.addBiome(Biome.DESERT, 1D);
		Region.DESERT.addBiome(Biome.RED_DESERT, 0.25D);

		Region.RAINFOREST.addBiome(Biome.RAINFOREST, 1D);

		Region.COOL_TEMPERATE.addBiome(Biome.BOREAL_TAIGA, 2D);
		Region.COOL_TEMPERATE.addBiome(Biome.MARSH, 1D);
		Region.COOL_TEMPERATE.addBiome(Biome.MOOR, 0.5D);
		Region.COOL_TEMPERATE.addBiome(Biome.GRASSLAND, 1D);

		Region.SNOW.addBiome(Biome.TUNDRA, 3D);
		Region.SNOW.addBiome(Biome.SNOW_TAIGA, 1D);
	}

	@Override
	public void start(Stage stage) {
		Pane pane = new Pane();
		Canvas canvas = new Canvas(WIDTH, HEIGHT);
		final GraphicsContext graphics = canvas.getGraphicsContext2D();
		
		PixelWriter pw = graphics.getPixelWriter();
		
		drawAndNotif(pw);

		pane.getChildren().add(canvas);

		Scene scene = new Scene(pane, WIDTH, HEIGHT, Color.WHITESMOKE);
		
		scene.addEventHandler(KeyEvent.KEY_PRESSED, k -> {
			if (k.getCode() == KeyCode.UP) {
				zOff = (zOff - (zoom + 1));
				draw(pw, false);
			} else if (k.getCode() == KeyCode.LEFT) {
				xOff = (xOff - (zoom + 1));
				draw(pw, false);
			} else if (k.getCode() == KeyCode.DOWN) {
				zOff = (zOff + (zoom + 1));
				draw(pw, false);
			} else if (k.getCode() == KeyCode.RIGHT) {
				xOff = (xOff + (zoom + 1));
				draw(pw, false);
			} if (k.getCode() == KeyCode.Z && zoom < 3) {
				++zoom;
				populator.buildBiomes();
				draw(pw, false);
			} else if (k.getCode() == KeyCode.X && zoom > 0) {
				--zoom;
				populator.buildBiomes();
				draw(pw, false);
			}
		});
		
		stage.setTitle("Application");
		stage.setScene(scene);
		stage.show();
	}

	private void drawAndNotif(PixelWriter pw) {
		long time = System.currentTimeMillis();

		draw(pw, true);

		long currentTime = System.currentTimeMillis();
		System.out.println("Finished Generating. Time elapsed: " + String.valueOf((int) ((double) (currentTime - time) * 0.001D)) + "s.");
	}

	private void draw(PixelWriter pw, boolean notif) {
		int transformWidth = (WIDTH >> 4);

		for (int chunkX = 0; chunkX < transformWidth; ++chunkX) {
			if (notif) {
				System.out.println("Generating: " + String.valueOf((int) (100D * ((double) chunkX) / (double) transformWidth)) + "%");
			}
			for (int chunkZ = 0; chunkZ < (HEIGHT >> 4); ++chunkZ) {
				WorldArea chunk = new WorldArea(chunkX + xOff, chunkZ + zOff);
				int[] biomes = populator.populate(chunk).biomes;
				for (int localX = 0; localX < 16; ++localX) {
					int totalX = (chunkX << 4) + localX;
					if (totalX >= WIDTH) {
						break;
					}
					for (int localZ = 0; localZ < 16; ++localZ) {
						int totalZ = (chunkZ << 4) + localZ;
						if (totalZ >= HEIGHT) {
							break;
						}
						pw.setColor(totalX, totalZ, Biome.getBiome(biomes[(localX << 4) + localZ]).colour);
					}
				}

			}
		}
	}

}
