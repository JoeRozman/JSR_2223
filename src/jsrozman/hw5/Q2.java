package jsrozman.hw5;

import algs.days.day26.FloydWarshallUndirected;
import algs.hw5.FloydWarshallSolutionAnimation;
import algs.hw5.map.HighwayMapWeighted;
import algs.hw5.map.WeightedInformation;
import edu.princeton.cs.algs4.EdgeWeightedGraph;

/**
 * Question 2 of Homework 5.
 * 
 * Given the Massachusetts highway map data, find two vertices in the graph such that 
 * the shortest distance between them is greater than any other pair of vertices in the graph.
 * 
 * In other words, can you find two locations in Massachusetts such that using the available 
 * map data, you’ve computed the shortest total distance in terms of accumulated mileage, 
 * and no other pair of vertices demands a longer trip.
 */
public class Q2 {
	public static void main(String[] args) {
		WeightedInformation info = HighwayMapWeighted.undirectedGraph();
		EdgeWeightedGraph graph = info.ewgraph;
		
		System.out.println("Computing Floyd-Warshall: This might take up to a minute...");
		FloydWarshallUndirected fw = new FloydWarshallUndirected(graph);
		System.out.println("done");
		
		// THIS IS WHERE YOU MUST DO SOME WORK TO DETERMINE TWO VERTICES
		// THAT HAVE THE GREATEST OF THE SHORTEST DISTANCES.
		double greatestDist = 0.0;
		int vertex1 = 0;
		int vertex2 = 0;
		for(int u = 0; u < graph.V(); u++) {
			for(int v = 0; v < graph.V(); v++) {
				if(fw.distTo(u, v) > greatestDist) {
					greatestDist = fw.distTo(u, v);
					vertex1 = u;
					vertex2 = v;
				}
			}
		}
		// To visually animate the solution, properly use integer vertex endpoints. You know this is 
		// right when you see it!
		new FloydWarshallSolutionAnimation(fw).launch(vertex1, vertex2);
		System.out.println("vertex1 is " + info.labels.get(vertex1) + " @ " + info.positions.get(vertex1));
		System.out.println("vertex1 is " + info.labels.get(vertex2) + " @ " + info.positions.get(vertex1));
		System.out.println("Total mileage: " + greatestDist);
	}
}
