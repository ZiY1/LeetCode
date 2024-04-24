// Solution: Indexing Sort

// Assumption:

// Example:
//         0  1  2
// nums = [0, 1, 3]
//                 i
// Algorithm:
// 1. Iterate through nums, for each element X at the index i:
//    a. If index != X && X < size, swap element at index i and element at index X
//    b. Otherwise, move to next element
// 3. Iterate through nums again, for each element X, find the index where the index != X and the missing number is the index.
// If no such index found, missing number = size

// Time Complexity: O(N)
// Space Complexity: O(1)
class Solution {
    public int missingNumber(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (i != nums[i] && nums[i] < nums.length) {
                swap(nums, i, nums[i]);
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (i != nums[i]) {
                return i;
            }
        }

        return nums.length;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}