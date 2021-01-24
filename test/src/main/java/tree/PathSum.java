package tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Cracking the coding interview ch4 problem 12 - PathSum
 * 
 * Find all paths in a binary tree which sum to given value.
 * The path does not have to begin from root or end at leaf. But it has to be a straight line;
 *  
 * @author ketav
 */
public class PathSum<T extends Number> {	//works only for integer for given problem

	private T val;
	private PathSum<T> left, right;
	
	public PathSum(T val) {
		this.val = val;
	}
	
	@Override
	public String toString() {
		return val.toString();
	}
	
	/**
	 * Option 1 : when traversal reaches leaf node, apply window logic
	 * 
	 * Cons: 
	 * 1. too high on space complexity, 
	 * 2. answer only available after all nodes are traversed from root to leaf (depth can be too high)
	 * 
	 * @param root
	 * @param val
	 * @return
	 */
	public Set<List<PathSum<T>>> findPaths(PathSum<T> root, int val) {
		Set<List<PathSum<T>>> list = new HashSet<>(0);
		if(root == null) {
			return list;
		}
		List<PathSum<T>> temp = new ArrayList<>(0);
		findPaths(root, list, temp, val);
		return list;
	}
	
	private void findPaths(PathSum<T> currentNode, Set<List<PathSum<T>>> list, List<PathSum<T>> temp, int val) {
		if(currentNode != null) {
			temp.add(currentNode);
			if(currentNode.left == null && currentNode.right == null) {	//reached leaf
				findSumPath(list, temp, val);
			} else {
				findPaths(currentNode.left, list, new ArrayList<>(temp), val);
				findPaths(currentNode.right, list, new ArrayList<>(temp), val);
			}
		}
	}
	
	private void findSumPath(Set<List<PathSum<T>>> list, List<PathSum<T>> temp, int val) {
		int sum = 0, windowStart=0, windowEnd=0;
		while(windowEnd < temp.size()) {
			sum += temp.get(windowEnd).val.intValue();
			windowEnd++;
			if(sum > val) {
				sum -= temp.get(windowStart).val.intValue();
				windowStart++;
			} 
			if (sum == val) {
				int i = windowStart;
				List<PathSum<T>> lst = new ArrayList<>();
				while(i < windowEnd) {
					lst.add(temp.get(i++));
				}
				list.add(lst);
			}
		}
	}
	
	/**
	 * Option 2: Maintain 2 lists: 
	 * 		1. Running sum from each node till current node 
	 * 			[node.value gets added to all previous items in the list
	 * 			 current node's value alone gets appended to the list]
	 * 		2. Running node list from root till current node
	 * Pros: Answer available as soon as value is reached
	 * 		Ideally Traversal can be stopped without traversing till leaf node.
	 * 
	 * Cons: space complexity similar to option 1 
	 * 
	 * @param root
	 * @param val
	 * @return
	 */
	public Set<List<PathSum<T>>> findPaths2(PathSum<T> root, int val) {
		if(root == null) {
			return new HashSet<>();
		}

		List<PathSum<T>> nodeList = new ArrayList<>();
		List<Integer> sumList = new ArrayList<>();

		Set<List<PathSum<T>>> ans = new HashSet<>();
		findPaths2(root, nodeList, sumList, val, ans);
		return ans;
	}

	private void findPaths2(PathSum<T> node, List<PathSum<T>> nodeList, 
			List<Integer> sumList, int val, Set<List<PathSum<T>>> ans) {
		if(node != null) {
			nodeList = addItemToNewNodeList(nodeList, node);
			sumList = addItemToNewSumList(sumList, node.val.intValue());
			checkSumListMatchesToVal(nodeList, sumList, val, ans);
			findPaths2(node.left, nodeList, sumList, val, ans);
			findPaths2(node.right, nodeList, sumList, val, ans);
		}
	}

	private List<PathSum<T>> addItemToNewNodeList(List<PathSum<T>> nL, PathSum<T> node) {
		List<PathSum<T>> newList = new ArrayList<>(nL);
		newList.add(node);
		return newList;
	}

	private List<Integer> addItemToNewSumList(List<Integer> sumList, Integer nv) {
		List<Integer> newList = new ArrayList<>();
		for(int i=0; i<sumList.size(); i++) {
			newList.add(sumList.get(i) + nv);
		}
		newList.add(nv);
		return newList;
	}

	private void checkSumListMatchesToVal(List<PathSum<T>> nL, List<Integer> sL, Integer val, Set<List<PathSum<T>>> ans) {
		for(int i=0; i<sL.size(); i++) {
			if(sL.get(i) == val) {
				ans.add(nL.subList(i, nL.size()));
			}
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
		PathSum<Integer> root = new PathSum<>(5);
		root.left = new PathSum<>(3);
		root.right = new PathSum<>(8);
		root.left.left = new PathSum<>(2);
		root.left.right = new PathSum<>(4);
		root.left.left.left = new PathSum<>(1);
		root.right.left = new PathSum<>(6);
		root.right.right = new PathSum<>(9);
		root.right.left.right = new PathSum<>(7);
		
		Set<List<PathSum<Integer>>> sets = root.findPaths2(root, 10);
		for(List<PathSum<Integer>> lst : sets) {
			for(PathSum<Integer> i : lst) {
				System.out.print(i.val.intValue()+" ");
			}
			System.out.println();
		}
	}

}
 
