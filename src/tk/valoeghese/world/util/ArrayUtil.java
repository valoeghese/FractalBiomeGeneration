package tk.valoeghese.world.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class ArrayUtil {
	
	@SafeVarargs
	public static <T> List<T> listOf(T...items) {
		return Arrays.asList(items);
	}
	
	@SafeVarargs
	public static <T> T[] arrayOf(T...items) {
		return items;
	}
	
	public static <T> T[] insertAtIndex(T[] items, T item, int index) {
		List<T> pseudo = new ArrayList<>();
		
		int itemsIndex = 0;
		for (int i = 0; i < pseudo.size(); ++i) {
			if (i == index) {
				pseudo.add(item);
			} else {
				pseudo.add(items[itemsIndex]);
				++itemsIndex;
			}
		}
		return pseudo.toArray(items);
	}
}
