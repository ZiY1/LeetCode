// Assumption:

// Example:
// matrix     k = 8
// 1  5  9
// 10 11 13
// 12 13 15

// PQ
//  1
// Generated = {}

// PQ
//    5
// 10
// Generated = {1}

// PQ
//    9 
// 10   11
// Generated = {1, 5}

// PQ
//    10
// 11    13
// Generated = {1, 5, 9}

// PQ
//    11
// 13    12
// Generated = {1, 5, 9, 10}

// PQ
//    12
// 13    13    
// Generated = {1, 5, 9, 10, 11}

// PQ
//    13
// 13       
// Generated = {1, 5, 9, 10, 11, 12}

// PQ
//     13 -> 8th smallest element
//  15     
// Generated = {1, 5, 9, 10, 11, 12, 13} 

// Algorithm: Best First Search (BFS II OR Dijkstra Algo)
// 1. Initial State(Start Node):
// matrix[0][0] (min element)
// 2. Node Expansion/Generation Rule
// Expand matrix[row][col]
// generate matrix[row][col + 1]
// generate matrix[row + 1][col]
// 3. Termination Condition
// when (k - 1)-th element is poped our of PQ, the root of PQ(Min-Heap) is the kth smallest element
// 4. De-duplication
// use a 2D boolean matrix to mark if generated so that the same element will
// not be generated twice

// Data Structure: Priority Queue (Min Heap) + matrix
// Why use PQ?
// - PQ is used s.t all the cost of the nodes that are expanded are monotonically non-decreasing
// Why use matrix?
// - Deduplication OR keep track of the generated node

// Time Complexity: O(KlogK)
// Space Complexity: O(N^2 + K)
class Solution {

    class Cell {
        public final int row;
        public final int col;
        public final int val; // Used to compare in priority queue

        public Cell (int row, int col, int val) {
            this.row = row;
            this.col = col;
            this.val = val;
        }
    }

    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;

        // Keep track of which cell has been generated so it will no longer be generated again
        boolean[][] generated = new boolean[n][n];

        Comparator<Cell> cellComparator = new Comparator<>() {
            @Override
            public int compare(Cell cell1, Cell cell2) {
                return Integer.compare(cell1.val, cell2.val);
            }
        };

        PriorityQueue<Cell> pq = new PriorityQueue<>(cellComparator);

        // Enqueue the start cell
        pq.offer(new Cell(0, 0, matrix[0][0]));
        // Mark the start cell generated
        generated[0][0] = true;

        // Based on the PQ property that the elements poped out are monotonically non-decreasing,
        // We need to pop out (k - 1) times so that the kth smallest element will be the root
        while (k > 1) {
            // Expand and dequeue the root in PQ/Min-Heap
            Cell currCell = pq.poll();

            // Generate the current cell

            // Generate the right neighbor if allowed
            if (currCell.col + 1 < n && !generated[currCell.row][currCell.col + 1]) {
                // Enqueue right neighbor
                pq.offer(new Cell(currCell.row, currCell.col + 1, matrix[currCell.row][currCell.col + 1]));
                // Mark generated
                generated[currCell.row][currCell.col + 1] = true;
            }

            // Generate the bottom neighbor if allowed
            if (currCell.row + 1 < n && !generated[currCell.row + 1][currCell.col]) {
                // Enqueue the bottom neighbor
                pq.offer(new Cell(currCell.row + 1, currCell.col, matrix[currCell.row + 1][currCell.col]));
                // Mark generated
                generated[currCell.row + 1][currCell.col] = true;
            }

            k--;
        }

        // Now the root of the PQ/Min-Heap is the kth smallest
        return pq.peek().val;
    }
}

// Time Analysis:
// Total iterations: k - 1 since the PQ is dequeued at most k - 1 times
// For each iteration:
//  1. extract the min element -> logK
//  2. insert the at most 2 generated elements -> 2logK
// Total Time = O((K - 1) * 3logK) = O(KlogK)

// Space Analysis:
// PQ: O(K)
// Matrix: O(N^2)
// Total Space = O(K + N^2)

