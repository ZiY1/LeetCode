class Solution {
    // Time Complexity: O(NlogC)
    // Space Complexity: O(1)
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int left = matrix[0][0];
        int right = matrix[n - 1][n - 1] + 1;

        while (left < right) {
            int mid = left + (right - left) / 2;
            int count = countSmallerOrEqualTo(matrix, mid);

            if (count == k) {
                right = mid;
            } else if (count > k) {
                right = mid;
            } else if (count < k) {
                left = mid + 1;
            }
        }

        // Based on the question, we are sure there must exist kth smallest
        // Otherwise, we need to perform some checks
        return left;
    }

    private int countSmallerOrEqualTo(int[][] matrix, int target) {
        int n = matrix.length;
        int count = 0;

        int row = 0;
        int col = n - 1;

        while (row < n && col >= 0) {
            int curr = matrix[row][col];
            if (target < curr) {
                col--;
            } else {
                count += (col + 1);
                row++;
            }
        }
        return count;
    }
}