package linkedlist;

/**
 * Cracking the coding interview Ch 2 Problem 5 
 */
public class ListSum {

	@SuppressWarnings("unchecked")
	public static ListNode<Integer> sumLists(ListNode<Integer> l1, ListNode<Integer> l2) {
		ListNode<Integer> head = new ListNode<>(0);
		ListNode<Integer> runner = head;
		int carry = 0;
		int sum = 0;
		int rem = 0;
		while(l1 != null && l2 != null) {
			sum = carry + l1.val + l2.val;
			carry = sum / 10;
			rem = sum % 10;
			runner.next = new ListNode<>(rem);
			runner = runner.next;
			l1 = l1.next;
			l2 = l2.next;
		}
		Object [] ret = addRemainingNodes(l1, runner, carry);
		ret = addRemainingNodes(l2, (ListNode<Integer>)ret[0], (int)ret[1]);
		if((int)ret[1] > 0) {
			((ListNode<Integer>)ret[0]).next = new ListNode<>((int)ret[1]);
		}
		return head.next;
	}

	private static Object[] addRemainingNodes(ListNode<Integer> l1, ListNode<Integer> runner, int carry) {
		int sum = 0;
		int rem = 0;
		while(l1 != null) {
			sum = carry + l1.val ;
			carry = sum / 10;
			rem = sum % 10;
			runner.next = new ListNode<>(rem);
			runner = runner.next;
			l1 = l1.next;
		}
		return new Object [] {runner, carry};
	}

	public static void main(String[] args) {
		ListNode<Integer> head = ListNode.getSampleList();
		ListNode<Integer> head2 = ListNode.getSampleList();
		ListNode<Integer> sum = sumLists(head, head2);
		head.print(head);
		head2.print(head2);
		sum.print(sum);
	}

}
