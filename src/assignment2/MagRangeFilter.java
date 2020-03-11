package assignment2;

import java.util.Scanner;

import assignment1.QuakeEntry;

public class MagRangeFilter implements Filter{
	private double minMag;
	private double maxMag;
	private String name = "MagRangeFilter";
	
	public MagRangeFilter(Scanner in) {
//		System.out.println("Creating filter for MagnitudeRangeFilter.");
		System.out.println("Enter a MINIMUM magnitude:");
		this.minMag = Double.parseDouble(in.next());
		System.out.println("Enter a MAXIMUM magnitude:");
		this.maxMag = Double.parseDouble(in.next());
		
	}
	
	@Override
	public boolean satisfies(QuakeEntry qe) {

		return qe.getMagnitude() >= minMag && qe.getMagnitude() <= maxMag;
	}

	@Override
	public String getName() {

		return name;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		return sb.append(name).append(": ").append(minMag).append(" - ").append(maxMag).toString();
	}
}
