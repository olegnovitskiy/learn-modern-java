package org.sample;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * Implement methods count and convert, using Stream API
 * @author admin
 *
 */
public class StreamSamples {

	public static void main(String[] args) {
		System.out.println(count(Arrays.asList(Arrays.asList(1, 2), Arrays.asList(3, 4, 5))));
		System.out.println(convert(Arrays.asList(Arrays.asList(1, 2), Arrays.asList(3, 4, 5))));
		Map<Integer, Integer> map1 = new HashMap<>();
		map1.put(1, 10);
		map1.put(2, 20);

		Map<Integer, Integer> map2 = new HashMap<>();
		map1.put(5, 15);
		map1.put(7, 30);
		map1.put(2, 20);

		System.out.println(mapIntersect(map1, map2));

	}

	/**
	 * Method should return total number of all elements in all lists.
	 * For example, 5 for Arrays.asList(Arrays.asList(1, 2), Arrays.asList(3, 4, 5))
	 * @param items
	 * @return
	 */
	public static int count(List<List<Integer>> items) {
		return items.stream().mapToInt(Collection::size).sum();
	}

	/**
	 * Method should convert list of lists into one-dimensional list 
	 * For example, if we pass parameter below we will get list of 5 elements 
	 * Arrays.asList(Arrays.asList(1, 2), Arrays.asList(3, 4, 5))
	 * @param items
	 * @return
	 */
	public static List<Integer> convert(List<List<Integer>> items) {
		return items.stream().flatMap(Collection::stream).collect(toList());
	}

	public static <K, V> Map<K, V> mapIntersect(Map<K, V> map1, Map<V, K> map2) {

		return map1.entrySet().stream().filter(entry ->
				map2.get(entry.getValue()).equals(entry.getKey()))
				.collect(Collectors.toMap(
						entry -> entry.getKey(), entry2 -> entry2.getValue()
				));
	}
}
