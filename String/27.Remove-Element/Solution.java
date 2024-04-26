// Clarification:

// Assumption:
// nums is not null

// Example:
// 0  1  2  3
// 2  2  2  3       val = 3
//       s
//            f

// Approach: Two Pointers
// slow:
// - Every elements on the left-hand side of slow are the result to returned (excluding slow).
// All elements that are not the target val should be put on the left-hand side of slow.
// - increments when the current element is not our target val (after we copied the element to return to slow)
// fast:
// - to explore un-checked element
// - increments at every iteration

// Time Complexity: O(N)
// Space Complexity: O(1)

// Summary: Two Pointers in String Technique 
// 1. What is the "physical meaning" of slow and fast pointers
// 2. When to incremet slow and fast pointers

class Solution {
    public int removeElement(int[] nums, int val) {
        int slow = 0;

        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != val) {
                nums[slow] = nums[fast];
                slow++;
            }
        }

        return slow;
    }
}