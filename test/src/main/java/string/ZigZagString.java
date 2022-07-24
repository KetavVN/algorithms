package string;

import java.util.HashMap;
import java.util.Map;

/**

https://leetcode.com/problems/zigzag-conversion/

Write the code that will take a string and make this conversion given a number of rows:

string convert(string s, int numRows);

Example 1:
Input: s = "PAYPALISHIRING", numRows = 3
Output: "PAHNAPLSIIGYIR"

Example 2:
Input: s = "PAYPALISHIRING", numRows = 4
Output: "PINALSIGYAHRPI"
Explanation:
P     I    N
A   L S  I G
Y A   H R
P     I

Example 3:

Input: s = "A", numRows = 1
Output: "A"

Constraints:
    1 <= s.length <= 1000
    s consists of English letters (lower-case and upper-case), ',' and '.'.
    1 <= numRows <= 1000

 * @author ketav
 *
 */
public class ZigZagString {

	/**
	 * 16ms faster than 51.19% other solutions.
	 * 52mb less than 36.71% other solutions.
	 */
	public String convert2(String s, int numRows) {
        if(numRows < 2) return s;
        Map<Integer, StringBuilder> map = prepareRowMap(numRows);
        boolean directionFlip = false;
        int index = 0;
        for(int i=0; i < s.length(); i++) {
            //System.out.print(String.format("index=%d, i=%d directionFlip=%s map=", index, i, directionFlip));
            map.get(index).append(s.charAt(i));
            //System.out.println(map);
            directionFlip = ((index+1) % (numRows) == 0) ? !directionFlip : directionFlip;
            if(!directionFlip) index++;
            else if(directionFlip && index-1>=0) index--;
            else {directionFlip = !directionFlip; index++;}
        }
        return getResultStringFromRows(numRows, map).toString();
    }

	/**
	 * 9ms faster than 74.35% of solutions
	 * 42.7mb less than 92.21% on solutions 
	 */
	public String convert3(String s, int numRows) {
		if(numRows < 2) return s;
		Map<Integer, StringBuilder> map = prepareRowMap(numRows);
		int direction = 0;
		int index = 0;
		for(int i=0; i < s.length(); i++) {
			//System.out.print(String.format("index=%d, i=%d directionFlip=%s map=", index, i, direction));
			map.get(index).append(s.charAt(i));
			//System.out.println(map);
			if(direction==0) {
				if(index+1 < numRows)
					index++;
				else {
					direction=1;
					index--;
				}
			} else if(direction==1) {
				if(index-1 >= 0)
					index--;
				else {
					direction=0;
					index++;
				}
			}
		}
		return getResultStringFromRows(numRows, map).toString();
	}

	/**
	 * 16ms faster than 51.19% other solutions
	 * 52.1mb less than 36.71% other solutions
	 */
	public String convert(String s, int numRows) {
		if(numRows < 2) return s;
		Map<Integer, StringBuilder> map = prepareRowMap(numRows);
		for(int i=0; i < s.length();) {
			i = traverseDown(s, map, 0, numRows, i);
			i = traverseUp(s, map, numRows-2, 1, i);
		}
		return getResultStringFromRows(numRows, map).toString();
	}

	public int traverseDown(String s, Map<Integer, StringBuilder> map, int start, int end, int cur) {
		while(start < end && cur < s.length()) {
			map.get(start).append(s.charAt(cur));
			start++;
			cur++;
		}
		return cur;
	}

	public int traverseUp(String s, Map<Integer, StringBuilder> map, int start, int end, int cur) {
		while(start >= end  && cur < s.length()) {
			map.get(start).append(s.charAt(cur));
			start--;
			cur++;
		}
		return cur;
	}

	private Map<Integer, StringBuilder> prepareRowMap(int numRows) {
		Map<Integer, StringBuilder> map = new HashMap<>();
		for(int i=0; i < numRows; i++)
			map.put(i, new StringBuilder());
		return map;
	}

	private StringBuilder getResultStringFromRows(int numRows, Map<Integer, StringBuilder> map) {
		StringBuilder finalStrBuilder = new StringBuilder();
		for(int i=0; i < numRows; i++)
			finalStrBuilder.append(map.get(i));
		return finalStrBuilder;
	}

	public static void main(String [] args) {
		ZigZagString obj = new ZigZagString();
		String ans, expected;
		System.out.println(String.format("actual=%s expected=%s, pass=%s", 
				(ans=obj.convert("PAYPALISHIRING",3)), (expected="PAHNAPLSIIGYIR"), ans.equals(expected)));
		System.out.println(String.format("actual=%s expected=%s pass=%s", 
				(ans=obj.convert("PAYPALISHIRING",4)), (expected="PINALSIGYAHRPI"), ans.equals(expected)));
	}

}
