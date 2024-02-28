class Solution {
    // Time Complexity: O(logN)
    // Space Complexity: O(1)
    public int findPeakElement(int[] nums) {
        // Idea:
        // Compare with the element on the left to determine the peak
        // Notice special case of the first element

        // When we use [left, right] template but with stop case left < right instead of left <= right
        // post processing needed since we will exclude the answer when updating the boundary for sake 
        // of following [left, right] template

        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
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

        // Post-processing needed since we did exclude the answer when updating the boundary
        // Compare left with the its left element
        if (left == 0) {
            return left;
        } else if (nums[left - 1] > nums[left]) {
            return left - 1;
        }
        return left;
    }
}