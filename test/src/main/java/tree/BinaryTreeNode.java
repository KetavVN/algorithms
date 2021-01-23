package tree;

public class BinaryTreeNode<T> {

	public T val;
	public BinaryTreeNode<T> left, right;
	
	public BinaryTreeNode(T val) {
		this.val = val;
	}

	@Override @SuppressWarnings("unchecked")
	public boolean equals(Object o) {
		if(o instanceof BinaryTreeNode) {
			BinaryTreeNode<T> ot = (BinaryTreeNode<T>) o; 
			return o!=null && val.equals(ot.val);
		}
		return false;
	}
	
	public boolean isBalanced() {
		int leftHeight = getHeight(left, 0);
		int rightHeight = getHeight(right, 0);
		return Math.abs(leftHeight-rightHeight) <= 1;
	}
	
	private int getHeight(BinaryTreeNode<T> root, int height) {
		if(root == null) {
			return height;
		}
		int leftHeight = getHeight(root.left, height+1);
		int rightHeight = getHeight(root.right, height+1);
		if(leftHeight > rightHeight) {
			return leftHeight;
		}
		return rightHeight;
	}
	
	public static void main(String ...args) {
	
		BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(5);
		root.left = new BinaryTreeNode<Integer>(3);
		root.left.left = new BinaryTreeNode<Integer>(2);
		root.left.right = new BinaryTreeNode<Integer>(4);
		root.left.left.left = new BinaryTreeNode<Integer>(1);
		root.right = new BinaryTreeNode<Integer>(8);
		root.right.left = new BinaryTreeNode<Integer>(6);
		root.right.right = new BinaryTreeNode<Integer>(9);
		root.right.left.right = new BinaryTreeNode<Integer>(7);
		
		System.out.println(root.isBalanced());
		
	}
	
}
