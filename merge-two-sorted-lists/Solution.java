// https://leetcode.com/problems/merge-two-sorted-lists/description/

class Solution {

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // assume: "sorted linked lists", input list mods ok but not required: "splicing together the nodes",
        // case: one list is null: take the other
        // Until one list ends: take from lowest-val head.
        // Then take the remaining from the other list.
        ListNode head = null, cur = null;
        for (; ; ) {
            ListNode made;
            if (list1 == null && list2 == null) {
                return head;
            } else if (list2 == null) {
                made = list1;
                list1 = null;
            } else if (list1 == null) {
                made = list2;
                list2 = null;
            } else if (list1.val < list2.val) {
                made = new ListNode(list1.val);
                list1 = list1.next;
            } else {
                made = new ListNode(list2.val);
                list2 = list2.next;
            }

            if (head == null) {
                head = made;
            }
            if (cur != null) {
                cur.next = made;
            }

            cur = made;
        }
    }


}
