package tk.valoeghese.world.gen.fractal.util;

import java.util.List;
import java.util.function.LongFunction;

import tk.valoeghese.world.Main;
import tk.valoeghese.world.gen.CachingIntSampler;
import tk.valoeghese.world.gen.fractal.FractalAddBiome;
import tk.valoeghese.world.gen.fractal.FractalAddClimate;
import tk.valoeghese.world.gen.fractal.FractalAddShore;
import tk.valoeghese.world.gen.fractal.FractalAddSpecial;
import tk.valoeghese.world.gen.fractal.FractalAddTemperature;
import tk.valoeghese.world.gen.fractal.FractalAddVariants;
import tk.valoeghese.world.gen.fractal.FractalContinent;
import tk.valoeghese.world.gen.fractal.FractalCorrectPatches;
import tk.valoeghese.world.gen.fractal.FractalCreateExtraLand;
import tk.valoeghese.world.gen.fractal.FractalModerateEdge;
import tk.valoeghese.world.gen.fractal.FractalScale;
import tk.valoeghese.world.gen.fractal.FractalShapeEdge;
import tk.valoeghese.world.gen.fractal.type.ParentedFractal;
import tk.valoeghese.world.util.ArrayUtil;

public final class BiomeFractals {
	
	private static List<FractalSampleFactory> createFactories(LongFunction<FractalSamplerInfo<?>> infoProvider) {
		FractalSampleFactory continent = FractalContinent.INSTANCE.create(infoProvider.apply(1L));
		continent = FractalScale.NOISY.create(infoProvider.apply(2L), continent);
		
		continent = FractalCreateExtraLand.PREPARE.create(infoProvider.apply(10L), continent);
		continent = FractalScale.BASIC.create(infoProvider.apply(0L), continent);
		
		continent = FractalCreateExtraLand.CREATE.create(infoProvider.apply(10L), continent);
		continent = FractalShapeEdge.INSTANCE.create(infoProvider.apply(11L), continent);
		continent = FractalAddTemperature.INSTANCE.create(infoProvider.apply(12L), continent);
		continent = FractalCorrectPatches.INSTANCE.create(infoProvider.apply(13L), continent);
		continent = FractalScale.BASIC.create(infoProvider.apply(0L), continent);
		
		continent = FractalShapeEdge.INSTANCE.create(infoProvider.apply(20L), continent);
		continent = FractalAddClimate.INSTANCE.create(infoProvider.apply(20L), continent);
		continent = FractalCorrectPatches.INSTANCE.create(infoProvider.apply(20L), continent);
		continent = FractalAddSpecial.INSTANCE.create(infoProvider.apply(21L), continent);
		continent = FractalScale.SHAPING.create(infoProvider.apply(1000L), continent);
		
		FractalSampleFactory biome = FractalAddBiome.INSTANCE.create(infoProvider.apply(100L), continent);
		biome = repeatFractal(1001L, FractalScale.SHAPING, 2, biome, infoProvider);
		
		biome = FractalModerateEdge.INSTANCE.create(infoProvider.apply(1001L), biome);
		biome = FractalAddVariants.INSTANCE.create(infoProvider.apply(1000L), biome);
		
		biome = repeatFractal(1000L, FractalScale.SHAPING, 2, biome, infoProvider);
		biome = FractalAddShore.INSTANCE.create(infoProvider.apply(200L), biome);
		biome = FractalScale.SHAPING.create(infoProvider.apply(1002L), biome);
		
		biome = repeatFractal(1001L, FractalScale.SHAPING, 2, biome, infoProvider);
		biome = repeatFractal(1L, FractalScale.REVERSE_BASIC, Main.zoom, biome, infoProvider);
		
		return ArrayUtil.listOf(biome);
	}
	
	private static FractalSampleFactory repeatFractal(long baseSalt, ParentedFractal fractal, int count, FractalSampleFactory factory, LongFunction<FractalSamplerInfo<?>> infoProvider) {
		FractalSampleFactory result = factory;
		
		for (int i = 0; i < count; ++i) {
			result = fractal.create(infoProvider.apply(baseSalt + i), result);
		}
		return result;
	}
	
	public static CachingIntSampler[] build(long seed) {
		List<FractalSampleFactory> factories = createFactories((salt) -> new FractalRandomProvider(17, seed, salt));
		
		CachingIntSampler provider = factories.get(0).buildCaching(25);
		
		return new CachingIntSampler[] {provider};
	}
}