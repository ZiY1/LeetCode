// Clarification:

// Assumption:
// The input string neither null nor empty

// Approach: Two Pointers Implicitly Maintain a Stack
// slow:
// - Every elements to the left-hand side (including slow) of the slow are the result to return
// - Two cases when move the slow pointer
//   1. If an element explored by fast is the same as the top element at slow (element at slow), the top element should be 
//      poped from "Stack" (slow move one position to the left)
//   2. If an element explored by fast is NOT the same as the top element at slow (element at slow), push this new element in 
//      "Stack" (move slow one position to the right, copy the element to slow)
// fast:
// - To explore and process elements
// - Increments at every iteration

// Examples:

//   c a b a c a
//     s
//             f

// a y x x z y
//   s
//           f

// Time Complexity: O(N)
// Space Complexity: O(N)
class Solution {
    public String removeDuplicates(String s) {
        char[] chars = s.toCharArray();

        int slow = 0;
        for (int fast = 1; fast < chars.length; fast++) {
            if (slow == -1 || chars[fast] != chars[slow]) { // The "Stack" is empty OR The new element != "Stack" top element
                // "Stack Push"
                chars[++slow] = chars[fast];
            } else if (chars[fast] == chars[slow]) { // The new element = "Stack" top element
                // "Stack Pop"
                slow--;
            }
        }

        return new String(chars, 0, slow + 1);
    }
}