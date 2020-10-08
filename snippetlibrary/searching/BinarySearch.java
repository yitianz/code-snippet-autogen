package searching;

import java.util.List;
import java.util.ArrayList;

public class BinarySearch {

    public static int bsearch(List<Integer> list, int target){
        int low = 0, high = list.size() - 1;
        while(low <= high){
            int mid = low + (high - low)/2;
            if(list.get(mid) == target){
                return mid;
            }
            else if(list.get(mid) < target){
                low = mid + 1;
            }
            else{
                high = mid - 1;
            }
        }
        return -1;
    }

    public static int bsearch(int[] arr, int target){
        int low = 0, high = arr.length - 1;
        while(low <= high){
            int mid = low + (high - low)/2;
            if(arr[mid] == target){
                return mid;
            }
            else if(arr[mid] < target){
                low = mid + 1;
            }
            else{
                high = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int value = bsearch(new ArrayList<>(),-1);
        value = bsearch(new int[]{}, -1);
    }

}
