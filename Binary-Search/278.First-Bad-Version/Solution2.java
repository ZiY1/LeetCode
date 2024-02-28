/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

      public class Solution extends VersionControl {
        // Time Complexity O(logN)
        // Space Complexity O(1)
        public int firstBadVersion(int n) {
            int left = 1;
            int right = n;
    
            while (left < right - 1) {
                int mid = left + (right - left) / 2;
                if (isBadVersion(mid)) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
    
            // Post-processing for searching left bound
            // First check if left is the answer
            // Then check if right is the answer
            
            if (isBadVersion(left)) {
                return left;
            }
    
            if (isBadVersion(right)) {
                return right;
            }
    
            return -1;
        }
    }