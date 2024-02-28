class Solution {
    // Time Complexity: O(NlogC)
    // Space Complexity: O(1)
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int left = matrix[0][0];
        int right = matrix[n - 1][n - 1];

        while (left < right - 1) {
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

        System.out.println(left);
        System.out.println(right);

        // Post-processing
        int countL = countSmallerOrEqualTo(matrix, left);
        int countR = countSmallerOrEqualTo(matrix, right);

        // We must check left first
        // In the case both of left and right count > k, the left must be the answer
        if (countL >= k) {
            return left;
        }

        if (countR >= k) {
            return right;
        }

        return -1;
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