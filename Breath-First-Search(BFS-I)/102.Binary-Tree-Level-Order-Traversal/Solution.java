// Assumption:

// Data Structure: Queue
// Queue is used to :
// 1. keep track of generated nodes (neighbors) of the current node in BFS
// 2. Determine which node to process next

// Example & Algorithm (BFS I):
//     3
//    / \
//   9  20
//  / \ / \
//  2 4 15 7
// queue = {3}
// queue.size() = 1 -> [[3]]
// queue = {9, 20}
// queue.size() = 2 -> [[3], [9, 20]]
// queue = {2, 4, 15, 7}
// queue.size() = 4 -> [[3], [9, 20], [2, 4, 15, 7]]

// 1. Define a queue, enqueue the root, record the current size of the queue
// 2. While queue is not empty
//  2.1 Record the size of current queue
//  2.2 Explore (size of current queue) number of nodes in the queue, for each node X:
//      a. Add X to a temp list
//      b. Dequeue X
//      c. Generate X's child/children, enqueue generated child/children
//  2.3 Add temp to result

// Time Complexity: O(V), where V represents total # of nodes/vertices
// Space Complexity: O(V)

// Summary:
// 1. BFS I uses FIFO queue
// 2. BFS I is used to determine the relationship between adjacent level/layers

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
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        // NEVER EVER forget to check the corner cases
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();

        // First enqueue root
        queue.offer(root);

        // When queue is empty, we have BF-searched all the nodes
        while (!queue.isEmpty()) {

            // !!! size = # of generated nodes in the next layer
            int currLevelSize = queue.size();

            List<Integer> temp = new ArrayList<>();
            for (int i = 0; i < currLevelSize; i++) {
                TreeNode currNode = queue.poll();
                temp.add(currNode.val);

                if (currNode.left != null) {
                    queue.offer(currNode.left); // enqueue
                }
                if (currNode.right != null) {
                    queue.offer(currNode.right); // enqueue
                }
            }

            result.add(temp);
        }

        return result;
    }
}

// Time Analysis:

// BFS visited each vertice/node once in the graph -> V
// For each vertice/node, each adajacent node is visited once, thus all edges are visited once -> E
// Total time for BFS: O(V + E)

// In this case, our graph is a bianry tree, s.t. the # of edges E is 
// always one less than the # of vertices V: E = V - 1
// Total Cost: O(V + (V - 1)) = O(V)
// Intuitively, for each vertice/node there are either 0 child, 1 child 
// or 2 children which are constant # of operations

// Space Analysis
// The maximum amount of extra space required is dominated by 
// the maximum number of nodes that can appear at any level of the tree 
// (which happens at the widest level of the tree). 
// In the worst-case scenario (a perfectly balanced binary tree), 
// this would be approximately V/2 nodes



