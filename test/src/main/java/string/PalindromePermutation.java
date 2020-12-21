package string;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string write a program to check if it is a permutation of Palindrome string
 * Cracking the coding interview Ch 1 Problem 4
 * 
 * @author Ketav
 *
 */
public class PalindromePermutation {

	/**
	 * logic:
	 * 	even chars = each chars appear even number of times
	 *  odd chars = one char appears odd number of times, all other characters appears even number of times
	 *  
	 *  1. prepare count of each character - omiting spaces
	 *  2. loop through map 
	 *  	2.1 count total length
	 *  	2.2 count of characters whie appear odd number of times - cntOdd
	 *  3. if length is odd => cntOdd == 1
	 *  	else cntOdd == 0
	 */
	public boolean isPalindromePermutation(String one) {
		boolean palindrome = false;
		if(one == null || one.isEmpty()) {
			return palindrome;
		}
		Map<Character, Integer> cntMap = prepareMap(one);
		return countChars(cntMap);
	}

	private boolean countChars(Map<Character, Integer> cntMap) {
		int cnt = 0;
		int len = 0;
		for(Map.Entry<Character, Integer> e : cntMap.entrySet()) {
			len += e.getValue();
			if(e.getValue() % 2 == 1) {
				cnt++;
			}
		}
		return len % 2 == 1 ? cnt == 1 : cnt == 0;
	}

	private Map<Character, Integer> prepareMap(String one) {
		Map<Character, Integer> cntMap = new HashMap<>();
		for(Character c : one.toCharArray()) {
			if(c != ' ') {
				if(!cntMap.containsKey(c)) {
					cntMap.put(c, 0);
				}
				cntMap.put(c, cntMap.get(c)+1);
			}
		}
		return cntMap;
	}

	public static void main(String [] args) {
		PalindromePermutation obj = new PalindromePermutation();
		System.out.println(obj.isPalindromePermutation("tact coa"));
		System.out.println(obj.isPalindromePermutation("ccivi"));
		System.out.println(obj.isPalindromePermutation("ketav"));
	}
}
