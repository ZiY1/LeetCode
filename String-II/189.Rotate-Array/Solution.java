// Clarification:

// Assumption:
// Input array is neither null nor empty

// index   0 1 2 3 4 5 6
// nums = [1 2 3 4 5 6 7] k = 9 % 7 = 2

// W1       W2
// 1 2 | 3 4 5 6 7

// After reversing the whole array
// W1       W2
// 7 6 | 5 4 3 2 1

// After reversing within each word
// W1       W2
// 6 7 | 1 2 3 4 5

// Approach: Two Pointers
// 1. Shift size: k % size of nums
// 2. If shift size = 0, no need to rotate
// 3. Treat the nums as two words:
// - First word: 0 ... k - 1
// - Second word: k ... n - 1

// Step 1: Reverse the whole array
// Step 2: Reverse within each word

// Time Complexity: O(N)
// Space Complexity: O(1)
class Solution {
    public void rotate(int[] nums, int k) {
        int shiftSize = k % nums.length;
        if (shiftSize == 0) {
            return;
        }

        // Step 1: Reverse the whole array
        reverseArray(nums, 0, nums.length - 1);

        // Step 2: Reverse within each word
        // Reverse first word
        reverseArray(nums, 0, shiftSize - 1);
        // Reverse second word
        reverseArray(nums, shiftSize, nums.length - 1);
    }

    private void reverseArray(int[] nums, int left, int right) {
        while (left < right) {
            // Swap
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            // Update
            left++;
            right--;
        }
    }
}