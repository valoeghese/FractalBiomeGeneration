package tk.valoeghese.world.gen.fractal.util;

public interface FractalRandom {
	public int nextInt(int bound);
	public int chooseInt(int...items);
	public double nextDouble();
}
