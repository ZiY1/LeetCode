// Assumption:
// 1. Either or both list1 and list2 could be null

// Example(hand drawing needed!):

// Algorithm: Two Pointers(p1 and p2) & Sewing Needle(sn)
// p1 and p2: determine whose value is smaller, and keep track of the next node
// sn: points to the current node, sewing the next node based on p1 and p2

// a. Determine whose value is smaller between p1.val and p2.val
// b. Let sn sewing the samller node
// c. Move the samller node pointer to the next node
// d. Move the sn to the next node
// 1. Repeat a to d until p1 or p2 points to null
// 2. Sew the last node: If p1 points to null, let sn sew p2, vice versa

// Time Complexity: O(N + M), where N is the length of list1 and M is the length of list2
// Space Complexity: O(1)

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }

        ListNode dummyHead = new ListNode(0, null);
        ListNode sn = dummyHead;
        ListNode p1 = list1;
        ListNode p2 = list2;

        while (p1 != null && p2 != null) {
            if (p1.val <= p2.val) {
                // sn sews p1
                sn.next = p1;
                // p1 moves to the next node
                p1 = p1.next;
            } else {
                // sn sews p2
                sn.next = p2;
                // p2 moves to the next node
                p2 = p2.next;
            }
            sn = sn.next;
        }

        // Sew the last node
        if (p1 == null) {
            sn.next = p2;
        } else {
            sn.next = p1;
        }

        return dummyHead.next;
    }
}