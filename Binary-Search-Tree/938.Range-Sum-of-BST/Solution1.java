// Clarification:

// Assumption:

// Approach: Recursion + BST Traversal
// - A BST is defined recursively as left-subtree < root < right-subtree
// - Perform a BST traversal starting from the root
// - In order to sum the node in the given range effectively, we need to prune the BST:
//   When to visit left-subtree?
//   Only if current root.val > low. Otherwise, we know all keys in left-subtree < k1, no need to visit
//   When to visit right-subtree?
//   Only if current root.val < high. Otherwise, we know all keys in right-subtree > k1, no need to visit

// Example:
// low = 7, high = 15
//       10
//   5        15
// 3   7           18

// rangeSum = 32

// Time Complexity: O(N)
// Space Complexity (call stack): O(H), where H is the height of the BST 

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
    public int rangeSumBST(TreeNode root, int low, int high) {
        // Base case:
        if (root == null) {
            return 0;
        }

        int rangeSum = 0;

        if (root.val > low) {
            rangeSum += rangeSumBST(root.left, low, high);
        }

        if (root.val >= low && root.val <= high) {
            rangeSum += root.val;
        }

        if (root.val < high) {
            rangeSum += rangeSumBST(root.right, low, high);
        }

        return rangeSum;
    }
}

// Time Analysis:

// Case 1: the BST is perfectly balanced
// Recursion Tree:
// Level            Cost
//   0       o       2^0 = 1
//         /   \
//   1    o     o    2^1 = 2
// ...
// log(2,n)          2^(log(2,n)) = N
// Total Cost: O(N)

// Case 2: the BST is extremely unbalanced:
// Total Cost: O(N)