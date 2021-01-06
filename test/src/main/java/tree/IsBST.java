package tree;

/**
 * Cracking the coding interview Ch-4 Problem-5 - IsBST
 * Check if the supplied tree is BST or not
 */
public class IsBST {

	public boolean isBST(BinaryTreeNode<Integer> node) {
		return isBST(node, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	private boolean isBST(BinaryTreeNode<Integer> node, int min, int max) {
		if(node != null) {
			if(min >= node.val || max < node.val) {
				return false;
			}
			return isBST(node.left, min, node.val) && isBST(node.right, node.val, max);
		}
		return true;
	}
	
	/**
	 *        5
	 *      /   \
	 *     3     7
	 *    / \   / \
	 *   2   4 6   8
	 *  /           \
	 * 2             9
	 * @param args
	 */
	public static void main(String [] args) {
		BinaryTreeNode<Integer> root = new BinaryTreeNode<>(5);
		root.left = new BinaryTreeNode<>(3);
		root.left.left = new BinaryTreeNode<>(2);
		root.left.right = new BinaryTreeNode<>(4);
		root.left.left.left = new BinaryTreeNode<>(2);

		root.right = new BinaryTreeNode<>(7);
		root.right.left = new BinaryTreeNode<>(6);
		root.right.right = new BinaryTreeNode<>(8);
		root.right.right.right = new BinaryTreeNode<>(9);

		System.out.println(new IsBST().isBST(root));
	}
}
