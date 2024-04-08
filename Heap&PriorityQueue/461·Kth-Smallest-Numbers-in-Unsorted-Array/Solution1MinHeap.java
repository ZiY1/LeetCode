// Assumptions:
// 1. k starts at 1
// 2. k < size of the input array
// 3. The relationship between N and K discussed at bottom

// Data Strcture: Min Heap
// Min heap can help us find the kth smallest by keep polling its root element 
// because the root of the min heap is always the smallest

// Example & Algorithm:
// num = [3, 4, 1, 2, 5], k = 3
// 1. Build min heap of size N based on the input array
//       3              1
//    4     1   ->   2     3
//  2   5          4   5

// 2. Keep polling the root (min-heapify) (k - 1) times
//       3
//    4     5

// 3. Peek the root which is the kth smallest element

// Time Complexity: O(N + KlogN)
// Space Complexity: O(N)

public class Solution {
    /**
     * @param k: An integer
     * @param nums: An integer array
     * @return: kth smallest element
     */
    public int kthSmallest(int k, int[] nums) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int i = 0; i < nums.length; i++) {
            minHeap.offer(nums[i]);
        } 

        for (int i = 0; i < k - 1; i++) {
            minHeap.poll();
        }

        return minHeap.peek();
    }
}

// Time Analysis:

// 1. Build a min heap of size N -> O(N)
// 2. Poll the root, and internally perform min-heapify (K - 1) times -> O((K - 1)logN)
// Total Cost: O(N + KlogN)

// Best Case: K is some small constant
// Time = O(N + ClogN) = O(N)

// Worst Case: K is approaching N
// Time = O(N + NlogN) = O(NlogN)