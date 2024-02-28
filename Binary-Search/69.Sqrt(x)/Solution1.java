class Solution {
    // Time Complexity: O(logN)
    // Space Complexity: O(1)
    public int mySqrt(int x) {
        // Find a integer y s.t y^2 <= x

        // [left, right]
        int left = 0;
        int right = x;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            long midSqr = (long)mid * mid;
            if (midSqr == x) {
                return mid;
            } else if (midSqr > x) {
                right = mid - 1;
            } else if (midSqr < x) {
                left = mid + 1;
            }
        }

        return left - 1;
    }
}