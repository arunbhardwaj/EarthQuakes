package assignment3;

import java.util.Comparator;

import assignment1.Location;
import assignment1.QuakeEntry;

public class DistanceComparator implements Comparator<QuakeEntry> {
	Location fromWhere;
	
	public DistanceComparator(Location fromWhere) {
		this.fromWhere = fromWhere;
	}
	public int compare(QuakeEntry q1, QuakeEntry q2) {
		double dist1 = q1.getLocation().distanceTo(fromWhere);
		double dist2 = q2.getLocation().distanceTo(fromWhere);
		return Double.compare(dist1, dist2);
	}
}
