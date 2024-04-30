// Clarification:

// Assumption:
// Input string is either null nor empty

// Example:
// s = [o  l  l  e  h]
//            l  
//            r

// Approach: Two Pointers Recursive
// left: points to the first element at start
// right: points to the last element at start
// 1. Swap element at left and right
// 2. left++, right-- 
// 3. Repeat 1 and 2 when left < right

// Time Complexity: O(N/2) = O(N)
// Space Complexity (call stack): O(N/2) = O(N)
class Solution {
    public void reverseString(char[] s) {
        reverseStringHelper(s, 0, s.length - 1);
    }

    private void reverseStringHelper(char[] s, int left, int right) {
        // Base case:
        if (left >= right) {
            return;
        }

        // Recurisve rule:
        swap(s, left, right);
        reverseStringHelper(s, left + 1, right - 1);
    }

    private void swap(char[] s, int i, int j) {
        char temp = s[i];
        s[i] = s[j];
        s[j] = temp;
    }
}