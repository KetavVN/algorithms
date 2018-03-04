package array;

/*
	QuickSort is the fastest way to put an element at correct place.
	inplace, not stable
	worst time n^2
	avg time nlogn
	best time nlogn
*/
public class QuickSort {

	public void sort(int [] arr) {
		if(arr == null || arr.length == 0) {
			return;
		}
		quickSort(arr, 0, arr.length-1);
	}

	private void quickSort(int [] arr, int startIndex, int endIndex) {
		if(startIndex < endIndex) {
			int pivotIndex = partition(arr, startIndex, endIndex);
			quickSort(arr, startIndex, pivotIndex-1);
			quickSort(arr, pivotIndex+1, endIndex);
		}
	}

	private int partition(int [] arr, int startIndex, int endIndex) {
		int midIndex = getMidIndex(startIndex, endIndex);
		//int pivot = arr[midIndex];
		swap(arr, midIndex, endIndex);
		int j = startIndex, k=startIndex;
		while(j<endIndex) {
			if(arr[j] < arr[endIndex]) {	//move smaller elements on left of 'k'
				swap(arr, j, k);
				k++;
			}
			j++;
		}
		swap(arr, k, endIndex);	//kth index is bigger or same as pivot thus swap with it.
		return k;
	}

	private int getMidIndex(int start, int end) {
		return start + (end-start) / 2;
	}

	private void swap(int [] arr, int index1, int index2) {
		if(index1 == index2) {
			return;
		}
		int temp = arr[index1];
		arr[index1] = arr[index2];
		arr[index2] = temp;
	}
	
	public static void main(String ...args) {
		QuickSort obj = new QuickSort();
		int [] arr = new int [] {3,2,6,4,7,9,1,0,8,5};
		obj.sort(arr);
		for(int a : arr)
			System.out.print(a+" ");
		System.out.println();
	}
	
}
