// Assumption:
// Input binary tree is not null

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
// Space Complexity (call stack): O(H), where H is the height of the input binary tree

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
        // Base case:
        //!!! the base case of binary tree is not the leaf node, it is the children of leaf node which is null
        if (root == null) {
            return new ArrayList<>();
        }

        // Recurisve case:
        List<Integer> result = new ArrayList<>();

        List<Integer> leftSubTreeResult = postorderTraversal(root.left);

        List<Integer> rightSubTreeResult = postorderTraversal(root.right);

        result.addAll(leftSubTreeResult);
        result.addAll(rightSubTreeResult);
        result.add(root.val);

        return result;

    }
}