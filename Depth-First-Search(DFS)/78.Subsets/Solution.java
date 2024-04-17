// Assumption:
// Input array contains unqiue elements

// Example: [1, 2, 4]
//                                                 []
//                                +1 /                             \ -1
// level for 1                   [[1]]                             []
//                      +2/               \-2             +2/                 \-2
// level for 2        [[1,2]]            [[1]]             [[2]]              []
//                 +3/       \-3     +3/       \-3      +3/      \-3      +3/    \-3
// level for 3 [[1,2,3]]  [[1,2]]   [[1,3]]   [[1]]  [[[2,3]]]   [[2]]   [[3]]   []

// Algorithm: DFS/Backtracking
// DFS Basic Technique:
// 1. What does each level represent? How many levels do we have?
//   a. For each level, it makes the decision on whether to put this element into final set or not
//   b. Total n elements -> n levels/layers
// 2. How many different states do we have on this level?
//   a. Two, each state considers selected or not selected

// Time Complexity: O(2^N)
// Space Complexity (call stack): O(N)

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<Integer> currSubset = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();

        subsetsHelper(nums, 0, currSubset, result);

        return result;
    }

    private void subsetsHelper(int[] nums, int level, List<Integer> currSubset, List<List<Integer>> result) {
        // Base case: 
        // We have tried both add/not add the element for all levels.
        if (level == nums.length) {
            // !!!Make a copy of temp and add it to rest!!!
            // Otherwise, we are adding a reference to the same ArrayList object (temp) every time
            result.add(new ArrayList(currSubset));
            return;
        }

        // Recursive rule:

        // Include the ith element, where i = level
        currSubset.add(nums[level]);
        // Recurisvely try both add/not add staring from the (i + 1)-th element 
        subsetsHelper(nums, level + 1, currSubset, result);

        // Exclude the ith element
        currSubset.remove(currSubset.size() - 1); // Backtrack
        // Recurisvely try both add/not add staring from the (i + 1)-th element 
        subsetsHelper(nums, level + 1, currSubset, result);
    }
}

// Time Analysis:

// Recurrence formula:
// T(n) = 2 * T(n - 1) + O(1)

// Recursion Tree:
//  Level                     Cost
//    0           o             2^0 = 1
//              /   \ 
//    1       o      o          2^1 = 2
//           / \    / \         
//          o   o  o   o        2^2 = 4
// ...
//    N                         2^N
// Total cost = 1 + 2 + 4 + ... 2^N = 2^(N+1) - 1 = O(2^N)
