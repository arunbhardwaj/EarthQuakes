package assignment1;

/**
 * Find N-closest quakes
 * 
 * @author Duke Software/Learn to Program
 * @version 1.0, November 2015
 */

import java.util.*;

public class ClosestQuakes {
    public ArrayList<QuakeEntry> getClosest(ArrayList<QuakeEntry> quakeData, Location current, int howMany) {
        
    	ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
    	ArrayList<QuakeEntry> temp = new ArrayList<QuakeEntry>(quakeData);
        // TO DO
       
        System.out.println("read data for " +quakeData.size());
        for (int i = 0; i < howMany; i++) {
        	int closestIndex = 0;
        	for (int j = 0; j < temp.size(); j++) {
        		if (temp.get(j).getLocation().distanceTo(current) < temp.get(closestIndex).getLocation().distanceTo(current)) {
        			closestIndex = j;
        		}
        	}
        	ret.add(temp.get(closestIndex));
        	temp.remove(closestIndex);
        }
        
        return ret;
    }

    public void findClosestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        
        String source = "C:\\Users\\Arun\\eclipse-workspace\\EarthQuake\\src\\data\\nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
       
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size());

        Location jakarta  = new Location(-6.211,106.845);

        ArrayList<QuakeEntry> close = getClosest(list,jakarta,10);
        for(int k=0; k < close.size(); k++){
            QuakeEntry entry = close.get(k);
            double distanceInMeters = jakarta.distanceTo(entry.getLocation());
            System.out.printf("%4.2f\t %s\n", distanceInMeters/1000,entry);
        }
        System.out.println("number found: "+close.size());
    }
    
    
    
}
