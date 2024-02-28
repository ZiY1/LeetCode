class Solution {
    // Time Complexity: O(logN)
    // Space Complexity: O(1)
    public int closestNumber(int[] a, int target) {
        // A cleaner way compare to solution 1

        int left = 0;
        int right = a.length - 1;

        while (left < right - 1) {
            int mid = left + (right - left) / 2;

            if (a[mid] == target) {
                return mid;
            } else if (a[mid] < target) { 
                left = mid;
                // left = mid + 1 ? 
                // No! mid might be the cloest element, cannot exclude
            } else if (a[mid] > target) {
                right = mid;
                // right = mid - 1 ? 
                // No! mid might be the cloest element, cannot exclude
            }
        }

        // Post-processing
        if (Math.abs(a[left] - target) < Math.abs(a[right] - target)) {
            return left;
        } else {
            return right;
        }

    }
}
