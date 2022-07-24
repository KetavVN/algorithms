package array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
https://leetcode.com/problems/two-sum/

Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
You may assume that each input would have exactly one solution, and you may not use the same element twice.
You can return the answer in any order.

Example 1:
Input: nums = [2,7,11,15], target = 9
Output: [0,1]
Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].

Example 2:
Input: nums = [3,2,4], target = 6
Output: [1,2]

Example 3:
Input: nums = [3,3], target = 6
Output: [0,1]

Constraints:
    2 <= nums.length <= 104
    -109 <= nums[i] <= 109
    -109 <= target <= 109
    Only one valid answer exists.

Follow-up: Can you come up with an algorithm that is less than O(n2) time complexity?
 
 * @author ketav
 *
 */
public class TwoSum {

	/**
	 * 15ms - faster than 44.87% of Java online submissions for Two Sum.
	 * 49.1 MB, less than 5.95% of Java online submissions for Two Sum.
	 */
	public int[] twoSum(int[] nums, int target) {
        Map<Integer, List<Integer>> valIndMap = new HashMap<>();
        for(int i=0; i<nums.length; i++) {
            valIndMap.putIfAbsent(nums[i], new ArrayList<>());
            valIndMap.get(nums[i]).add(i);
        }
        for(int i=0; i<nums.length; i++) {
            if(valIndMap.containsKey(target-nums[i])) {
                for(Integer ind : valIndMap.get(target-nums[i])) {
                    if(i != ind) {
                        return new int [] {i, ind};
                    }
                }
            }
        }
        return null;
    }
}
