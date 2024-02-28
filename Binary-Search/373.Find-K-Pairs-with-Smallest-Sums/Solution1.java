class Solution {
    // Time Complexity: O((M+N) * logC)
    // Space Complexity: O(K)
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        // Idea:
        // 1. Use binary search by value to find the kth smallest sum
        // 2. Add pairs whose sum <= kthSum
        //   2.1 first add pairs whose sum < kthSum, if total pairs added = k, done
        //   2.2 if total pairs added < k, add pairs whose sum = kthSum until total = k

        // Treat two arrays as a single matrix, where each cell represent the nums1[i] + nums2[j]
        //   1 2 3
        // 4 5 6 7
        // 5 6 7 8
        // 6 7 8 9

        // 1. Use binary search by value to find the kth smallest sum
        int n = nums2.length - 1;
        int m = nums1.length - 1;

        // [left, right)
        long left = nums1[0] + nums2[0];
        long right = nums1[m] + nums2[n] + 1;

        while (left < right) {
            long mid = left + (right - left) / 2;
            long count = countSmallerOrEqualTo(nums1, nums2, mid); // find number of elements(sum) <= mid

            if (count == k) {
                right = mid;
            } else if (count > k) {
                right = mid;
            } else if (count < k) {
                left = mid + 1;
            }
        }

        long kthSum = left;

        // 2. Add pairs whose sum <= kthSum

        List<List<Integer>> rest1 = new ArrayList<>();
        List<List<Integer>> rest2 = new ArrayList<>();
        
        for (int i = 0; i < nums1.length; i++) {
            int j = 0;
            while (j < nums2.length && nums1[i] + nums2[j] <= kthSum && rest1.size() <= k && rest2.size() <= k) {
                if (nums1[i] + nums2[j] < kthSum) {
                    List<Integer> tmp = new ArrayList<>();
                    tmp.add(nums1[i]);
                    tmp.add(nums2[j]);
                    rest1.add(tmp);
                } else if (nums1[i] + nums2[j] == kthSum) {
                    List<Integer> tmp = new ArrayList<>();
                    tmp.add(nums1[i]);
                    tmp.add(nums2[j]);
                    rest2.add(tmp);
                }
                j++;
            }
        }

        int numToAdd = Math.min(rest2.size(), k - rest1.size());

        for (int i = 0; i < numToAdd; i++) {
            rest1.add(rest2.get(i));
        }

        return rest1;
    }

    private long countSmallerOrEqualTo(int[] nums1, int[] nums2, long target) {
        //   1 2 3
        // 4 5 6 7
        // 5 6 7 8
        // 6 7 8 9

        long count = 0;
        // start at top right corner of the matrix
        int col = nums1.length - 1;
        int row = 0;

        while (row < nums2.length && col >= 0) {
            long currVal = nums1[col] + nums2[row];

            if (target < currVal) {
                // Elinimate the whole col
                col--;
            } else {
                // Count the row, then eliminate the row
                count += (col + 1);
                row++;
            }
        }

        return count;
    }
}

// Analyze Time Complexity:
// Binary search by value: O((M+N) * logC)
// Add elements: O(N)