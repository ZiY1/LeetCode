// Clarification:

// Assumptions:
// 1. s is not null or empty
// 2. s consists of lowercase English letter

// Approach: Two Pointers Explicitly Maintain a Stack
// slow pointer s: index all letters to the left of s(excluding s) should be the final answer
// - when to increment s? when the letters s and f point to are not the identical
// fast pointer f: current index to scan through the string from left to right
// - when to increment f? f increments at every iteration

// stack.top() is s
// 1. If stack.top() and f are identical, pop s, f skips all identical chars
// 2. If stack.top() and f are not identical, push f to stack

// Examples:
// a b b a
// s
//   f

// 0 1 2 3 4 5 6 7 8 9
// a a a a a a a a a a
//                   f
// stack: a
// count = 9
// final stack: 

// Time Complexity: O(N)
// Space Complexity: O(N)

class Solution {
    public String removeDuplicates(String s) {
        if (s.length() <= 1) {
            return s;
        }

        List<Character> stack = new ArrayList<>();

        char[] charArr = s.toCharArray();
        int fast = 0;

        while (fast < charArr.length) {
            char c = charArr[fast];
            // If element at stack top = new element
            if (!stack.isEmpty() && c == stack.get(stack.size() - 1)) {
                int count = 0;
                // Count the total # of duplicate new elements at once
                while (fast < charArr.length && charArr[fast] == c) {
                    fast++;
                    count++;
                }
                // If there are even # of duplicate new elements, every pair will be canceled out, and leave the stack top as it is
                // If there are odd # of duplicate new elements, every pair will be canceled out, and the last new duplcate will cancel out the stack top
                if (count % 2 != 0) { 
                    stack.remove(stack.size() - 1);
                }
            } else {
                stack.add(charArr[fast]);
                fast++;
            }
        }

        StringBuilder builder = new StringBuilder();
        for (Character c : stack) {
            builder.append(c);
        }

        return builder.toString();
    }
}