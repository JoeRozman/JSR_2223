package jsrozman.hw2;

import algs.hw2.Card;
import algs.hw2.Deck;
import algs.hw2.Node;
import algs.hw2.Suit;

/**
 * COPY THIS CLASS into your development area and complete it.
 * @author Home
 *
 */
public class MyDeck extends Deck {
	
	/**
	 * Ensure that no one OUTSIDE of this class invokes the no-argument constructor. You will find
	 * it useful to have this constructor within the copy() method since it must return an accurate
	 * copy of the current Deck, and it will first need to construct an "empty" MyDeck object
	 * without using the MyDeck(int max_rank) constructor.
	 * 
	 */
	
	protected int N;
	
	protected MyDeck() {
		// You do not need to modify this method. This constructor exists to ensure that 
		// within this class, you can construct an empty MyDeck whose first and last are null.
	}
	
	/** 
	 * Construct a playing deck with {max_rank} cards in specific order.
	 * 
	 * Once done, the linked list of card Nodes must represent a deck that looks like the following (if 
	 * {max_rank} were 3). The suites are ordered Club < Diamond < Hearts < Spades.
	 * 
	 * AC -> 2C -> 3C -> AD -> 2D -> 3D -> AH -> 2H -> 3H -> AS -> 2S -> 3S
	 * 
	 * Note your deck will have 4*{max_rank} cards.
	 * 
	 * Performance must be O(N) where N is max_rank.
	 */
	public MyDeck(int max_rank) {
		if (max_rank < Card.ACE || max_rank > Card.KING) { throw new IllegalArgumentException("max_rank must be between " + Card.ACE + " and " + Card.KING + " respectively"); }
		Suit s = Suit.SPADES;
		Card c = new Card(s, max_rank);
		first = new Node(c);
		last = new Node(c);	
		N = 1;
		
		for(int i = max_rank-1; i > 0; i--) {
			c = new Card(s, i);
			add(c);
		}
		
		s = Suit.HEARTS;
		
		for(int i = max_rank; i > 0; i--) {
			c = new Card(s, i);
			add(c);
		}
		
		s = Suit.DIAMONDS;
		
		for(int i = max_rank; i > 0; i--) {
			c = new Card(s, i);
			add(c);
		}
		
		s = Suit.CLUBS;
		
		for(int i = max_rank; i > 0; i--) {
			c = new Card(s, i);
			add(c);
		}
		
		
	}
	
	void add(Card c) {
		Node oldfirst = first;
		first = new Node(c, oldfirst);
		N++;
	}
	
	@Override //O(1)
	public Card peekTop() {
		return first.card;
	}

	@Override //O(1)
	public Card peekBottom() {
		return last.card;
	}

	@Override //O(N)
	public boolean match(Card c, int n) {
		Node nNode = first;
		for(int i = 1; i < n; i++) {
			nNode = nNode.next;
			
			if(nNode.card.equals(c)) {
				return true;
			}
		}
		
		return false;
	}
	
	@Override //O(N)
	public Deck copy() {
		MyDeck newDeck = new MyDeck();
		Node n1 = first;
		newDeck.first = new Node(n1.card);
		Node n2 = newDeck.first;
		n1 = n1.next;
		
		while(n1 != null) {
			n2.next = new Node(n1.card);
			n1 = n1.next;
			n2 = n2.next;
		}
		
		return newDeck;
	}

	@Override //O(1)
	public int size() {
		return N;
	}
	
	@Override //O(N)
	protected Node cutInHalf() {
		if(N%2 != 0) {
			throw new RuntimeException("Must be an even amount of cards.");
		}
		
		Node n1 = first;
		Node n2 = first;
		
		while(n1 != null) {
			n2 = n2.next;
			
			if(n2 == null || n2.next == null) {
				Node secondHalf = n1.next;
				n1.next = null;
				 
				return secondHalf;
			}
			
			n1 = n1.next;
			n2 = n2.next;
		}
		
		return null;
	}

	@Override //O(N)
	public void out() {
		Node n1 = new Node(first.card, first.next);
		Node n2 = cutInHalf();
		Node n3 = first;
		n1 = n1.next;
		
		while(n1 != null) {
			n3.next = new Node(n2.card);
			n3.next.next = new Node(n1.card);
			n1 = n1.next;
			n2 = n2.next;
			n3 = n3.next.next;
		}
		
		n3.next = last;
	}

	@Override //O(N)
	public void in() {
		Node n1 = new Node(first.card, first.next);
		Node n2 = cutInHalf();
		first = new Node(n2.card, first);
		Node n3 = first;
		n2 = n2.next;
		
		while(n2 != null) {
			n3.next = new Node(n1.card);
			n3.next.next = new Node(n2.card);
			n1 = n1.next;
			n2 = n2.next;
			n3 = n3.next.next;
		}
		
		n3.next = n1;
	}

	@Override //O(N)
	public String representation() {
		Node n = first;
		String rep = "";
		while(n != null) {
			rep += n.card.toString() + " ";
			n = n.next;
		}
		return rep;
	}
	
	@Override //O(N)
	public boolean isInOrder() {
		Node n = first;
		
		while(n.next != null) {
			if(n.card.suit.getValue() == n.next.card.suit.getValue()){
				if(n.card.rank > n.next.card.rank) {
					return false;
				}
			}
			
			else if(n.card.suit.getValue() > n.next.card.suit.getValue()) {
				return false;
			}
			
			n = n.next;
		}
		
		return true;
	}

	@Override //O(N)
	public boolean isInReverseOrder() {//check this
		Node n = first;
		
		while(n.next != null) {
			if(n.card.suit.getValue() == n.next.card.suit.getValue()){
				if(n.card.rank < n.next.card.rank) {
					return false;
				}
			}
			
			else if(n.card.suit.getValue() < n.next.card.suit.getValue()) {
				return false;
			}
			
			n = n.next;
		}
		
		return true;
	}
}