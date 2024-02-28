class Solution {
    // Time Complexity: O(M * log(M * N))
    // Space Complexity: O(1)
    public int findKthNumber(int m, int n, int k) {
        // Idea: binary search by value

        int left = 1;
        int right = (m * n);

        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            int count = countSmallerThanOrEqualTo(m, n, mid);

            if (count == k) {
                right = mid;
            } else if (count > k) {
                right = mid;
            } else if (count < k) {
                left = mid + 1;
            }
        }

        // Post-processing
        int countL = countSmallerThanOrEqualTo(m, n, left);
        int countR = countSmallerThanOrEqualTo(m, n, right);

        // Must check countL(left) first
        if (countL >= k) {
            return left;
        } else if (countR >= k) {
            return right;
        } else {
            return -1;
        }
    }

    private int countSmallerThanOrEqualTo(int m, int n, int target) {
        int count = 0;
        for (int i = 1; i <= m; i++) {
            int currCount = target / i;
            count += Math.min(n, currCount);
        }
        return count;
    }
}