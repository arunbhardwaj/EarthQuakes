package assignment1;

import java.util.Comparator;

public class QuakeEntry implements Comparable<QuakeEntry> {
    private Location myLocation;
    private String title;
    private double depth;
    private double magnitude;

    public QuakeEntry(double lat, double lon, double mag, 
    String t, double d) {
        myLocation = new Location(lat,lon);

        magnitude = mag;
        title = t;
        depth = d;
    }

    public Location getLocation(){
        return myLocation;
    }

    public double getMagnitude(){
        return magnitude;
    }

    /**
     * 
     * @return the title of the QuakeEntry
     */
    public String getInfo(){
        return title;
    }

    public double getDepth(){
        return depth;
    }
    
    public String toString(){
        return String.format("(%3.2f, %3.2f), mag = %3.2f, depth = %3.2f, title = %s", myLocation.getLatitude(),myLocation.getLongitude(),magnitude,depth,title);
    }
    
    @Override
    public int compareTo(QuakeEntry q2) {
    	/*
        double difflat = myLocation.getLatitude() - loc.myLocation.getLatitude();
        if (Math.abs(difflat) < 0.001) {
            double diff = myLocation.getLongitude() - loc.myLocation.getLongitude();
            if (diff < 0) return -1;
            if (diff > 0) return 1;
            return 0;
        }
        if (difflat < 0) return -1;
        if (difflat > 0) return 1;
		*/
    	if (magnitude < q2.getMagnitude()) {
    		return -1;
    	} else if (magnitude > q2.getMagnitude()) {
    		return 1;
    	} else {
    		return Double.compare(depth, q2.getDepth());
    	}
    	
        // never reached
//        return 0;
    }
    
    static Comparator<QuakeEntry> magnitude_sort = new Comparator<QuakeEntry>() {
    	public int compare(QuakeEntry q1, QuakeEntry q2) {
    		int magnitudeCompared = Double.compare(q1.getMagnitude(), q2.getMagnitude());
    	
    		return magnitudeCompared;
    	}
    };
    
    static Comparator<QuakeEntry> magnitude_sort_reversed = new Comparator<QuakeEntry>() {
    	public int compare(QuakeEntry q1, QuakeEntry q2) {
    		int magnitudeCompared = Double.compare(q2.getMagnitude(), q1.getMagnitude());
    	
    		return magnitudeCompared;
    	}
    };
    
	

}