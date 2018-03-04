package linklist;
/*
write a program to rearrange following link list
a1->a2->a3->a4->b1->b2->b3-b4
into 
a1->b1->a2->b2->a3->b3->a4->b4
*/

public class ListNode<T extends Comparable<T>> {

    private T val;
    private ListNode<T> next;

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
    
    /*
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
        
        ListNode<T> valNode = null, prev = null, current = head;
        while(valNode == null && current != null) {
            if(current.val.equals(val)) {
                valNode = current;
                break;
            }
            prev = current;
            current = current.next;
        }
        
        if(valNode == null) {
            return null;
        }
        
        if(prev != null) {
            prev.next = valNode.next;
        }
        
        valNode.next = head;
        head = valNode;
        
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
            System.out.print(head.val+" ");
            head = head.next;
        }
    }

    @Override
	public String toString() {
		return "ListNode [val=" + val + "]";
	}

	public static void main(String ...args) {
        
        ListNode<Integer> head = new ListNode<>(1);
        head.next = new ListNode<Integer>(3);
        head.next.next = new ListNode<Integer>(5);
        head.next.next.next = new ListNode<Integer>(2);
        head.next.next.next.next = new ListNode<Integer>(4);
        head.next.next.next.next.next = new ListNode<Integer>(6);

        //head.organize(head);
        head.print(head);
        
        head = head.reorder(head, new Integer(5));
        
        System.out.println();
        head.print(head);
    }

}
