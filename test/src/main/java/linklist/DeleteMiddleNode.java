package linkedlist;

/**
 * Delete middle node
 * Cracking the coding interview Ch 2 Problem 3 - LinkedList - DeleteMiddleNode
 */
public class DeleteMiddleNode {

	public static void delete(ListNode head, ListNode nodeToDelete) {
		if(head == null || nodeToDelete == null) {
			return;
		}
		while(head.next!=null && head.next != nodeToDelete) {
			head = head.next;
		}
		if(head.next == nodeToDelete) {
			head.next = nodeToDelete.next;
			nodeToDelete.next = null;
		}
	}
	
	public static void main(String[] args) {
		//test 1: node is present
		ListNode head = ListNode.getSampleList();
		ListNode nodeToDelete = KthNode.getKthNodeFromLast(head, 1);
		System.out.print("Original List : ");
		ListNode.printList(head);
		System.out.println("nodeToDelete = " + nodeToDelete);
		delete(head, nodeToDelete);
		System.out.print("After delete List : ");
		ListNode.printList(head);

		System.out.println();

		//test 1: node is not present in list
		head = ListNode.getSampleList();
		nodeToDelete = new ListNode(0);
		System.out.print("Original List : ");
		ListNode.printList(head);
		System.out.println("nodeToDelete = " + nodeToDelete);
		delete(head, nodeToDelete);
		System.out.print("After delete List : ");
		ListNode.printList(head);
	}

}
