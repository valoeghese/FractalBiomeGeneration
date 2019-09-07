package tk.valoeghese.world.gen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CachingIntSampler implements IntSampler {
	
	private Map<Long, Integer> cache = new HashMap<>();
	private List<Long> cacheIndex = new ArrayList<>();
	
	private final int cacheCapacity;
	private final IntSampler parent;
	
	public CachingIntSampler(int capacity, IntSampler parent) {
		this.cacheCapacity = capacity;
		this.parent = parent;
	}

	@Override
	public int sample(int x, int z) {
		long coordinate = ((long) x & 0xFFFFFFFFL) | (((long)z & 0xFFFFFFFFL) << 32);
		
		if (cache.containsKey(coordinate)) {
			//System.out.println("retrieving from cache");
			return cache.get(coordinate);
		} else {
			int result = parent.sample(x, z);
			cache.put(coordinate, result);
			cacheIndex.add(coordinate);
			// check if cache is above capacity
			if (cacheIndex.size() > cacheCapacity) {
				long keyToRemove = cacheIndex.remove(0);
				cache.remove(keyToRemove);
			}
			return result;
		}
	}
	
	public int[] sample(int x, int z, int width, int depth) {
		int[] result = new int[width * depth];
		for (int localX = 0; localX < width; ++localX) {
			for (int localZ = 0; localZ < width; ++localZ) {
				result[localX * depth + localZ] = this.sample(x + localX, z + localZ);
			}
		}
		return result;
	}

}
