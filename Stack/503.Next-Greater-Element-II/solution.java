// Assumptions:
// 1. nums is not empty and not null
// 2. integer values in nums are within the int data type

// Example:
// nums = [1,2,1]
// numsExt = [1,2,1,1,2,1]
// rest = [2,-1,2]

// Data Structure: Monotinic Stack
// We need to quickly look up the stack top to determine if stack top is "shorter" than the current element
// monotonic stack | 2 1

// Technics:
// Double the size of the input array by concate with its own duplication
// e.g. nums = [1,2,1] -> [1,2,1,1,2,1]

// Trick:
// Use % operator to perform the circular effect
// e.g. i = 9 % 5 = 4, 8 % 5 = 3, 7 % 5 = 2, 6 % 5 = 1, 5 % 5 = 0, 4 % 5 = 4 ... 0 % 5 = 0

// Algorithm:
// Note: in order to simulate the circular int array, we can use a trick without actually doubling the nums
//       0 1 2  3 4 5
// e.g. [1,2,1],1,2,1]          5%3 = 2, 4%3=1 3%3=0
// 1. Iterate though i = (nums.length * 2) - 1 to 0, for each element X = nums[i % nums.length]:
//  1.1 Keep poping the stack top if stack top <= X until stack is empty or stack top > X
// 2. If the stack is empty, there is no next greater element
//    Otherwise, the next greater element is stack top
//    Push the current element into the stack

// Amortized Time Complexity: O(N)
// Space Complexity: O(1)
class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int numsSize = nums.length;
        int[] rest = new int[nums.length];
        Stack<Integer> stack = new Stack<>();
        
        for (int i = (numsSize * 2) - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= nums[i % numsSize]) {
                stack.pop();
            }

            rest[i % numsSize] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(nums[i % numsSize]);
        }

        return rest;
    }
}

// Amortized Time Analysis:
// 6 1 2 3 4 5
// monotinic stack: 5 -> cost = 1
// monotinic stack: 5 4 -> cost = 1
// monotinic stack: 5 4 3 -> cost = 1
// monotinic stack: 5 4 3 2 -> cost = 1
// monotinic stack: 5 4 3 2 1 -> cost = 1
// monotinic stack: 6 -> cost = 6

// At 1st to (n-1)-th iteration: cost = n - 1
// At nth iteration, we poped all element out from the stack from n-1 to 1: cost = n
// Total cost = (n + (n - 1)) / n = (2n - 1) / n = O(N)