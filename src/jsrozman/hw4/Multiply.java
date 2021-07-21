package jsrozman.hw4;

// Question 1. complete this class. It must extend Expression
public class Multiply extends Expression {
	final Expression left;
	final Expression right;
	
	public Multiply(Expression left, Expression right) {
		this.left = left;
		this.right = right;
	}
	
	@Override
	public double eval() {
		return left.eval() * right.eval();
	}

	@Override
	public String format() {
		return String.format("(%s*%s)", left.format(), right.format());
	}

	@Override
	public int height() {
		if(left.height() > right.height()) {
			return 1 + left.height();
		}
		
		return 1 + right.height();
	}

}