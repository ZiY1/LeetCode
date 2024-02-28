class Solution {
    // Time Complexity: O(logN)
    // Space Complexity: O(1)
    public int search(int[] nums, int target) {
        // [l, r) -> [l, m) [m+1, r)
        int left = 0;
        int right = nums.length;

        while (left < right) { 
            // Can left <= right? No!
            //  0  1
            // [1, 2], target = 0
            // l = 0, r = 2, m = 1
            // l = 0, r = 0, m = 0
            // l = 0, r = 0, m = 0
            // stuck here, infinite loop
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target){
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid;
                // Can right = mid - 1? No!
                //  0  1
                // [1, 2], target = 1
                // l = 0, r = 2, m = 1
                // l = 0, r = 0, m = 0   -> left = right -> stop -> nums[0] unsearched
            } 
        }
        return -1;
    }
}