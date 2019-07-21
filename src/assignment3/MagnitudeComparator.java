package assignment3;

import java.util.Comparator;

import assignment1.QuakeEntry;

public class MagnitudeComparator implements Comparator<QuakeEntry> {
	public int compare(QuakeEntry q1, QuakeEntry q2) {
		return Double.compare(q1.getMagnitude(), q2.getMagnitude());
	}
}
