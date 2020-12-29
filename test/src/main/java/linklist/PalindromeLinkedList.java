package linkedlist;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
  Check if linked list is palindrome
  Cracking the coding interview Ch 2 Problem 6
**/
public class PalindromeLinkedList {
	
	public static class ListNode {
		private int val;
		private ListNode next;
		public ListNode(int v) {
			this.val = v;
		}
		@Override
		public String toString() {
			return Integer.toString(val);
		}
	}

	/**
	 * option 1 - using buffer
	 * Time Complexity = O(n)
	 * Space Complexity = O(n)
	 */
	public static boolean isPalindrome(ListNode head) {
		if(head == null) {
			return false;
		}

		Stack<ListNode> stack = new Stack<>();
		ListNode current = head;

		while(current != null) {
			stack.push(current);
			current = current.next;
		}

		current = head;
		while(current != null) {
			if(current.val != stack.pop().val) {
				return false;
			}
			current = current.next;
		}
		return true;
	}

	/**
	 * Option 2: Recursive but still uses buffer (Queue + Stack)
	 * 	- without buffer answer would be O(n^2)
	 * @param head
	 * @return
	 */
	public static boolean isPalindromeRec2(ListNode head) {
		if(head == null) {
			return false;
		}
		Queue<ListNode> q = new LinkedList<>();
		isPalindromeRec2(head, null, q);
		return q.isEmpty();
	}

	public static ListNode isPalindromeRec2(
			ListNode current, ListNode prev, Queue<ListNode> queue) {
		if(current != null) {
			queue.add(current);
			ListNode end = isPalindromeRec2(current.next, current, queue);
			if(end.val == queue.peek().val) {
				queue.remove();
			}
		}
		return prev;
	}

	/**
	 * Option 2: Recursive but still uses buffer (Queue + Stack)
	 * 	- without buffer answer would be O(n^2)
	 * @param head
	 * @return
	 */
	public static boolean isPalindromeRec(ListNode head) {
		return (Boolean) isPalindromeRec(head, null, new LinkedList<>())[1];
	}

	public static Object [] isPalindromeRec(
			ListNode current, ListNode prev, Queue<ListNode> queue) {
		if(current == null) {
			return new Object [] {prev, Boolean.TRUE};
		}
		queue.add(current);
		Object [] end = isPalindromeRec(current.next, current, queue);
		Object [] ret = new Object[2];
		if(end[1] == Boolean.TRUE) {
			ret[0] = prev;
			ret[1] = ((ListNode)end[0]).val == queue.remove().val;
		} else {
			ret = end;
		}
		return ret;
	}

	/**
	 * Option 3: Recursive no explicit memory usage 
	 * - worst case - O(n^2) solution
	 * @param head
	 * @return
	 */
	public static boolean isPalindromeRec3(ListNode head) {
		return isPalindromeRec3(head, head) > 0;
	}

	public static int isPalindromeRec3(ListNode head, ListNode current) {
		if(current != null) {
			int frontInd = isPalindromeRec3(head, current.next);
			if(frontInd > -1) {
				ListNode frontNode = getNode(head, frontInd);
				return current.val == frontNode.val ? frontInd+1 : -1;
			}
			return frontInd;
		}
		return 0;
	}

	private static ListNode getNode(ListNode head, int frontInd) {
		ListNode node = head;
		for(int i=0; i < frontInd && node!=null; i++, node = node.next);
		return node;
	}

	public static void main(String [] args) {
		ListNode head = new ListNode(1);
		head.next =  new ListNode(2);
		head.next.next =  new ListNode(3);
		//head.next.next.next =  new ListNode(3);
		head.next.next.next =  new ListNode(2);
		head.next.next.next.next =  new ListNode(1);
		System.out.println(isPalindromeRec3(head));
	}
	
}
