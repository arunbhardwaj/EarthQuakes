package assignment2;

import java.util.Scanner;

import assignment1.Location;
import assignment1.QuakeEntry;

public class DistanceFilter implements Filter {
	private double distMax;
	private Location from;
	private String name;
	
	public DistanceFilter(double distMax, Location from, Scanner in) {
		this.distMax = distMax;
		this.from = from;
		System.out.println("Creating filter for DistanceFilter.");
		System.out.println("Name this filter:");
		this.name = in.next();
	}
	
	@Override
	public boolean satisfies(QuakeEntry qe) {

		return qe.getLocation().distanceTo(from) <= this.distMax;
	}

	@Override
	public String getName() {

		return name;
	}

}
