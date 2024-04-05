// Assumption:

// Examples:

//   1         1
// 2   3     2   3
// Equivalent

//   1         1
// 2   3     3   2
// Equivalent: Flip at node 1

// Observations:
// In order for two binary trees to be flip equivalent, either one of two cases must be satisfied
// Case 1: Two trees are identical s.t.
//  1. Left Children of Tree1 = Left Children of Tree2
//  AND
//  2. Right Children of Tree1 = Right Children of Tree2
// Case 2: Two trees are flipped s.t.
//  1. Left Children of Tree1 = Right Children of Tree2
//  AND
//  2. Right Children of Tree1 = Left Children of Tree2

// Algorithm:
// Base case:
//  null  null

//  1     null
//  null   1

//  1      1

// Recursive case:
// Recursively checking if one of the two cases is satisfied

// Time Complexity: O(N^2)
// Space Complexity (call stack): O(logN), assume the tree height is

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
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        // Base case:
        if (root1 == null && root2 == null) {
            return true;
        } else if (root1 == null || root2 == null) {
            return false;
        } else if (root1.val != root2.val) {
            return false;
        }

        // Recurisve case:
        return (flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right)) ||
               (flipEquiv(root1.left, root2.right) && flipEquiv(root1.right, root2.left));
    }
}

// Time Analysis: Assume the binary tree is perfectly balanced

// Recurrence Formula:
// T(n) = 4 * T(n/2) + O(1)

// Recursion Tree:
//  Level                         Cost
//    0               o              1  = 4^0
//              /  |     |   \
//    1        o   o     o    o      4  = 4^1
//           /       ...       \
//    2     o        ...        o    16 = 4^2
//   ...
//   log_{2, n}                         = 4^log_{2, n}
// Total cost = 4^log_{2, n} = 2^(2 * log_{2, n}) = 2^(log_{2, n^2} = n^2 = O(N^2)

// Master Method:
// T(n) = 4 * T(n/2) + O(1)
// n^log{b, a} = n^log{2, 4} = n^2
// f(n) = 1
// Total cost = O(N^2)