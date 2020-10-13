package sorting;

public class QuickSortAlgorithm {


    public static int[] quickSort(int[] arr) {
        int lb = 0;
        int ub = arr.length - 1;
        return quickSortAlgo(arr, lb, ub);

    }

    private static int[] quickSortAlgo(int[] arr, int lb, int ub) {
        if (lb < ub) {
            int loc = quickSortPartition(arr, lb, ub);
            quickSortAlgo(arr, lb, loc - 1);
            quickSortAlgo(arr, loc + 1, ub);
        }
        return arr;
    }

    private static int quickSortPartition(int[] arr, int lb, int ub) {

        int start = lb;
        int end = ub;
        int pivot = arr[lb];
        while (start < end) {

            while (arr[start] <= pivot) {
                start++;

            }
            while (arr[end] > pivot) {
                end--;
            }
            if (start < end) {
                swap(arr, start, end);
            }
        }
        swap(arr, lb, end);
        return end;

    }

    private static void swap(int[] arr, int start, int end) {
        int temp = 0;
        temp = arr[start];
        arr[start] = arr[end];
        arr[end] = temp;
    }

}
