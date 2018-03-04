package tree;

import java.util.List;
import java.util.ArrayList;

public class CommonAncesstor<T> {

	private T val;
	private CommonAncesstor<T> left, right;
	
	public CommonAncesstor(T v) {
		val = v;
	}
	
	public CommonAncesstor<T> findCommonAncesstor(CommonAncesstor<T> n1, CommonAncesstor<T> n2) {
		if(n1 == null || n2 == null)
			return null;
		List<CommonAncesstor<T>> commonNodes = new ArrayList<>();
		findCommonAncesstor(n1, n2, commonNodes);
		return !commonNodes.isEmpty() ? commonNodes.get(0) : null;
	}
	
	@SuppressWarnings("unused")
	private boolean findCommonAncesstor1(CommonAncesstor<T> n1, CommonAncesstor<T> n2, boolean found[], 
			List<CommonAncesstor<T>> commonNodes) {
		if(this == n1)
			found[0] = true;
		if(this == n2)
			found[1] = true;
		if(found[0] && found[1]) {
			//commonNodes.add(this);
			return true;
		}
		if(!found[0] || !found[1]) {
			if(left != null && left.findCommonAncesstor1(n1, n2, found, commonNodes)) {
				commonNodes.add(this);
				return true;
			} else if (right != null && right.findCommonAncesstor1(n1, n2, found, commonNodes)) {
				commonNodes.add(this);
				return true;
			}
		}
		return false;
	}

	@SuppressWarnings("unused")
	private boolean findCommonAncesstor(CommonAncesstor<T> n1, CommonAncesstor<T> n2, boolean found[], 
			List<CommonAncesstor<T>> commonNodes) {
		if(!found[0] && this == n1)
			found[0] = true;
		if(!found[1] && this == n2)
			found[1] = true;
		if(found[0] && found[1]) {
			//commonNodes.add(this);
			return true;	//start to backtrack
		}
		if(!found[0] || !found[1]) {
			boolean l = false;
			if(left != null && (l = left.findCommonAncesstor(n1, n2, found, commonNodes))) {
				//do nothing
			} 
			boolean r = false;
			if (right != null && (r = right.findCommonAncesstor(n1, n2, found, commonNodes))) {
				//do nothing
			}
			if(l || r) {
				commonNodes.add(this);
				System.out.println(this);
				return true;
			}
		} 

		return false;
	}
	
	private boolean[] findCommonAncesstor(CommonAncesstor<T> n1, CommonAncesstor<T> n2, List<CommonAncesstor<T>> commonNodes) {
		boolean [] found = new boolean [] {false, false};
		if(!found[0] && this == n1)
			found[0] = true;
		if(!found[1] && this == n2)
			found[1] = true;
		if(found[0] && found[1]) {
			if(commonNodes.isEmpty())
				commonNodes.add(this);
			return found;
		}

		boolean l[] = new boolean [] {false, false};
		if(left != null) {
			l = left.findCommonAncesstor(n1, n2, commonNodes);
		}
		boolean r[] = new boolean [] {false, false};
		if (right != null) {
			r = right.findCommonAncesstor(n1, n2, commonNodes);
		}

		if(!found[0]) found[0] = l[0] || r[0];
		if(!found[1]) found[1] = l[1] || r[1];
		if(found[0] && found[1] && commonNodes.isEmpty())
			commonNodes.add(this);
		return found;
	}
	
	@Override
	public String toString() {
		return val.toString();
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
	public static void main (String ...args) {
		CommonAncesstor<Integer> root = new CommonAncesstor<>(5);
		root.left = new CommonAncesstor<>(3);
		root.right = new CommonAncesstor<>(8);
		root.left.left = new CommonAncesstor<>(2);
		root.left.right = new CommonAncesstor<>(4);
		root.left.left.left = new CommonAncesstor<>(1);
		root.right.left = new CommonAncesstor<>(6);
		root.right.right = new CommonAncesstor<>(9);
		root.right.left.right = new CommonAncesstor<>(7);
		
		System.out.println(String.format("ancesstor(%s, %s) -> %s",root.left.left.left, root.right.left.right, root.findCommonAncesstor(root.left.left.left, root.right.left.right)));	//5
		//System.out.println("ancesstor(1,4) -> "+root.findCommonAncesstor(root.left.left.left, root.left.right));		//3
		//System.out.println("ancesstor(1,4) -> "+root.right.findCommonAncesstor(root.left.left.left, root.left.right));	//null since search begins from 8
	}
	
}
 
