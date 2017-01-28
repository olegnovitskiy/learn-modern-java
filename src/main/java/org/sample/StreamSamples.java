package org.sample;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implement methods count and convert, using Stream API
 * @author admin
 *
 */
public class StreamSamples {

	public static void main(String[] args) {
		System.out.println(count(Arrays.asList(Arrays.asList(1, 2), Arrays.asList(3, 4, 5))));
		System.out.println(convert(Arrays.asList(Arrays.asList(1, 2), Arrays.asList(3, 4, 5))));

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
		return items.stream().flatMap(Collection::stream).collect(Collectors.toList());
	}

}
