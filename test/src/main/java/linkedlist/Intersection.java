package linkedlist;

import java.util.HashSet;
import java.util.Set;

/**
 * Cracking the coding interivew Ch2 Problem 7
 * Find intersection node between 2 unsorted list
 * Option 1: use buffer - O(m+n) - below implementation uses this algorithm
 * Option 2: do not use buffer - O(m*n)
 * 
 * if lists were sorted - merge sort type algorithm O(m+n) with no buffer
 */
public class Intersection {

	public static ListNode<Integer> getIntersectionNode(ListNode<Integer> l1, ListNode<Integer> l2) {
		Set<ListNode<Integer>> set = new HashSet<>();
		while(l1!=null) {
			set.add(l1);
			l1 = l1.next;
		}
		while(l2!=null) {
			if(set.contains(l2)) {
				return l2;
			}
			l2 = l2.next;
		}
		return null;
	}

	/**
	 * l1 and l2 are sorted
	 * 
	 * @param l1
	 * @param l2
	 * @return
	 */
	public static ListNode<Integer> getIntersectionNode2(ListNode<Integer> l1, ListNode<Integer> l2) {
		while(l1!=null && l2!=null) {
			if(l1.val == l2.val) {
				ListNode<Integer> ref = l1;
				ListNode<Integer> runner1 = l1;
				while(runner1!=null && runner1.val == ref.val) {
					ListNode<Integer> runner2 = l2;
					while(runner2!=null && runner2.val == ref.val) {
						if(runner1 == runner2) {
							return runner1;
						}
						runner2 = runner2.next;
					}
					runner1 = runner1.next;
					l2 = runner2;
				}
				l1 = runner1;
			} else if (l1.val < l2.val) {
				l1 = l1.next;
			} else {
				l2 = l2.next;
			}
		}
		return null;
	}
	
	public static void main(String[] args) {
		//unsorted test
		ListNode<Integer> l1 = ListNode.getSampleList();
		ListNode<Integer> l2 = new ListNode<>(-1);
		l2.next = new ListNode<>(-2);
		l2.next.next = l1;
		l1.print(l1);
		l2.print(l2);
		System.out.println(getIntersectionNode(l1, l2));
		
		
		//sorted test 1
		l1 = new ListNode<>(1);
		l1.next = new ListNode<>(3);
		l1.next.next = new ListNode<>(3);
		l1.next.next.next = new ListNode<>(4);
		l1.next.next.next.next = new ListNode<>(5);
		
		l2 = new ListNode<>(1);
		l2.next = new ListNode<>(2);
		l2.next.next = new ListNode<>(3);
		l2.next.next.next = new ListNode<>(3);
		l2.next.next.next.next = l1.next.next.next;
		
		l1.print(l1);
		l2.print(l2);
		System.out.println(getIntersectionNode2(l1, l2));
		
		//sorted test 2
		l2 = new ListNode<>(1);
		l2.next = new ListNode<>(2);
		l2.next.next = new ListNode<>(3);
		l2.next.next.next = new ListNode<>(3);
		l1.print(l1);
		l2.print(l2);
		System.out.println(getIntersectionNode2(l1, l2));
		
	}

}
