package assignment1;

public class DepthFilter implements Filter {
	private double minDepth;
	private double maxDepth;
	
	public DepthFilter(double minDepth, double maxDepth) {
		this.minDepth = minDepth;
		this.maxDepth = maxDepth;
	}
	
	@Override
	public boolean satisfies(QuakeEntry qe) {
		// TODO Auto-generated method stub
		return qe.getDepth() > this.minDepth && qe.getDepth() < this.maxDepth;
	}

}
