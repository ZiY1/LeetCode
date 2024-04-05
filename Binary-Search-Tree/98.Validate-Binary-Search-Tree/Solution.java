// Assumption:
// The keys are integer

// Example:

//     5
// 1        7
//       6     8

// Algorithm:
// Based on the property of a valid BST: keys of left-subtree < curr node key < keys of right-subtree:
// - When we try to visit the left-subtree, we can set a max boundary (exclusive max) based on current node key
// - When we try to visit the right-subtree, we can set a min boundary (exclusive max) based on current node key
// - Valid range initially (neg inf, pos inf)

// Base case:
// 1. null
// 2. one node -> check if the value is within range (min, max)

// Recursive case:
// Recursively check the left-subtree and right-subtree, updating range (min, max)

// Time Complexity: O(N), where N is # of nodes in the BST
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
    public boolean isValidBST(TreeNode root) {
       return isValidBSTHelper(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValidBSTHelper(TreeNode root, long min, long max) {
        // Base case:
        if (root == null) {
            return true;
        } else if (root.val <= min || root.val >= max) {
            return false;
        }

        // Recurisve case:
        return isValidBSTHelper(root.left, min, root.val) && isValidBSTHelper(root.right, root.val, max);
    }
}

// Time Analysis:

// Case 1: Input binary tree perfectly balanced

// Recurrence Formula:
// T(n) = 2 * T(n/2) + O(1)

// Recurison Tree
//   Level           Cost
//    0       o       1 = 2^0
//          /   \ 
//    1    o     o    2 = 2^1
// ...
//  log(2,n)            = 2^log(2,n)
// Total cost = O(N)

// Case 1: Input binary tree extremely  unbalanced
// ...
// O(N)