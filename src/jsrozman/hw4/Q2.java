package jsrozman.hw4;

import algs.days.day20.DepthFirstPaths;
import algs.days.day21.BreadthFirstPaths;
import algs.hw4.map.*;
import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.StdOut;

/**
 * The goal of this question is to:
 * 
 * 1. Find the western-most location in Massachusetts
 * 2. Find the eastern-most location in Massachusetts
 * 3. Determine the shortest distance between these two locations IN TERMS OF THE 
 *    TOTAL NUMBER OF HIGHWAY EDGES USED. YOU ARE NOT YET READY TO TAKE MILEAGE INTO ACCOUNT
 * 4. Next create a copy of the highway graph that removes all line segments from I-90, the 
 *    Massachusetts Turnpike toll road.
 * 5. From this copy, determine the shortest distance between these two locations IN TERMS OF THE 
 *    TOTAL NUMBER OF HIGHWAY EDGES USED. YOU ARE NOT YET READY TO TAKE MILEAGE INTO ACCOUNT.
 */
public class Q2 {
	
	/**
	 * This method must create a copy of the graph, which you can do by recreate a graph with 
	 * the same number of vertices as the original one, BUT you only add an edge to the copy
	 * if the edge in the original graph IS NOT EXCLUSIVELY a line segment from the Mass Pike.
	 * 
	 * For example, in the data set you will see two nodes:
	 * 
	 * 		I-90@49|MA 42.169702 -72.580876
	 * 		I-90@51|MA 42.161558 -72.541995
	 * 
	 * These lines correspond to vertex #639 (the first one @49) and vertex #641 (the second one @51).
	 * Because the label for both of these vertices includes "I-90@" this edge must not appear in 
	 * the copied graph, since it is a highway segment exclusively on the Mass Turnpike.
	 * 
	 * Note that the edge is eliminated only when BOTH are present. For example, the following
	 * line segment will remain:
	 * 
	 * 		I-95(23)/MA128	                ==> vertex #705
	 * 		I-90@123B&I-95@24&MA128@24(95)  ==> vertex #1785
	 */
	static Information remove_I90_segments(Information info) {
		Graph copy = null;
		
		copy = new Graph(info.graph.V());
		// DO YOUR WORK HERE...
		
		for(Integer v : info.positions.keys()) {
			for(Integer w : info.graph.adj(v)) {
				if(!info.labels.get(v).contains("I-90@") && !info.labels.get(w).contains("I-90@")) 
					copy.addEdge(v, w);
				
			}
			
		}
		
		Information newInfo = new Information(copy, info.positions, info.labels);
		return newInfo;
	}
	
	
	/** 
	 * This helper method returns the western-most data point in the Information, given its latitude and
	 * longitude.
	 * 
	 * https://en.wikipedia.org/wiki/Latitude
	 * https://en.wikipedia.org/wiki/Longitude
	 * 
	 */
	public static int westernMostVertex(Information info) {
		int minID = 0;
		float min = Float.MAX_VALUE;
		for(Integer id : info.positions.keys()) {
			GPS gps = info.positions.get(id);
			if(gps.longitude < min) {
				min = gps.longitude;
				minID = id;
			}
		}
		return minID;
	}
	
	/** 
	 * This helper method returns the western-most data point in the Information, given its latitude and
	 * longitude.
	 * 
	 * https://en.wikipedia.org/wiki/Latitude
	 * https://en.wikipedia.org/wiki/Longitude
	 * 
	 */
	public static int easternMostVertex(Information info) {
		//698
		int maxID = 0;
		float max = -Float.MAX_VALUE;
		for(Integer id : info.positions.keys()) {
			GPS gps = info.positions.get(id);
			if(gps.longitude > max) {
				max = gps.longitude;
				maxID = id;
			}
		}
		return maxID;
	}
	
	/** 
	 * This helper method returns the southern-most data point in the Information, given its latitude and
	 * longitude.
	 * 
	 * https://en.wikipedia.org/wiki/Latitude
	 * https://en.wikipedia.org/wiki/Longitude
	 * 
	 */
	public static int southernMostVertex(Information info) {
		int minID = 0;
		float min = Float.MAX_VALUE;
		for(Integer id : info.positions.keys()) {
			GPS gps = info.positions.get(id);
			if(gps.latitude < min) {
				min = gps.latitude;
				minID = id;
			}
		}
		return minID;
	}
	
	/** 
	 * This helper method returns the northern-most data point in the Information, given its latitude and
	 * longitude.
	 * 
	 * https://en.wikipedia.org/wiki/Latitude
	 * https://en.wikipedia.org/wiki/Longitude
	 * 
	 */
	public static int northernMostVertex(Information info) {
		int maxID = 0;
		float max = -Float.MAX_VALUE;
		for(Integer id : info.positions.keys()) {
			GPS gps = info.positions.get(id);
			if(gps.latitude > max) {
				max = gps.latitude;
				maxID = id;
			}
		}
		return maxID;
	}
	
	public static void main(String[] args) {
		Information info = HighwayMap.undirectedGraph();
		bfsPrint(info);
		dfsPrint(info);
		info = remove_I90_segments(info);
		StdOut.println("------------Removed I90 Segments-----------");
		bfsPrint(info);
		
	}
	
	public static void bfsPrint(Information info) {
		BreadthFirstPaths bfs = new BreadthFirstPaths(info.graph, westernMostVertex(info));
		int counter = 0;
		
		//BFS
		StdOut.print("West to East BFS: ");
		for(int id : bfs.pathTo(easternMostVertex(info))) {
			StdOut.print(info.labels.get(id) + " - ");
			counter++;
		}
		StdOut.println();
		StdOut.println("Edges: " + (counter-1));//subtract one because it counts an unneeded edge
		
		counter = 0;
		StdOut.print("South to North BFS: ");
		bfs = new BreadthFirstPaths(info.graph, southernMostVertex(info));
		for(int id : bfs.pathTo(northernMostVertex(info))) {
			StdOut.print(info.labels.get(id) + " - ");
			counter++;
		}
		StdOut.println();
		StdOut.println("Edges: " + (counter-1));
	}
	
	public static void dfsPrint(Information info) {
		int counter = 0;
		
		StdOut.print("West to East DFS: ");
		DepthFirstPaths dfs = new DepthFirstPaths(info.graph, westernMostVertex(info));
		for(int id : dfs.pathTo(easternMostVertex(info))) {
			StdOut.print(info.labels.get(id) + " - ");
			counter++;
		}
		StdOut.println();
		StdOut.println("Edges: " + (counter-1));
		
		counter = 0;
		StdOut.print("South to North DFS: ");
		dfs = new DepthFirstPaths(info.graph, southernMostVertex(info));
		for(int id : dfs.pathTo(northernMostVertex(info))) {
			StdOut.print(info.labels.get(id) + " - ");
			counter++;
		}
		StdOut.println();
		StdOut.println("Edges: " + (counter-1));
	}
}
