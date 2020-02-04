package array;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/minimum-window-substring/
 * 
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
 * 
 * Example:
 * Input: S = "ADOBECODEBANC", T = "ABC"
 * Output: "BANC"
 * 
 * Note:
 * 1. If there is no such window in S that covers all characters in T, return the empty string "".
 * 2. If there is such window, you are guaranteed that there will always be only one unique minimum window in S.
 * 
 * Success Details 
 * Runtime: 300 ms, faster than 5.04% of Java online submissions for Minimum Window Substring.
 * Memory Usage: 42.4 MB, less than 10.64% of Java online submissions for Minimum Window Substring.
 * 
 * @author Ketav
 */
public class SmallestSubString {

	public static void main (String ... args) {
		System.out.println(new SmallestSubString().minWindow("ADOBECODEBANC", "ABC"));
		System.out.println(new SmallestSubString().minWindow("a", "b"));
		System.out.println(new SmallestSubString().minWindow("aaaaaaaaaaaabbbbbcdd", "abcdd"));
	}
	
	public String minWindow(String s, String t) {

		Map<Character, Integer> expectedCount = getExpectedCharCount(t);
		Map<Character, Integer> actualCount = new HashMap<>();

		int i=0, start=0;
		String minStr="";

		for(; i<s.length(); i++) {
			
			if(expectedCount.containsKey(s.charAt(i))) {
				if(!actualCount.containsKey(s.charAt(i))) {
					actualCount.put(s.charAt(i), 0);
				}
				actualCount.put(s.charAt(i), actualCount.get(s.charAt(i)) + 1);
			} else if (actualCount.isEmpty()) {
				start++;
			}
			
			minStr = getMinStr(s, expectedCount, actualCount, i, start, minStr);
			
			while (start<i && canRemoveElement(expectedCount, actualCount, start, s)) {
				removeElement(actualCount, s.charAt(start));
				start++;
				minStr = getMinStr(s, expectedCount, actualCount, i, start, minStr);
			}
			
		}

		//System.out.println(actualCount);

		return minStr;
	}

	/**
	 * if current window contains all characters as expected count, 
	 * and window length is smaller than previous window, new window will be returned.
	 */
	private String getMinStr(String s, Map<Character, Integer> expectedCount, 
			Map<Character, Integer> actualCount,
			int i, int start, String minStr) {
		if(areEquals(expectedCount, actualCount)) {
			String tmp = s.substring(start, i+1);
			minStr = (minStr.equals("") || tmp.length()<=minStr.length()) ? tmp : minStr;
		}
		return minStr;
	}

	/**
	 * element can be removed if actual count of character exceeds expected count
	 * and all elements are present in actual map
	 */
	private boolean canRemoveElement(Map<Character, Integer> expectedCount, 
			Map<Character, Integer> actualCount, int start, String s) {
		Character removeChar = s.charAt(start);
		return !actualCount.containsKey(removeChar) 
				|| actualCount.get(removeChar)>expectedCount.get(removeChar);
	}

	/**
	 * remove element from actual and reduce the count
	 */
	private boolean removeElement(Map<Character, Integer> actual, Character c) {
		boolean removed = false;
		if(actual.containsKey(c)) {
			if(actual.get(c) > 1) {
				actual.put(c, actual.get(c)-1);
			} else {
				actual.remove(c);
			}
			removed = true;
		}
		return removed;
	}

	/**
	 * return true if both expected and actual map are equal
	 */
	private boolean areEquals(Map<Character, Integer> expected, Map<Character, Integer> actual) {
		for(Map.Entry<Character, Integer> entry : expected.entrySet())
			if(!actual.containsKey(entry.getKey()) 
				|| actual.get(entry.getKey())<entry.getValue())
				return false;
		return true;
	}

	/**
	 * create map of expected count
	 */
	private Map<Character, Integer> getExpectedCharCount(String t) {
		Map<Character, Integer> map = new HashMap<>();
		for(int i=0; i<t.length(); i++) {
			if(!map.containsKey(t.charAt(i))) {
				map.put(t.charAt(i), 0);
			}
			map.put(t.charAt(i), map.get(t.charAt(i))+1);
		}
		return map;
	}

}
