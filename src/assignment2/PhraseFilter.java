package assignment2;

import java.util.Scanner;

import assignment1.QuakeEntry;
import assignment1.SubStringSearch;

public class PhraseFilter implements Filter {
	private SubStringSearch qe;
	private String phrase;
	private String where;
	private String name;
	
	public PhraseFilter(Scanner in) {
		System.out.println("Creating filter for PhraseFilter.");
		System.out.println("Enter a PHRASE to find:");
        this.phrase = in.next();
        System.out.println("Enter a LOCATION to find the phrase:");
        this.where = in.next();
        System.out.println("Name this filter:");
        this.name = in.next();
	}
	

	public PhraseFilter(String phrase, String where) {
		this.phrase = phrase;
		this.where = where;
	}
	
	@Override
	public boolean satisfies(QuakeEntry qe) {

		this.qe = new SubStringSearch(qe.getInfo(), phrase);
		return this.qe.contains(phrase, this.where);
	}


	@Override
	public String getName() {

		return name;
	}

}
