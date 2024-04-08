// Assumptions:
// 1. k starts at 1
// 2. k < size of the input array
// 3. The relationship between N and K discussed at bottom

// Data Strcture: Max Heap
// - We can use a max heap of size k to represent the k smallest element 
// - The root is the largest element among the k samllest element, which is 
// the kth smallest element

// Example & Algorithm:
// num = [3, 4, 1, 2, 5], k = 3
// 1. Build a max heap of size k using the first k element in the input array
//       3              4
//    4     1   ->   3     1

// 2. From the (k + 1)th element to the last element, for each element X:
//    If X >= root, do nothing because we want the k smallest
//    Otherwise, poll the root, offer X (max-heapify)
//       3
//    2     1

// 3. Peek the root element

// Time Complexity: O(K + (N - K)logK)
// Space Complexity: O(K)

public class Solution {
    /**
     * @param k: An integer
     * @param nums: An integer array
     * @return: kth smallest element
     */
    public int kthSmallest(int k, int[] nums) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());

        // Build a max heap of size K
        for (int i = 0; i < k; i++) {
            maxHeap.offer(nums[i]);
        }

        // Update the max heap
        for (int i = k; i < nums.length; i++) {
            // Current element < root of the max heap 
            if (nums[i] < maxHeap.peek()) {
                maxHeap.poll();
                maxHeap.offer(nums[i]);
            }
        }

        return maxHeap.peek();
    }
}

// Time Analysis:

// 1. Build a max heap of size K -> O(K)
// 2. From k + 1 to last element, possible
//  a. poll  -> log(K)
//  b. offer -> log(K)
// Total Cost: O(K + (N - K)logK)

// Assumption of different relationship between N and K:

// Best Case: K is some small constant
// O(K + (N - K)logK) = O(N * logK)

// Worst Case: K is about N/2
// O(N + (N - N/2)logN) = O(NlogN)