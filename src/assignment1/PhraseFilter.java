package assignment1;

public class PhraseFilter implements Filter {
	private SubStringSearch qe;
	private String phrase;
	private String where;
	
	public PhraseFilter(String phrase, String where) {
		this.phrase = phrase;
		this.where = where;
	}
	
	@Override
	public boolean satisfies(QuakeEntry qe) {
		// TODO Auto-generated method stub
		this.qe = new SubStringSearch(qe.getInfo(), phrase);
		return this.qe.contains(phrase, this.where);
	}

}
