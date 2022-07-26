package linkedlist;

/**
https://leetcode.com/problems/reverse-linked-list/

Given the head of a singly linked list, reverse the list, and return the reversed list.

Example 1:
Input: head = [1,2,3,4,5]
Output: [5,4,3,2,1]

Example 2:
Input: head = [1,2]
Output: [2,1]

Example 3:
Input: head = []
Output: []

Constraints:
    The number of nodes in the list is the range [0, 5000].
    -5000 <= Node.val <= 5000

Follow up: A linked list can be reversed either iteratively or recursively. Could you implement both?

 * @author ketav
 *
 */
public class ReverseLinkedList {

	/**
	 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Reverse Linked List.
	 * Memory Usage: 42.5 MB, less than 78.40% of Java online submissions for Reverse Linked List. 
	 */
	public ListNode<Integer> reverseList(ListNode<Integer> head) {
        return head != null ? reverse(head, head.next) : head;
    }
    
    public ListNode<Integer> reverse(ListNode<Integer> cur, ListNode<Integer> nxt) {
        if(nxt == null) {
            return cur;
        } else {
            ListNode<Integer> head = reverse(nxt, nxt.next);
            nxt.next = cur;
            cur.next = null;
            return head;
        }
    }
}
