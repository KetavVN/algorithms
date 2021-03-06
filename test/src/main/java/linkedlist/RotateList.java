package linkedlist;

/*
https://leetcode.com/problems/rotate-list/

Given a linked list, rotate the list to the right by k places, where k is non-negative.

Example 1:

Input: 1->2->3->4->5->NULL, k = 2
Output: 4->5->1->2->3->NULL
Explanation:
rotate 1 steps to the right: 5->1->2->3->4->NULL
rotate 2 steps to the right: 4->5->1->2->3->NULL

Example 2:

Input: 0->1->2->NULL, k = 4
Output: 2->0->1->NULL
Explanation:
rotate 1 steps to the right: 2->0->1->NULL
rotate 2 steps to the right: 1->2->0->NULL
rotate 3 steps to the right: 0->1->2->NULL
rotate 4 steps to the right: 2->0->1->NULL

Solution:
100% faster than others

 */
public class RotateList {

	class ListNode {
		int val;
		ListNode next;
	}

	public ListNode rotateRight(ListNode head, int k) {

		if(head == null) {
			return head;
		}

		int len = 0;
		ListNode front = head;
		while(front != null) {
			len++;
			front = front.next;
		}

		k = k % len;
		//System.out.println("len="+len+" k="+k);
		if(k == 0) {
			return head;
		}

		if(k < 0) {
			k = len + k;
		}

		front = head;
		int i = 0;
		while(i < k) {
			front = front.next;
			i++;
		}

		ListNode back = head;
		while(front.next != null) {
			front = front.next;
			back = back.next;
		}

		ListNode temp = back.next;
		back.next = null;
		front.next = head;
		head = temp;

		return head;

	}

}
