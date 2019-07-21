package assignment3;

import java.util.Comparator;

import assignment1.QuakeEntry;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {
	
	public int compare(QuakeEntry q1, QuakeEntry q2) {
		String title1 = q1.getInfo();
		String title2 = q2.getInfo();
		String lastwordq1 = title1.substring(title1.lastIndexOf(" ") + 1);
		String lastwordq2 = title2.substring(title2.lastIndexOf(" ") + 1);
		int result = lastwordq1.compareTo(lastwordq2);
		if (result == 0) {
			return Double.compare(q1.getMagnitude(), q2.getMagnitude());
		} else {
			return result;
		}
	}

}
