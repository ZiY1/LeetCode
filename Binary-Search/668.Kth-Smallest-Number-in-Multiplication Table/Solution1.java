class Solution {
    // Time Complexity: O(M * log(M * N))
    // Space Complexity: O(1)
    public int findKthNumber(int m, int n, int k) {
        // Idea: binary search by value

        int left = 1;
        int right = (m * n) + 1;

        while (left < right) {
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

        return left;
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