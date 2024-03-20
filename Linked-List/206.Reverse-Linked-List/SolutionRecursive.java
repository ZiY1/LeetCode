// Assumption:
// 1. The input list could be null

// Algorithm: recursive

// Base case:
// 1. No nodes: return head
// null
// head
// 2. One node: return head
// 1 -> null
// head

// Recursive case:
// 1. Assume the recursive call successfully reverse the list start at head.next, and return us the new head
// 1 -> 2 <- 3 <- 4
// head          new head
// 2. Now reverse the link bewteen head and head.next (Becareful)
//  2.1 let head.next point to head
//  1 <-> 2 <- 3 <- 4
//  2.2 let head point to null!!! Otherwise, circle forms
//  1 <- 2 <- 3 <- 4
// 3. return the new head

// Time Complexity: O(N)
// Space Complexity (call stack): O(N)

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
        // Base case:
        if (head == null || head.next == null) { // no node or one node
            return head;
        }

        // Recursive case:
        ListNode newHead = reverseList(head.next); // reverse the list staring from head.next
        head.next.next = head; // let head.next points to head which create a cycle
        head.next = null; // let head points to null which remove the cycle

        return newHead;
    }
}