class Solution {
    // Time Complexity: O(logN)
    // Space Complexity: O(1)
    public int search(int[] nums, int target) {
        int pivot = nums[0];
        int left = 0;
        int right = nums.length;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                // Supposed to search left half
                // 4 5 6 7 0 1 2
                // p   m     t
                if (target < pivot && nums[mid] >= pivot) {
                    // search right
                    left = mid + 1;
                } else {
                    right = mid;
                }
            } else if (nums[mid] < target) {
                // Supposed to search right half
                // 4 5 6 7 0 1 2
                // p   t     m  
                if (target >= pivot && nums[mid] < pivot) {
                    // Search left
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
        }

        if (left < 0 || left >= nums.length) {
            return -1;
        }

        if (nums[left] == target) {
            return left;
        }

        return -1;
    }
}