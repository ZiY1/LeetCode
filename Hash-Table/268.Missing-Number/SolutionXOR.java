// Solution: XOR

// Assumption:

// Algorithm:
// 1. Iterate through nums, XOR each element -> result1
// 2. Start from result 1, XOR from 0 to n -> missing number

// Example:
// nums = [3, 0, 1]
//      11(3)
// XOR  00(0)
//      11
// XOR  01(1)
//      10    <- result1
// XOR  00(0)
//      10
// XOR  01(1)
//      11
// XOR  10(2)
//      01
// XOR  11(3)
//      10    <- Missing Number

// Time Complexity: O(N)
// Space Complexity: O(1)
class Solution {
    public int missingNumber(int[] nums) {
        int result = nums[0];
        for (int i = 1; i < nums.length; i++) {
            result ^= nums[i];
        }
        for (int i = 0; i <= nums.length; i++) {
            result ^= i;
        }

        return result;
    }
}