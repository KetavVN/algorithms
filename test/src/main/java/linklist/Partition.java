package linkedlist;

/**
 * Cracking the coding interview Ch2 Problem 4 - Partition
 * Partition the list around a value such that all elements smaller or equal than value are on left of valueNode
 * and larger elements are on right side on the valueNode
 */
public class Partition {

	public static ListNode partition(ListNode head, int val) {

		if(head == null) { return head; }

		//1. find valueNode
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode prev = dummy;
		ListNode node = head;
		while(node != null && node.val != val) {
			prev = node;
			node = node.next;
		}
		if(node == null) {
			return head;
		}

		//2. move valueNode to head
		prev.next = node.next;
		node.next = head;
		dummy.next = node;
		prev = dummy;
		ListNode valueNode = node;
		head = null;

		//3. iterate over remaining list and move smaller or equal items on left of valueNode
		while(node.next != null) {
			if(node.next.val <= valueNode.val) {
				if(head == null) {
					head = node.next;
				}
				ListNode temp = node.next;	//move this tmp node in between prev & refNode.
				node.next = temp.next;		//before move make sure current node's next reference is fixed.
				temp.next = valueNode;
				prev.next = temp;
				prev = temp;
			} else {
				node = node.next;
			}
		}

		//4. return head if there were any swaps else return valueNode which was head.
		return head != null ? head : valueNode;
	}
	
	public static void main(String[] args) {
		ListNode head = ListNode.getSampleList();
		System.out.print("Before partition : ");
		ListNode.printList(head);
		head = Partition.partition(head, 2);
		System.out.print("After partition : ");
		ListNode.printList(head);
	}

}
