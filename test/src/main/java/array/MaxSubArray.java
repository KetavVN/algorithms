package array;

/*
https://leetcode.com/problems/maximum-subarray/

leetcode 30 day challenge day 3

Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest 
sum and return its sum.

Example:

Input: [-2,1,-3,4,-1,2,1,-5,4],
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.

Follow up:

If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, 
which is more subtle.

Execution Details:
100% faster than other solutions
execution time = 0ms
memory usage = 39.3mb

 */
public class MaxSubArray {

	public int maxSubArray(int[] nums) {

		if(nums == null || nums.length == 0) return 0;

		int maxSum = Integer.MIN_VALUE;
		int currentSum = 0;
		int start=0, end=0;

		for(; end<nums.length; end++) {
			currentSum += nums[end];
			maxSum = (currentSum > maxSum) ? currentSum : maxSum;

			while(start < end && (nums[start] < 0 || currentSum < 0) ) {
				currentSum -= nums[start];
				maxSum = (currentSum > maxSum) ? currentSum : maxSum;
				start++;
			}
		}

		while(start < end-1) {
			currentSum -= nums[start];
			maxSum = (currentSum > maxSum) ? currentSum : maxSum;
			start++;
		}

		return maxSum;
	}

}
