// Assumptions:
// Input list could be null

// Example:

// 1 -> 4 -> 3 -> 2 -> 5 -> 2   X = 3
//                          c

// DummyHead1: 0 -> 1 -> 2 -> 2
//                            t
// DummyHead2: 0 -> 4 -> 3 -> 5
//                            t

// Algorithm: Needle Threading 
// 1. Initialize two dummy heads: maintain the head information
//    DummyHead1: store the list nodes value < X
//    DummyHead2: store the list nodes value >= X
// 2. Initialize two tails tailNeedleSmall and tailNeedleLarge:
//    Purose: maintain the tail information so we can append(thread) new node on tail
// 3. Iterate through the input list, for each Node N:
//    a. If N < X,
//       append(thread) N to tailNeedleSmall
//       move tailNeedleSmall to next node
//    b. If N >= X,
//       append(thread) N to tailNeedleLarge
//       move tailNeedleLarge to next node
// 4. append(thread) the head of large list to tail of the small list
// 5. Let tail of the large list point to null

// Time Complexity: O(N)
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
    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummyHead1 = new ListNode(0, null);
        ListNode dummyHead2 = new ListNode(0, null);
        ListNode tailNeedleSmall = dummyHead1;
        ListNode tailNeedleLarge = dummyHead2;

        ListNode cursor = head;
        while (cursor != null) {
            if (cursor.val < x) {
                tailNeedleSmall.next = cursor;
                tailNeedleSmall = tailNeedleSmall.next;
            } else {
                tailNeedleLarge.next = cursor;
                tailNeedleLarge = tailNeedleLarge.next;
            }
            cursor = cursor.next;
        }

        tailNeedleSmall.next = dummyHead2.next;
        
        tailNeedleLarge.next = null; // !!!

        return dummyHead1.next;
    }
}