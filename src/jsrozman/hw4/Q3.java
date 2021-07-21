package jsrozman.hw4;

import edu.princeton.cs.algs4.*;

/**
 * How many random directed graphs of V vertices have a cycle? and are connected?
 * 
 * Create a random graph by adding an edge between two vertices u and v with a probability
 * of 50%.
 * 
 * Run the same trial, this time using graphs whose edges each have a probability of 1/N chance
 * of being present.
 */
public class Q3 {
	final static int N = 15;
	final static int SIZE = 10000;
	
	public static void main(String[] args) {
		System.out.println("Graphs with probability 0.5");
		System.out.println("N\t#Cycles\t#Connected");
		for(int i = 2; i < N; i++) {
			Digraph[] graphs = new Digraph[SIZE];
			int numOfCycles = 0;
			int numOfConnected = 0;
			for(Digraph g : graphs) {
				g = DigraphGenerator.simple(i, .5);
				DirectedCycle dc = new DirectedCycle(g);
				if(dc.hasCycle()) numOfCycles++;
				DirectedDFS dd = new DirectedDFS(g, 0);
				if(dd.marked(1)) numOfConnected++;
			}
			System.out.println(i + "\t" + numOfCycles + "\t" + numOfConnected);
		}
		
		System.out.println();
		System.out.println("Graphs with probability 1/N");
		System.out.println("N\t#Cycles\t#Connected");
		for(int i = 2; i < N; i++) {
			Digraph[] graphs = new Digraph[SIZE];
			int numOfCycles = 0;
			int numOfConnected = 0;
			for(Digraph g : graphs) {
				g = DigraphGenerator.simple(i, 1.0/i);
				DirectedCycle dc = new DirectedCycle(g);
				if(dc.hasCycle()) numOfCycles++;
				DirectedDFS dd = new DirectedDFS(g, 0);
				if(dd.marked(1)) numOfConnected++;
			}
			System.out.println(i + "\t" + numOfCycles + "\t" + numOfConnected);
		}
	}
}