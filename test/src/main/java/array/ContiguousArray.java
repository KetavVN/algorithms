package array;

import java.util.*;

/*
https://leetcode.com/problems/contiguous-array/
Leetcode challenge day 13

Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.

Example 1:

Input: [0,1]
Output: 2
Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.

Example 2:

Input: [0,1,0]
Output: 2
Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.

Note: The length of the given binary array will not exceed 50,000. 

Success Details:
20ms 78.76% faster than otehr solutions

*/
public class ContiguousArray {

	public int findMaxLength(int[] nums) {
        
        if(nums == null || nums.length == 0) return 0;
        
        Map<Integer, Integer> valIndMap = new HashMap<>();
        valIndMap.put(0, -1);
        
        int cnt = 0, maxLen = 0, diff = 0;
        
        for(int i=0; i < nums.length; i++) {
            if(nums[i] == 0)
                cnt -= 1;
            else
                cnt += 1;
            
            if(valIndMap.containsKey(cnt))
                maxLen = maxLen < (diff=(i-valIndMap.get(cnt))) ? diff : maxLen;
            else
                valIndMap.put(cnt, i);
            
        }
        
        return maxLen;
    }

}
