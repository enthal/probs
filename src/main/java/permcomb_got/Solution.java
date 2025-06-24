package permcomb_got;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    // combine(4,3): [[1, 2, 3], [1, 2, 4], [1, 3, 4], [2, 3, 4]]
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        combine(1, n, k, new ArrayList<>(), result);
        return result;
    }

    private void combine(int start, int n, int k, List<Integer> current, List<List<Integer>> result) {
        if (current.size() == k) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = start; i <= n; i++) {
            current.add(i);
            combine(i + 1, n, k, current, result);  // move forward
            current.remove(current.size() - 1);     // backtrack
        }
    }

    // permute([1,2,3]): [[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]]
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        permute(nums, new boolean[nums.length], new ArrayList<>(), result);
        return result;
    }

    private void permute(int[] nums, boolean[] used, List<Integer> current, List<List<Integer>> result) {
        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;

            used[i] = true;
            current.add(nums[i]);

            permute(nums, used, current, result);

            current.remove(current.size() - 1);
            used[i] = false;
        }
    }

}
