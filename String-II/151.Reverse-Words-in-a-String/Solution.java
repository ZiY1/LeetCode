// Clarifications:
// 1. Do we need to reverse the order within each word?
// 2. How should we deal with the empty spaces?
// We need to perform two major actions:
// 1. Reverse the order of the words while maintaining the order within each word
// 2. Remove any leading/trailing/duplicate empty spaces

// Assumption:
// Input string is neither null nor empty

// Example:

// After Step 1:
// s = h e l l o _ w o r l d w o r l d _ _ _
//                           s
//                                            f
// wordCount = 1

// After Step 2:
// s = d l r o w _ o l l e h
//               l   
//               r

// After Step 3:
// s = w o r l d _ h e l l o
   
// Approach: Two Pointers

// Step 1: Remove any leading/trailing/duplicate empty spaces
// slow:
// - All elements on the left-hand side of slow (excluding slow) are the result for step 1
// - Increments after an element to return is copied from fast
// fast:
// - To explore unprocessed elements
// - Increments at every iteration
//
// Copy each word to slow with a leading space (except the first word)
//(W1)(_W2)(_W3)...(_Wn)
// At each iteration, copy a word to slow:
// 1. fast skips all spaces until pointing to a letter
// 2. Copy an empty space to slow (except the first word)
// 3. Copy the whole word to slow letter by letter
// 4. Count the word

// Step 2: Reverse the whole string

// Step 3: Reverse within each word

// Time Complxity: O(N)
// Space Complexity: O(N) for Java since Java String is immutable
class Solution {
    public String reverseWords(String s) {
        char[] chars = s.toCharArray();

        // Step 1: Remove any leading/trailing/duplicate empty spaces
        int endIndex = removeSpaces(chars);

        // Step 2: Reverse the whole string
        reverseString(chars, 0, endIndex);

        // Step 3: Reverse within each word
        int wordStart = 0;
        int fast = 0;

        while (true) {
            // wordStart and fast must be at the start of the current word

            // Move fast to (end + 1) of the current word
            while (fast <= endIndex && !Character.isWhitespace(chars[fast])) {
                fast++;
            }

            // Reverse the current word
            reverseString(chars, wordStart, fast - 1);

            if (fast == endIndex + 1) {
                break;
            }

            // Update fast and wordStart to start of next word
            fast++;
            wordStart = fast;
        }

        return new String(chars, 0, endIndex + 1);
    }

    // Input: the character array
    // Output: the end index of the result
    private int removeSpaces(char[] chars) {
        int slow = 0;
        int fast = 0;
        int wordCount = 0;

        while (true) {
            // fast skips all white spaces
            while (fast < chars.length && Character.isWhitespace(chars[fast])) {
                fast++;
            }
            
            // Finished process all elements
            if (fast == chars.length) {
                break;
            }

            if (wordCount > 0) {
                chars[slow++] = ' ';
            }

            // Copy the whole word to slow
            while(fast < chars.length && !Character.isWhitespace(chars[fast])) {
                chars[slow++] = chars[fast++];
            }

            wordCount++;
        }

        return slow - 1;
    }

    private void reverseString(char[] chars, int left, int right) {
        while (left < right) {
            swap(chars, left, right);
            left++;
            right--;
        }
    }

    private void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }
}

// Time Analysis:
// Step 1: Remove any leading/trailing/duplicate empty spaces -> O(N)
// Step 2: Reverse the whole string -> O(N)
// Step 3: Reverse within each word -> O(N)
// Total Time: O(N)