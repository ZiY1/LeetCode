// Assumption:
// Input list is sorted in ascending order

// Example:

//  1

//  1 -> 1 -> 2

//  1 -> 2 -> 2

//    1 -> 2 -> 3 -> null
//              p     c

// Algorithm:
// 1. Edge case: If the input list is null or single node, done
// 2. Init prev -> first node and curr -> second node
// 3. If prev.val = curr.val,
//    point prev to curr.next
//    move curr
//    Otherwise,
//    Move prev and curr
// 4. Stop when curr is null

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

        ListNode prev = head;
        ListNode curr = head.next;

        while (curr != null) {
            if (prev.val == curr.val) {
                prev.next = curr.next;
                curr = curr.next;
            } else {
                prev = curr;
                curr = curr.next;
            }
        }

        return head;
    }
}