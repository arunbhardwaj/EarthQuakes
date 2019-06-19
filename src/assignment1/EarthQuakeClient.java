package assignment1;

import java.util.*;
import java.util.Scanner;
//import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Creates and populates an ArrayList object with earthquakes in the 
     * ArrayList quakeData that have a magnitude that is greater than or 
     * equal to magMin.
     * @param quakeData
     * @param magMin
     * @return earthquakes with magnitudes greater than or equal to user input
     */
    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData, double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();

        for (QuakeEntry entry : quakeData) {
        	if (entry.getMagnitude() >= magMin) {
        		answer.add(entry);
        	}
        }
        return answer;
    }

    /**
     * Creates and populates an ArrayList object with earthquakes in quakeData 
     * whose distance from a specified location are less than or equal to some 
     * user-input distance. 
     * @param quakeData list of earthquakes
     * @param distMax max distance from location
     * @param from location to find nearby earthquakes
     * @return earthquakes that are a certain distance away from a location
     */
    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData, 
    													double distMax, 
    													Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        
        for (QuakeEntry entry : quakeData) {
        	if (entry.getLocation().distanceTo(from) <= distMax) {
        		answer.add(entry);
        	}
        }

        return answer;
    }

    /**
     * Finds all earthquakes with depths in between the specified range.
     * @param quakeData ArrayList of all earthquakes
     * @param minDepth minimum depth
     * @param maxDepth maximum depth
     * @return ArrayList of earthquakes with depths in between a specified range
     */
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, double minDepth, double maxDepth) {
    	ArrayList<QuakeEntry> result = new ArrayList<QuakeEntry>();
    	for (QuakeEntry qe : quakeData) {
    		double depth = qe.getDepth();
    		if (depth >= minDepth && depth <= maxDepth) {
    			result.add(qe);
    		}
    	}
    	return result;
    }
    
    /**
     * Finds all earthquakes with a title that matches a chosen phrase 
     * at a specific position within that title.
     * @param quakeData ArrayList of all earthquakes
     * @param phrase that we want to look for in the title
     * @param where in the title should we look
     * @return ArrayList of earthquakes with titles matching our phrase
     */
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, String phrase, String where) {
    	ArrayList<QuakeEntry> result = new ArrayList<QuakeEntry>();
		for (QuakeEntry entry : quakeData) {
			SubStringSearch qe = new SubStringSearch(entry.getInfo(), phrase);
			if (qe.contains(phrase, where)) {
				result.add(entry);
			}
		}
    	
    	return result;
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "C:\\Users\\Arun\\eclipse-workspace\\EarthQuake\\src\\data\\nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        
        ArrayList<QuakeEntry> answer = filterByMagnitude(list, 5.0);
        for (QuakeEntry qe : answer) {
        	System.out.println(qe);
        }
        
        System.out.println("Found " + answer.size() + " quakes that match that criteria");

    }

    public void quakesOfDepth() {
    	EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "C:\\Users\\Arun\\eclipse-workspace\\EarthQuake\\src\\data\\nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        
        ArrayList<QuakeEntry> answer = filterByDepth(list, -10000.0, -5000.0);
        for (QuakeEntry qe : answer) {
        	System.out.println(qe);
        }
        
        System.out.println("Found " + answer.size() + " quakes that match that criteria");

    }
    
    /**
     * Uses filterByPhrase to print all earthquakes from a data source
     * that have the desired phrase in their title in a given position
     * in the title. Also prints the number of earthquakes found that
     * match the criteria.
     */
    public void quakesByPhrase() {
    	EarthQuakeParser parser = new EarthQuakeParser();
    	Scanner in = new Scanner(System.in);
    	
    	// Specify a source file:
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "C:\\Users\\Arun\\eclipse-workspace\\EarthQuake\\src\\data\\nov20quakedatasmall.atom";
        
        
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        
        
        System.out.println("Enter a phrase to find:");
        String phrase = in.next();
        System.out.println("Enter a location to find the phrase:");
        String where = in.next();
        
        
        ArrayList<QuakeEntry> answer = filterByPhrase(list, phrase, where);
        for (QuakeEntry qe : answer) {
        	System.out.println(qe);
        }
        
        System.out.println("Found " + answer.size() + " quakes that match that criteria");

    }
    
    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        // String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "C:\\Users\\Arun\\eclipse-workspace\\EarthQuake\\src\\data\\nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
        // Location city = new Location(35.988, -78.907);

        // This location is Bridgeport, CA
         Location city =  new Location(38.17, -118.82);

        // This location is New York, NY
        // Location city = new Location(40.7141667, -74.0063889);
        
        // TODO
        ArrayList<QuakeEntry> answer = filterByDistanceFrom(list, 1000000, city);
        for (QuakeEntry qe : answer) {
        	System.out.println(qe);
        }
        System.out.println("Found " + answer.size() + " quakes that match that criteria");
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
    
    
}
