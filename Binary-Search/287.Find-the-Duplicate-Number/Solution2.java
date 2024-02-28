class Solution {
    // Time Complexity: O(NlogN)
    // Space Complexity: O(1)
    public int findDuplicate(int[] nums) {
        // Idea: binary search by value
        // 1. Guess a value
        // 2. Count the number of elements <= guess
        // 2.1 If count = guess, duplicate must > guess, search right half
        // 2.2 If count > guess, duplicate <= guess, search left half
        // 2.3 If count < guess, duplicate must > guess, search right half

        // Given nums containing n + 1 ints and in range [1, n]
        int left = 1;
        int right = nums.length - 1;

        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            int count = countLessThanOrEqualTo(nums, mid);

            if (count == mid) {
                left = mid + 1;
            } else if (count > mid) {
                right = mid;
            } else if (count < mid) {
                left = mid + 1;
            }
        }

        // Post-processing
        int countL = countLessThanOrEqualTo(nums, left);
        int countR = countLessThanOrEqualTo(nums, right);

        if (countL > left) {
            return left;
        }

        if (countR > right) {
            return right;
        }

        return -1;
    }

    private int countLessThanOrEqualTo(int[] nums, int target) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= target) {
                count++;
            }
        }
        return count;
    }
}