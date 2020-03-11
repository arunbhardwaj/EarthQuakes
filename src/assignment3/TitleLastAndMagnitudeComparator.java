package assignment3;

import java.util.Comparator;

import assignment1.QuakeEntry;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {
	
	public int compare(QuakeEntry q1, QuakeEntry q2) {
		String q1title = q1.getInfo();
		String q2title = q2.getInfo();
		String q1lastword = q1title.substring(q1title.lastIndexOf(" ") + 1);
		String q2lastword = q2title.substring(q2title.lastIndexOf(" ") + 1);
		int result = q1lastword.compareTo(q2lastword);
		if (result == 0) {
			return Double.compare(q1.getMagnitude(), q2.getMagnitude());
		} else {
			return result;
		}
	}

}
