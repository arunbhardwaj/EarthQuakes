package assignment1;

/**
 * SubStringSearch utilizes Knuth-Morris-Pratt algorithm
 * to search for occurrences of a "substring" within a 
 * larger "main text" by employing the observation that
 * the "substring" may contain patterns that can shorten 
 * search times when mismatches occur. 
 * 
 * @author Arun
 *
 */
public class SubStringSearch {
	
	private String sourceString;
	private String subString;
	private int[] patternArray;
	private int index;
	
	public SubStringSearch(String sourceString) {
		this.sourceString = sourceString;
	}
	
	public SubStringSearch(String sourceString, String subString) {
		this.sourceString = sourceString;
		this.subString = subString;
		this.patternArray = buildPatternArray(this.subString);
	}

	/**
	 * Checks if this objects text contains the substring chosen.
	 * Future improvements: using a SubStringSearch object to call the method
	 * instead of a text object to call contains on a substring
	 * is not intuitive (as is the fact that unintuitive is not 
	 * an actual word contained in an authoritative dictionary
	 * source)
	 * 
	 * @return true if substring was found within the text or false if not
	 */
	public boolean contains() {
		this.index = 0;
		int i = 0, j = 0;
		while (j < subString.length() && i < sourceString.length()) {
			if (sourceString.charAt(i) == subString.charAt(j) && j == subString.length()-1) {
				return true;
			} else if (sourceString.charAt(i) == subString.charAt(j)) {
				i++; j++;
			} else if (sourceString.charAt(i) != subString.charAt(j) && j != 0) {
				j = patternArray[j-1];
				this.index = i-j;
			} else {
				this.index = ++i;
			}
		}
		
		if (this.index >= sourceString.length()) {
			this.index = -1;
		}
		return false;
	}
	
	/**
	 * Checks if this objects text contains the substring chosen, with 
	 * added functionality of checking from a certain location indicated 
	 * with where.
	 * 
	 * @param subString the substring to search the text for
	 * @param where location where to begin search ("start", "end", "any" are currently the only options supported)
	 * @return true if found, false if not found
	 */
	public boolean contains(String subString, String where) {
		this.subString = subString;
		
		if (where.equalsIgnoreCase("start")) {
				this.index = 0;
				int i = 0, j = 0;
				
				while (j < subString.length() && i < sourceString.length()) {
					if (sourceString.charAt(i) == subString.charAt(j) && j == subString.length()-1) {
						return true;
					} else if (sourceString.charAt(i) == subString.charAt(j)) {
						i++; j++;
					} else if (sourceString.charAt(i) != subString.charAt(j)) {
						this.index = -1;
						return false;
					}
				}
				
		} else if (where.equalsIgnoreCase("end")) {
				int i = sourceString.length() - subString.length(); this.index = i;
				int j = 0;
				
				while (j < subString.length() && i < sourceString.length()) {
					if (sourceString.charAt(i) == subString.charAt(j) && j == subString.length()-1) {
						return true;
					} else if (sourceString.charAt(i) == subString.charAt(j)) {
						i++; j++;
					} else if (sourceString.charAt(i) != subString.charAt(j)) {
						this.index = -1;
						return false;
					}
				}
				
		} else if (where.equalsIgnoreCase("any")) {
				return this.contains();

		} else {
				System.out.println("There was an error.");
				return false;
		}
		return false;
	}
	
	private int[] buildPatternArray(String str) {
		int[] temp = new int[str.length()];
		temp[0] = 0;
		int i = 1;
		int j = 0;
		
		
		while (i < str.length()) {
			if (str.charAt(i) == str.charAt(j)) {
				temp[i] = j+1;
				i++; j++;

			} else if (str.charAt(i) != str.charAt(j)) {
				if (j == 0) { 
					temp[i] = 0; 
					i++;
				} else {
					j = temp[j-1];
				}
			}
		}
		
		return temp;
	}
	
	/* a main to test this class. 
	public static void main(String[] args) {
		String test = "dsgwadsgz";
		String originalString = "adsgwadsxdsgwadsgz";
		SubStringSearch obj = new SubStringSearch(originalString, test);
		
		
		if (args.length > 0) {
			try {
				obj = new SubStringSearch(args[1], args[0]);
			} catch (Exception e) {
				System.out.println("Incorrect arguments were entered. Search keyword followed by full text.");
			}
		}
		
		if (obj.contains("California", "end")) {
			System.out.println("match was found at index: " + obj.index);
		}
		
		
	}
	*/ 
}
