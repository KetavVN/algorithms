package dp;

import java.util.List;
import java.util.LinkedList;

public class StringPermutations {

	public List<String> findPermutations(String str) {
		List<String> permutations = new LinkedList<>();
		if(str == null || str.length() == 0) {
			return permutations;
		}
		char [] chars = str.toCharArray();
		findPermutations(chars, 0, new StringBuilder(), permutations);
		return permutations;
	}

	private void findPermutations(char [] chars, int current, 
			StringBuilder sb, List<String> permutations) {
		if(current == chars.length) {
			permutations.add(sb.toString());
			return;
		} else {
			for(int i=current; i<chars.length; i++) {
				StringBuilder sb1 = new StringBuilder(sb);
				sb1 = sb1.append(chars[current]);
				findPermutations(chars, current+1, sb1, permutations);
				shiftChars(chars, current);
			}
		}
	}
	
	private void shiftChars(char [] chars, int current) {
		int i = current;
		char swap = chars[current];
		while((i+1)<chars.length) {
			chars[i] = chars[i+1];
			i++;
		}
		chars[chars.length-1] = swap;
	}
	
	public static void main(String ...args) {
		StringPermutations obj = new StringPermutations();
		String str = "ketav";
		List<String> permutations = obj.findPermutations(str);
		for(String permutation : permutations) {
			System.out.println(permutation);
		}
	}
	
}
