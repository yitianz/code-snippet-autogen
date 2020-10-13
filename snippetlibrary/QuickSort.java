public class QuickSort {
    public int[][] partition(int[] arr, int pivot) {
        // Partitions array into 2 subarrays left and right based on pivot element
        // 2 pointers going towards middle of array
        // Swap if out of order
        
    }
    public void sort(int[] arr) {
        // Pick the 1st element as pivot
        // Put elems < pivot into left and elems > pivot into right (unordered)
        // Do the same thing on subarrays
        // O(NlogN) time, O(1) space (in-place)
        if (arr.length < 2) {
            return;
        }
        int pivot = arr[0];
        partition(arr, pivot);
    }
}