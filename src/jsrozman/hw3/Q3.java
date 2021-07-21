package jsrozman.hw3;

import algs.hw3.ShakespearePlay;
import jsrozman.hw3.BST.Node;

public class Q3 {
	public static void main(String[] args) {
		BST bst = new BST();
		
		for(int i = 1; i <= 38; i++) {
			ShakespearePlay sp = new ShakespearePlay(i);
			setup(bst, sp);
		}
		
		final String MOSTCOMMON = bst.mostFrequent();
		System.out.println("Most common word: " + MOSTCOMMON + "\n");
		
		for(int i = 1; i <= 38; i++) {
			BST temp = new BST();
			ShakespearePlay sp = new ShakespearePlay(i);
			String words[] = new String[5];
			boolean flag = false;
			
			setup(temp, sp);
			words[0] = temp.mostFrequent();
			temp.delete(words[0]);
			words[1] = temp.mostFrequent();
			temp.delete(words[1]);
			words[2] = temp.mostFrequent();
			temp.delete(words[2]);
			words[3] = temp.mostFrequent();
			temp.delete(words[3]);
			words[4] = temp.mostFrequent();
			
			for(String s : words) {
				if(s.equals("and")) flag = true;	
			}
			
			if(!flag) System.out.println(sp.getTitle() + ": " + words[0] + " " + words[1] + " " + words[2] + " " + words[3] + " " + words[4] + " ");
		}
		
		String longest = Q3_1(bst);
		System.out.println("\n" + longest + ": is a classical Greek name. The name has the sense of \"male victor, warrior\"");
	}
	
	private static void setup(BST bst, ShakespearePlay sp) {
		for(String s : sp) {
			if(bst.get(s) != null) {
				int count = bst.get(s);
				bst.delete(s);
				bst.put(s, ++count);
			}
			else {
				bst.put(s, 1);
			}
		}
	}
	
	public static String Q3_1(BST bst){
		return longestString(bst.root, bst.root).key;
	}
	
	private static Node longestString(Node n, Node longest) {
		if(n == null) return longest;
		
		if(n.key.length() > longest.key.length()) {
			longest = n;
		}
		
		Node l1 = longestString(n.left, longest);
		Node l2 = longestString(n.right, longest);
		
		if(l1.count > l2.count) {
			return l1;
		}
		
		return l2;
	}
}
