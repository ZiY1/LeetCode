class Solution {
    // Time Complexity: O(logN)
    // Space Complexity: O(1)
    public int findPeakElement(int[] nums) {
        // Idea:
        // Compare with the element on the left to determine the peak
        // Notice special case of the first element

        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (mid == 0) {
                // search right but notice current mid could be peak
                left = mid + 1;
            } else if (nums[mid] > nums[mid - 1]) {
                // search right but notice current mid could be peak
                left = mid + 1;
            } else if (nums[mid] < nums[mid - 1]) {
                // search left and current mid can not be peak
                right = mid - 1;
            }
        }

        // We can assure that there will be at least one peak in input array
        return left - 1;
    }
}