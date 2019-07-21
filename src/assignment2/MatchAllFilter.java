package assignment2;

import java.util.ArrayList;

import assignment1.QuakeEntry;

public class MatchAllFilter implements Filter {
	private ArrayList<Filter> filters;
	
	public MatchAllFilter() {
		filters = new ArrayList<Filter>();
	}
	
	public void addFilter(Filter f) {
		filters.add(f);
	}
	
	@Override
	public boolean satisfies(QuakeEntry qe) {
		// TODO Auto-generated method stub
		for (Filter f: filters) {
			if (!f.satisfies(qe)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public String getName() {
		StringBuilder sb = new StringBuilder();
		for (Filter f : filters) {
			sb.append(f.getName()).append(" ");
		}
		return sb.toString();
	}

}
