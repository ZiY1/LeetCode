class Solution {
    // Time Complexity: O(logN)
    // Space Complexity: O(1)
    public static int findLargestNumSmallerThanTarget(int[] nums, int target) {
        // Similar to lower bound question
        // [left, right) -> [left, mid) [mid + 1, right)
        int left = 0;
        int right = nums.length;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                right = mid;
            } else if (nums[mid] > target) {
                right = mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }
        }

        if (left - 1 < 0 || left - 1 >= nums.length) {
            return -1;
        }

        return left - 1;
    }

    public static int findLargestNumSmallerThanTarget2(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }

        int left = 0;
        int right = nums.length - 1;

        while (left < right - 1) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                right = mid - 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid;
            }
        }

        if (nums[right] < target) {
            return right;
        }

        if (nums[left] < target) {
            return left;
        }

        return -1;
    }

}
