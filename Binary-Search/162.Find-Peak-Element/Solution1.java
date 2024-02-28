class Solution {
    // Time Complexity: O(logN)
    // Space Complexity: O(1)
    public int findPeakElement(int[] nums) {
        // Idea:
        // Compare with the element on the right to determine the peak
        // Notice special case of the first element

        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] > nums[mid + 1]) {
                // search left but notice current mid could be peak
                right = mid;
            } else if (nums[mid] < nums[mid + 1]) {
                // search right but notice current mid must not be peak
                left = mid + 1;
            }
        }

        // There is no need for post processing since we did not intentionally exclude the answer
        return left;
    }
}