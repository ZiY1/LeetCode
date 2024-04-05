// Assumption:
// Input list is sorted in ascending order

// Example:

//  1

//  1 -> 1 -> 2

//  1 -> 2 -> 2

//  1 -> 2 -> 2 -> 3

// Algorithm:
// 1. Edge case: If the input list is null or single node, done
// 2. Itearte the cursor from the first node to the !!!LAST node, for each node X
//    a. Compare the value of X and X.next
//    b1. If value is same, delete the second duplicate node, !!!Don't move cursor
//    b2. If value is not the same, !!!move the cursor

// Time Complexity: O(N)
// Space Complxity: O(1)

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
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode cursor = head;

        while (cursor.next != null) { // Stop at last node
            // Compare the value of the current node and next node
            if (cursor.val == cursor.next.val) {
                // Delete the second duplicate
                // !!! Don't move cursor
                cursor.next = cursor.next.next;
            } else {
                // Move cursor
                cursor = cursor.next;
            }
        }

        return head;
    }
}
