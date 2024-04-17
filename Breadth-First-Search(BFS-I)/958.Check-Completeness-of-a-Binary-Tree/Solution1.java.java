// Assumption:

// Complete Binary Tree Definition:
// - Every level, except possibly the last, is completely filled, 
// and all nodes in the last level are as far left as possible.

// Obervation & Example & Algorithm (BFS I):
// Observe the relationship between a current node and its child/children.

// There are two cases that violate complete binary tree properties.

// To detect these two violations, run BFS on the root,
// !!! set a boolean flag to indicate if the current or subsequent node in the queue can have child or not

// 1. If the current node has only a right child,
//    set canHaveChild = false to indicate the current node cannot have right child
//    o
//  /   \
// null  o

// 2. If the current node has only a left child but the left child is not the last node in the BT;
//    In other words, when the current node has only a left child, its sibling also has child/children
//    set canHaveChild = false to indicate the subsequent nodes in the queue can not have any child
//        A
//      /    \
//     B      C (Since B has only a left child, its sibling C cannot have any child. Otherwise, it is not complete)
//   /  \    /  \
//  o null  o    o

// Time Complexity: O(V)
// Space Compelxity: O(V)

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */

 class Solution {
    public boolean isCompleteTree(TreeNode root) {
        // Corner case:
        if (root == null) return true;

        // A boolean flag to indicate if the next node should have child or not
        boolean canHaveChild = true;

        Queue<TreeNode> queue = new LinkedList<>();

        // Enqueue the root
        queue.offer(root);

        while (!queue.isEmpty()) {
            // Dequeue
            TreeNode currNode = queue.poll();

            if (!canHaveChild && currNode.left != null) {
                return false;
            } else if (canHaveChild && currNode.left != null) {
                queue.offer(currNode.left);
            } else if (currNode.left == null) {
                canHaveChild = false;
            }

            if (!canHaveChild && currNode.right != null) {
                return false;
            } else if (canHaveChild && currNode.right != null) {
                queue.offer(currNode.right);
            } else if (currNode.right == null) {
                canHaveChild = false;
            }
        }

        return true;
    }
}

// Time Analysis:

// BFS on binary tree:
// Since each parent has 0, 1 or 2 child (constant), running time: O(V)