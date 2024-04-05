// Assumption:
// The input is a valid BST

// Example:

// [10, 22]
//        20
//    8       22
// 4    12

// Algorithm:
// - In order to print the keys in ascending order,
//   we must perform a in order traversal: left -> root -> right
// - In order to return the node in the given range effectively, we need to prune the BST:
//   When to visit left-subtree?
//   Only if current root.val > k1. Otherwise, we know all keys in left-subtree < k1, no need to visit
//   When to visit left-subtree?
//   Only if current root.val < k2. Otherwise, we know all keys in right-subtree > k1, no need to visit


// Base case:
// null
// val -> check if val within [k1, k2]

// Recursive case:
// Recurisvely check the left-subtree and right-subtree when needed

// Time Complexity: O(N), where N is the # of nodes in the BST
// Space Complexity (call stack): O(H), where H is the height of the BST 

/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */

 public class Solution {
    /**
     * @param root: param root: The root of the binary search tree
     * @param k1: An integer
     * @param k2: An integer
     * @return: return: Return all keys that k1<=key<=k2 in ascending order
     */
    public List<Integer> searchRange(TreeNode root, int k1, int k2) {
       List<Integer> result = new ArrayList<>();
       searchRangeHelper(root, k1, k2, result);
       return result;
    }

    private void searchRangeHelper(TreeNode root, int k1, int k2, List<Integer> result) {
        // Base case:
        if (root == null) {
            return;
        } 

        // Recursive case:

        // Left-Subtree Traversal
        if (root.val > k1) {
            searchRangeHelper(root.left, k1, k2, result);
        }

        // Root
        if (root.val >= k1 && root.val <= k2) {
            result.add(root.val);
        }

        // Right-Subtree Traversal
        if (root.val < k2) {
            searchRangeHelper(root.right, k1, k2, result);
        }
    }
}

// Time Analysis:

// T(n) = 2 * T(n/2) + O(1)
// ...
// Total cost: O(N)