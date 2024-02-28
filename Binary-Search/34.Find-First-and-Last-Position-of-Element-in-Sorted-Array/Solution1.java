
class Solution {
    // Time Complexity: O(logN)
    // Space Complexity: O(1)
    public int[] searchRange(int[] nums, int target) {
        int left = leftBound(nums, target);
        int right = rightBound(nums, target);

        return new int[] { left, right };
    }

    // [l, r) -> [l, m) [m+1, r)
    private int leftBound(int[] nums, int target) {
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

        if (left < 0 || left >= nums.length) {
            return -1;
        }

        if (nums[left] == target) {
            return left;
        }

        return -1;
    }

    // [l, r) -> [l, m) [m+1, r)
    private int rightBound(int[] nums, int target) {
        int left = 0;
        int right = nums.length;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }
        }

        if (left - 1 < 0 || left - 1 >= nums.length) {
            return -1;
        }

        if (nums[left - 1] == target) {
            return left - 1;
        }

        return -1;
    }
}

// We we must stick to the template, sometimes I feel like right = mid - 1 is
// also correct?
// 0 1 2 3 4 5 6
// [1, 2, 3, 4, 5, 6, 7]

// Is [left, mid - 1) [mid + 1, r) correct?
// No
// left = 0, right = 7, mid = 3
// [0, 2) [4, 7) where 2 is skipped/unsearched

// Can we init right = nums.length - 1?
// No
// 0
// [1], target = 1
// left = 0, right = 0
// We check left - 1, where this element is skipped/unsearched