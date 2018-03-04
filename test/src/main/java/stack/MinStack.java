package stack;

import java.lang.reflect.Array;

public class MinStack<T extends Comparable<T>> {
    
    private T[] stack, minStack;
    private int current;
    
	@SuppressWarnings("unchecked")
	public MinStack(Class<T> cls, int size) {
        stack = (T[]) Array.newInstance(cls, size);
        minStack = (T[]) Array.newInstance(cls, size);
        current = 0;
    }
    
    public void push(T ele) {
        if(current < stack.length) {
            stack[current] = ele;
            if(current == 0) {
                minStack[current] = ele;
            } else if (minStack[current-1].compareTo(ele) > 0) {
                minStack[current] = ele;
            } else {
                minStack[current] = minStack[current-1];
            }
            current++;
        } else {
            throw new RuntimeException("stack is full");
        }
    }
    
    public T pop() {
        if(current >= 0) {
            current --;
            T retEle = stack[current];
            stack[current] = null;
            minStack[current] = null;
            return retEle;
        } else {
            throw new RuntimeException("stack is empty");
        }
    }
    
    public T min() {
        if(current-1 >= 0) {
            return minStack[current-1];
        } else {
            throw new RuntimeException("stack is empty");
        }
    }
    
    public static void main(String ...args) {
        MinStack<Integer> minStack = new MinStack<>(Integer.class, 10);
        minStack.push(5);
        minStack.push(6);
        minStack.push(7);
        minStack.push(4);
        minStack.push(3);
        System.out.println(minStack.min() + " "+ minStack.pop());
        System.out.println(minStack.min() + " "+ minStack.pop());
        System.out.println(minStack.min() + " "+ minStack.pop());
        System.out.println(minStack.min() + " "+ minStack.pop());
        System.out.println(minStack.min() + " "+ minStack.pop());
        //System.out.println(minStack.min() + " "+ minStack.pop());
    }
    
}
