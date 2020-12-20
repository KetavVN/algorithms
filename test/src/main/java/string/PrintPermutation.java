
package string;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PrintPermutation {

	public void printPermutation(String s, String b) {
		if(s == null || s.isEmpty() || b == null || b.isEmpty()) {
			return;
		}

		Map<Character, Integer> actual = getActualMap(s);
		Map<Character, Integer> found = new HashMap<>();
		
		Set<String> ans = new HashSet<>();
		
		int beginInd = 0;
		for(int i=0; i<b.length(); i++) {
			char c = b.charAt(i);
			if(!found.containsKey(c)) {
				found.put(c, 0);
			}
			if(actual.containsKey(c) && actual.get(c) > found.get(c)) {
				found.put(c, found.get(c)+1);
			} else {
				if( (i-beginInd) == s.length() ) {
					ans.add(b.substring(beginInd, beginInd+s.length()));
				}
				found.clear();
				i = beginInd;
				beginInd++;
			}
		}

		if(isValidPermutation(s, actual, found)) {
			ans.add(b.substring(beginInd, beginInd+s.length()));
		}

		for(String str : ans) {
			System.out.println(str);
		}

	}

	private boolean isValidPermutation(String s, Map<Character, Integer> actual, Map<Character, Integer> found) {
		boolean bool = true;
		for(Character c : s.toCharArray()) {
			if(!found.containsKey(c) || found.get(c) != actual.get(c)) {
				bool = false;
			}
		}
		return bool;
	}

	private Map<Character, Integer> getActualMap(String s) {
		Map<Character, Integer> actual = new HashMap<>();
		for(char c : s.toCharArray()) {
			if(!actual.containsKey(c)) {
				actual.put(c, 0);
			}
			actual.put(c, actual.get(c)+1);
		}
		return actual;
	}

	public static void main(String [] args) {
		new PrintPermutation().printPermutation("abbc", "caabbcbba");
	}
	
}
