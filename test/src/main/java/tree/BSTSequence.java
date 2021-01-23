package tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Cracking the coding interview Ch-4 Problem-9 - BST Sequence
 * 
 * BST was created from traversing through an array of distince elements from left to right
 * Given a BST find all possible combination of arrays which would create this tree
 * 
 * Ex: 
 *  Input:
 *     2
 *    / \
 *   1   3
 *   
 *  Output : {[2,1,3], [2,3,1]}
 * 
 * @author ketav
 */
public class BSTSequence {

	public Set<List<Integer>> getBSTArrays(BinaryTreeNode<Integer> root) {
		
		if(root == null) {
			return new LinkedHashSet<>();
		}
		
		Set<List<Integer>> l = getBSTArrays(root.left);
		Set<List<Integer>> r = getBSTArrays(root.right);
		
		Set<List<Integer>> combined = new LinkedHashSet<>();
		
		for(List<Integer> rL : r) {
			for(List<Integer> lL : l) {
				weave(lL, rL, new ArrayList<>(), combined, 0, 0);
				weave(rL, lL, new ArrayList<>(), combined, 0, 0);
			}
		}

		return appendRootNode(root, combined);
		
	}

	private Set<List<Integer>> appendRootNode(BinaryTreeNode<Integer> root, Set<List<Integer>> combined) {
		Set<List<Integer>> ans = new HashSet<>();
		List<Integer> lst = new ArrayList<>();
		lst.add(root.val);
		if(combined == null || combined.isEmpty()) {
			ans.add(lst);
		} else {
			for(List<Integer> l : combined) {
				List<Integer> copy = new ArrayList<>(lst);
				copy.addAll(l);
				ans.add(copy);
			}
		}
		return ans;
	}

	private void weave(List<Integer> l, List<Integer> r, List<Integer> current, Set<List<Integer>> weaved, int lInd, int rInd) {
		if(lInd == l.size() && rInd == r.size() ) {
			weaved.add(current);
		}
		List<Integer> copy = copyList(current); 
		if(lInd < l.size()) {
			copy.add(l.get(lInd));
			weave(l, r, copy, weaved, ++lInd, rInd);
		} 
		if(rInd < r.size()) {
			copy.add(r.get(rInd));
			weave(l, r, copy, weaved, lInd, ++rInd);
		} 
	}

	private List<Integer> copyList(List<Integer> current) {
		return new ArrayList<>(current);
	}

	public static void main(String [] args) {
		BinaryTreeNode<Integer> root = new BinaryTreeNode<>(5);
		root.left  = new BinaryTreeNode<>(3);
		root.left.right = new BinaryTreeNode<>(4);
		root.right  = new BinaryTreeNode<>(7);
		root.right.left  = new BinaryTreeNode<>(6);
		root.right.right  = new BinaryTreeNode<>(8);
		
		Set<List<Integer>> ans = new BSTSequence().getBSTArrays(root);
		for(List<Integer> arr : ans) {
			System.out.println(arr);
		}
	}
}
