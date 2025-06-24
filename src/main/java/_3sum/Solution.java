package _3sum;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        return threeSum_O_nSquared(nums);
    }

    public List<List<Integer>> threeSum_O_nSquared(int[] nums) {
        var indexTriples = new ArrayList<Set<Integer>>();
        var indexListsByNum = new HashMap<Integer, List<Integer>>();
        for (var i=0; i<nums.length; i++) {
            var indexList = indexListsByNum.computeIfAbsent(nums[i], ii -> new ArrayList<>());
            if (indexList.size()<3) indexList.add(i);  // Optimization: Can't use more than 3.
        }

        // O(n^2)
        for (var i=0; i<nums.length; i++) {
            for (var j=0; j<nums.length; j++) {
                if (i!=j) {
                    var num = - (nums[i]+nums[j]);
                    var ks = indexListsByNum.get(num);
                    if (ks!=null) {
                        for (var k: ks) {
                            if (i != k && j != k) {
                                indexTriples.add(Set.of(i,j,k));
                            }
                        }
                    }
                }
            }
        }

        return indexTriples.stream()
                .map(t->t.stream().map(i->nums[i]).sorted().toList())
                .distinct()
                // .sorted()
                .toList();
    }

    public List<List<Integer>> threeSum_O_nCubed(int[] nums) {
        var indexTriples = new ArrayList<Set<Integer>>();

        // O(n^3)
        for (var i=0; i<nums.length; i++) {
            for (var j=0; j<nums.length; j++) {
                if (i!=j) {
                    for (var k=0; k<nums.length; k++) {
                        if (i != k && j != k) {
                            if (nums[i] + nums[j] + nums[k] == 0) {
                                indexTriples.add(Set.of(i,j,k));
                            }
                        }
                    }
                }
            }
        }

        return indexTriples.stream()
                .map(t->t.stream().map(i->nums[i]).sorted().toList())
                .distinct()
                // .sorted()
                .toList();
    }
}
