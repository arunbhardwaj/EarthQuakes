package assignment1;
import java.util.*;

public class LargestQuakes{
	
	public void findLargestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
		
//        String source = "C:\\Users\\Arun\\eclipse-workspace\\EarthQuake\\src\\data\\nov20quakedatasmall.atom";
        String source = "C:\\Users\\Arun\\eclipse-workspace\\EarthQuake\\src\\data\\nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        
        
//        for (QuakeEntry qe : list) {
//        	System.out.println(qe);
//        }
        System.out.println("read data for a total of " + list.size() + " earthquakes.");
        
//        int largestIndex = indexOfLargest(list);
//        System.out.printf("The largest magnitude earthquake is at location %d "
//        		+ "and has a magnitude of %.2f.\n", largestIndex,
//        		list.get(largestIndex).getMagnitude());
        
        ArrayList<QuakeEntry> results = getLargest(list, 5);
        for (QuakeEntry qe : results) {
        	System.out.println(qe);
        }
        
	}	
	
	public int indexOfLargest(ArrayList<QuakeEntry> data) {
		int index = 0;
		for (int i = 0; i < data.size(); i++) {
			if (data.get(i).getMagnitude() > data.get(index).getMagnitude()) {
				index = i;
			}
		}
		
		return index;
	}
	
	public ArrayList<QuakeEntry> getLargest( ArrayList<QuakeEntry> quakeData, int howMany) {
		ArrayList<QuakeEntry> results = new ArrayList<QuakeEntry>();
		ArrayList<QuakeEntry> temp = new ArrayList<QuakeEntry>(quakeData);
		
		
		/*
		 * 
		 * Method 2: Uses sort to get the last elements in the list.
		 * 
		 *	for (int i = temp.size() - 1; i > temp.size() - 1 - howMany; i--) {
		 * 		results.add(temp.get(i));
		 *	}
		 *
		 */
		if (howMany < temp.size()) {
			
			for (int i = 0; i < howMany; i++) {
				results.add(temp.get(indexOfLargest(temp)));
				temp.remove(indexOfLargest(temp));
				
			}
			
			
			
		} else {
			System.out.println("There are not that many earthquakes in the source file.");
			Collections.sort(temp, QuakeEntry.magnitude_sort_reversed);
			results = temp;
		}
		
		return results;
	}

}
