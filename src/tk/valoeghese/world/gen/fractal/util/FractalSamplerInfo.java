package tk.valoeghese.world.gen.fractal.util;

public interface FractalSamplerInfo<T extends FractalRandom> {
	public T create(int x, int z);
	public int getSamplerCapacity();
}
