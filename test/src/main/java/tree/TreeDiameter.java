package tree;

/**
 * https://leetcode.com/problems/diameter-of-binary-tree
 * 
 * Leetcode day 10
 * 
 * Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.
 * Example:
 * Given a binary tree
 *     1
 *    / \
 *   2   3
 *  / \
 * 4   5
 *
 * Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].
 * Note: The length of path between two nodes is represented by the number of edges between them. 
 *
 * Solution Details:
 * 0ms 100% faster than other solutions
 * 
 * @author ketav
 */
public class TreeDiameter {

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}


	public int diameterOfBinaryTree(TreeNode root) {

		if (root == null) return 0;

		int [] arr = findLargestPath(root);

		return arr[0] > arr[1] ? arr[0]-1 : arr[1]-1;

	}

	/*
		need to send [0]=max(height) as well as [1]=max(pathLengt)
		pathLength = leftHeight + rightHeight + 1;
	 */
	private int[] findLargestPath(TreeNode root) {

		if(root == null) return new int [] {0, 0};
		int [] left  = findLargestPath(root.left);
		int [] right = findLargestPath(root.right);

		int pathLength1=0;

		int [] ret = new int [2];
		ret[0] = left[0] > right[0] ? left[0]+1 : right[0]+1;   //maxHeight
		ret[1] = (pathLength1 = left[0] + right[0] + 1) > left[1] ? pathLength1 : left[1];
		ret[1] = ret[1] > right[1] ? ret[1] : right[1];

		return ret;
	}
	
	public static void main(String[] args) {
		TreeDiameter obj = new TreeDiameter();
		TreeNode root = obj.new TreeNode(1);
		root.left = obj.new TreeNode(2);
		root.right = obj.new TreeNode(3);
		root.left.left = obj.new TreeNode(4);
		root.left.right = obj.new TreeNode(5);
		
		System.out.println(obj.diameterOfBinaryTree(root));
	}

}
