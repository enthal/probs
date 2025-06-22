package remove_letter_to_equalize_frequency;

import java.util.HashMap;
import java.util.TreeMap;


// aa : t
//   countsByCP: a:2
//   countsByCPCount: 2:1
// aaa : t
//   countsByCP: a:3
//   countsByCPCount: 3:1
// ab : t
//   countsByCP: a:1 b:1
//   countsByCPCount: 1:2
// abc : t
//   countsByCP: a:1 b:1 c:1
//   countsByCPCount: 1:3
// abb : t
//   countsByCP: a:1 b:2
//   countsByCPCount: 1:1 2:1
// aabb : f
//   countsByCP: a:2 b:2
//   countsByCPCount: 2:1
// abbc : t
//   countsByCP: a:1 b:2 c:1
//   countsByCPCount: 1:2 2:1
// abbcc : f
//   countsByCP: a:1 b:2 c:2
//   countsByCPCount: 1:1 2:2
// abbccddd : f
//   countsByCP: a:1 b:2 c:2 d:3
//   countsByCPCount: 1:1 2:2 3:1
// aabbcccdd : t
//   countsByCP: a:2 b:2 c:3 d:2
//   countsByCPCount: 2:3 3:1
// abbcc : t
//   countsByCP: a:1 b:2 c:2
//   countsByCPCount: 1:1 2:2
// ddaccb: f
//   countsByCP: a:1 b:1 c:2 d:2
//   countsByCPCount: 1:2 2:2


class Solution {
    /// @return true iff word can have exactly 1 index removed
    ///  and leave a string where each letter has equal frequency.
    ///
    /// • 2 <= word.length <= 100
    /// • word consists of lowercase English letters only.
    ///
    public boolean equalFrequency(String word) {
        return equalFrequency_n_sq(word); // O(n^2): faster benchmark than O(n): n<=100, only constant size allocation
    }

    public boolean equalFrequency_n_sq(String word) { // O(n^2): works
        var w = word.toCharArray();
        // for each w index i (to skip)...
        top: for (var i=0; i<w.length; i++) {
            // compute count (frequency) per letter
            // for each w index j (to count) ...
            var countsByCharI = new int[1+'z'-'a'];
            for (var j=0; j<w.length; j++) { // embarrassingly parallelize-able
                if (i!=j) // unless this is the skipped index
                    countsByCharI[w[j]-'a']++; // count the letter
            }

            // Does word without the skipped index have each letter of equal frequency?
            // for each countsByCharI count value...
            for (int ci=0, got=0; ci<countsByCharI.length; ci++) {
                if (countsByCharI[ci]!=0) { // this letter was in word?
                    if (got == 0) {
                        got = countsByCharI[ci];
                    } else if (got != countsByCharI[ci]) { // unequal frequency?
                        continue top;
                    }
                }
            }
            return true;
        }
        return false;
    }

    /// @return true when any:
    ///  • countsByCP.size==1                         // 1 letter n times: remove any
    ///  • countsByCPCount.size==1 && only key == 1   // all letters freq=1: remove any
    ///  • countsByCPCount matching: n:m n+1:1        // all m letters have freq=n except one (removable) letter has freq:n+1
    ///  • countsByCPCount matching: 1:1 n:m          // all m letters have freq=n except one (removable) letter has freq:1
    ///
    public boolean equalFrequency_n(String word) { // O(n): works, but slower benchmark
        var countsByCP = new HashMap<Integer, Integer>();
        word.codePoints().forEach(cp -> countsByCP.merge(cp, 1, Integer::sum));
        var countsByCPCount = new TreeMap<Integer, Integer>();
        countsByCP.values().forEach(cpCount -> countsByCPCount.merge(cpCount, 1, Integer::sum));

        var cpCounts = countsByCPCount.keySet().stream().toList();
        return
                // 1 letter n times: remove any
                countsByCP.size()==1 ||

                // all letters freq=1: remove any
                countsByCPCount.size()==1 && cpCounts.get(0) == 1 ||

                ( // countsByCPCount matching: n:m n+1:1        // all m letters have freq=n except one (removable) letter has freq:n+1
                        countsByCPCount.size() == 2 &&
                        1 == cpCounts.get(1) - cpCounts.get(0) &&
                        1 == countsByCPCount.values().stream().toList().get(1)
                ) ||

                ( // countsByCPCount matching: 1:1 n:m          // all m letters have freq=n except one (removable) letter has freq:1
                        countsByCPCount.size() == 2 &&
                        countsByCPCount.entrySet().stream().findFirst().stream().allMatch(kv -> kv.getKey()==1&&kv.getValue()==1)
                );
    }
}
