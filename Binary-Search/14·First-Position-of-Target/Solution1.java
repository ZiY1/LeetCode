class Solution {
    // Time Complexity: O(logN)
    // Space Complexity: O(1)
    public int binarySearch(int[] nums, int target) {
        // [left, right)
        int left = 0;
        int right = nums.length;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                right = mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid;
            }
        }

        // Note that in this template, left could search out of bound to the right
        // becuase [left, right)
        if (left < 0 || left >= nums.length) {
            return -1;
        }

        if (nums[left] == target) {
            return left;
        }

        return -1;
    }
}

// This template will find:
// First index i such that nums[i] >= target

