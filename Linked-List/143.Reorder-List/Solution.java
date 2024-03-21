// Algorithm: Two Pointer(p1 and p2), Sewing Neddle(sn)
// p1 and p2: determine which node to sew next and keep track of the next node after sewing the current node
// sn: sew the current node with the next node

// 1. Find and return the middle node
// 2. Reverse the second half of the list and return the tail
// 3. 
// a. Create a dummy head, let sn points to the dummy head
//    Let p1 point to head and p2 point to tail
// b. Let sn sew p1, move p1 to the next node, then move sn to the next node
//    Let sn sew p2, move p2 to the next node, then move sn to the next node

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
    public void reorderList(ListNode head) {
        // 1. Find and return the middle node (return the head of second half if even nodes)
        ListNode midNode = findMiddle(head);
        // 2. Reverse the second half of the list
        ListNode tail = reverseList(midNode);
        // 3. Reorder the list
        ListNode dummyHead = new ListNode(0, null);
        ListNode sn = dummyHead;
        ListNode p1 = head;
        ListNode p2 = tail;

        while (p1 != null  && p2 != null) {
            // Sew p1
            sn.next = p1;
            // Move p1 and sn
            p1 = p1.next;
            sn = sn.next;
            // Sew p2
            sn.next = p2;
            // Move p2 and sn
            p2 = p2.next;
            sn = sn.next;
        }

        // Cycle is created by sewing p2 at last step, where sn and p2 all point to last node.
        // To avoid the cycle, let sn points to null
        sn.next = null; //!!! 

    }

    private ListNode findMiddle(ListNode head) {
        // 1 2 3 4 5 6 null
        //     s
        //         f

        // 1 2 3 4 5 null
        //     s
        //         f
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        if (fast.next == null) {
            return slow;
        } else {
            return slow.next;
        }
        
    }

    private ListNode reverseList(ListNode head) {
        // null <- 1  <- 2  <- 3    null
        //                      p   c,n

        ListNode prev = null;
        ListNode curr = head;
        ListNode next = head;

        while (curr != null) {
            // Move next to keep track of next node after reversing the current node
            next = next.next;
            // Reverse the link between curr and prev
            curr.next = prev;
            // Update prev and curr
            prev = curr;
            curr = next;
        }

        return prev;
    }
}