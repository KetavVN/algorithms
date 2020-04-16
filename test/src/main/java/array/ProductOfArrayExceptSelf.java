package array;

/*
https://leetcode.com/problems/product-of-array-except-self/
LeetCode challenge day 15

Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

Example:

Input:  [1,2,3,4]
Output: [24,12,8,6]

Constraint: It's guaranteed that the product of the elements of any prefix or suffix of the array (including the whole array) fits in a 32 bit integer.

Note: Please solve it without division and in O(n).

Follow up:
Could you solve it with constant space complexity? (The output array does not count as extra space for the purpose of space complexity analysis.)

Success Details:
2ms 53.5mb faster than 19.31% submissions
*/
public class ProductOfArrayExceptSelf {

	public int[] productExceptSelf(int[] nums) {
        if(nums == null || nums.length == 0) {
            return new int[0];
        }
        int [] res = new int[nums.length];
        updateMultiplicationArray(nums, res, 0, 1);
        return res;
    }
    
    private int updateMultiplicationArray(int [] arr, int[] res, int ind, int mul) {
        if(ind < arr.length) {
            int ret = updateMultiplicationArray(arr, res, ind+1, mul*arr[ind]);
            res[ind] = mul*ret;
            return arr[ind]*ret;
        }
        return 1;
    }
}
