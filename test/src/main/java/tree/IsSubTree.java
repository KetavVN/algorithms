package tree;

/**
 * Cracking the coding interview ch4 problem 10 - checkSubTree / isSubTree
 * 
 * Given 2 trees, check if tree2 is subtree of tree1
 * 
 * @author ketav
 *
 */
public class IsSubTree {

	public boolean isSubTree(BinaryTreeNode<Integer> root1, BinaryTreeNode<Integer> root2) {
		boolean isSubTree = false;
		if(root1 != null) {
			if(root1.equals(root2)) {
				isSubTree = checkSubTree(root1, root2);
			}
			if(!isSubTree)
				isSubTree = isSubTree(root1.left, root2);
			if(!isSubTree)
				isSubTree = isSubTree(root1.right, root2);
		}
		return isSubTree;
	}
	
	private boolean checkSubTree(BinaryTreeNode<Integer> t1, BinaryTreeNode<Integer> t2) {
		if(t2 == null) {
			return true;
		}
		if(t1 == null) {
			return false;
		}
		return t1.equals(t2) && checkSubTree(t1.left, t2.left) && checkSubTree(t1.right, t2.right); 
	}
	
	/*
            5
          /   \
         3     8
        / \   / \
       2   4 6   9
      /       \
     1         7
    */
	public static void main(String [] args) {
		BinaryTreeNode<Integer> root = new BinaryTreeNode<>(5);
		root.left = new BinaryTreeNode<>(3);
		root.right = new BinaryTreeNode<>(8);
		root.left.left = new BinaryTreeNode<>(2);
		root.left.right = new BinaryTreeNode<>(4);
		root.right.left = new BinaryTreeNode<>(6);
		root.right.right = new BinaryTreeNode<>(9);
		root.left.left.left = new BinaryTreeNode<>(1);
		root.right.left.right = new BinaryTreeNode<>(7);
		
		BinaryTreeNode<Integer> root2 = new BinaryTreeNode<>(8);
		root2.left = new BinaryTreeNode<>(6);
		root2.left.right = new BinaryTreeNode<>(7);
		
		System.out.println(new IsSubTree().isSubTree(root, root2));
	}
	
}
