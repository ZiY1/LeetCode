// Assumption:
// Input BST is not null

// Example:
//                    1
//          2                   3
//     4        5         6           7
// null null null null  null null  null null

// 4, 2, 5, 1, 6, 3, 7

// Algorithm: 
// Base case:
// Return when current node is null
// Recursive case:
// 1. Recursively print left subtree
// 2. Print root
// 3. Recursivelt print right subtree

// Time Complexity: O(N), where N is the number of nodes in the input
// Space Complexity (call stack): O(H), where H is the height of the input BST

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
    public List<Integer> inorderTraversal(TreeNode root) {
        // Base case:
        //!!! the base case of BST is not the leaf node, it is the children of leaf node which is null
        if (root == null) {
            return new ArrayList<>();
        }

        // Recurisve case:
        List<Integer> result = new ArrayList<>();

        List<Integer> leftSubTreeResult = inorderTraversal(root.left);

        List<Integer> rightSubTreeResult = inorderTraversal(root.right);

        result.addAll(leftSubTreeResult);
        result.add(root.val);
        result.addAll(rightSubTreeResult);

        return result;
    }
}