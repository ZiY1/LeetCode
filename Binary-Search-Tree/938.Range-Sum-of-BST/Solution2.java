// Clarification:

// Assumption:

// Approach: Reecursion + BST Traversal
// - A BST is defined recursively as left-subtree < root < right-subtree
// - Perform a BST traversal starting from the root
//   Three cases for the current node value:
//   1. If the current node in range [low, high], add it to sum, recurisvely search left-subtree and right-subtree
//   2. If the current node < low, we can prun the left-subtree since left-subtree < root. Thus, we vist only the right-subtree
//   3. If the current node > high, we can prun the right-subtree since righ-subtree > root. Thus, we vist only the left-subtree

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

        // Recursive rule:
        if (root.val >= low && root.val <= high) {
            rangeSum += root.val;
            rangeSum += rangeSumBST(root.left, low, high);
            rangeSum += rangeSumBST(root.right, low, high);
        } else if (root.val < low) {
            rangeSum += rangeSumBST(root.right, low, high);
        } else if (root.val > high) {
            rangeSum += rangeSumBST(root.left, low, high);
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