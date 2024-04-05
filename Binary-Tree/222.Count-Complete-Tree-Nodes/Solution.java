// Assumption:

// Example:
//     1
//   2   3
// 4

// count = 4

// Algorithm:
// 1. Recusively find the # of nodes in the left subtree
// 2. Recusively find the # of nodes in the right subtree
// 3. Total nodes at current node is #left + #right + 1

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
    public int countNodes(TreeNode root) {
        // Base case:
        if (root == null) {
            return 0;
        }

        // Recursive case:
        return (countNodes(root.left) + countNodes(root.right) + 1);
    }
}