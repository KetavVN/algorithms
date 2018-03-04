package array;

//bottom up heap is good when entire array is known from the beginning
public class HeapSortBottomUp {
	
	public void sort(int [] arr) {
		if(arr == null || arr.length == 0) {
			return;
		}
		heapSort(arr);
	}
	
	private void heapSort(int [] arr) {
		
		//use "bottom-up" heapify
		heapify(arr, arr.length-1);	//n*log(n) time to construct heap
		
		for(int i=1; i<=arr.length-i; i++) {
			
			//swap top and last index
			swap(arr, 0, arr.length-i);
			
			//get max element at top using "bottom-up" heapify
			shiftDown(arr, 0, arr.length-i); //log(n) effort to place 0th element to correct place
		}
		
	}
	
	private void heapify(int [] arr, int endIndex) {
		for(int i=endIndex/2; i>=0; i--)
			shiftDown(arr, i, endIndex);
	}
	
	private void shiftDown(int [] arr, int nonLeafParent, int endIndex) {
		int childIndex = nonLeafParent * 2 + 1;
		while(childIndex < endIndex) {
			if(childIndex+1 < endIndex && arr[childIndex] < arr[childIndex+1])
				childIndex++;
			if(arr[nonLeafParent] < arr[childIndex]) {
				swap(arr, nonLeafParent, childIndex);
			}
			nonLeafParent = childIndex;
			childIndex = nonLeafParent * 2 + 1;
		}
	}
	
	private void swap(int [] arr, int index1, int index2) {
		int temp = arr[index1];
		arr[index1] = arr[index2];
		arr[index2] = temp;
	}
	
	public static void main(String ...args) {
		HeapSort obj = new HeapSort();
		int [] arr = new int [] {3,2,6,4,7,9,1,0,8,5,-1};
		obj.sort(arr);
		for(int a : arr)
			System.out.print(a+" ");
		System.out.println();
	}
	
}
