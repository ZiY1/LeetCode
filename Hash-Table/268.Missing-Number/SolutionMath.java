// Solution: Math

// Assumption:
// n(n+1) and sumofNums will not cause integer overflow

// Example:
// One missing number in n distinct numbers in [0, n] = n(n+1) / 2 - sumOfNums
// e.g. Missing number in [3, 0, 1] in [0, 3] = 3 * 4 / 2 - 4 = 2

// Potential Drawbacks:
// 1. n(n+1) may cause integer overflow when n is too large
// 2. sumOfNums may cause integer overflow when n is too large

// Follow Up:
// For mathmatical computation, when we face '/', what should we worry about?
// 1. Division by 0
// 2. Integer loss precision

// Time Complexity: O(N)
// Space Complexity: O(1)
class Solution {
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int numsSum = 0;
        for (int i = 0; i < nums.length; i++) {
            numsSum += nums[i];
        }

        return n * (n + 1) / 2 - numsSum;
    }
}