// Assumption:
// 1. head could point to a null list
// 2. The input node cannot be null

// Example:
// 1. Insert at head
// 1 -> 4 -> 6 -> 8 -> null, 0
// curr
// null
// curr
// 2. Insert at middle
// 1 -> 4 -> 6 -> 8 -> null, 3
// curr
// 3. Insert at tail
// 1 -> 4 -> 6 -> 8 -> null, 10
//              curr

// Algorithm:
// There are three cases when inserting a new node X into a sorted linked list:
// Case 1: Insert before the head when X.val <= head.val
// 1. Check if the head is null OR X.val <= head.val.
// 2. If so, set X.next to head and return X as the new head.
// Case 2 & 3: Insert in the middle OR Insert after the tail
// 1. Keep moving curr until either curr.next is null OR X.val is less than curr.next.val.
//    This ensures curr is positioned correctly for the insertion.
// 2. Insert the new node: Set newNode.next to curr.next, then set curr.next to newNode.

// Time Complexity: O(N)
// Space Complexity: O(1)

/**
 * Definition for ListNode
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */

class Solution {
    /**
     * @param head: The head of linked list.
     * @param val: An integer.
     * @return: The head of new linked list.
     */
    public ListNode insertNode(ListNode head, int val) {
        ListNode newNode = new ListNode(val);

        // Case 1: Insert before the head (newNode becomes the newHead)
        if (head == null || val <= head.val) {
            newNode.next = head;
            return newNode;
        }

        ListNode curr = head;

        // Case 2 & 3: Insert in the middle or after the tail
        // Skip all nodes whose value < val
        while (curr.next != null && curr.next.val < newNode.val) {
            curr = curr.next;   
        }

        // Two situations now:
        // 1. curr.value < value <= current.next.value
        // 2. curr is tail
        // In either case, insert the new node after curr
        newNode.next = curr.next;
        curr.next = newNode;
        return head;
    }
}