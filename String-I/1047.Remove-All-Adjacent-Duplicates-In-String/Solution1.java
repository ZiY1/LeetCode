// Clarification:

// Assumption:
// The input string neither null nor empty

// Approach: Two Pointers Implicitly Maintain a Stack
// slow:
// - Every elements to the left-hand side (excluding slow) of the slow are the result to return
// - Two cases when move the slow ponter
//   1. If an element explored by fast is the same as the top element at slow (left side of slow), the top element should be 
//      poped from "Stack" (slow move one position to the left)
//   2. If an element explored by fast is NOT the same as the top element at slow (left side of slow), push this new element in 
//      "Stack" (copy the element to slow, and move slow one position to the right)
// fast:
// - To explore and process elements
// - Increments at every iteration

// Examples:

// c a b a c c
//     s
//             f

// a y x x z y
//     s
//             f

// Time Complexity: O(N)
// Space Complexity: O(N)
class Solution {
    public String removeDuplicates(String s) {
        char[] chars = s.toCharArray();

        int slow = 0;
        for (int fast = 0; fast < chars.length; fast++) {
            if (slow == 0 || chars[fast] != chars[slow - 1]) { // The "Stack" is empty OR The new element != "Stack" top element
                // "Stack Push"
                chars[slow++] = chars[fast];
            } else if (chars[fast] == chars[slow - 1]) { // The new element = "Stack" top element
                // "Stack Pop"
                slow--;
            }
        }

        return new String(chars, 0, slow);
    }
}