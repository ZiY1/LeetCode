class Solution {
    // Time Complexity: O(N)
    // Space Complexity: O(N)
    public boolean isValid(String s) {
        // DS: stack
        // Why stack? By using stack, we can quickly look up the previous pushed element to see if there is a match
        // Algo:
        // Iterate through the string, for each element:
        //  1. If the current element is either '(', '{', '[', push to the stack.
        //  2. If the current element is either ')', '}', ']':
        //   2.1 If the stack is empty, meaning no match, return false.
        //   2.2 If the stack is not empty, pop from stack and compare with the current element 
        //       to check if they match. If no, return false.
        // After we iterated the whole string, if the stack still have element left, return false.

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);
            if (curr == '(' || curr == '{' || curr == '[') {
                stack.push(curr);
            } else if (stack.isEmpty()) {
                return false;
            } else {
                char prev = stack.pop();
                if (!isMatch(prev, curr)) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    private boolean isMatch(char c1, char c2) {
        return ((c1 == '(' && c2 == ')') || 
                (c1 == '{' && c2 == '}') ||
                (c1 == '[' && c2 == ']'));
    }
}
