class Solution {
    // Time Complexity: O(logN)
    // Space Complexity: O(1)
    public int searchInsert(int[] nums, int target) {
        // A little bit more post-processing compare to solution 1

        int left = 0;
        int right = nums.length - 1;

        while (left < right - 1) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }
        }

        // Post-processing
        if (nums[left] >= target) {
            return left;
        }

        if (nums[right] >= target) {
            return right;
        }

        return nums.length;
    }
}