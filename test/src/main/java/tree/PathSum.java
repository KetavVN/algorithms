package tree;

import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

/*
find all paths in a binary tree which sum to given value.
the path does not have to begin from root or end at leaf. But it has to be a straight line;
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
	
	/*private class SummaryArrayList extends ArrayList<Integer> {
		
		private float total;
		
		@Override
		public void add(Integer ele) {	//only additions are supported for the time being
			total+=ele;
			super.add();
		}
		
		@Override
		public int hashCode() {
			return currentNodeList.hashCode();
		}
		
		@Override
		public boolean equals() {
			return currentNodeList.equals();
		}
		
		public float getTotal() {
			return total;
		}
		
	}
	
	private void findPaths(PathSum<T> currentNode, Set<List<PathSum<T>>> list, Set<List<PathSum<T>>> temp, int val) {
		if(currentNode == null) {
			return;
		}
		
		for(List<PathSum<T>> prevNodeList : temp) {
			
		}
		
		List<PathSum<T>> newPathFromCurrentNode = new SummaryArrayList<>();
		newPathFromCurrentNode.add(currentNode);
		
	}*/
	
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
		int sum = 0, startIndex=0, endIndex=0;
		while(endIndex < temp.size()) {
			sum += temp.get(endIndex).val.intValue();
			endIndex++;
			if(sum > val) {
				sum -= temp.get(startIndex).val.intValue();
				startIndex++;
			} 
			if (sum == val) {
				int i = startIndex;
				List<PathSum<T>> lst = new ArrayList<>();
				while(i < endIndex) {
					lst.add(temp.get(i++));
				}
				list.add(lst);
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
		
		Set<List<PathSum<Integer>>> sets = root.findPaths(root, 8);
		for(List<PathSum<Integer>> lst : sets) {
			for(PathSum<Integer> i : lst) {
				System.out.print(i.val.intValue()+" ");
			}
			System.out.println();
		}
	}

}
 
