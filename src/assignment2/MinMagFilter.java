package assignment2;

import java.util.Scanner;

import assignment1.QuakeEntry;

public class MinMagFilter implements Filter{
	private double minMag;
	private double maxMag;
	private String name;
	
	public MinMagFilter(Scanner in) {
		System.out.println("Creating filter for MinMagFilter.");
		System.out.println("Enter a MINIMUM magnitude:");
		this.minMag = Double.parseDouble(in.next());
		System.out.println("Enter a MAXIMUM magnitude:");
		this.maxMag = Double.parseDouble(in.next());
		
		System.out.println("NAME this filter:");
		this.name = in.next();
		
	}
	
	@Override
	public boolean satisfies(QuakeEntry qe) {

		return qe.getMagnitude() >= minMag && qe.getMagnitude() <= maxMag;
	}

	@Override
	public String getName() {

		return name;
	}

}
