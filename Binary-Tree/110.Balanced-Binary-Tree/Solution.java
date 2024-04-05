// Assumption:

// Example:
//     1
//   2   3
// Balance: leftSubTreeDepth = 1, rightSubTreeDepth = 1

//     1
//   2
// Balance: leftSubTreeDepth = 1, rightSubTreeDepth = 0

//     1
//   2
// 3
// Not Balance: leftSubTreeDepth = 2, rightSubTreeDepth = 0

// Algorithm:
// 1. Check if the tree is balanced at current level
//  1.1 If |left subtree depth - right subtree depth| > 1, then not balance
// 2. Recursively check if left sub tree is balanced
// 3. Recursively check if the right sub tree is balanced

// Time Complexity: 
// - Worst case: O(N^2)
// - Best case: O(NlogN)

// Space Complexity: 
// - Worst case: O(N)
// - Best case: O(logN)

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
    public boolean isBalanced(TreeNode root) {
        // Height-balanced: |Left Subtree Height - Right Subtree Height| <= 1

        // Base case:
        if (root == null) {
            return true;
        }

        // Recurisve case:
        if (Math.abs(getDepth(root.left) - getDepth(root.right)) > 1) {
            return false;
        }

        return isBalanced(root.left) && isBalanced(root.right);
    }

    private int getDepth(TreeNode root) {
        // Base case:
        if (root == null) {
            return 0;
        }

        // Recurisve case:
        return Math.max(getDepth(root.left), getDepth(root.right)) + 1;
    }
}

// Time & Space Analysis:
// Recursion Tree:

// Case: extremely unbalanced
//  Level            Cost
//   0        o        N
//          /  
//   1     o           N
//       /  
//   2  o              N
//  ...
//   N                 N
// Total cost: O(N^2)
// Call stack depth: O(N)

// // Case: perfectly balanced
//  Level                                      Cost
//   0         o                                 N
//           /   \
//   1      o     o      N/2 + N/2             = N
//         / \   / \ 
//   2    o   o o   o    N/4 + N/4 + N/4 + N/4 = N
//  ...
//  log{2, N}                                    N
// Total cost: O(NlogN)
// Call stack depth: O(logN)

// Master Method:
// Recurrence Equaltion T(n) = 2 * T(n/2) + n
// n^(log_{b, a}) = n^(log_{2, 2}) = n^1 = n
// f(n) = n
// Since n^(log_{b, a}) = f(n) -> T(n) = O(NlogN)