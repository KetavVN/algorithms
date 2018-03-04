package stack;

import java.util.Stack;

public class TowerOfHannoi {

	public void swap(int n, Stack<Integer> source, Stack<Integer> aux, Stack<Integer> dest) {
		if(n == 1) {
			Integer disk = source.pop();
			dest.push(disk);
		} else if (n == 2) {
			Integer disk1 = source.pop();
			aux.push(disk1);
			Integer disk2 = source.pop();
			dest.push(disk2);
			disk1 = aux.pop();
			dest.push(disk1);
		} else {
			swap(n-1, source, dest, aux);
			Integer disk = source.pop();
			dest.push(disk);
			swap(n-1, aux, source, dest);
		}
	}
	
	public void printStack(Stack<Integer> stack) {
		if(stack == null || stack.isEmpty()) {
			return;
		}
		Stack<Integer> copy = new Stack<>();
		int len = stack.size();
		int i = 0;
		while(i < len) {
			Integer item = stack.pop();
			System.out.print(item+" ");
			copy.push(item);
			i++;
		}
		
		i = 0;
		while(i < len) {
			Integer item = copy.pop();
			stack.push(item);
			i++;
		}
		//System.out.println();
	}

	public static void main(String ...args) {
		TowerOfHannoi obj = new TowerOfHannoi();
		Stack<Integer> source = new Stack<>();
		source.push(3);
		source.push(2);
		source.push(1);
		Stack<Integer> aux = new Stack<>();
		Stack<Integer> dest = new Stack<>();
		
		System.out.print("Source[");
		obj.printStack(source);
		System.out.println("]");
		System.out.print("Aux[");
		obj.printStack(aux);
		System.out.println("]");
		System.out.print("Dest[");
		obj.printStack(dest);
		System.out.println("]");
		
		obj.swap(3, source, aux, dest);
		
		System.out.print("Source[");
		obj.printStack(source);
		System.out.println("]");
		System.out.print("Aux[");
		obj.printStack(aux);
		System.out.println("]");
		System.out.print("Dest[");
		obj.printStack(dest);
		System.out.println("]");
	}
}
