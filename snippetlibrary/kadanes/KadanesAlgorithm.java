package kadanes;

import java.util.ArrayList;
import java.util.List;

public final class KadanesAlgorithm {
    public static long kadanes(List<Integer> nums){
        long _$loc_ = 0, _$glob_ = Long.MIN_VALUE;
        for (Integer n : nums) {
            _$loc_ = Math.max(n, _$loc_ +n);
            _$glob_ = Math.max(_$loc_, _$glob_);
        }
        return _$glob_;
    }

    public static long kadanes(int[] nums){
        long _$loc_ = 0, _$glob_ = Long.MIN_VALUE;
        for (Integer n : nums) {
            _$loc_ = Math.max(n, _$loc_ + n);
            _$glob_ = Math.max(_$loc_, _$glob_);
        }
        return _$glob_;
    }

    public static void main(String[] args) {
        long maxSubarraySum = kadanes(new ArrayList<>());
        maxSubarraySum = kadanes(new int[]{});
    }
}
