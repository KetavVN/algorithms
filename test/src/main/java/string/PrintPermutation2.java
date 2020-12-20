
package string;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * using Sliding window
 * @author ketav
 */
public class PrintPermutation2 {

	public void printPermutation(String s, String b) {
		if(s == null || s.isEmpty() || b == null || b.isEmpty()) {
			return;
		}

		Map<Character, Integer> actual = getActualMap(s);
		Map<Character, Integer> found = new HashMap<>();

		Set<String> ans = new HashSet<>();

		for(int i=0; i<s.length()-1; i++) {
			char c = b.charAt(i);
			if(actual.containsKey(c)) {
				if(!found.containsKey(c)) {
					found.put(c, 0);
				}
				found.put(c, found.get(c)+1);
			}
		}

		int i = s.length()-1;
		do {
			i++;
			char c = b.charAt(i);
			if(actual.containsKey(c)) {
				if(!found.containsKey(c)) {
					found.put(c, 0);
				}
				found.put(c, found.get(c)+1);
			}
			if(isValidPermutation(actual, found)) {
				ans.add(b.substring(i-s.length(), i));
			}
			char c2 = b.charAt(i - s.length());
			if(found.containsKey(c2)) {
				found.put(c2, found.get(c2)-1);
				if(found.get(c2)==0) {
					found.remove(c2);
				}
			}
			//i++;
		} while(i < b.length());
		
		
	}

	private boolean isValidPermutation(Map<Character, Integer> actual, Map<Character, Integer> found) {
		if(found.size() == actual.size()) {
			for(Map.Entry<Character, Integer> entry : actual.entrySet()) {
				if(!found.containsKey(entry.getKey()) || found.get(entry.getKey()) != entry.getValue()) {
					return false;
				}
			}
		}
		return true;
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
		new PrintPermutation().printPermutation("abbc", "caabbcabcab");
	}
	
}
