// Assumption:

// Example:
//   3
// 1   2
//       4
// Max Depth: 3

// Algorithm:
// 1. Recursively find the max depth of left subtree and right subtree
// 2. Take the max between these two, then add 1 is the max depth at current node

// Time Complexity: O(N), where N is the # of nodes in the input BT
// Space Complexity: O(H), where H is the height of the BT

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int maxDepth(TreeNode root) {
        // Base case:
        if (root == null) {
            return 0;
        }

        // Recursive case:
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}