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

*/
public class MoveZerosToEnd {
    
    /*
        Success Details:
        Beats 12.69% of solutions
        Runtime: 4 ms
        Memory Usage: 40.6 MB
    */
    public void moveZeroes1(int[] nums) {
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
    
    /*
        Success Details
        Runtime: 0 ms, faster than 100.00% of Java online submissions for Move Zeroes.
        Memory Usage: 39.9 MB, less than 6.29% of Java online submissions for Move Zeroes.
    */
    public void moveZeroes2(int[] nums) {
        
        if(nums == null || nums.length == 0) return;
        
        int back = -1;
        
        for(int i=0; i<nums.length; i++) {
            
            //find first 0
            if(nums[i] == 0 && back < 0)
                back = i;
            
            //after first 0 is found
            if(back >=0) {
                
                //move pointer forward until 0s round
                while(i+1<nums.length && nums[i+1]==0) i++;
                
                //when non zero is found swap with back index which points to earliest 0
                //and move forward earliest 0 index by 1
                while(i+1<nums.length && nums[i+1] != 0) {
                    nums[back++] = nums[i+1];
                    nums[++i]=0;
                }
            }
            
        }
        
    }
    
    /*
        most elegant solutions!
        Beats 100% of solutions
        Runtime: 0 ms
        Memory Usage: 37.7 MB
    */
    public void moveZeroes(int[] nums) {
        if(nums == null || nums.length == 0) {
            return;
        }
        int ind = 0;
        for(int i=0; i<nums.length; i++) {
            if(nums[i] != 0) {
                nums[ind++] = nums[i];
            }
        }
        while(ind < nums.length) {
            nums[ind++] = 0;
        }
    }
    
}
