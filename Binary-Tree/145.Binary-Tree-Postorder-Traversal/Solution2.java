// Assumption:
// Input BST is not null

// Example:
//                    1
//          2                   3
//     4        5         6           7
// null null null null  null null  null null

// 4, 5, 2, 1, 6, 3, 7

// Algorithm: 
// Base case:
// Return when current node is null
// Recursive case:
// 1. Recursively print left subtree
// 2. Recursivelt print right subtree
// 3. Print root

// Time Complexity: O(N), where N is the number of nodes in the input
// Space Complexity (call stack): O(H), where H is the height of the input BST

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
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        postorderTraversalHelper(root, result);
        return result;
    }

    private void postorderTraversalHelper(TreeNode root, List<Integer> result) {
        // Base case:
        //!!! the base case of BST is not the leaf node, it is the children of leaf node which is null
        if (root == null) {
            return;
        }

        // Recursive case:
        postorderTraversalHelper(root.left, result);

        postorderTraversalHelper(root.right, result);

        result.add(root.val);
    }
}