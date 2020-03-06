package array;

public class RotatedArraySearch {

	public static void main(String ... args) {
		System.out.println(new RotatedArraySearch().search(new int [] {2,2,2,0,0,1}, 0));
	}
	
	public boolean search(int[] nums, int target) {

		if(nums == null || nums.length == 0) {
			return false;
		}

		int pivot = findPivot(nums, 0, nums.length-1);

		System.out.println("pivot="+pivot);

		if(target == nums[pivot]) {
			return true;
		}
		
		if(pivot>0 && pivot<nums.length) {
			if (target >= nums[0] && target <= nums[pivot-1]) {
				return findTarget(nums, 0, pivot-1, target);
			} else {
				return findTarget(nums, pivot+1, nums.length-1, target);
			}
		}
		
		return findTarget(nums, 0, nums.length-1, target);
		
	}

	private int findPivot(int [] nums, int start, int end) {
		
		if(start == end-1) {
			return nums[start] <= nums[end] ? start : end;
		}
		
		if(start < end) {
			int mid = (start + end) / 2;
			//System.out.println("mid="+mid);
			boolean flag1=false, flag2=false; 
			if(nums[start] == nums[mid]) {
				flag1=true;
				//linear from start to mid..return if lower item found
				for(int i=start+1; i<=mid; i++) {
					//System.out.println(String.format("1. nums[%d] = %d, nums[%d] = %d", i-1, nums[i-1], i, nums[i]));
					if(nums[i-1] > nums[i]) {
						return i;
					}
				}
			}
			if(nums[mid] == nums[end]) {
				flag2=true;
				//linear from mid to end.. return if lower item found
				for(int i=mid+1; i<=end; i++) {
					//System.out.println(String.format("2. nums[%d] = %d, nums[%d] = %d", i-1, nums[i-1], i, nums[i]));
					if(nums[i-1] > nums[i]) {
						return i;
					}
				}
			}
			
			if(flag1 && flag2) {
				return start;
			}
			
			if (nums[start] > nums[mid]) {
				//go left
				//System.out.println(String.format("3. nums[%d] = %d, nums[%d] = %d", start, nums[start], end, nums[end]));
				return findPivot(nums, start, mid);
			} else {
				//go right
				//System.out.println(String.format("4. nums[%d] = %d, nums[%d] = %d", start, nums[start], end, nums[end]));
				return findPivot(nums, mid, end);
			}
		}
		
		return start;
		
	}

	private boolean findTarget(int [] nums, int start, int end, int target) {
		
		if(start<end) {
			int mid = (start + end) / 2;
			if(nums[mid] == target) {
				return true;
			} else if (target > nums[mid]) {
				//go right
				return findTarget(nums, mid+1, end, target);
			} else {
				//go left
				return findTarget(nums, start, mid-1, target);
			}
		}
		
		return start>=0 && start<nums.length && nums[start] == target;
		
	}
	
}
