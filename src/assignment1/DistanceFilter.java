package assignment1;

public class DistanceFilter implements Filter {
	private double distMax;
	private Location from;
	
	public DistanceFilter(double distMax, Location from) {
		this.distMax = distMax;
		this.from = from;
	}
	
	@Override
	public boolean satisfies(QuakeEntry qe) {
		// TODO Auto-generated method stub
		return qe.getLocation().distanceTo(from) <= this.distMax;
	}

}
