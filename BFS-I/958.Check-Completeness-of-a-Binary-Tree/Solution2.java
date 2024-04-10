// Assumption:

// Complete Binary Tree Definition:
// - Every level, except possibly the last, is completely filled, 
// and all nodes in the last level are as far left as possible.

// Observation & Example & Algorithm (BFS I):
// Observe the relationship between the current node and its child/children.

// There are two cases that violate complete binary tree properties:
// 1. If left child is null AND right child is not null, then it is not a complete tree
//    o
//  /   \
// null  o

// 2. If right child is null AND right left is not null, then check any of the following node can be expanded to 
// generate more nodes. If so, then it is not a complete tree
//        A
//      /    \
//     B      C (Since B has only a left child, its sibling C cannot have any child. Otherwise, it is not complete)
//   /  \    /  \
//  o null  o    o

// To detect these two violations, run BFS on the root, indicate if the current child is missing:
// 1. If current left child missing, check case 1
// 2. ELse if current right child missing check case 2

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
        if (root == null) return true;

        Queue<TreeNode> queue = new LinkedList<>();

        boolean childMissing = false;

        // Enqueue the root
        queue.offer(root);

        while (!queue.isEmpty()) {
            // Dequeue
            TreeNode currNode = queue.poll();

            if (currNode.left == null) {
                // left child is null -> current left child missing
                childMissing = true;
            } else if (childMissing) {
                // Previous child missing, and current left child is not null, meaning the current node can be expanded.
                // Thus, then it is not a complete tree
                return false;
            } else {
                // No previous child missing, and current left child is not null
                queue.offer(currNode.left);
            }

            if (currNode.right == null) {
                // right child is null -> current right child missing
                childMissing = true;
            } else if (childMissing) {
                // Current left child missing, and current right child is not null, then it is not a complete tree
                return false;
            } else {
                // No previous child missing, and current left child is not null
                queue.offer(currNode.right);
            }
        }

        return true;
    }
}

// Time Analysis:

// BFS on binary tree:
// Since each parent has 0, 1 or 2 child (constant), running time: O(V)