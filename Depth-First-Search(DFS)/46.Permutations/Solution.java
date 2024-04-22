// Assumption:
// Input array contains distinct integers

// Example:
// nums = [1,2,3]
//                                              o
//                    
//  Layer 0          1 | 2 3                  2 | 1 3                 3 | 1 2       
//
//  Layer 1     1 2 | 3   1 3 | 2       2 1 | 3    2 3 | 1       3 1 | 2    3 2 | 1
//
//  Layer 2      1 2 3     1 3 2         2 1 3      2 3 1          3 1 2     3 2 1

// Algorithm: DFS/Backtracking

// DFS Technique:
// 1. What does each layer represent? How many layers do we have?
// - Each layer represents different elements we can choose at i-th position
//   i.e. enumerate each unplaced element and place it in ith position in one permutation
//   e.g. current layer = 0: [1, x, x]  [2, x, x] [3, x, x]
// - Total N layers where N = size of nums
// 2. How many states do we have at each layers
// - The # of unplaced element

// Time Complexity: O(N!)
// Space Complexity (call stack): O(N)

class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<Integer> onePermute = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            onePermute.add(nums[i]);
        }
        List<List<Integer>> solution = new ArrayList<>();
        permuteHelper(0, onePermute, solution);
        return solution;
    }

    private void permuteHelper(int index, List<Integer> onePermute, List<List<Integer>> solution) {
        // Base case:
        if (index == onePermute.size() - 1) {
            solution.add(new ArrayList<>(onePermute));
            return;
        }

        // Recursive rule:

        // Enumerate all unpaced element
        for (int i = index; i < onePermute.size(); i++) {
            // For each unplaced element, swap it to index position
            swap(onePermute, i, index);
            // Recursively permute the next layer
            permuteHelper(index + 1, onePermute, solution);
            // Undo
            swap(onePermute, i, index);
        }
    }

    private void swap(List<Integer> arr, int i, int j) {
        int temp = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, temp);
    }
}

// Time Analysis:

// Recursion Tree:
// Level              Cost
//           o
//        / ... \    
//  0    o       o    N
//
//  1                 N * (N - 1)
// ...
//  N - 1             N * (N - 1) * (N - 2) * ... * 1 = N!
// Total cost: O(N!)