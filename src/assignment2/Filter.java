/**
 * 
 */
package assignment2;

import assignment1.QuakeEntry;

/**
 * @author Arun
 *
 */
public interface Filter {
	public boolean satisfies(QuakeEntry qe);
	public String getName();
	public String toString();
}
