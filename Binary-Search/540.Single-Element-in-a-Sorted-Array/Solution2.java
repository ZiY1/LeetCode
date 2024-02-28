class Solution {
    // Time Complexity: O(logN)
    // Space Complexity: O(1)
    public int singleNonDuplicate(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }

        int left = 0;
        int right = nums.length - 1;

        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (isNonDuplicate(nums, mid)) {
                return mid;
            } else if (isLeftDuplicateNumIdxEven(nums, mid)) {
                left = mid + 1;
            } else if (!isLeftDuplicateNumIdxEven(nums, mid)) {
                right = mid - 1;
            }
        }

        // Post-processing
        if (isNonDuplicate(nums, left)) {
            return nums[left];
        }

        if (isNonDuplicate(nums, right)) {
            return nums[right];
        }

        return -1;
    }

    private boolean isNonDuplicate(int[] nums, int index) {
        if (index == 0) {
            return nums[index] != nums[index + 1];
        } else if (index == nums.length - 1) {
            return nums[index] != nums[index - 1];
        } else {
            return (nums[index] != nums[index + 1] && nums[index] != nums[index - 1]);
        }
    }

    private boolean isLeftDuplicateNumIdxEven(int[] nums, int index) {
        if (index == 0) {
            return true;
        } else if (index == nums.length - 1) {
            return isEven(nums.length - 1);
        } 

        if (nums[index] == nums[index + 1]) {
            return isEven(index);
        } else if (nums[index] == nums[index - 1]) {
            return isEven(index - 1);
        }

        return false;
    }

    private boolean isEven(int num) {
        return (num % 2 == 0);
    }
}

//  0 1 2 3 4 5 6 7 8
// [1,1,2,3,3,4,4,8,8]

//  0 1 2 3  4  5  6 
// [3,3,7,7,10,11,11]

// Observation:
// DuplicateNum index: even, odd -> NonDuplicate must appears on the right
// DuplicateNum index: odd, even -> NonDuplicate must appears on the left