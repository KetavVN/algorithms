package array;

import java.util.*;

//https://leetcode.com/problems/median-of-two-sorted-arrays/description/
//This is not optimum solution they problem has asked for!
public class SortedArraysMedian {

	public double findMedianSortedArrays(int[] nums1, int[] nums2) {

		int start = (nums1.length + nums2.length - 1) / 2;
		int end = (nums1.length + nums2.length) % 2 == 0 ? start+1 : start;
		int divisor = (nums1.length + nums2.length) % 2 == 0 ? 2 : 1;

		//System.out.println(String.format("start=%d, end=%d, divisor=%d", start, end, divisor));

		int k=0, i=0, j=0;
		double sum = 0;
		int num=0;

		while(i<nums1.length && j<nums2.length && k<=end) {
			if(nums1[i] < nums2[j]) {
				num = nums1[i];
				i++;
			} else {
				num = nums2[j];
				j++;
			}
			if(k>=start && k<=end) {
				sum+=num;
			}
			k++;
		}

		sum+=traverse(nums1, i, k, start, end);
		sum+=traverse(nums2, j, k, start, end);

		return sum/divisor;
	}
	
	private double traverse(int [] arr, int index, int k, int start, int end) {
		double sum = 0;
		while(k<=end && index<arr.length) {
			if(k>=start)
				sum+=arr[index];
			index++;
			k++;
		}
		return sum;
	}

	public static void main(String ...args) {

		SortedArraysMedian obj = new SortedArraysMedian();

		int [] arr1 = new int [] {1, 3};
		int [] arr2 = new int [] {2};

		double actual = obj.findMedianSortedArrays(arr1, arr2);
		double expected = 2f;
		if(actual != expected) {
			System.out.println(String.format("FAIL: actual=%f, expected=%f", actual, expected));
		}

		arr1 = new int [] {1, 2};
		arr2 = new int [] {3, 4};

		actual = obj.findMedianSortedArrays(arr1, arr2);
		expected = 2.5f;
		if(actual != expected) {
			System.out.println(String.format("FAIL: actual=%f, expected=%f", actual, expected));
		}

		arr1 = new int [0];
		arr2 = new int [] {1, 2, 3, 4};

		actual = obj.findMedianSortedArrays(arr1, arr2);
		expected = 2.5f;
		if(actual != expected) {
			System.out.println(String.format("FAIL: actual=%f, expected=%f", actual, expected));
		}

	}

}
