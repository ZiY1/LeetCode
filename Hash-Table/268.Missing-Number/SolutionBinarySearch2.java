// Solution: Binary Search (Left-Inclusive, Right-Exclusive)

// Assumption:
// All the numbers of nums are unique

// Example:
// nums = [3, 0, 1]
//     
// 0 1 2 3
// l m   r
// m = l + (r - l) / 2 = 1
// count = 2

// 0 1 2 3
//     l r
//     m
// leftCount = 

// Algorithm:
// 0. Init left = 0, right = n
// 1. Guess an missing number mid = left + (right - left) / 2
// 2. Count the # of element <= mid
// 3. Two cases:
//    a. If count = (mid + 1), guess too small, need to guess larger, search right half -> left = mid + 1
//    b. If count < (mid + 1), might be the solution OR 
//                             guess too large, need to guess smaller, search left half -> right = mid (cannot exclude the mid b/c it might be the answer)
// 4. Termination: when left and right are adajacent, need to count the # of element <= left and right again: 
//                 If leftCount < left, then left is the result
//                 Otherwise, result is rightCount

// Time Complexity: O(NlogN)
// Space Complexity: O(1)
class Solution {
    public int missingNumber(int[] nums) {
        // Binary Search Template: [left, right) -> [left, mid), [mid + 1, right)
        int left = 0;
        int right = nums.length + 1;

        while (left < right) {
            int mid = left + (right - left) / 2;
            int count = countLessThanOrSmallerTo(nums, mid);

            if (count == mid + 1) {
                left = mid + 1;
            } else if (count < mid + 1) {
                right = mid;
            }
        }
        
        return left;
    }

    private int countLessThanOrSmallerTo(int[] nums, int target) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= target) {
                count++;
            }
        }
        return count;
    }
}

// Time Analysis:
// 1. For problem size = N, binary search reduce the problem size by half each iteration i.e. either search left or right.
// Thus, total logN iterations.
// 2. For each iteration, need to count -> O(N)
// Total time = O(NlogN)