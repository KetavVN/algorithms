package linkedlist;

/**
 * Delete middle node
 * Cracking the coding interview Ch 2 Problem 3 - LinkedList - DeleteMiddleNode
 */
public class DeleteMiddleNode {

	public static void delete(ListNode<Integer> head, ListNode<Integer> nodeToDelete) {
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
		ListNode<Integer> head = ListNode.getSampleList();
		ListNode<Integer> nodeToDelete = KthNodeFromLast.getKthNodeFromLast(head, 1);
		System.out.print("Original List : ");
		head.print(head);
		System.out.println("nodeToDelete = " + nodeToDelete);
		delete(head, nodeToDelete);
		System.out.print("After delete List : ");
		head.print(head);

		System.out.println();

		//test 1: node is not present in list
		head = ListNode.getSampleList();
		nodeToDelete = new ListNode<Integer>(0);
		System.out.print("Original List : ");
		head.print(head);
		System.out.println("nodeToDelete = " + nodeToDelete);
		delete(head, nodeToDelete);
		System.out.print("After delete List : ");
		head.print(head);
	}

}
