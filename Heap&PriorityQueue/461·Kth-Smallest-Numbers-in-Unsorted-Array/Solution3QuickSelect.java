// Assumption:
// 1. k starts at 1
// 2. k < the size of input array

// Example & Algorithm: Randomized Quick Selection
// nums = [3, 4, 1, 2, 5], k = 3

// Base case: if start == end: 
// Then there is one element left, which is the answer

// Recursive case:
// 1. Using a randomized pivot at q to partition the array from index start to end s.t.
// nums[start, q - 1] <= nums[q] < nums[q + 1, end]

// Lamuto's Partiton: Two pointers
// i: elements on the left-hand side of i (including i) are elements <= pivot
// j: elements on the right-hand side of j, from j to end - 1 (including j) are elements > pivot
//   0 1 2 3 4
//   3 1 2 4 5 pivot = 4
//       i q j

// 2. Calculate # of elements size from start to q: q - start + 1
// size = 3 - 0 + 1 = 4

// 3. Three cases:
//    a. If size = k, nums[k] is the kth smallest element
//    b. If size < k, the kth smallest element must be on the right half
//       Recursively quick select the right half
//    c. If size > k, the kth smallest element must be on the left half
//       Recursively quick select the left half
//       size = 4 > k = 3
//       0 1 2 
//       3 1 2
//       s   e

// Time Complexity:
// Best case: O(N)
// Worst case: O(N^2)
// Space Complexity: O(1)
public class Solution {
    /**
     * @param k: An integer
     * @param nums: An integer array
     * @return: kth smallest element
     */
    public int kthSmallest(int k, int[] nums) {
        return randomizedQuickSelect(nums, k, 0, nums.length - 1);
    }

    private int randomizedQuickSelect(int[] nums, int k, int start, int end) {
        // Base case:
        if (start == end) {
            return nums[start];
        }

        // Recursive case:
        int q = randomizedPartition(nums, start, end);

        int partitionSize = q - start + 1;

        if (partitionSize == k) {
            return nums[q];
        } else if (partitionSize < k) {
            // Search right half
            
            // E.g. k = 5
            // 0 1 2 3 4 5 6
            // 4 5 1 5 7 6 9
            //       q

            // 0 1 2 
            // 7 6 9
            return randomizedQuickSelect(nums, k - partitionSize, q + 1, end);
            // Q: Why k - partitionSize?
        } else {
            return randomizedQuickSelect(nums, k, start, q - 1);
        }
    }

    private int randomizedPartition(int[] nums, int start, int end) {
        Random random = new Random();
        // Generate a random index between [0, end - start + 1)
        int randomIndex = start + random.nextInt(end - start + 1);
        // Swap the last element (used as pivot in partition) with the random index
        swap(nums, randomIndex, end);

        return lamutoPartition(nums, start, end);
    }

    private int lamutoPartition(int[] nums, int start, int end){
        int pivot = nums[end];
        int i = start - 1;

        // Iterate from [start, end - 1]
        for (int j = start; j < end; j++) {
            if (nums[j] <= pivot) {
                i++;
                swap(nums, i, j);
            }
        }

        // Swap last element with element at i + 1
        swap(nums, i + 1, end);

        return i + 1;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

// Time Analysis:

// Best case: assume the input array is partitoned perfectly balance
// Recurrence Formula:
// T(n) = T(n/2) + O(n)
// T(n/2) -> search either left half or right half
// O(n) -> running time of partiton

// Recursion Tree:
//  Level        Cost
//    0      o     N
//    1      o     N/2
//    2      o     N/4
// ...
// log(2,n)  o     1
// Total Cost = N + N/2 + N/4 + N/8 + ... + N/(2^(log(2, n))) = N(1 / (1 / 2)) = 2N = O(N)
// Geometric Series: 
// S = a (1 / (1 - r))
// a: first term
// r: common ratio

// Master Method:
// T(n) = T(n/2) + O(n)
// n^(log(b,a)) = n^(log(2,1)) = n^0 = 1
// f(n) = O(n)
// Total Cost = O(N)

// Worst case: assume the input array is partitoned extremely unbalanced (small chance)
// Recurrence Formula:
// T(n) = T(n - 1) + O(n)

// Recursion Tree:
//  Level        Cost
//    0      o     N
//    1      o     N - 1
//    2      o     N - 2
// ...
//    N      o     1
// Total Cost = N + (N - 1) + (N - 2) + ... + 1 = N(N + 1) / 2 = O(N^2)

