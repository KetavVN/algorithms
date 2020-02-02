package array;

/**
 * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/submissions/
 * 
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * (i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
 * 
 * Find the minimum element.
 * 
 * You may assume no duplicate exists in the array.
 * 
 * Example 1:
 * Input: [3,4,5,1,2] 
 * Output: 1
 * 
 * Success Details 
 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Find Minimum in Rotated Sorted Array.
 * Memory Usage: 37.3 MB, less than 100.00% of Java online submissions for Find Minimum in Rotated Sorted Array.

 * @author ketav
 *
 */
public class RotatedArrayMinElement {
	
	public int findMin(int[] nums) {
		if(nums == null || nums.length == 0)
			return -1;
		return findMinValue(nums, 0, nums.length-1);
	}

	public int findMinValue(int [] nums, int start, int end) {
		if (start<end) {
			int mid = (start+end)/2;
			if(nums[mid] > nums[end])
				return findMinValue(nums, mid+1, end);
			else
				return findMinValue(nums, start, mid);
		}
		return nums[start];
	}
}
