package string;

import java.util.HashMap;
import java.util.Map;

/**

https://leetcode.com/problems/isomorphic-strings/submissions/

Given two strings s and t, determine if they are isomorphic. Two strings s and t are isomorphic if the characters in s can be replaced to get t.
All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character, but a character may map to itself.

Example 1:
Input: s = "egg", t = "add"
Output: true

Example 2:
Input: s = "foo", t = "bar"
Output: false

Example 3:
Input: s = "paper", t = "title"
Output: true

Constraints:
    1 <= s.length <= 5 * 104
    t.length == s.length
    s and t consist of any valid ascii character.
 
 * @author ketav
 *
 */
public class IsomorphicString {

	/**
	 * 21ms 25.84% faster than other solutions
	 * 42.9mb 66.85% less memory than other solutions
	 */
	public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> map = new HashMap<>();
        Map<Character, Character> rev = new HashMap<>();
        for(int i=0; i<s.length(); i++) {
            map.putIfAbsent(s.charAt(i), t.charAt(i));
            rev.putIfAbsent(t.charAt(i), s.charAt(i));
            if((map.get(s.charAt(i)) != t.charAt(i)) 
               || (rev.get(t.charAt(i)) != s.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
