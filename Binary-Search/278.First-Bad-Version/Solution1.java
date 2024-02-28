/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

      public class Solution extends VersionControl {
        // Time Complexity: O(logN)
        // Space Complexity: O(1)
        public int firstBadVersion(int n) {
            // Goal: find first bad version
            // 1 2  3   4   5   6
            // 1 2 bad bad bad bad
            // first bad version is 3
            // Turn this problem to problem we know: find left bound of the bad version
    
            // Since n <= 2^31 - 1 -> n + 1 causes overflow
            // Workaround: Left Inclusive, Right Inclusive
            // [left, right] -> [left, mid - 1] [mid + 1, right]
    
            int left = 1;
            int right = n; 
    
            while (left <= right) {
                int mid = left + (right - left) / 2;
                
                if (isBadVersion(mid)) {
                    // If current version is a bad version, search left half
                    right = mid - 1;
                } else {
                    // !!! If current version is not a bad version, we know for sure the bad version must be on right half
                    left = mid + 1;
                }
            }
    
            if (left < 0 || left > n) {
                return -1;
            }
    
            return left;
        }
    }