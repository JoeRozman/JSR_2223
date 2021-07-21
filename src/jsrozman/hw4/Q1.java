package jsrozman.hw4;

import algs.days.day04.FixedCapacityStack;
import edu.princeton.cs.algs4.StdIn;

public class Q1 {
	/**
	 * Complete this implementation that takes a postfix expression string and converts it into
	 * an Expression node using a fixed Capacity stack. When done, an Expression node will
	 * be returned.
	 * 
	 * Using the postfix expression as input
	 * 
	 *     3 1 + 4 / 1 5 + 9 * 2 6 * - *
	 *  
	 * should produce the expression from the homework, namely
	 * 
	 *     (((3+1)/4 * (((1+5)*9)-(2*6)))
	 *
	 * Note that postfix expressions do not need parentheses, which is one of their
	 * major selling points.
	 */
	public static void main(String[] args) {

		// since everything IS an expression (even Values) you only need a single stack.
		FixedCapacityStack<Expression> exprs = new FixedCapacityStack<Expression>(100);
		
		// fill in here...
		while(!StdIn.isEmpty()) {
			String s = StdIn.readString();
			
			if(s.equals("+")) {
				Expression e1 = exprs.pop();
				Expression e2 = exprs.pop();
				Expression add = new Add(e1, e2);
				exprs.push(add);
			}
			else if(s.equals("-")) {
				Expression e1 = exprs.pop();
				Expression e2 = exprs.pop();
				Expression subtract = new Subtract(e2, e1);
				exprs.push(subtract);
			}
			else if(s.equals("*")) {
				Expression e1 = exprs.pop();
				Expression e2 = exprs.pop();
				Expression multiply = new Multiply(e1, e2);
				exprs.push(multiply);
			}
			else if(s.equals("/")) {
				Expression e1 = exprs.pop();
				Expression e2 = exprs.pop();
				Expression divide = new Divide(e2, e1);
				exprs.push(divide);
			}
			else {
				Value v = new Value(Double.valueOf(s));
				exprs.push(v);
			}
		}
		Expression exp = exprs.pop();
		System.out.println(exp.format() + " = " + exp.eval());
		System.out.println("Height: " + exp.height());
	}
}
