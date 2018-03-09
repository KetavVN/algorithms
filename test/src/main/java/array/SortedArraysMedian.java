package array;

//https://leetcode.com/problems/median-of-two-sorted-arrays/description/
public class SortedArraysMedian {

	//using merge sort variation - O(1) space complexity, O((m+n)/2) time complexity
	public double findMedianSortedArrays1(int[] nums1, int[] nums2) {

		int start = (nums1.length + nums2.length - 1) / 2;
		int end = (nums1.length + nums2.length) % 2 == 0 ? start+1 : start;
		int divisor = (nums1.length + nums2.length) % 2 == 0 ? 2 : 1;

		int k=0, i=0, j=0;
		double median = 0;
		int num=0;

		while(i < nums1.length && j < nums2.length && k<=end) {
			if(nums1[i] < nums2[j]) {
				num = nums1[i];
				i++;
			} else {
				num = nums2[j];
				j++;
			}
			if(k >= start && k <= end) {
				median += num;
			}
			k++;
		}

		median += traverse(nums1, i, k, start, end);
		median += traverse(nums2, j, k, start, end);

		return median/divisor;
	}
	
	private double traverse(int [] arr, int index, int k, int start, int end) {
		double median = 0;
		while(k<=end && index<arr.length) {
			if(k>=start)
				median+=arr[index];
			index++;
			k++;
		}
		return median;
	}
	
	/**
	 * using partitioning logic
	 *  divide arr1 and arr2 into sub arrays -> arr1[arr11, arr12] , arr2[arr21 arr22] 
	 *  such that following is true:
	 *  1. arr11.len + arr21.len = arr12.len + arr22.len
	 *  2. subArr1[last] <= subArr2[first]
	 * O(1) space complexity
	 */
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		
		if(nums1 == null || nums1.length == 0) {
			return findMedian(nums2);
		} else if (nums2 == null || nums2.length == 0) {
			return findMedian(nums1);
		}
		
		int len = nums1.length + nums2.length;
		
		if(len % 2 == 0) {
			if(nums1[nums1.length-1] <= nums2[0]) {
				return (double)(nums1[nums1.length-1] + nums2[0] ) /2;
			} else if (nums2[nums2.length-1] <= nums1[0]) {
				return (double) (nums2[nums2.length-1] + nums1[0] ) /2;
			} else {
				double median = findEqualSidesMedian(nums1, nums2);
				if(median == 0f) {
					median = findEqualSidesMedian(nums2, nums1);
				}
				return median;
			}
		} else {
			double median = findSides(nums1, nums2);
			if(median==0f) {
				median = findSides(nums2, nums1);
			}
			return median;
		}
	}
	
	//O(n*log(m)) avg time complexity
	public double findEqualSidesMedian(int [] arr1, int [] arr2) {
		
		double median = 0;
		int j = 0, len = arr1.length + arr2.length;
		
		for(int i=0; i<arr1.length; i++) {
			j = nearestLower(arr2, arr1[i], 0, arr2.length-1);
			if(j>=0) {
				if((i+j+2) == (len-i-j-2)) {
					median = arr1[i];
					if(i+1<arr1.length) {
						if(j+1<arr2.length) {
							if(arr1[i+1] <= arr2[j+1]) {
								median += arr1[i+1];
							} else {
								median += arr2[j+1];
							}
						} else {
							median += arr1[i+1];
						}
					} else if(j+1<arr2.length) {
						median += arr2[j+1];
					}
					return median/2;
				}
			}
		}
		return median;
	}
	
	//O(n*log(m)) avg time complexity
	public double findSides(int [] arr1, int [] arr2) {
		
		double median = 0;
		int j = 0, len = arr1.length + arr2.length;
		
		for(int i=0; i<arr1.length; i++) {
			j = nearestLower(arr2, arr1[i], 0, arr2.length-1);
			if(j>=0) {
				if((i+j+2) == (len-i-j-2+1)) {
					return arr1[i];
				} else if((i+j+2+1) == (len-i-j-2)) {
					if(i+1<arr1.length) {
						if(j+1<arr2.length) {
							if(arr1[i+1] <= arr2[j+1]) {
								return arr1[i+1];
							} else {
								return arr2[j+1];
							}
						} else {
							return arr1[i+1];
						}
					} else if(j+1<arr2.length) {
						return arr2[j+1];
					}
				}
			}
		}
		
		return median;
	}
	
	//O(log(m)) avg time complexity
	private int nearestLower(int [] arr, int target, int start, int end) {
		if(start<end) {
			if(start+1==end) {
				return arr[start] > target ? -1 : arr[end] > target ? start : end;
			}
			int mid = start + (end-start)/2;
			if(arr[mid] == target) {
				while(mid+1<arr.length && arr[mid] == arr[mid+1]) {
					mid++;
				}
				return mid;
			} else if(arr[mid] > target) {
				return nearestLower(arr, target, start, mid);
			} else {
				return nearestLower(arr, target, mid, end);
			}
		}
		return arr[start] > target ? -1 : start;
	}
	
	//O(1) time complexity
	private double findMedian(int [] arr) {
		
		if(arr == null || arr.length == 0) {
			return 0f;
		}
		
		double median = 0, divisor = 1;
		if(arr.length %2 == 0) {
			median = arr[arr.length/2-1] + arr[arr.length/2];
			divisor = 2f;
		} else {
			median = arr[arr.length/2];
		}
		
		return median/divisor;
	}
	
	public static void main(String ...args) {

		SortedArraysMedian obj = new SortedArraysMedian();
		boolean flag = true;
		
		int [] arr1 = new int [] {1, 3};
		int [] arr2 = new int [] {2};
		double actual = 0f, expected = 0f;
		
		actual = obj.findMedianSortedArrays(arr1, arr2);
		expected = 2f;
		if(actual != expected) {
			System.out.println(String.format("FAIL: actual=%f, expected=%f", actual, expected));
			flag = false;
		}

		arr1 = new int [] {1, 2};
		arr2 = new int [] {3, 4};

		actual = obj.findMedianSortedArrays(arr1, arr2);
		expected = 2.5f;
		if(actual != expected) {
			System.out.println(String.format("FAIL: actual=%f, expected=%f", actual, expected));
			flag = false;
		}

		arr1 = new int [0];
		arr2 = new int [] {1, 2, 3, 4};

		actual = obj.findMedianSortedArrays(arr1, arr2);
		expected = 2.5f;
		if(actual != expected) {
			System.out.println(String.format("FAIL: actual=%f, expected=%f", actual, expected));
			flag = false;
		}

		if(flag)
			System.out.println("PASS!");
		
	}

}
