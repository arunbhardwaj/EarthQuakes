package assignment2;

import java.util.Scanner;

import assignment1.QuakeEntry;
import assignment1.SubStringSearch;

public class PhraseFilter implements Filter {
	private String phrase;
	private String where;
	private String name = "PhraseFilter";
	
	public PhraseFilter(Scanner in) {
		System.out.println("Enter a PHRASE to look up an earthquake, or type \"all\" to see all:");
		this.phrase = in.next();
		if (this.phrase.equalsIgnoreCase("all")) {
			this.phrase = " ";
		}
//        System.out.println("Enter a LOCATION to find the phrase:"); this.where = in.next();
        this.where = "any"; 
   
	}
	

	public PhraseFilter(String phrase, String where) {
		this.phrase = phrase;
		this.where = where;
	}
	
	@Override
	public boolean satisfies(QuakeEntry qe) {
		
		SubStringSearch search = new SubStringSearch(qe.getInfo(), phrase);
		return search.contains(phrase, this.where);
	}


	@Override
	public String getName() {

		return name;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (this.phrase.equalsIgnoreCase(" ")) {
			return sb.append(name).append(": Searching through all results.").toString();
		}
		return sb.append(name).append(": Searching for \"").append(phrase).append("\"").toString();
	}

}
