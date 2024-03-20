// Assumption:
// 1. The list could be null

// 3 -> 2 -> 0 -> 4 -> null
//           s
//                      f     

// 3 -> 2 -> 0 -> 4 -> 5 -> null
//           s
//                     f

// 3 -> 2 -> 0 -> 4 -> 2
//                s
//                f   

// Algorithm: Two Pointers
// 1. Init slow and fast pointers
//    slow moves one step
//    fast moves two steps
// 2. If cycle exists, two pointers will eventually meet 
// 3. If no cycle exists, fast pointer will move to null

// Time Complexity: O(N)
// Space Complexity: O(1)

/**
 * Definition for singly-linked list.
 * class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) {
 * val = x;
 * next = null;
 * }
 * }
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) { // while (fast.next != null && fast.next.next != null) also works
            slow = slow.next;
            fast = fast.next.next;

            // Key: if slow is outpaced by fast by one cycle, eventually they will meet
            if (slow == fast) {
                return true;
            }
        }

        return false;
    }
}
