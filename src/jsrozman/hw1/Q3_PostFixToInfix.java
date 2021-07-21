package jsrozman.hw1;

import algs.days.day04.FixedCapacityStack;
import edu.princeton.cs.algs4.*;

/**
 * Complete this implementation that takes a postfix expression and converts it into
 * an Infix Expression using a fixed Capacity stack. Also perform the necessary 
 * computation to produce its value
 * 
 * Using the postfix expression as input
 * 
 *     3 6 + 5 * 8 2 - /
 *  
 * should produce the following as output:
 * 
 *     (((3 + 6) * 5) / (8 - 2)) = 7.5
 *
 * Note that postfix expressions do not need parentheses, which is one of their
 * major selling points.
 */
public class Q3_PostFixToInfix {
	
	public static void main(String[] args) {
		
		FixedCapacityStack<String> exprs = new FixedCapacityStack<String>(100);
		FixedCapacityStack<Double> vals = new FixedCapacityStack<Double>(100);
		
		// COMPLETE IN HERE...
		while(! StdIn.isEmpty()) {
			String s = StdIn.readString();
			double v = 0;
			if(s.equals("+")) {
				String sNum1 = exprs.pop(); 
				String sNum2 = exprs.pop();
				String expression = "( " + sNum2 + " + " + sNum1 + " )"; // takes the numbers and puts them into the expression 
				exprs.push(expression);
				v = vals.pop();
				v = vals.pop() + v;
				vals.push(v);
			}
			else if(s.equals("-")) {
				String sNum1 = exprs.pop();
				String sNum2 = exprs.pop();
				String expression = "( " + sNum2 + " - " + sNum1 + " )";
				exprs.push(expression);
				v = vals.pop();
				v = vals.pop() - v;
				vals.push(v);
			}
			else if(s.equals("*")) {
				String sNum1 = exprs.pop();
				String sNum2 = exprs.pop();
				String expression = "( " + sNum2 + " * " + sNum1 + " )";
				exprs.push(expression);
				v = vals.pop();
				v = vals.pop() * v;
				vals.push(v);
			}
			else if(s.equals("/")) {
				String sNum1 = exprs.pop();
				String sNum2 = exprs.pop();
				String expression = "( " + sNum2 + " / " + sNum1 + " )";
				exprs.push(expression);
				v = vals.pop();
				v = vals.pop() / v;
				vals.push(v);
			}
			else {
				exprs.push(s);
				vals.push(Double.parseDouble(s));
			}
		}
		
		StdOut.print(exprs.pop() + " = " + vals.pop());
		StdOut.println();
	}
}
