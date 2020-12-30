package linkedlist;

/**
 * Implement Stack using linkedlist
 */
public class Stack<T extends Comparable<T>> {

	private ListNode<T> head = new ListNode<>();

	public void push(T item) {
		ListNode<T> node = new ListNode<>(item);
		node.next = head.next;
		head.next = node;
	}

	public T pop() {
		ListNode<T> node = head.next;
		if(node == null) {
			throw new RuntimeException("Empty stack exception");
		}
		head.next = node.next;
		node.next = null;
		return node.val;
	}

	public T peek() {
		T item = head.next != null ? head.next.val : null;
		if(item == null) {
			throw new RuntimeException("Empty stack exception");
		}
		//head.next = head.next.next;
		return item;
	}

	public boolean isEmpty() {
		return head.next == null;
	}

	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<>();
		stack.push(1);
		stack.push(2);
		stack.push(4);
		stack.push(6);
		System.out.println(stack.pop());
		System.out.println(stack.peek());
		System.out.println(stack.pop());
		System.out.println(stack.isEmpty());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.isEmpty());
		System.out.println(stack.pop());	//throws exception
	}

}
