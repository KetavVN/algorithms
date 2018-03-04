package tree;

/*
Given Binary TreeA and Binary TreeB, check if 'B' is subTree of 'A'
SubTree = A contains all nodes exactly in same order as B
*/

public class SubTree<T> {

	private T val;
	private SubTree<T> left, right;
	
	public SubTree(T v) {
		val = v;
	}
	
	public boolean isSubTree(SubTree<T> rootA, SubTree<T> rootB) {
		if(rootA == null || rootB == null) {
			return false;
		}
		return isSubTree(rootA, rootB, rootB);
	}
	
	private boolean isSubTree(SubTree<T> currentA, SubTree<T> rootB, SubTree<T> currentB) {
		if (currentA == null && currentB == null) {
			return true;
		}
		if((currentA == null && currentB !=null) || (currentA != null && currentB == null)) {
			return false;
		}
		if(currentA == currentB) {	//should use equals
			boolean left = isSubTree(currentA.left, rootB, currentB.left);
			boolean right = isSubTree(currentA.right, rootB, currentB.right);
			return left || right;
		} else {
			boolean left = isSubTree(currentA.left, rootB, rootB);
			boolean right = isSubTree(currentA.right, rootB, rootB);
			return left || right;
		}
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
	public static void main(String ...args) {
		SubTree<Integer> root = new SubTree<>(5);
		root.left = new SubTree<>(3);
		root.left.left = new SubTree<>(2);
		root.left.right = new SubTree<>(4);
		root.left.left.left = new SubTree<>(1);
		root.right = new SubTree<>(8);
		root.right.left = new SubTree<>(6);
		root.right.right = new SubTree<>(9);
		root.right.left.right = new SubTree<>(7);
		
		System.out.println(root.isSubTree(root.right, root.right.left)); //true
		System.out.println(root.isSubTree(root.left, root.right.left));	//false
		System.out.println(root.isSubTree(root.left, root));			//false
		System.out.println(root.isSubTree(root, root.left));			//true
		System.out.println(root.isSubTree(root.left, root.left));		//true
		System.out.println(root.isSubTree(root.left, null));			//false
		System.out.println(root.isSubTree(null, root));					//false
		System.out.println(root.isSubTree(null, root));					//false
	}

}
