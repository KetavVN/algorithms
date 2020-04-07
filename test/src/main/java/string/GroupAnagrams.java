package string;

import java.util.*;

/*
https://leetcode.com/problems/group-anagrams
leetcode challenge day 6

Given an array of strings, group anagrams together.

Example:

Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
Output:
[
  ["ate","eat","tea"],
  ["nat","tan"],
  ["bat"]
]

Note:

    All inputs will be in lowercase.
    The order of your output does not matter.

Success Details:
Beats 29% of solutions
Runtime: 15 ms
Memory Usage: N/A

*/
public class GroupAnagrams {

    public List<List<String>> groupAnagrams(String[] strs) {
        
        Map<String, List<String>> map = new HashMap<>();
        
        for(String str : strs) {
            char [] arr = str.toCharArray();
            Arrays.sort(arr);
            String str1 = new String(arr);
            if(!map.containsKey(str1)) {
                map.put(str1, new ArrayList<String>());
            }
            map.get(str1).add(str);
        }
        
        return new ArrayList<List<String>>(map.values());
    }

}
