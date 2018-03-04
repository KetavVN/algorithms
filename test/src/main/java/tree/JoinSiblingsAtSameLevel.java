package tree;

import java.util.LinkedList;
import java.util.Queue;

public class JoinSiblingsAtSameLevel {
	
	@SuppressWarnings("unused")
	private static class Node<T> {

		private T val;
		private Node<T> left, right, sibling;
		
		public Node(T val) {
			this.val = val;
		}
		
		@Override
		public String toString() {
			return val.toString();
		}
		
	}
	
	public static <T> void connectSiblings(Node<T> node) {
		Queue<Node<T>> queue = new LinkedList<>();
		queue.add(node);
		while(!queue.isEmpty()) {
			int len = queue.size();
			Node<T> temp = null;
			for(int i=0; i<len; i++) {
				Node<T> root = queue.poll();
				if(root.left!=null) queue.add(root.left);
				if(root.right!=null) queue.add(root.right);
				if(temp!=null)
					temp.sibling = root;
				temp = root;
			}
		}
	}
	
	public static <T> void connectSiblings2(Node<T> node) {
		Queue<Node<T>> queue = new LinkedList<>();
		queue.add(node);
		connectSiblings(queue);
	}
	
	private static <T> void connectSiblings(Queue<Node<T>> queue) {
		if(!queue.isEmpty()) {
			int len = queue.size();
			Node<T> temp = null;
			for(int i=0; i<len; i++) {
				Node<T> root = queue.poll();
				if(root.left!=null) queue.add(root.left);
				if(root.right!=null) queue.add(root.right);
				if(temp!=null)
					temp.sibling = root;
				temp = root;
			}
			connectSiblings(queue);
		}
	}
	
	/*
	 *         5
	 *       /   \
	 *      3     8
	 *     / \   / \
	 *    2   4 6   9
	 *   /       \
	 *  1         7
	 */
	public static void main(String ...args) {
		
		Node<Integer> root = new Node<Integer>(5);
		root.left = new Node<Integer>(3);
		root.left.left = new Node<Integer>(2);
		root.left.left.left = new Node<Integer>(1);
		root.left.right = new Node<Integer>(4);
		root.right = new Node<Integer>(8);
		root.right.left = new Node<Integer>(6);
		root.right.right = new Node<Integer>(9);
		root.right.left.right = new Node<Integer>(7);
		
		connectSiblings(root);
		System.out.println();
	}
}
