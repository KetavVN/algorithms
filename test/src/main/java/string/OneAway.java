package string;

import java.util.ArrayList;
import java.util.List;

/**
 * Check if two string are one update away
 * 	update can be insert / swap(replace) / delete
 * Operation can be performed on one string only
 * Cracking the coding interview Ch 1 Problem 5
 * 
 * @author ketav
 *
 */
public class OneAway {

	public boolean isOneAway(String one, String two) {
		//input validation
		int size = one.length() > two.length() ? one.length() : two.length();
		List<Character> str1 = getList(one);
		List<Character> str2 = getList(two);
		return isOneAway(str1, str2, 0, 0, size, 0);
	}
	
	private boolean isOneAway(List<Character> str1, List<Character> str2, int ind1, int ind2, int size, int updateCnt) {
		boolean equal = false;
		if(updateCnt > 1) {
			return equal;
		}
		while(ind1<str1.size() && ind2<str2.size() 
				&& str1.get(ind1) == str2.get(ind2)) {
			ind1++;
			ind2++;
		}
		
		equal = ind1 == str1.size() && ind2 == str2.size();

		if(!equal) {
			//delete case
			if(str1.size() > str2.size()) {
				equal = isOneAway(str1, str2, ind1+1, ind2, size, updateCnt+1);
			} else {
				equal = isOneAway(str1, str2, ind1, ind2+1, size, updateCnt+1);
			}
		}
		if(!equal) {
			//insert case
			if(str1.size() > str2.size()) {
				equal = isOneAway(str1, str2, ind1, ind2+1, size, updateCnt+1);
			} else {
				equal = isOneAway(str1, str2, ind1+1, ind2, size, updateCnt+1);
			}
		}
		if(!equal) {
			equal = isOneAway(str1, str2, ind1+1, ind2+1, size, updateCnt+1);
		}
		return equal;
	}
	
	private List<Character> getList(String one) {
		List<Character> list = new ArrayList<>();
		for(Character c : one.toCharArray()) {
			list.add(c);
		}
		return list;
	}

	public static void main(String[] args) {
		OneAway obj = new OneAway();
		System.out.println(obj.isOneAway("abcd", "abkd"));
	}

}
