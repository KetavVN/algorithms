package array;
/*
	Heaps are very useful data structure
	Mainly in Priority Queues and/or Thread Management
	min/max heap are also useful in getting K max or min elements from a input stream, finding median
	
	HeapSort is in-place algorithm but not stable
	stable = elements appear in order in final result
	
	-this implementation is incorrect implementation of top-down heap. look at heapSort2 and heapSort3 for different types
*/
public class HeapSort {

	public void sort(int [] arr) {
		if(arr == null || arr.length == 0) {
			return;
		}
		heapSort(arr);
	}
	
	private void heapSort(int [] arr) {
		for(int i=0; i<arr.length; i++) {
			heapify(arr, arr.length-1-i);
			swap(arr, 0, arr.length-1-i);
		}
	}
	
	private void heapify(int [] arr, int endIndex) {
		for(int i=1; i<=endIndex; i++) {	//first left child
			shiftUp(arr, 0, i);
		}
	}

	private void shiftUp(int [] arr, int maxParent, int childIndex) {
		while(childIndex > maxParent) {
			int parent = (childIndex-1)/2;	//0 based index system
			if(arr[parent] < arr[childIndex]) {
				swap(arr, parent, childIndex);
				childIndex = parent;
			} else {
				return;
			}
		}
	}
	
	private void swap(int [] arr, int index1, int index2) {
		int temp = arr[index1];
		arr[index1] = arr[index2];
		arr[index2] = temp;
	}
	
	public static void main(String ...args) {
		HeapSort obj = new HeapSort();
		int [] arr = new int [] {3,2,6,4,7,9,1,0,8,5};
		obj.sort(arr);
		for(int a : arr)
			System.out.print(a+" ");
		System.out.println();
	}
	
}
