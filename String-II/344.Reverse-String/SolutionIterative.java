// Clarification:

// Assumption:
// Input string is either null nor empty

// Example:
// s = [o  l  l  e  h]
//            l  
//            r

// Approach: Two Pointers Iterative
// left: points to the first element at start
// right: points to the last element at start
// 1. Swap element at left and right
// 2. left++, right-- 
// 3. Repeat 1 and 2 when left < right

// Time Complexity: O(N/2) = O(N)
// Space Complexity: O(1)
class Solution {
    public void reverseString(char[] s) {
        int left = 0;
        int right = s.length - 1;

        while (left < right) {
            swap(s, left, right);
            left++;
            right--;
        }
    }

    private void swap(char[] s, int i, int j){
        char temp = s[i];
        s[i] = s[j];
        s[j] = temp;
    }
}