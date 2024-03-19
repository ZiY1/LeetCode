// Assumption:
// 1. temperatures array are not empty and not null

// Data Structure: Monotonic Stack
// 1. We need to quickly look back and pop out all stack top < current element X as they are "shorter" and will be "blocked"
// 2. Since we are looking for the number of days you have to waitto get a warmer temperature, we store each element index 
// in the stack 

// Example
//  0  1  2  3
// [69,72,76,73]
// stack | 2 1 0
// [ 1, 1, 0, 0]

// Algorithm:
// 1. Iterate through the input array from the last element to the first element, for each element X:
//  1.1 Keep popping out the temp[stack top] <= X until the stack is empty or temp[stack top] > X
// 2. If stack is empty, there is no future day
//    Otherwise, the number of days to get a warmer temperture is (stack top - current index)
//    Push the current index into the stack

// Amortized Time Complexity: O(N)
// Space Complexity: O(1)
class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int[] days = new int[temperatures.length]; 
        Stack<Integer> stack = new Stack<>(); // Store the index of temperture

        for (int i = temperatures.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && temperatures[stack.peek()] <= temperatures[i]) {
                stack.pop();
            }

            days[i] = stack.isEmpty() ? 0 : (stack.peek() - i);
            stack.push(i);
        }

        return days;
    }
}