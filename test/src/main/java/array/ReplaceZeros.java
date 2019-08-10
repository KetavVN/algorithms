package array;

//https://leetcode.com/problems/move-zeroes/
public class ReplaceZeros {

    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Move Zeroes.
    //Memory Usage: 37.7 MB, less than 100.00% of Java online submissions for Move Zeroes.
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
