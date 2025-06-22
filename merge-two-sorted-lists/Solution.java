// https://leetcode.com/problems/merge-two-sorted-lists/description/

class Solution {

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // assume: "sorted linked lists", input list mods expected: "splicing together the nodes",
        // case: one list is null: take the other
        // Until one list ends: take from lowest-val head.
        // Then take the remaining from the other list.
        ListNode head = null, cur = null;
        for (; ; ) {
            ListNode got;
            if (list1 == null && list2 == null) {
                return head;
            } else if (list2 == null) {
                got = list1;
                list1 = null;
            } else if (list1 == null) {
                got = list2;
                list2 = null;
            } else if (list1.val < list2.val) {
                got = list1;
                list1 = list1.next;
            } else {
                got = list2;
                list2 = list2.next;
            }

            if (head == null) {
                head = got;
            }
            if (cur != null) {
                cur.next = got;
            }

            cur = got;
        }
    }
}
