class Solution {
    // Time Complexity: O(NlogC)
    // Space Complexity: O(1)
    public int findKthLargest(int[] nums, int k) {
        int min = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < min) {
                min = nums[i];
            } else if (nums[i] > max) {
                max = nums[i];
            }
        }

        int left = min;
        int right = max;

        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            int count = countLargerOrEqualTo(nums, mid);

            if (count == k) {
                left = mid;
            } else if (count > k) {
                left = mid;
            } else if (count < k) {
                right = mid - 1;
            }
        }

        // Post-processing
        int countL = countLargerOrEqualTo(nums, left);
        int countR = countLargerOrEqualTo(nums, right);

        // We must check right count first
        // If both left and right count >= k, then right must be the answer
        if (countR >= k) {
            return right;
        }

        if (countL >= k) {
            return left;
        }

        return -1;
    }

    private int countLargerOrEqualTo(int[] nums, int target) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= target) {
                count++;
            }
        }

        return count;
    }
}