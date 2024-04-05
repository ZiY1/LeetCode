// Assumption:

// Example:
//   1       1
// 2   3   2   3
// Same

//   1       1
// 2       2   
// Same

//   1       1
// 2           2  
// Not Same

// Observation:
// In order to be the same tree, the following properties must be satisfied at the same time
// 1. Left Children of Tree p = Left Children of Tree q
// 1. Right Children Tree p = Right Children of Tree q

// Algorithm: Recursion
// !!!Base case:
// null    null

// 1       null

// null     1

// 1        1

// Recursive case:
// Recursively check if the two properties are satisified

// Time Complexity: O(N), where N is the # of nodes in the binary tree
// Space Complexity (call stack): O(H), where H is the height of the binary tree

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
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // Base case:
        if (p == null && q == null) {
            return true;
        } else if (p == null || q == null) {
            return false;
        } else if (p.val != q.val) {
            return false;
        }
        
        // Recursive case:
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}

// Time Analysis:

// Case 1: binary tree perfectly balanced

// Construct recurrence formula:
// T(n) = T(n/2) + T(n/2) + 1
//      = 2 * T(n/2) + 1

// Recursion tree:
// Level              cost
//  0         o        1 = 2^0
//          /   \
//  1      o     o     2 = 2^1
//       /   \ /   \
//  2   o    o o    o  4 = 2^2
// ...

// log_{2, n}          2^(log_{2, n}) = n  
// Total cost = O(N) 
// Trick: look at the cost at bottom level since cost(bottom level) > cost(sum of cost of all prev level)

// Master Method:
// T(n) = 2 * T(n/2) + 1
// n^log{b, a} = n^log{2, 2} = n
// f(n) = 1
// -> O(N)

// Case 2: binary tree extremely unbalanced

// Construct recurrence formula:
// T(n) = T(n - 1)

// Recursion tree:
// Level              cost
//  0         o        1
//          /   
//  1      o           1
//       /   
//  2   o              1
// ...

//  N                  1
// Total cost = O(N) 