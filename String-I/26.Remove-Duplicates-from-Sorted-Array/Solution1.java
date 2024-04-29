// Clarification:

// Assumption:
// Input nums is not null or empty

// Approach: Two Pointers
// slow:
// - Every element on the left-hand side of slow (including slow) are result to return
// - Two cases when slow should move or not:
//   1. When element <- fast  = element <- slow, slow should not move becuase it is a duplicate
//   2. When element <- fast != element <- slow, first, slow should move one position to the right, then slow should copy element <- fast

// fast:
// - To explore and process each element
// - Increments at every iteration

// Examples:
// 1 2 2
//   s
//     f

// 0 1 2 3 4 2 2 3 3 4
//         s
//                   f

// Time Complexity: O(N)
// Space Complexity: O(1)
class Solution {
    public int removeDuplicates(int[] nums) {
        int slow = 0;
        for (int fast = 1; fast < nums.length; fast++) {
            if (nums[slow] != nums[fast]) {
                nums[++slow] = nums[fast];
            }
        }

        // Return the # of unique elements
        return slow + 1;
    }
}