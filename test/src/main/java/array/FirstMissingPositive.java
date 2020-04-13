package array;

import java.util.*;

/*
https://leetcode.com/problems/first-missing-positive/

Difficulty level: Hard

Given an unsorted integer array, find the smallest missing positive integer.

Example 1:
Input: [1,2,0]
Output: 3

Example 2:
Input: [3,4,-1,1]
Output: 2

Example 3:
Input: [7,8,9,11,12]
Output: 1

Note:
Your algorithm should run in O(n) time and uses constant extra space.

Success Details
Runtime: 1 ms, faster than 31.14% of Java online submissions for First Missing Positive.
Memory Usage: 37.9 MB, less than 6.85% of Java online submissions for First Missing Positive.

*/
public class FirstMissingPositive {

	public int firstMissingPositive(int[] nums) {
	
        if(nums == null || nums.length == 0)
            return 1;
     
        Set<Integer> set = new HashSet<>();
        for(int i=0; i<nums.length; i++) {
            while(nums[i]>0 && nums[i] != i && nums[i]<nums.length) {
                swap(nums, i);
            }
            if(nums[i] >= nums.length) {
                set.add(nums[i]);
                nums[i] = 0;
            }
            //System.out.print(nums[i]+" ");
        }
        
        
        
        int missingNum = 0;
        for(int i=1; i<nums.length && missingNum == 0 ; i++)
            if(nums[i] != i)
                missingNum = i;
        
        if(missingNum == 0) {
            missingNum = nums.length; 
            while(set.contains(missingNum)) missingNum++;
        }
        
        return missingNum;
        
    }
    
    private void swap(int [] nums, int i) {
        
        int temp1 = nums[i];
        int temp2 = nums[nums[i]];
        
        //forward or backward index substitution
        nums[nums[i]] = temp1;
        
        //current index substitution
        nums[i] = temp1!=temp2 ? temp2 : 0;
        
    }
}
