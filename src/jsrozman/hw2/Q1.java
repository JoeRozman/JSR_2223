package jsrozman.hw2;

import algs.hw2.Deck;
import algs.hw2.Node;

public class Q1 {
	
	public static void main(String[]args) {
		System.out.println();
		System.out.println("in() shuffles");
		
		for (int max_rank = 1; max_rank <= 13; max_rank++) {
	      Deck d = new MyDeck(max_rank);
	      // now write code to perform a number of in() shuffles 
	      d.in();
	      int numOfIns = 1;
	      
	      /*while(!d1.peekTop().equals(d2.peekTop())) {*/
	      while(!d.isInOrder()) {
	    	  d.in();
	    	  numOfIns++;
	      }
	      
	      System.out.println(max_rank + "\t" + numOfIns);
	    }
	    
	    System.out.println();
	    System.out.println("out() shuffles");
	    for (int max_rank = 1; max_rank <= 13; max_rank++) {
		      Deck d = new MyDeck(max_rank);	
		      // now write code to perform a number of out() shuffles 
		      d.out();
		      int numOfOuts = 1;
		      while(!d.isInOrder()) {
		    	  d.out();
		    	  numOfOuts++;
		      }
		      
		      System.out.println(max_rank + "\t" + numOfOuts);
	    }
	    
	    System.out.println();
	    System.out.println("in() reverse order shuffles");
	    
	    for (int max_rank = 1; max_rank <= 13; max_rank++) {
		      MyDeck d = new MyDeck(max_rank);
		      // now write code to perform a number of in() shuffles 
		      d.in();
		      int numOfIns = 1;
		      
		      while(d.isInReverseOrder() != true) {
		    	  d.in();
		    	  numOfIns++;
		    	  if(numOfIns > d.N) {
		    		  numOfIns = -1; // infinite case
		    		  break;
		    	  }
		      }
		      
		      System.out.println(max_rank + "\t" + numOfIns);
	    }
	}
}
