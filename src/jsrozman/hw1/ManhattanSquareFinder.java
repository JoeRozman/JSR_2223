package jsrozman.hw1;

import algs.hw1.*;
import algs.hw1.api.*;

public class ManhattanSquareFinder implements IManhattanSquareFinder {

	/** 
	 * Return the Coordinate of location in ManhattanSquare containing target.
	 * 
	 * You can inspect the contents of the array for ms using the distance() method.
	 */
	public Coordinate find(ManhattanSquare ms, int target) {
		//throw new RuntimeException("to be completed by student.");
		
		int d1 = ms.distance(0, 0, target);
		Coordinate[] coords1 = new Coordinate[ms.N*ms.N];
		if(d1 == 0) {
			return new Coordinate(0,0);
		}
		else if(d1 < ms.N) {
			for(int i = 0; i <= d1; i++) {
				coords1[i] = new Coordinate(i, d1-i);
			}
		}
		else if(d1 >= ms.N) { //has to be different because it peaks then drops
			for(int i = 0; i < d1; i++) {
				coords1[i] = new Coordinate(i, d1-i);
			}
		}
	
		//call down here if d1 = 0, saves a probe
		int d2 = ms.distance(ms.N-1, 0, target);
		Coordinate[] coords2 = new Coordinate[ms.N*ms.N];
		
		if(d2 == 0) {
			return new Coordinate(ms.N-1, 0);
		}
		else if(d2 < ms.N) {
			for(int i = 0; i <= d2; i++) {
				coords2[i] = new Coordinate(ms.N-1-i, d2-i);
			}
		}
		else if(d2 >= ms.N) { //has to be different because it peaks then drops
			for(int i = 0; i < d2; i++) {
				coords2[i] = new Coordinate(ms.N-1-i, d2-i);
			}
		}
			
		for(Coordinate c1 : coords1) {
			for(Coordinate c2 : coords2) {
				if(c1.equals(c2)) {
					return c1;
				}
			}
		}
		
		return null;
	}	
	
	// You do not need to modify below this line.
	// ------------------------------------------
	public static void main(String[] args) {
		for (int n = 1; n < 15; n++) {
			ManhattanSquare ms = new ManhattanSquare(n, 99);
			int numProbes = ms.solver(new ManhattanSquareFinder());
			System.out.println(n + "\t" + numProbes);
		}
	}
}