class Solution {
    // Time Complexity: O(logN)
    // Space Complexity: O(1)
    public int lastPosition(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }

        int left = 0;
        int right = nums.length - 1;

        while (left < right - 1) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                left = mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            }
        }

        // Post-processing for searching right bound
        // First check if right is the answer
        // Then check if left is the answer
        
        if (nums[right] == target) {
            return right;
        }

        if (nums[left] == target) {
            return left;
        }

        return -1;
    }
}
