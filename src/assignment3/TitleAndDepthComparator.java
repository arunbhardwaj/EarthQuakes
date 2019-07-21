package assignment3;

import java.util.Comparator;

import assignment1.QuakeEntry;

public class TitleAndDepthComparator implements Comparator<QuakeEntry> {
	public int compare(QuakeEntry q1, QuakeEntry q2) {
		if (q1.getInfo().compareTo(q2.getInfo()) != 0) {
			return q1.getInfo().compareTo(q2.getInfo());
		} else {
			return Double.compare(q1.getDepth(), q2.getDepth());
		}
	}
}
