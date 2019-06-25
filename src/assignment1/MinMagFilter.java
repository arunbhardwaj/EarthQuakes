package assignment1;

public class MinMagFilter implements Filter{
	private double minMag;
	
	public MinMagFilter(double minMag) {
		this.minMag = minMag;
	}
	
	@Override
	public boolean satisfies(QuakeEntry qe) {
		// TODO Auto-generated method stub
		return qe.getMagnitude() >= minMag;
	}

}
