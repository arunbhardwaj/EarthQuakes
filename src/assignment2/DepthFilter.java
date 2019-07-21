package assignment2;

import java.util.Scanner;

import assignment1.QuakeEntry;

public class DepthFilter implements Filter {
	private double minDepth;
	private double maxDepth;
	private String name;
	
	public DepthFilter(Scanner in) {
		System.out.println("Creating filter for DepthFilter.");
		System.out.println("Enter a MINIMUM depth:");
		this.minDepth = Double.parseDouble(in.next());
		System.out.println("Enter a MAXIMUM depth:");
		this.maxDepth = Double.parseDouble(in.next());

		System.out.println("Name this filter:");
		this.name = in.next();
	}
	
	@Override
	public boolean satisfies(QuakeEntry qe) {

		return qe.getDepth() >= this.minDepth && qe.getDepth() <= this.maxDepth;
	}

	@Override
	public String getName() {
		
		return name;
	}

}
