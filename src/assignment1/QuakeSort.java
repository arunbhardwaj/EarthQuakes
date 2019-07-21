package assignment1;

import java.util.ArrayList;

public class QuakeSort {
	/**
	 * Uses selection sort to sort an ArrayList by magnitude. Uses
	 * @param list 
	 */
	public static ArrayList<QuakeEntry> sortByMagnitude(ArrayList<QuakeEntry> list) {
		ArrayList<QuakeEntry> result = new ArrayList<QuakeEntry>();
		
		while (!list.isEmpty()) {
			QuakeEntry min = list.get(getSmallestQuakeIndex(list));
			result.add(min);
			list.remove(min);
		}
		
		return result;
	}
	
	/**
	 * Sorts an ArrayList of QuakeEntry's by Magnitude (smallest to largest) 
	 * using a variation on selection sort that uses swapping.
	 * @param list of QuakeEntry's
	 */
	public static void sortByMagnitude_WithSwap(ArrayList<QuakeEntry> list) {
		int min;
		QuakeEntry temp;
		
		for (int i = 0; i < list.size(); i++) {
			min = getSmallestQuakeIndex(list, i);
			temp = list.get(i);
			list.set(i, list.get(min));
			list.set(min, temp);
		}
	}
	
	/**
	 * Wrapper method that finds the smallest magnitude QuakeEntry in 
	 * the list starting from index 0. 
	 * @param list of QuakeEntrys
	 * @return the integer index of smallest magnitude QuakeEntry
	 */
	private static int getSmallestQuakeIndex(ArrayList<QuakeEntry> list) {
		return getSmallestQuakeIndex(list, 0);
	}
	
	private static int getSmallestQuakeIndex(ArrayList<QuakeEntry> list, int from) {
		int min = from;
		for (int i = from + 1; i < list.size(); i++) {
			if (list.get(i).getMagnitude() < list.get(min).getMagnitude()) {
				min = i;
			}
		}
		return min;
	}
	
	
	/**
	 * Sorts an ArrayList of QuakeEntry's by Depth (smallest to largest) 
	 * using a variation on selection sort that uses swapping.
	 * @param list
	 */
	public static void sortByLargestDepth(ArrayList<QuakeEntry> list) {
		int min;
		QuakeEntry temp;
		
		for (int i = 0; i < list.size(); i++) {
			min = getLargestDepth(list, i);
			temp = list.get(i);
			list.set(i, list.get(min));
			list.set(min, temp);
		}
	}
	
	/**
	 * Wrapper method that finds the greatest Depth QuakeEntry in 
	 * the list starting from index 0. 
	 * @param list ArrayList of QuakeEntry's
	 * @return integer index of lowest depth QuakeEntry
	 */
	@SuppressWarnings("unused")
	private static int getLargestDepth(ArrayList<QuakeEntry> list) {
		return getLargestDepth(list, 0);
	}
	
	private static int getLargestDepth(ArrayList<QuakeEntry> list, int from) {
		int min = from;
		for (int i = from + 1; i < list.size(); i++) {
			if (list.get(i).getDepth() < list.get(min).getDepth()) {
				min = i;
			}
		}
		return min;
	}
}
