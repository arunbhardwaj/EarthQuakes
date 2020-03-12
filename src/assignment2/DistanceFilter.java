package assignment2;

import java.util.Scanner;

import assignment1.Location;
import assignment1.QuakeEntry;

public class DistanceFilter implements Filter {
	private double distMax;
	private double mLatitude;
	private double mLongitude;
	private Location from;
	private String name = "DistanceFilter";
	
	public DistanceFilter(Scanner in) {		
		System.out.println("Enter the latitude and longitude of a location, separated by a space:");
		mLatitude = Double.parseDouble(in.next());
		mLongitude = Double.parseDouble(in.next());
		
		System.out.println("How far from the location (in kilometers) do you want to look?");
		this.distMax = in.nextDouble()*1000;
		
		this.from = new Location(mLatitude, mLongitude);
		
	}
	
	@Override
	public boolean satisfies(QuakeEntry qe) {

		return qe.getLocation().distanceTo(from) <= this.distMax;
	}

	@Override
	public String getName() {

		return name;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		return sb.append(name).append(": ").append(distMax).append(" meters from (").append(mLatitude).append(", ").append(mLongitude).append(")").toString();
	}

}
