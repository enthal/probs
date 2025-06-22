package valid_parentheses;
// https://leetcode.com/problems/valid-parentheses/

import java.util.Stack;

//   .
// "()"
// 0

class Solution {
    static final String PARENS_OPEN  = "([{";
    static final String PARENS_CLOSE = ")]}";
    public boolean isValid(String s) {
        var stack = new Stack<Integer>();

        for (char c : s.toCharArray()) {
            var pI = PARENS_OPEN.indexOf(c);
            if (pI >= 0) {
                stack.push(pI);
            } else {
                if (stack.isEmpty() || stack.pop() != PARENS_CLOSE.indexOf(c)) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
