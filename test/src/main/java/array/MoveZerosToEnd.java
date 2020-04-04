package array;

/*
https://leetcode.com/problems/move-zeroes

Given an array nums, write a function to move all 0's to the end of it while maintaining the 
relative order of the non-zero elements.

Example:

Input: [0,1,0,3,12]
Output: [1,3,12,0,0]

Note:

    You must do this in-place without making a copy of the array.
    Minimize the total number of operations.

LeetCode day 4

Success Details:
Beats 12.69% of solutions
Runtime: 4 ms
Memory Usage: 40.6 MB

*/
public class MoveZerosToEnd {
    public void moveZeroes(int[] nums) {
        if(nums == null || nums.length == 0) return;
        
        int end = nums.length;
        
        for(int i=nums.length-1; i>=0; i--) {
            if(nums[i] == 0) {
                for(int j=i+1; j<end; j++) {
                    nums[j-1] = nums[j];
                }
                nums[end-1] = 0;
                end--;
            }
        }
        
    }
}
