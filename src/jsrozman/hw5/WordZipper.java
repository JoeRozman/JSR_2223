package jsrozman.hw5;

import java.io.FileNotFoundException;
import java.util.Scanner;

// use any classes from Sedgewick...
import edu.princeton.cs.algs4.*;

// Note that the Day18 implementation of AVL removes <Key,Value> and only uses <Key>
import algs.days.day18.AVL;
import algs.hw5.Dictionary;

/**
 * Copy this class into your project area and modify it for problem 1 on HW5.
 */
public class WordZipper {

	/**
	 * Represent the mapping of (uniqueID, 3- and 4-letter words) from String <-> Integer where Integer is vertex id
	 */
	static SeparateChainingHashST<String,Integer> map = new SeparateChainingHashST<String,Integer>();
	static SeparateChainingHashST<Integer,String> reverse = new SeparateChainingHashST<Integer,String>();

	/** Store all three-letter and four-letter words (in lowercase). */
	static AVL<String> avl; 
	
	/**
	 * Return a Queue of words that result by adding a single letter to the three letter word.
	 * 
	 * There are 4*26 possible words that could result by adding a single letter (a-z) at each of the 
	 * four possible spots
	 * 
	 *      E A T
	 *      
	 *     SEAT
	 *      ERAT
	 *       EAST
	 *        EATS
	 *        
	 * It is acceptable for this method to return duplicates in the queue.
	 * 
	 * For example, if the word is "BET" then it could include in its response
	 * "BEET" (where the E is inserted between the B and E) and "BEET" (where
	 * the E is inserted between the E and the T).
	 */
	public static Queue<String> addOne(String three) {
		Queue<String> q = new Queue<String>();
		for(char c = 'a'; c < 26; c++) {
			for(int j = 0; j < three.length(); j++) {
				String four = three.substring(0, j);
				four = four + c + three.substring(j, three.length());
				if(avl.contains(four)) q.enqueue(four);
			}
		}
		
		return q;
	}

	/**
	 * Return valid words by removing one of the four letters.
	 * 
	 * It is acceptable for this method to return duplicates in the queue.
	 * For example, if the word is 'BEET' then the words returned could 
	 * be {"BEE", "BET", "BET"}
	 */
	public static Queue<String> removeOne(String four) {
		Queue<String> q = new Queue<String>();
		for(int i = 1; i <= four.length(); i++) {
			String three = four.substring(0, i-1) + four.substring(i);
			//check if word is real word
			if(avl.contains(three)) q.enqueue(three);
		}
		return q;
	}
	
	/**
	 * Main method to execute.
	 *
	 * From console, enter the start and end of the word ladder.
	 */
	public static void main(String[] args) throws FileNotFoundException {
		
		// Use this to contain all three- and four-letter words that you find in dictionary
		avl = new AVL<String>();

		// Construct AVL tree of all three- and four-letter words.
		// Note: you will have to copy this file into your project to access it.
		Scanner sc = Dictionary.words();
		
		// now construct graph, where each node represents a word, and an edge exists between
		// two nodes if their respective words are off by a single letter. Hint: use the
		// keys() method provided by the AVL tree. Your graph will be an undirected graph.
		
		// TODO: FILL IN HERE
		int count = 0;
		while(sc.hasNext()) {
			String key = sc.next();
			if(key.length() > 2 && key.length() < 5) {
				avl.insert(key);
				map.put(key, count);
				reverse.put(count, key);
				count++;
			}
		}
		
		Graph G = new Graph(map.size());
		for(String key : avl.keys()) {
			Queue<String> q = removeOne(key);
			while(!q.isEmpty()) {
				String fromQ = q.dequeue();
				boolean edgeExists = false;
				for(Integer vertex : G.adj(map.get(key))) {
					if(vertex.equals(map.get(fromQ))) {
						edgeExists = true;
					}
				}	
				if(!edgeExists) {
					G.addEdge(map.get(key), map.get(fromQ));
				}	
			}
		}
		sc.close();  // once done, you can close this resource.
		
		// this loop will complete when the user enters in a non-word.
		while (true) {
			StdOut.println("Enter word to start from (all in lower case):");
			String start = StdIn.readString().toLowerCase();
			StdOut.println("Enter word to end at (all in lower case):");
			String end = StdIn.readString().toLowerCase();
	
			// need to validate that these are both actual four-letter words in the dictionary
			if (!avl.contains(start)) {
				StdOut.println (start + " is not a valid word in the dictionary.");
				System.exit(-1);
			}
			if (!avl.contains(end)) {
				StdOut.println (end + " is not a valid word in the dictionary.");
				System.exit(-1);
			}
			
			// Once both words are known to exist in the dictionary, then create a search
			// that finds shortest distance (should it exist) between start and end.
			// be sure to output the words in the word zipper, IN ORDER, from the start to end.
			// IF there is no word zipper possible, then output "NONE POSSIBLE."
			BreadthFirstPaths bfs = new BreadthFirstPaths(G, map.get(start));
			if(bfs.hasPathTo(map.get(end))) {
				for(Integer i : bfs.pathTo(map.get(end))) {
					System.out.println(reverse.get(i));
				}
			}
			else System.out.println("NONE POSSIBLE.");
		}
		
	}
}
