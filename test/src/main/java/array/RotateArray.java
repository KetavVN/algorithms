package array;

/**
https://leetcode.com/problems/rotate-array/

Given an array, rotate the array to the right by k steps, where k is non-negative.

Example 1:

Input: nums = [1,2,3,4,5,6,7], k = 3
Output: [5,6,7,1,2,3,4]
Explanation:
rotate 1 steps to the right: [7,1,2,3,4,5,6]
rotate 2 steps to the right: [6,7,1,2,3,4,5]
rotate 3 steps to the right: [5,6,7,1,2,3,4]

Example 2:

Input: nums = [-1,-100,3,99], k = 2
Output: [3,99,-1,-100]
Explanation: 
rotate 1 steps to the right: [99,-1,-100,3]
rotate 2 steps to the right: [3,99,-1,-100]

Constraints:
    1 <= nums.length <= 105
    -231 <= nums[i] <= 231 - 1
    0 <= k <= 105

Follow up:
    Try to come up with as many solutions as you can. There are at least three different ways to solve this problem.
    Could you do it in-place with O(1) extra space?
 
 * @author ketav
 *
 */
public class RotateArray {
	
	/**
	 * 2ms faster than 49.24% other solutions
	 * 64.6mb less than 28.75% other solutions 
	 */
	public void rotate(int[] nums, int k) {
        if(k == nums.length) return;
        int rotate = k > nums.length ? k % nums.length : k;
        int [] temp = new int[rotate];
        for(int i=nums.length-rotate, j=0; i<nums.length && j<rotate; i++, j++) {
            temp[j] = nums[i];
        }
        for(int i=nums.length-1; i>=rotate && i>=0; i--) {
            nums[i] = nums[i-rotate];
        }
        for(int i=0; i<rotate ;i++) {
            nums[i] = temp[i];
        }
    }
}
