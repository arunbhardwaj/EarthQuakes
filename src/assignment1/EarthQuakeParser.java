package assignment1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import assignment2.DepthFilter;
import assignment2.DistanceFilter;
import assignment2.Filter;
import assignment2.MatchAllFilter;
import assignment2.MagRangeFilter;
import assignment2.PhraseFilter;
import assignment3.*;

public class EarthQuakeParser {
	
    public ArrayList<QuakeEntry> read(String source) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document = null;

            if (source.startsWith("http")){
                document = builder.parse(source);
            }
            else {
                document = builder.parse(new File(source));
            }

            NodeList nodeList = document.getDocumentElement().getChildNodes();

            ArrayList<QuakeEntry> list = new ArrayList<QuakeEntry>();
            
            //TODO: implement a progress bar for reading in large data files. 
//            int percent = 0;
//            int total = nodeList.getLength();
//            String status = "[          ] " + percent + "%";
            
            for(int k=0; k < nodeList.getLength(); k++){
                Node node = nodeList.item(k);
                
                if (node.getNodeName().equals("entry")) {
                    Element elem = (Element) node;
                    NodeList t1 = elem.getElementsByTagName("georss:point");
                    NodeList t2 = elem.getElementsByTagName("title");
                    NodeList t3 = elem.getElementsByTagName("georss:elev");
                    double lat = 0.0, lon = 0.0, depth = 0.0;
                    String title = "NO INFORMATION";
                    double mag = 0.0;
                    
                    // Get Latitude and Longitude
                    if (t1 != null) {
                        String s2 = t1.item(0).getChildNodes().item(0).getNodeValue();
                        //System.out.print("point2: "+s2);
                        String[] args = s2.split(" "); // Latitude and Longitude are separated by a white space
                        lat = Double.parseDouble(args[0]);
                        lon = Double.parseDouble(args[1]);
                    }
                    // Get magnitude and title
                    if (t2 != null){
                        String s2 = t2.item(0).getChildNodes().item(0).getNodeValue();
                        
                        String mags = s2.substring(2,s2.indexOf(" ",2)); // Magnitude value ends at the next white space
                        if (mags.contains("?")) {
                            mag = 0.0;
                            System.err.println("unknown magnitude in data");
                        }
                        else {
                            mag = Double.parseDouble(mags);
                            //System.out.println("mag= "+mag);
                        }
                        int sp = s2.indexOf(" ",5);
                        title = s2.substring(sp+1);
                        if (title.startsWith("-")){
                            int pos = title.indexOf(" ");
                            title = title.substring(pos+1);
                        }
                    }
                    // Get depth
                    if (t3 != null){
                        String s2 = t3.item(0).getChildNodes().item(0).getNodeValue();
                        depth = Double.parseDouble(s2);
                    }
                    QuakeEntry loc = new QuakeEntry(lat,lon,mag,title,depth);
                    list.add(loc);
                    
                }
                
            }
            return list;
        }
        catch (ParserConfigurationException pce){
            System.err.println("parser configuration exception");
        }
        catch (SAXException se){
            System.err.println("sax exception");
        }
        catch (IOException ioe){
            System.err.println("ioexception");
        }
        return null;
    }
    
    /**
	 * This is where the program starts. 
	 * 
	 * The output from this method is written to an output file 
	 * specified by args[0]. The program reads earthquake data for 
	 * the past 7 days from the official usgs.gov website.
	 * 
	 * @param args an array of command line arguments,
	 * expected value is
	 *   args[0] - name of the output file
	 */
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException{
        System.out.println("Reading in the data.");
    	EarthQuakeParser xp = new EarthQuakeParser();
        String source = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = xp.read(source);
        
        //TODO: Let users have an optional commands file instead of having to reenter commands every run.
        if (args.length != 1) {
			System.err.println("Error: invalid usage\n"
					+ "EarthQuakeParser outputFile\n");
			System.exit(0);
		}
        
    	/***************************************************************** 
		 ** open a file output stream for writing results out           ** 
		 *****************************************************************/

		PrintWriter output = null;
		File outputFile = new File(args[0]);

		try {
			output = new PrintWriter(outputFile);
		}
		catch (FileNotFoundException e) {
			System.err.printf("Error: cannot open file %s for writing.\n.",
					outputFile.getAbsolutePath());
		}
        
		/*****************************************************************
		 ** execute commands from the user input                        **
		 *****************************************************************/
		
        // Sort by Country and State alphabetically and then by Magnitude
        Collections.sort(list, new TitleLastAndMagnitudeComparator());
        
        System.out.println("Total # of quakes = " +list.size());
        
        EarthQuakeClient client = new EarthQuakeClient();
        Scanner in = new Scanner(System.in);
        
        System.out.println("Enter search criterias to filter results? (Y/N)");
        if (in.next().equalsIgnoreCase("n")) {
        	output.print("All results:\t");
        	output.println("\n- - - - - - - - - - - - - - - - - - - -\n");
        	for(QuakeEntry qe : list){
				output.println(qe.toString());
        	}
			output.println("\n=======================================\n");
			
        	in.close(); output.close(); System.exit(0);
        }
        
        MatchAllFilter maf = new MatchAllFilter();
        System.out.println("Search by magnitude? (Y/N)");
        if (in.next().equalsIgnoreCase("y")) {
        	Filter f1 = new MagRangeFilter(in);
        	maf.addFilter(f1);
        }
        System.out.println("Search by depth? (Y/N)");
        if (in.next().equalsIgnoreCase("y")) {
        	Filter f2 = new DepthFilter(in);
        	maf.addFilter(f2);
        }
        System.out.println("Search by distance from a set location? (Y/N)");
        if (in.next().equalsIgnoreCase("y")) {
        	Filter f3 = new DistanceFilter(in);
        	maf.addFilter(f3);
        }
        System.out.println("Search by phrase? (Y/N)");
        if (in.next().equalsIgnoreCase("y")) {
        	Filter f4 = new PhraseFilter(in);
        	maf.addFilter(f4);
        }
        in.close();
        
        ArrayList<QuakeEntry> results = client.filter(list, maf);
        output.print("Results of \n" + maf.toString() + "\t");
        output.println("\n- - - - - - - - - - - - - - - - - - - -\n");
        for (QuakeEntry qe : results) {
			output.println(qe.toString());
        }
        output.println("\n=======================================\n");

        output.println("found " + results.size() + " that match the criteria.");
        System.out.println("found " + results.size() + " that match the criteria.");
        System.out.println("Filters used are: " + maf.getName());
        
        output.close();
		
    }
    
}
