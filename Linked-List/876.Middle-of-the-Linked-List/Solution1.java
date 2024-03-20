// Assumption:
// 1. The input list could be null

// Examples:
// 1 -> 2 -> 3 -> 4 -> 5 -> null
//           s
//                     f

// 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> null
//                s
//                                f

// Algorithm: Two Pointers
// 1. Init a slow and a fast pointer
//    slow -> head
//    fast -> head
// 2. While fast != null && fast.next != null
//  2.1 slow moves one step, fast moves two steps
// 3. Return slow

// Time Complexity: O(N/2) = O(N)
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
    public ListNode middleNode(ListNode head) {
        if (head == null) {
            return null;
        }
        
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }
}

// Take Away:

// 1 2 3 4 5
//     s
// For odd size, slow stops at the middle without doubt

// 1 2 3 4 5 6
//     s
//     t h
// For even size, let slow stops at the tail of first half because we will still be able to
// access the next node(head of next half)

// Thus, maintain a tail pointer is useful