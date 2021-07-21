package jsrozman.hw1;

import algs.hw1.*;
import algs.hw1.api.*;

/**
 * Copy this class into your USERID.hw1 package and complete it.
 */
public class FuzzyFinder implements IFuzzySquareFinder {

	/**
	 * Return the Coordinate(r,c) where target exists. If it is not in 
	 * the 2D array, return null.
	 * 
	 * You can inspect the contents of the array for fs using the probe3x3() method.
	 */
	public Coordinate find(FuzzySquare fs, int target) {
		//throw new RuntimeException("to be completed by student.");
		//System.out.println(fs.toString());
		for(int i = 0; i < fs.N; i++) {
			for(int j = 0; j < fs.N; j+=2) {
				if(fs.probe3x3(i, j, target) == 0) {
					if(fs.probe3x3(i, j-1, target) == 0 &&
							fs.probe3x3(i, j+1, target) == 0 && 
							fs.probe3x3(i-1, j, target) == 0 &&
							fs.probe3x3(i-1, j-1, target) == 0 &&
							fs.probe3x3(i-1, j+1, target) == 0 && 
							fs.probe3x3(i+1, j, target) == 0 && 
							fs.probe3x3(i+1, j-1, target) == 0 && 
							fs.probe3x3(i+1, j+1, target) == 0) { //checks to see if center is coordinate
						return new Coordinate(i, j);
					}
					else if(fs.probe3x3(i-1, j-1, target) == 0 &&
							fs.probe3x3(i+1, j-1, target) == 0) { //checks to see if edge is the coordinate
						return new Coordinate(i, j-1);
					}
					else if(fs.probe3x3(i-1, j+1, target) == 0 &&
							fs.probe3x3(i+1, j+1, target) == 0) {
						return new Coordinate(i, j+1);
					}
					else if(fs.probe3x3(i+1, j-1, target) == 0 &&
							fs.probe3x3(i+1, j+1, target) == 0) {
						return new Coordinate(i+1, j);
					}
					else if(fs.probe3x3(i-1, j-1, target) == 0 &&
							fs.probe3x3(i-1, j+1, target) == 0) {
						return new Coordinate(i-1, j);
					}
					else if(fs.probe3x3(i-1, j-1, target) == 0) { //checks to see if corner is the coordinate
						return new Coordinate(i-1, j-1);
					}
					else if(fs.probe3x3(i-1, j+1, target) == 0) {
						return new Coordinate(i-1, j+1);
					}
					else if(fs.probe3x3(i+1, j-1, target) == 0) {
						return new Coordinate(i+1, j-1);
					}
					else if(fs.probe3x3(i+1, j+1, target) == 0) {
						return new Coordinate(i+1, j+1);
					}
				}
			}	
		}
		
		return null;
	}	

	// You do not need to modify below this line.
	// ------------------------------------------
	public static void main(String[] args) {
		for (int i = 5; i < 40; i++) {
			FuzzySquare fs = new FuzzySquare(i, 99);
			fs.solver(new FuzzyFinder());
			System.out.println(i + "\t" + fs.getNumProbes());
		}
	}
}
