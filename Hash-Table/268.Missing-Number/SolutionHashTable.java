// Solution: Hash Table

// Assumption:

// Example:
// nums = [3, 0, 1]
// hash table = {<3, null>, <0, null>, <1, null>}
// 0 1 2 3
// i

// Algorithm:
// 1. Iterate through nums, for each element X, store it in a hash table <key = X, value = null>
// 2. Iterate from number 0 to n, where n is the size of nums, check if this number in the hash table

// Data Structure: Hash Table
// - To store element scanned

// Time Complexity: O(2N) = O(N)
// Space Complexity: O(N)
class Solution {
    public int missingNumber(int[] nums) {
        // Step 1:
        Map<Integer, Boolean> hashMap = new HashMap<>(); // boolean is used to save space as boolean is 1 bit (0 or 1)
        for (int i = 0; i < nums.length; i++) {
            hashMap.put(nums[i], true);
        }

        // Step 2:
        for (int i = 0; i <= nums.length; i++) {
            if (!hashMap.containsKey(i)) {
                return i;
            }
        }

        return -1; // Defensive programming
    }
}