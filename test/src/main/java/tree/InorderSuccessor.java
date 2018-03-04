package tree;

public class InorderSuccessor<T> {

	private T val;
	private InorderSuccessor<T> left, right, parent;

	public InorderSuccessor(T val) {
		this.val = val;
	}
	
	public InorderSuccessor<T> createLeft(T val) {
		left = new InorderSuccessor<>(val);
		left.parent = this;
		return left;
	}

	public InorderSuccessor<T> createRight(T val) {
		right = new InorderSuccessor<>(val);
		right.parent = this;
		return right;
	}
	
	public InorderSuccessor<T> getInorderSuccessor() {
	
		if(parent != null && right == null && parent.left == this) {
			return parent;
		} else if (right != null) {
			return right.getLeftMost();
		} else {
			return getParentOfFirstLeftChild();
		}

	}

	private InorderSuccessor<T> getParentOfFirstLeftChild() {
		
		if(parent != null) {
			if(parent.left == this) {
				return parent;
			}
			return parent.getParentOfFirstLeftChild();
		}

		return null;

	}

	public InorderSuccessor<T> getLeftMost() {
		if(left == null) return this;
		else return left.getLeftMost();
	}

	@Override
	public String toString() {
		return val.toString();
	}
	
	/*
		    0
		   / \
		  1   2
		 / \
		3   4
		   / \
		  5   6
	*/
	public static void main(String ...args) {
		InorderSuccessor<Integer> n0 = new InorderSuccessor<>(0);
		InorderSuccessor<Integer> n1 = n0.createLeft(1);
		InorderSuccessor<Integer> n2 = n0.createRight(2);
		InorderSuccessor<Integer> n3 = n1.createLeft(3);
		InorderSuccessor<Integer> n4 = n1.createRight(4);
		InorderSuccessor<Integer> n5 = n4.createLeft(5);
		InorderSuccessor<Integer> n6 = n4.createRight(6);
		System.out.println("inorder of n1 : "+n1.getInorderSuccessor()); //5
		System.out.println("inorder of n6 : "+n6.getInorderSuccessor()); //0
		System.out.println("inorder of n3 : "+n3.getInorderSuccessor()); //1
	}

}

