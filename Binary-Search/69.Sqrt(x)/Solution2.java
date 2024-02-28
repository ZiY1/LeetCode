class Solution {
    // Time Complexity: O(logN)
    // Space Complexity: O(1)
    public int mySqrt(int x) {
        // Find a integer y s.t y^2 >= x

        int left = 0;
        int right = x;

        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            long midSqr = (long)mid * mid;
            if (midSqr == x) {
                return mid;
            } else if (midSqr > x) {
                right = mid - 1;
            } else if (midSqr < x) {
                left = mid;
            }
        }

        if (right * right <= x) {
            return right;
        }

        if (left * left <= x) {
            return left;
        } 

        return -1;
    }
}