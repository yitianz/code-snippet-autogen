package kadanes;

import java.util.ArrayList;
import java.util.List;

public final class KadanesAlgorithm {
    public static long kadanes(List<Integer> nums){
        long loc = 0, glob = Long.MIN_VALUE;
        for (Integer n : nums) {
            loc = Math.max(n, loc+n);
            glob = Math.max(loc, glob);
        }
        return glob;
    }

    public static long kadanes(int[] nums){
        long loc = 0, glob = Long.MIN_VALUE;
        for (Integer n : nums) {
            loc = Math.max(n, loc+n);
            glob = Math.max(loc, glob);
        }
        return glob;
    }

    public static void main(String[] args) {
        long maxSubarraySum = kadanes(new ArrayList<>());
    }
}
