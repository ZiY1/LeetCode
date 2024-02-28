class Solution {
    // Time Complexity: O(log(m * n))
    // Space Complexity: O(1)
    public boolean searchMatrix(int[][] matrix, int target) {
        // Binary search can be applied when:
        // The first integer of each row is greater than the last integer of the previous row.
        // Otherwise, matrix -> array is not sorted
        // Key: treat matrix as a 1D array, map 1D array index to matrix index

        int m = matrix.length;
        int n =  matrix[0].length;

        // [left, right)
        int left = 0;
        int right = m * n; // index of last element in 1D space

        while (left < right) {
            int mid = left + (right - left) / 2;
            // Map array index to matrix index
            int row = mid / n;
            int col = mid % n;

            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] < target) {
                left = mid + 1;
            } else if (matrix[row][col] > target) {
                right = mid;
            }
        }

        return false;
    }
}