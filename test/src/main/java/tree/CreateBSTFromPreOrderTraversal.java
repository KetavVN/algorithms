package tree;

/*
https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal

Medium
Leetcode challenge day 19

Return the root node of a binary search tree that matches the given preorder traversal.

(Recall that a binary search tree is a binary tree where for every node, any descendant of node.left has a value < node.val, and any descendant of node.right has a value > node.val.  Also recall that a preorder traversal displays the value of the node first, then traverses node.left, then traverses node.right.)

Example 1:

Input: [8,5,1,7,10,12]
Output: [8,5,10,1,7,null,12]

Note: 
    1 <= preorder.length <= 100
    The values of preorder are distinct.

Solution Details:
0ms faster than 100%

*/
public class CreateBSTFromPreOrderTraversal {

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}

	public TreeNode bstFromPreorder(int[] preorder) {
        TreeNode root = new TreeNode(preorder[0]);
        for(int i=1; i<preorder.length; i++) {
            addNode(preorder[i], root);
        }
        return root;
    }
    
    private TreeNode addNode(int num, TreeNode parent) {
        if(num < parent.val) {
            if(parent.left != null) {
                return addNode(num, parent.left);
            } else {
                parent.left = new TreeNode(num);
                return parent.left;
            }
        } else {
            if(parent.right != null) {
                return addNode(num, parent.right);
            } else {
                parent.right = new TreeNode(num);
                return parent.right;
            }
        }
    }

}
