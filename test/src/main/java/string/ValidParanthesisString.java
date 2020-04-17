package string;

/*
https://leetcode.com/problems/valid-parenthesis-string
Medium
Leetcode challenge day 16

 Given a string containing only three types of characters: '(', ')' and '*', 
 write a function to check whether this string is valid. We define the validity of a string by these rules:

    Any left parenthesis '(' must have a corresponding right parenthesis ')'.
    Any right parenthesis ')' must have a corresponding left parenthesis '('.
    Left parenthesis '(' must go before the corresponding right parenthesis ')'.
    '*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string.
    An empty string is also valid.

Example 1:

Input: "()"
Output: True

Example 2:

Input: "(*)"
Output: True

Example 3:

Input: "(*))"
Output: True

Note:
    The string size will be in the range [1, 100].

Success Details:
344ms 15.59% faster than others

*/
public class ValidParanthesisString {
	
	public boolean checkValidString(String s) {
        return validateStr(s.toCharArray(), 0, 0);
    }
    
    private boolean validateStr(char [] s, int ind, int count) {
        if(count<0) {
            return false;
        }
        if(ind == s.length) {
            return count == 0;
        } else {
            if(s[ind] != '*') {
                count = s[ind] == '(' ? count+1 : s[ind] == ')' ? count-1 : count;
                //if(count < 0) return false;
                return validateStr(s, ind+1, count);
            } else {
                //3options
                return = validateStr(s, ind+1, count+1)
                        || validateStr(s, ind+1, count-1);
                        || validateStr(s, ind+1, count);
            }
        }
    }
	
}
