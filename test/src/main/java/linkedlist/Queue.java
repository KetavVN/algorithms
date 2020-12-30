package linkedlist;

/**
 * Implement Queue using linkedlist
 */
public class Queue<T extends Comparable<T>> {

	private ListNode<T> start, end;

	public void add(T item) {
		ListNode<T> node = new ListNode<>(item);
		if(isEmpty()) {
			start = end = node;
		} else {
			end.next = node;
			end = end.next;
		}
	}

	public T remove() {
		if(isEmpty()) {
			throw new RuntimeException("Empty queue exception");
		}
		ListNode<T> node = start;
		start = start.next;
		end = isEmpty() ? null : end;
		node.next = null;
		return node.val;
	}

	public T peek() {
		if(isEmpty()) {
			throw new RuntimeException("Empty queue exception");
		}
		ListNode<T> node = start;
		//start = start.next;
		//node.next = null;
		return node.val;
	}

	public boolean isEmpty() {
		return start == null;
	}

	public static void main(String[] args) {
		Queue<Integer> queue = new Queue<>();
		queue.add(1);
		queue.add(2);
		queue.add(3);
		System.out.println(queue.peek());
		System.out.println(queue.remove());
		System.out.println(queue.remove());
		System.out.println(queue.isEmpty());
		System.out.println(queue.remove());
		System.out.println(queue.remove());	//throws exception
	}

}
