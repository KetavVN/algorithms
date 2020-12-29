package linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * Remove duplicates from Linked List
 * follow up - no buffer
 * Cracking the coding interview Ch-2 Problem 1
 */
public class RemoveDuplicates {

	/**
	 * option 1: use hashMap - buffer
	 */
	public void removeDuplicates1(ListNode<Integer> head) {
		if(head == null) {
			return;
		}
		Map<Integer, ListNode<Integer>> nodeMap = new HashMap<>();
		ListNode<Integer> node = head;
		ListNode<Integer> prev = null;
		while(node!=null) {
			if(!nodeMap.containsKey(node.val)) {
				nodeMap.put(node.val, node);
				prev = node;
			} else {
				prev.next = node.next;
			}
			node = node.next;
		}
	}
	
	/**
	 * option 2: O(n^2) solution
	 */
	public void removeDuplicates2(ListNode<Integer> head) {
		if(head == null) {
			return;
		}
		ListNode<Integer> node = head;
		while(node != null) {
			ListNode<Integer> runner = node;
			while(runner!=null && runner.next!=null) {
				if(runner.next.val == node.val) {
					runner.next = runner.next.next;
				}
				runner = runner.next;
			}
			node = node.next;
		}
	}
	
	public static void main(String [] args) {
		RemoveDuplicates obj = new RemoveDuplicates();
		ListNode<Integer> head = ListNode.getSampleList();
		System.out.print("Original: ");
		head.print(head);
		obj.removeDuplicates1(head);
		System.out.print("After duplicate removal: ");
		head.print(head);
		
		head = ListNode.getSampleList();
		obj.removeDuplicates2(head);
		System.out.print("After duplicate removal2: ");
		head.print(head);
	}
	
}
