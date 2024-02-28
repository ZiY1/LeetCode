class Solution {
    // Time Complexity: O(logN)
    // Space Complexity: O(1)
    public int binarySearch(int[] nums, int target) {
        // [left, right]
        int left = 0;
        int right = nums.length - 1;

        while (left < right - 1) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                right = mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid;
            }
        }

        // Post-processing for searching left bound
        // First check if left is the answer
        // Then check if right is the answer
        
        if (nums[left] == target) {
            return left;
        }

        if (nums[right] == target) {
            return right;
        }

        return -1;
    }
}


