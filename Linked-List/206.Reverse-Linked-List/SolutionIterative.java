// Assumption:
// 1. The input list could be null

// Example:
// 0. Init
//    1 -> 2 -> 3
// p c,n

// 1.0 next moves 
//    1 -> 2 -> 3
//p   c   n

// 1.1 reverse between prev and curr
// Note: our next pointer helps us keep track of the next node, so we don't loss control of the list
//    1   2 -> 3
//p   c   n

// 1.2 p moves, c moves
//    1   2 -> 3
//    p  c,n

// 2. next moves, reverse between prev and curr, p moves, c moves
//    1 <- 2    3
//         p   c,n

// 3. next moves, reverse between prev and curr, p moves, c moves
//    1 <- 2 <- 3
//              p    c,n

// Algorithm: iterative
// 1. Define three pointers: prev, curr, next
//    next: to keep track of the next node so we don't loss control of the list
//    prev and curr: to reverse the list
// 
//    init prev points to null
//    inti curr and next points to head
// 2. While curr != null
//  2.1 move next
//  2.2 revserve the link between prev and curr
//  2.3 move prev, move curr
// 3. Return prev

// Time Complexity: O(N)
// Space Complexity: O(1)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        ListNode next = head;

        while (curr != null) {
            // Update next 
            next = next.next; // next can move only if we checked next is not currently null

            // Revser the list
            curr.next = prev;

            // Update prev and curr
            prev = curr;
            curr = next;
        }

        return prev;
    }
}
