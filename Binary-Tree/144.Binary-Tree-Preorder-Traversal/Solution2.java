// Assumption:
// Input binary tree is not null

// Example:
//                    1
//          2                   3
//     4        5         6           7
// null null null null  null null  null null

// 1, 2, 4, 5, 3, 6, 7

// Algorithm: 
// Base case:
// Return when current node is null
// Recursive case:
// 1. Print root
// 2. Recursively print left subtree
// 3. Recursivelt print right subtree

// Time Complexity: O(N), where N is the number of nodes in the input
// Space Complexity (call stack): O(Height) 
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
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        preorderTraversalHelper(root, result);
        return result;
    }

    private void preorderTraversalHelper(TreeNode root, List<Integer> result) {
        // Base case:
        //!!! the base case of binary tree is not the leaf node, it is the children of leaf node which is null
        if (root == null) {
            return;
        }

        // Recursive case
        result.add(root.val);
        preorderTraversalHelper(root.left, result);
        preorderTraversalHelper(root.right, result);
    }
}