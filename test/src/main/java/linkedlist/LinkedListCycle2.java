package linkedlist;

import java.util.HashSet;
import java.util.Set;

/*
https://leetcode.com/problems/linked-list-cycle-ii

Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

To represent a cycle in the given linked list, we use an integer pos which represents the position (0-indexed) in the linked list where tail connects to. If pos is -1, then there is no cycle in the linked list.

Note: Do not modify the linked list.

Example 1:

Input: head = [3,2,0,-4], pos = 1
Output: tail connects to node index 1
Explanation: There is a cycle in the linked list, where tail connects to the second node.

Example 2:

Input: head = [1,2], pos = 0
Output: tail connects to node index 0
Explanation: There is a cycle in the linked list, where tail connects to the first node.

Example 3:

Input: head = [1], pos = -1
Output: no cycle
Explanation: There is no cycle in the linked list.

Follow-up:
Can you solve it without using extra space?

*/
public class LinkedListCycle2 {
	
	class ListNode {
		int val;
		ListNode next;
		ListNode(int x) {
			val = x;
			next = null;
		}
	}
	
    /**
      Success Details
      Runtime: 4 ms, faster than 15.61% of Java online submissions for Linked List Cycle II.
      Memory Usage: 41.2 MB, less than 6.32% of Java online submissions for Linked List Cycle II.
     */
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        Set<ListNode> set = new HashSet<>();
        while(!set.contains(slow) && slow != null) {
            set.add(slow);
            slow = slow.next != null ? slow.next : null;
        }
        return set.contains(slow) ? slow : null;
    }
    
}
