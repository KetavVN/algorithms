package linkedlist;

/*
write a program to rearrange following link list
a1->a2->a3->a4->b1->b2->b3-b4
into 
a1->b1->a2->b2->a3->b3->a4->b4
 */
public class ListNode<T extends Comparable<T>> {

	public T val;
	public ListNode<T> next;

	public ListNode(T val) {
		this.val = val;
	}

	public int getLength(ListNode<T> head) {
		int len = 0;
		ListNode<T> current = head;
		while( current != null) {
			len++;
			current = current.next;
		}
		return len;
	}

	/* looks like logic to "partition" linked list (quick sort type)
        ll: 1-3-5-2-4-6
        input- head, 5
        output: 1-3-2-4-5-6
        logic:
            1. find value node
            2. put it at head
            3. from current=head.next till end of ll
                if current < valNode
                    -temp = current.next
                    -put current at position before valNode 
                    -current = temp            
	 */
	public ListNode<T> reorder(ListNode<T> head, T val) {

		if(head == null) {
			return head;
		}

		ListNode<T> prev = null;
		ListNode<T> current = head;
		while(current != null && !current.val.equals(val)) {
			prev = current;
			current = current.next;
		}

		//if input is not part of linked list return without doing any operation
		if(current == null) {
			return null;
		}

		//take out input node from middle of list
		if(prev != null) {
			prev.next = current.next;
		}

		//store reference of value's node in a variable for later use
		ListNode<T> valNode = current;

		//put input node at head
		current.next = head;
		head = current;

		//prepare for iteration on linked list
		current = head.next;
		prev = head;

		ListNode<T> replaceNode = null;

		while(current != null) {
			if(current.val.compareTo(valNode.val) < 0) {
				if(replaceNode == null) {
					head = current;
					replaceNode = current;
					prev.next = current.next;
					replaceNode.next = valNode;
					current = prev.next;
				} else {
					prev.next = current.next;
					current.next = valNode;
					replaceNode.next = current;
					replaceNode = current;
					current = prev.next;
				}
			} else {
				prev = current;
				current = current.next;
			}
		}

		return head;

	}

	public ListNode<T> organize(ListNode<T> head) {

		if(head == null) {
			return head;
		}

		//validate even number of nodes

		ListNode<T> a = head;
		ListNode<T> b = head.next;

		while(b.next != null 
				&& b.next.next != null) {
			a = a.next;
			b = b.next.next;
		}

		//a is the mid point reassign 
		b = a.next;      //now b is mid point
		a.next = null;	//break the chain for reorganization
		a = head;   //a is start

		System.out.println(a.val+" "+b.val);

		ListNode<T> bNext;
		while(b != null) {

			bNext = b.next;

			b.next = a.next;
			a.next = b;
			a = b.next;

			b = bNext;
		}

		return head;

	}

	public void print(ListNode<T> head) {
		while(head != null) {
			System.out.print(String.format("%s%s", head.val, head.next != null ? " -> ":"."));
			head = head.next;
		}
		System.out.println();
	}

	public static ListNode<Integer> getSampleList() {
		ListNode<Integer> head = new ListNode<>(1);
		head.next = new ListNode<Integer>(3);
		head.next.next = new ListNode<Integer>(5);
		head.next.next.next = new ListNode<Integer>(2);
		head.next.next.next.next = new ListNode<Integer>(4);
		head.next.next.next.next.next = new ListNode<Integer>(6);
		return head;
	}

	@Override
	public String toString() {
		return "ListNode [val=" + val + "]";
	}
	
	public static void main(String ...args) {

		ListNode<Integer> head = ListNode.getSampleList();

		//head.organize(head);
		head.print(head);

		head = head.reorder(head, 5);

		System.out.println();
		head.print(head);
	}

}
