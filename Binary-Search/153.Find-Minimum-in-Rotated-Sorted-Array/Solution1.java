class Solution {
    // Time Complexity: O(logN)
    // Space Complexity: O(1)
    public int findMin(int[] nums) {
        // Based on the assumptions:
        // 1. rotated between 1 and n times
        // 2. unique elements
        // Obervation:
        // 1. If rotated less than n times, then nums[0] > nums[nums.length - 1]
        // we shall make use of nums[0] as pivot:
        // Else if rotated exactly n times, then nums[0] < nums[nums.length - 1]
        // just return nums[0] as it is the min

        // <= deal with the case of single element and ascending order elements 
        if (nums[0] <= nums[nums.length -1]) {
            return nums[0];
        }

        int pivot = nums[0];

        int left = 0;
        int right = nums.length;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == pivot) {
                // search right
                left = mid + 1;
            } else if (nums[mid] > pivot){ 
                // search right
                left = mid + 1;
            } else if (nums[mid] < pivot) {
                // search left
                right = mid;
            }
        }

        // We can assure that there is one and only one minimum based on the question
        return nums[left];
        
    }
}