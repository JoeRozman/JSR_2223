package jsrozman.hw3;

import algs.days.day18.AVL;
import edu.princeton.cs.algs4.StdRandom;

public class Q4 {
	final static int N = 40;
	final static int SIZE = 10000;
	public static void main(String[] args) {
		System.out.println("N\tHeight\tNumberFound");
		int prevGreatest = 0;
		for(int i = 1; i < N; i++) {
			AVL<Integer>[] avls = new AVL[SIZE];
			int numOfTreesAtGreatestHeight = 0; 
			avls[0] = new AVL<Integer>();
			findHeight(avls[0], i);
			int greatestHeight = avls[0].height();
			
			for(AVL<Integer> a : avls) {
				a = new AVL<Integer>();
				findHeight(a, i);
				if(a.height() > greatestHeight) greatestHeight = a.height();
				else if(a.height() == greatestHeight) numOfTreesAtGreatestHeight++;
			}
			
			if(greatestHeight != prevGreatest || i == 1) System.out.println(i + "\t" + greatestHeight + "\t" + numOfTreesAtGreatestHeight);
			prevGreatest = greatestHeight;
		}
	}
	
	public static int findHeight(AVL<Integer> avl, int val) {
		while(val != 0) {
			avl.insert(StdRandom.uniform(N));
			val--;
		}
		
		return avl.height();
	}
}