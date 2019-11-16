package array;

//incomplete code - does not handle case like - 1,9,8,2,0 -> 2,0,1,8,9
//https://leetcode.com/problems/next-permutation/
class NextPermutation {

    public void nextPermutation(int[] nums) {
        if(nums == null || nums.length < 2) {
            return;
        }
        int i=nums.length-2;
        for(; i>=0; i--) {
            if(nums[i] < nums[i+1]) {
                swap(nums, i, i+1);
                return;
            }
        }
        if(i < 0) {
            reverse(nums, 0);
        }
    }
    
    public void swap(int [] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    public void reverse(int [] nums, int i) {
        if(i < nums.length-1-i) {
            swap(nums, i, nums.length-1-i);
            reverse(nums, i+1);
        }
    }
}
