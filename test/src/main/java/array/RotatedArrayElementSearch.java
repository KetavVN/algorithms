package array;

/**
 * https://leetcode.com/problems/search-in-rotated-sorted-array/
 * 
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
 * 
 * You are given a target value to search. If found in the array return its index, otherwise return -1.
 * You may assume no duplicate exists in the array.
 * Your algorithm's runtime complexity must be in the order of O(log n).
 * 
 * Example 1:
 * Input: nums = [4,5,6,7,0,1,2], target = 0
 * Output: 4
 * 
 * Example 2:
 * Input: nums = [4,5,6,7,0,1,2], target = 3
 * Output: -1
 * 
 * Success Details 
 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Search in Rotated Sorted Array.
 * Memory Usage: 37.8 MB, less than 47.17% of Java online submissions for Search in Rotated Sorted Array.
 * 
 * @author ketav
 */
public class RotatedArrayElementSearch {

	public static int search(int[] nums, int target) {
		if(nums == null || nums.length == 0)
			return -1;
		int index = findMinIndex(nums, 0, nums.length-1);
		//System.out.println(String.format("midIndex=%d", index));
		if(nums[index] == target) {
			return index;
		} else if (nums[0] <= target && index > 0 && target <= nums[index-1]) {
			return findTarget(nums, 0, index-1, target);
		} else if (nums[index] <= target && target <= nums[nums.length-1]) {
			return findTarget(nums, index, nums.length-1, target);
		}
		return -1;
	}

	private static int findTarget(int[] nums, int start, int end, int target) {
		//System.out.println(String.format("start=%d end=%d", start, end));
		if(start < end) {
			int mid = (start+end)/2;
			if(nums[mid] == target)
				return mid;
			else if(nums[mid] > target)
				return  findTarget(nums, start, mid, target);
			else
				return findTarget(nums, mid+1, end, target);
		}
		return nums[start] == target ? start : -1;
	}

	private static int findMinIndex(int[] nums, int start, int end) {
		if(start<end) {
			int mid = (start+end) / 2;	//len=5 :: (4-0)/2=2 | len=6 :: (5-0)/2 = 2
			if(nums[mid] > nums[end]) {
				return findMinIndex(nums, mid+1, end);
			} else /* if (nums[mid] < nums[start]) */ {
				return findMinIndex(nums, start, mid);
			}
		}
		return start;
	}

	public static void main(String ... args) {
		System.out.println(search(new int [] {3, 4, 5, 0, 1, 2}, 5));
	}

}
