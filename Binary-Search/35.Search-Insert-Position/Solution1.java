class Solution {
    // Time Complexity: O(logN)
    // Space Complexity: O(1)
    public int searchInsert(int[] nums, int target) {
        // Observation:
        // 1. if the target is found: return the index 
        // 2. if the target is not found: return the index to be inserted,
        // which is the position of first element larger than target!
        
        // Find the lower bound of target -> [left, right) template will solve the problem in a clean way

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

        return left;
    }
}
