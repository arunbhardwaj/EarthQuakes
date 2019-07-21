package assignment1;

import java.io.File;
import java.io.IOException;
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
import assignment2.MinMagFilter;
import assignment2.PhraseFilter;
import assignment3.*;

public class EarthQuakeParser {
    public EarthQuakeParser() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> read(String source) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();

            //Document document = builder.parse(new File(source));
            //Document document = builder.parse("http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/2.5_week.atom");
            Document document = null;

            if (source.startsWith("http")){
                document = builder.parse(source);
            }
            else {
                document = builder.parse(new File(source));
            }
            //Document document = builder.parse("http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom");

            NodeList nodeList = document.getDocumentElement().getChildNodes();

            ArrayList<QuakeEntry> list = new ArrayList<QuakeEntry>();

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

                    if (t1 != null) {
                        String s2 = t1.item(0).getChildNodes().item(0).getNodeValue();
                        //System.out.print("point2: "+s2);
                        String[] args = s2.split(" ");
                        lat = Double.parseDouble(args[0]);
                        lon = Double.parseDouble(args[1]);
                    }
                    if (t2 != null){
                        String s2 = t2.item(0).getChildNodes().item(0).getNodeValue();

                        String mags = s2.substring(2,s2.indexOf(" ",2));
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

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException{
        EarthQuakeParser xp = new EarthQuakeParser();
//        String source = "data/2.5_week.atom";
//        String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
//        String source = "C:\\Users\\Arun\\eclipse-workspace\\EarthQuake\\src\\data\\nov20quakedatasmall.atom";
//        String source = "C:\\Users\\Arun\\eclipse-workspace\\EarthQuake\\src\\data\\nov20quakedata.atom";
        String source = "C:\\Users\\Arun\\eclipse-workspace\\EarthQuake\\src\\data\\earthQuakeDataDec6sample1.atom";
        ArrayList<QuakeEntry> list  = xp.read(source);
        Scanner in = new Scanner(System.in);
        
//        Collections.sort(list, QuakeEntry.magnitude_sort_reversed);
//        QuakeSort.sortByMagnitude_WithSwap(list);
        Collections.sort(list, new TitleLastAndMagnitudeComparator());
        
        for(QuakeEntry qe : list){
            System.out.println(qe);
        }
        System.out.println("# quakes = " +list.size());
        
        int quakeNumber = 50;
        System.out.println("Print quake entry in position " + quakeNumber);
        System.out.println(list.get(quakeNumber));
        
        EarthQuakeClient o = new EarthQuakeClient();
        
       /*
	        First implementation to filter the data and gather output.
	        
	        o.bigQuakes();
	        o.closeToMe();
	        o.quakesOfDepth();
	        o.quakesByPhrase();
	        
	        ClosestQuakes cq = new ClosestQuakes();
	        cq.findClosestQuakes();
	        
	        LargestQuakes lq = new LargestQuakes();
	        lq.findLargestQuakes();
	        
        	Second implementation to search the data using filters.
        */
        
        Filter f1 = new MinMagFilter(in);
        Filter f2 = new DepthFilter(in);
        // Possible improvements, create a database of locations that can be searched by name of the city. So User can type in "Tulsa" and get the 
        // corresponding location entered without needing to know the coordinate-data.
//        Japan
//        Filter f3 = new DistanceFilter(10000000, new Location(35.42,139.43), in);
//        Tulsa, Oklahoma
        Filter f3 = new DistanceFilter(10000000, new Location(36.1314,-95.9372), in);
        Filter f4 = new PhraseFilter(in);
        in.close();
        
        /*
        Using a singular filter to search through the data:
        	ArrayList<QuakeEntry> result = o.filter(list, f1);
        	
        Using multiple filters to search through the data:
         */
        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(f1);
        maf.addFilter(f2);
        maf.addFilter(f3);
        maf.addFilter(f4);
        
        ArrayList<QuakeEntry> results = o.filter(list, maf);
        for (QuakeEntry qe : results) {
        	System.out.println(qe);
        }
        System.out.println("found " + results.size() + " that match the criteria.");
        
        System.out.println("Filters used are: " + maf.getName());
     
    }
    
}
