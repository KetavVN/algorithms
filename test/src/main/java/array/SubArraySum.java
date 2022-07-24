package array;

/**
 * https://leetcode.com/problems/minimum-size-subarray-sum/submissions/
 * 
 * Given an array of n positive integers and a positive integer s, find the minimal length of a contiguous subarray of which the sum >= s. If there isn't one, return 0 instead.
 * 
 * Example: 
 * Input: s = 7, nums = [2,3,1,2,4,3]
 * Output: 2
 * 
 * Explanation: the subarray [4,3] has the minimal length under the problem constraint.
 * 
 * Follow up:
 * If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n). 
 * 
 * Success Details 
 * Runtime: 1 ms, faster than 99.93% of Java online submissions for Minimum Size Subarray Sum.
 * Memory Usage: 42.4 MB, less than 5.71% of Java online submissions for Minimum Size Subarray Sum.
 * 
 * @author Ketav
 */
public class SubArraySum {

	public int minSubArrayLen(int s, int[] nums) {
		if(nums == null || nums.length == 0)
			return 0;
		int start=0;
		int sum = 0;
		int minWindow=Integer.MAX_VALUE;
		int i=0;
		for(; i < nums.length; ) {
			if(sum < s) {
				sum+=nums[i];
				i++;
			}
			if(sum >= s) {
				if(minWindow > (i-start)) {
					minWindow = i-start;
				}
				sum -= nums[start];
				start++;
			}
		}
		while(sum >= s) {
			if(minWindow > (i-start)) {
				minWindow = i-start;
			}
			sum -= nums[start];
			start++;
		}
		return minWindow > nums.length ? 0 : minWindow;
	}

	public static void main (String ... args) {
		System.out.println(new SubArraySum().minSubArrayLen(7, new int [] {1,1,1,2,2,3,4}));
		System.out.println(new SubArraySum().minSubArrayLen(4, new int [] {1,1,1,2,2,3,4}));
		System.out.println(new SubArraySum().minSubArrayLen(19, new int [] {1,1,1,2,2,3,4}));
		System.out.println(new SubArraySum().minSubArrayLen(19, new int [] {}));
		System.out.println(new SubArraySum().minSubArrayLen(19, null));
	}

}
