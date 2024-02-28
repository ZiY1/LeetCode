class Solution {
    // Time Complexity: O(logN)
    // Space Complexity: O(1)
    public int lastPosition(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }

        int left = 0;
        int right = nums.length;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                left = mid + 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid;
            }
        }

        if (left - 1 < 0 || left - 1 >= nums.length ) {
            return -1;
        }

        if (nums[left - 1] == target) {
            return left - 1;
        }

        return -1;
    }
}