// Assumptions:
// 1. nums1 and nums2 are both not empty and not null
// 2. nums1.length <= nums2.length
// 3. elements in nums1 are also in nums2

// Example:
// nums1 = [4,1,2], nums2 = [1,3,4,2]
// result = [-1,3,-1]

// Data Structure: Stack
// 1. We will construct a descending monotonic stack to simulate the next greater elements
// monotonic stack: | 4 3 1
// 2. We need to quickly look back and pop out the elements that is "shorter" than the current element 
// as they will be "blocked" when pushing the current element into the stack
// e.g. | 2 4 -> 2 is "shoter" and "block" by "4"

// Algorithm:
// 1. Iterate through the nums2 from the last element to the first element, for each element X
// 2. To determine its next greater element, we need to look up the stack:
//  2.1 Keep poping the stack top if top <= X until either the top > X or stack is empty
// 3. If the stack is empty, there is no greater element for X
//    If the stack is not empty, the greater element for X is the stack top
//    Push the current element into the stack
// 4. Store a key-value pair into a map where <key=current element X, value=next greater element>
// 5. Iterate through nums1, for each element: get its next greater element in the map

// Amortized Time Complexity: O(N)
// Space Complexity: O(N)
class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Stack<Integer> stack = new Stack<>();
        Map<Integer, Integer> hashMap = new HashMap<>();

        for (int i = nums2.length - 1; i >= 0; i--) {
            // Pop all the elements in monotonic stack that is < current element as they are "shorter" and will be "blocked" 
            while (!stack.isEmpty() && stack.peek() <= nums2[i]) {
                stack.pop();
            }
            
            int nextGreater = stack.isEmpty() ? -1 : stack.peek();
            stack.push(nums2[i]);
            hashMap.put(nums2[i], nextGreater);
        }

        int[] rest = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            rest[i] = hashMap.get(nums1[i]);
        }

        return rest;
    }
}