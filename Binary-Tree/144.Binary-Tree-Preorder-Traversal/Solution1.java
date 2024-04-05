// Assumption:
// Input BST is not null

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
        // Base case:
        //!!! the base case of BST is not the leaf node, it is the children of leaf node which is null
        if (root == null) {
            return new ArrayList<>();
        }

        // Recursive case:
        List<Integer> result = new ArrayList<>();

        result.add(root.val);

        List<Integer> leftSubTreeResult = preorderTraversal(root.left);
        List<Integer> rightSubTreeResult = preorderTraversal(root.right);

        result.addAll(leftSubTreeResult);
        result.addAll(rightSubTreeResult);

        return result;
    }
}