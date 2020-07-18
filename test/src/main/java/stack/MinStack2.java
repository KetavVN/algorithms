package stack;

import java.util.Stack;

/*
https://leetcode.com/problems/min-stack/
Leetcode day 10

Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

    push(x) -- Push element x onto stack.
    pop() -- Removes the element on top of the stack.
    top() -- Get the top element.
    getMin() -- Retrieve the minimum element in the stack.

 

Example:

MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin();   --> Returns -3.
minStack.pop();
minStack.top();      --> Returns 0.
minStack.getMin();   --> Returns -2.

Success Details:
5ms 68.93% faster than others
41.2mb

*/
public class MinStack2 {

    private Stack<Integer> stack = null;
    private Stack<Integer> minStack = null;
    
    /** initialize your data structure here. */
    public MinStack2() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }
    
    public void push(int x) {
        stack.push(x);
        int y = !minStack.isEmpty() && minStack.peek()<x ? minStack.peek() : x; 
        minStack.push(y);
    }
    
    public void pop() {
        if(!stack.isEmpty()) {
            stack.pop();
            minStack.pop();
        }
    }
    
    public int top() {
        return !stack.isEmpty() ? stack.peek() : 0;
    }
    
    public int getMin() {
        return !minStack.isEmpty() ? minStack.peek() : 0;
    }

}
