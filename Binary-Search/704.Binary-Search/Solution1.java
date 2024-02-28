class Solution {
    // Time Complexity: O(logN)
    // Space Complexity: O(1)
    public int search(int[] nums, int target) {
        // [l, r]
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target){
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } 
        }
        return -1;
    }
}