// Assumption:
// The graph may be disconnected

// Example:
// 0 - 1
// | \ |
// 3 - 2

// Queue = {0}
// Set A: 0
// Set B: 
// Visited = {}

// Queue = {1, 2, 3}
// Set A: 0
// Set B: 1, 2, 3
// Visited = {0}

// Queue = {2, 3}
// Set A: 0
// Set B: 1, 2, 3
// Visited = {0, 1}
// 1 is in Set B and its adjacent nodes should be in Set A. 
// However, 2 is in same set as 1. Thus, the graph is not bipartite

// Key Idea:
// Run BFS s.t assign the current node and its adjacent nodes to two opposite sets.
// When a current node and its adjacent node appear in same set, then we know the graph is not bipartite.

// Data Structure: Queue
// Queue is used in BFS to:
// 1. Keep track of generated nodes of the current node
// 2. Determine which node to process/expand next

// ALgorithm: BFS I
// Note that since we assume the graph may be disconnected, we must perform BFS on each connected components
// 1. Enque the start node in the current connected component, assign a set
// 2. While the queue is not empty
// 3. Deque a node U, and expand U
// 4. Generate U's all adjacent nodes, for each adjacent node V:
//  a. If V and U in same set, then the graph is not bipartite
//  b. If V is not visited and not yet assigned to a set, assign it to the opposite set of U
//     b1. Enqueu V

// Time Complexity: O(V + E), where V represents the # of vertices/nodes in the graph, E represent # of edges in the graph
// Space Complexity: O(V)

// Summary:
// 1. BFS I uses FIFO queue
// 2. BFS I is used to determine the relationship between adjacent level/layers
//    - Here we determine if nodes between adjacent level are in the same set or not
// 3. When the graph is disconnected, we are not running BFS on each node, we run BFS on each connected component.
// Thus the time complexity is same as the graph is connected: O(V + E)
class Solution {
    public boolean isBipartite(int[][] graph) {
        // Array visited has three states:
        // 1. 0: unvisited
        // 2. 1: visited and belong to set 1
        // 3. -1: visited and belong to set -1
        int[] visited = new int[graph.length];

        Queue<Integer> queue = new LinkedList<>();

        for (int startNode = 0; startNode < graph.length; startNode++) {
            // If the current start node has been processed/visited, do nothing
            if (visited[startNode] != 0) continue;

            // Enqueue start node
            queue.offer(startNode);
            // Mark visited and assign to a set
            visited[startNode] = 1;

            // When there are nodes left unexpanded
            while (!queue.isEmpty()) {
                // Deque
                int currNode = queue.poll();
                
                // Expand the current node
                for (int adjNode : graph[currNode]) {
                    // When the adjacent node and the current node are in the same set
                    if (visited[adjNode] == visited[currNode]) return false;

                    // When the adjacent node has not been visited and assigned to a set yet
                    if (visited[adjNode] == 0) {
                        // Mark visited and assign a opposite set as the current node
                        visited[adjNode] = -visited[currNode];
                        // !!! Don't forget to Enque 
                        queue.offer(adjNode);
                    }
                }
            }
        }

        return true;
    }
}