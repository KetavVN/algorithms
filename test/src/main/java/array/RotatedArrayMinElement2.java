package array;

/**
 * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/submissions/
 * 
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * (i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
 * 
 * Find the minimum element.
 * 
 * The array may contain duplicates.
 * 
 * Example 1:
 * Input: [3,4,5,1,2] 
 * Output: 1
 * 
 * Success Details 
 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Find Minimum in Rotated Sorted Array II.
 * Memory Usage: 39.4 MB, less than 31.25% of Java online submissions for Find Minimum in Rotated Sorted Array II.
 * 
 * @author ketav
 *
 */
public class RotatedArrayMinElement2 {

	public static int findMin(int[] nums) {
		if(nums == null || nums.length == 0)
			return -1;
		return findMinValue(nums, 0, nums.length-1);
	}

	public static int findMinValue(int [] nums, int start, int end) {
		if (start<end) {
			int mid = (start+end)/2;
			int left = mid, right=mid;
			//System.out.println(String.format("start=%d mid=%d end=%d",start, mid, end));
			while(right<nums.length-1 && nums[right+1]==nums[mid]) right++;
			while(left>1 && nums[left-1]==nums[mid]) left--;
			if((nums[mid] > nums[end]) 
				|| (right+1<=end && nums[mid] > nums[right+1])) {
				//System.out.println(String.format("here1: start=%d end=%d",right+1, end));
				return findMinValue(nums, right+1, end);
			} else if ((nums[mid] < nums[start]) 
				|| (left-1>=start && nums[left-1] < nums[mid])) {
				//System.out.println(String.format("here2: start=%d end=%d",start, left));
				return findMinValue(nums, start, left);
			}
		}
		return nums[start] < nums[end] ? nums[start] : nums[end];
	}

	public static void main(String ... args) {
		System.out.println(findMin(new int [] {1,0,1,1,1}));
		System.out.println(findMin(new int [] {1,1,1,0,1}));
	}

}
