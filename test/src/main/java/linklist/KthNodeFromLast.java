package linkedlist;

/**
  Cracking the coding interview Ch-2 Problem 2
**/
public class KthNodeFromLast {

	private static ListNode getNodeAtIndex(ListNode head, int index) {
		ListNode node = head;
		int i = 1;
		while(i < index) {
			node = node.next;
			i++;
		}
		return node;
	}
	
	public static ListNode getKthNodeFromLast(ListNode head, int k) {
		int len = ListNode.length(head);
		//System.out.println("len = " + len);
		if(len == 0 || k < 0 || k > len) {
			return null;
		}
		int ind = len - k;
		//System.out.println("index = " + ind);
		return getNodeAtIndex(head, ind);
	}
	
	public static void main(String[] args) {
		System.out.println(getKthNodeFromLast(ListNode.getSampleList(), 0));
		System.out.println(getKthNodeFromLast(ListNode.getSampleList(), 1));
		System.out.println(getKthNodeFromLast(ListNode.getSampleList(), 5));
	}

}
