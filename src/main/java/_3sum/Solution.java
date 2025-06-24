package _3sum;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        return threeSum_O_nCubed(nums);
    }

    public List<List<Integer>> threeSum_O_nCubed(int[] nums) {
        var distinctIndexTriples = new ArrayList<Set<Integer>>();

        // O(n^3)
        for (var i=0; i<nums.length; i++) {
            for (var j=0; j<nums.length; j++) {
                if (i!=j) {
                    for (var k=0; k<nums.length; k++) {
                        if (i != k && j != k) {
                            if (nums[i] + nums[j] + nums[k] == 0) {
                                distinctIndexTriples.add(Set.of(i,j,k));
                            }
                        }
                    }
                }
            }
        }

        return distinctIndexTriples.stream()
                .map(t->t.stream().map(i->nums[i]).sorted().toList())
                .distinct()
                // .sorted()
                .toList();
    }
}
