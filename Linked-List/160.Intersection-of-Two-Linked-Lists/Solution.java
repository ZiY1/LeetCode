// Assumption:
// ...

// Example:
// 
//       A1 -> A2 - 
//                   \
//                    -> C1 -> C2 -> C3
//                   /
// B1 -> B2 -> B3 -> 

// Critical Observation:
// 1. List 1 has two parts: A and C
// 2. List 2 has two parts: B and C
// 3. List 1 and 2 share C
// Thus, A + C + B = B + C + A

// Algorithm:
// Let cursor1 iterate through list1, when cursor1 at last node, redirect it to the head of list2
// Let cursor2 iterate through list2, when cursor2 at last node, redirect it to the head of list1

// If list 1 and list 2 has intersection, cursor1 and cursor2 will meet at the intersection point (A + C + B = B + C + A)
// Otherwise, they will meet at null (A + B = B + A)

// Time Comlexity: O(N)
// Space Complexity: O(1)

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode cursor1 = headA;
        ListNode cursor2 = headB;

        while (cursor1 != cursor2) {
            if (cursor1 == null) {
                cursor1 = headB;
            } else {
                cursor1 = cursor1.next;
            }

            if (cursor2 == null) {
                cursor2 = headA;
            } else {
                cursor2 = cursor2.next;
            }
        }

        return cursor1;
    }
}