// Description:
// Given a string, remove all leading/trailing/duplicated empty spaces.

// Assumption:
// The input string is not null or empty

// Example:
// "__Hello__World__" -> "Hello_World"

// Algorithm:
// slow:
// - All elements to the left-hand side of slow (excluding slow) are all processed elements (elements to be returned)
// - Increments after an element is processed(to be returned) and copied to slow
// fast:
// - To discover each element
// - Increments at every iteration
// (j, size-1): (unknown area to explore)
//
// (W1)(_W2)(_W3)...(_Wn)
// 1. Skip all leading/duplicate empty spaces
// 2. Add only one empty space in front of each word.(except for the 1st word)

// Time Complexity: O(N)
// Space Complexity: O(N) for Java since String is immutable

class Solution {
    public static String removeSpaces(String str) {
        char[] charArr = str.toCharArray();
        int slow = 0;
        int fast = 0;
        int wordCount = 0; // Special case for the 1st word

        while (true) {
            while (fast < charArr.length && Character.isWhitespace(charArr[fast])) {
                fast++; // 1. Skip all leading empty space in front of each word
            }
            if (fast == charArr.length) {
                break;
            }
            if (wordCount > 0) {
                charArr[slow++] = ' '; // 2. Add a empty space in front of (2ed+) word
            }
            while (fast < charArr.length && !Character.isWhitespace(charArr[fast])) {
                charArr[slow++] = charArr[fast++]; // 3. copy a word
            }
            wordCount++;
        }

        return new String(charArr, 0, slow);
    }

    public static void main(String[] args) {
        String str = "    Hello        World!   ";
        String res = removeSpaces(str);
        System.out.println(res);
    }
}
