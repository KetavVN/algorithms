package stack;

import java.util.Stack;

/**
 * Cracking the coding interview Ch 3 Problem 5
 */
public class SortStack {

	/**
	 * sort a stack in ascending order
	 * stack top must be minimum at all times
	 */
	public void sortStack(Stack<Integer> input) {
		if(input == null || input.isEmpty()) {
			return;
		}
		int len = input.size();
		Stack<Integer> temp = new Stack<>();
		int var = 0;
		boolean isSorted = false;
		while(!isSorted) {
			var = input.pop();
			sortItem(input, temp, var);
			isSorted = temp.size() == len;
		}
		while(!temp.isEmpty()) {
			input.push(temp.pop());
		}
	}
	
	/**
	 * Place an item into correct order in dest stack.
	 * Destination order stacktop is the biggest item in the stack at that point
	 * 
	 * @param src - input stack
	 * @param dest - temp stack
	 * @param item - item to be placed at the right place on dest stack
	 */
	public void sortItem(Stack<Integer> src, Stack<Integer> dest, int item) {
		//1. move all elements > item from dest to src
		while(!dest.isEmpty() && dest.peek() > item) {
			src.push(dest.pop());
		}

		//2. place item into dest
		dest.push(item);

		//3. put back all items from step1
		while(!src.isEmpty() && src.peek() > dest.peek()) {
			dest.push(src.pop());
		}
	}

	public static void main(String args[]) {
		Stack<Integer> input = new Stack<>();
		input.push(5);
		input.push(6);
		input.push(3);
		input.push(0);
		input.push(1);
		input.push(2);
		SortStack obj = new SortStack();
		obj.sortStack(input);
		while(!input.isEmpty()) {
			System.out.println(input.pop());
		}
	}
	
}
