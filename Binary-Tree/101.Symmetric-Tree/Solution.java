// Assumption:

// Examples:
//       1
//   2       2
// 3   4   4   3
// Symmetric

//       1
//   2       2
//     3       3
// Not Symmetric

//       1
//   2       2
// 3           3
// Symmetric

// Observation:
// A symmetric binary tree satisfy the following 2 properties at the same time:
// 1. Left Node of the Left-Subtree = Right Node of the Right-Subtree
// 2. Right Node of the Left-Subtree = Left Node of the Right-Subtree

// Algorithm: Recursion
// !!!Base case!!!:
//    1

//    1
// 2

//    1
//      2

//    1
//  2   2

// Recursive case:
// Recursively check:
// 1. If the left of left-subtree == right of right-subtree
// 2. If the right of left-subtree == left of right-subtree

// Time Complexity: O(N), where N is # of nodes in the binary tree
// Space Complexity: O(H), where H is height of the binary tree

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
    public boolean isSymmetric(TreeNode root) {
        return isSymmetricHelper(root.left, root.right);
    }

    private boolean isSymmetricHelper(TreeNode root1, TreeNode root2) {
        // Base case:
        // !!!
        if (root1 == null && root2 == null) {
            return true;
        } else if (root1 == null || root2 == null) {
            return false;
        } else if (root1.val != root2.val) {
            return false;
        }

        // Recursive case:
        return isSymmetricHelper(root1.left, root2.right) && isSymmetricHelper(root1.right, root2.left);
    }
}