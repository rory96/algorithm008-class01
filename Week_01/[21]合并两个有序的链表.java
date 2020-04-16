package com.keanu.algorithm.leetcode.easy;

/**
 * 21 - easy - merge two sorted lists
 */
public class MergeTwoSortedLists {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode res = new ListNode(0);
        ListNode tmp = res;
        while (l1 != null || l2 != null) {
            if (l1 == null) {
                tmp.next = new ListNode(l2.val);
                l2 = l2.next;
            } else if (l2 == null) {
                tmp.next = new ListNode(l1.val);
                l1 = l1.next;
            } else {
                if (l1.val > l2.val) {
                    tmp.next = new ListNode(l2.val);
                    l2 = l2.next;
                } else {
                    tmp.next = new ListNode(l1.val);
                    l1 = l1.next;
                }
            }
            tmp = tmp.next;
        }
        return res.next;
    }

    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}
