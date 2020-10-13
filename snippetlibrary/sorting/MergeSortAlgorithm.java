package sorting;

public class MergeSortAlgorithm {

    public static int[] mergeSortAlorithm(int[] arr) {
        return mergeSort(arr);
    }

    private static int[] mergeSort(int[] arr) {
        int lb = 0;
        int ub = arr.length - 1;
        return mergeSortAlgo(arr, lb, ub);
    }

    private static int[] mergeSortAlgo(int[] arr, int lb, int ub) {
        int mid = 0;
        int[] mergedResult = new int[arr.length];
        if (lb < ub) {
            mid = (lb + ub) / 2;
            mergeSortAlgo(arr, lb, mid);
            mergeSortAlgo(arr, mid + 1, ub);
            mergedResult = merge(arr, lb, mid, ub);
        }
        return mergedResult;

    }

    private static int[] merge(int[] arr, int lb, int mid, int ub) {

        int i = lb;
        int j = mid + 1;
        int k = lb;
        int[] b = new int[arr.length];
        while (i <= mid && j <= ub) {
            if (arr[i] <= arr[j]) {
                b[k] = arr[i];
                i++;
            } else {
                b[k] = arr[j];
                j++;
            }
            k++;
        }
        if (i > mid) {
            while (j <= ub) {
                b[k] = arr[j];
                j++;
                k++;
            }
        } else {
            while (i <= mid) {
                b[k] = arr[i];
                i++;
                k++;
            }
        }
        return copyArray(arr, b, lb, ub);
    }

    private static int[] copyArray(int[] arr, int[] b, int lb, int ub) {
        for (int k = lb; k <= ub; k++) {
            arr[k] = b[k];
        }
        return arr;
    }

}
