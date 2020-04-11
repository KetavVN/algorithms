package stack;

/*
https://leetcode.com/problems/backspace-string-compare/
LeetCode day 9

Given two strings S and T, return if they are equal when both are typed into empty text editors. 
# means a backspace character.

Example 1:

Input: S = "ab#c", T = "ad#c"
Output: true
Explanation: Both S and T become "ac".

Example 2:

Input: S = "ab##", T = "c#d#"
Output: true
Explanation: Both S and T become "".

Example 3:

Input: S = "a##c", T = "#a#c"
Output: true
Explanation: Both S and T become "c".

Example 4:

Input: S = "a#c", T = "b"
Output: false
Explanation: S becomes "c" while T becomes "b".

Note:

    1 <= S.length <= 200
    1 <= T.length <= 200
    S and T only contain lowercase letters and '#' characters.

Follow up:

    Can you solve it in O(N) time and O(1) space?

Solution Details:
1ms faster than 67%

*/
public class BackSpaceStringCompare {

    public boolean backspaceCompare(String s, String t) {
        
        if(s == t) return true;
        
        //not required due to problem definition
        /*if((s == null && t != null) || (s != null && t == null)) 
            return false;*/
        
        Stack<Character> stack1 = getStack(s);
        Stack<Character> stack2 = getStack(t);
        
        return stack1.equals(stack2);
        
    }
    
    private Stack getStack(String s) {
        Stack<Character> stack = new Stack<>();
        for(char c : s.toCharArray()) {
            if(c == '#') {
                if(!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.push(c);
            }
        }
        return stack;
    }

}

