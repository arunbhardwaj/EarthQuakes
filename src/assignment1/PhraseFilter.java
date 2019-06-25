package assignment1;

import java.util.Scanner;

public class PhraseFilter implements Filter {
	private SubStringSearch qe;
	private String phrase;
	private String where;
	private Scanner in = new Scanner(System.in);
	
	public PhraseFilter() {
		System.out.println("Enter a phrase to find:");
        this.phrase = in.next();
        System.out.println("Enter a location to find the phrase:");
        this.where = in.next();
        in.close();
	}
	

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
