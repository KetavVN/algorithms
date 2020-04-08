package linklist;

/*
https://leetcode.com/problems/middle-of-the-linked-list/
Leetcode challenge day 8

Given a non-empty, singly linked list with head node head, return a middle node of linked list.

If there are two middle nodes, return the second middle node.

Example 1:

Input: [1,2,3,4,5]
Output: Node 3 from this list (Serialization: [3,4,5])
The returned node has value 3.  (The judge's serialization of this node is [3,4,5]).
Note that we returned a ListNode object ans, such that:
ans.val = 3, ans.next.val = 4, ans.next.next.val = 5, and ans.next.next.next = NULL.

Example 2:

Input: [1,2,3,4,5,6]
Output: Node 4 from this list (Serialization: [4,5,6])
Since the list has two middle nodes with values 3 and 4, we return the second one.

Note:
    The number of nodes in the given list will be between 1 and 100.

Success Details:
Oms / 36.9mb

*/
public class ListMiddleNode {

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    
    //0ms 36.9mb
    public ListNode middleNode(ListNode head) {
        
        ListNode slow = head;
        
        ListNode fast = slow != null && slow.next != null ? slow.next : null;
        
        while(fast!=null) {
            slow = slow.next;
            fast = fast.next!=null && fast.next.next!=null ? fast.next.next : null;
        }
        
        return slow;
    }

    //0ms 36.7mb
    public ListNode middleNodeRecursive(ListNode head) {
        
        ListNode slow = head;
        
        ListNode fast = slow != null && slow.next != null ? slow.next : null;
        
        return getMiddleNode(slow, fast);
        
    }

    public ListNode getMiddleNode(ListNode slow, ListNode fast) {
        if(fast == null) {
            return slow;
        }
        return getMiddleNode(slow.next, fast.next != null && fast.next.next != null ? fast.next.next : null);
    }

}
