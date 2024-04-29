// Clarification:

// Assumption:
// The input text is neither null nor empty

// Example:
// "_ _ i _ _ _ am _ _ good"
// # words = 3
// # spaces = 7
// equal space = 2
// i _ _ _ am _ _ _ good_
//                        s
//                        f

// Approach: Two Pointers
// slow:
// - All elements on the left-hand side of slow are result to return
// - Increments after an element is processed and copied to slow
// fast:
// - Discover un-processed elements
// - Increments at every iteration
//
// 1. Iterate through the string, Count the total number of words and spaces.
// 2. Calculate the equal space to distribute between words.
// 3. Use a fast pointer to skip spaces until identifying words, and copy the word letter iteratively to the slow pointer 
// begining with the calculated equal space (except the first word)to construct the result.
// 4. Fill the remaining spaces with spaces

// Note:
// For string rearrangement (where the output size equals the input size), care must be taken to ensure that the
// slow pointer does not overwrite characters that have not yet been processed by the fast pointer. This is managed by
// ensuring the fast pointer processes characters from the original input array, while the slow pointer modifies a copy.

// Time Complexity: O(N)
// Space Complexity: O(N) for Java since Java String is immutable
class Solution {
    public String reorderSpaces(String text) {
        char[] chars = text.toCharArray();
        int wordCount = 0;
        int spaceCount = 0;
        int fast = 0;

        // Count words and spaces
        while (true) {
            while (fast < chars.length && Character.isWhitespace(chars[fast])) {
                fast++;
                spaceCount++;
            }

            if (fast == chars.length) {
                break;
            }

            while (fast < chars.length && !Character.isWhitespace(chars[fast])) {
                fast++;
            }
            wordCount++;
        }

        int equalSpace = 0;

        if (wordCount > 1) {
            equalSpace = spaceCount / (wordCount - 1);
        }
 
        fast = 0; // points to the original input text
        int slow = 0;
        wordCount = 0;

        while (true) {
            while (fast < chars.length && Character.isWhitespace(text.charAt(fast))) {
                fast++;
            }

            if (fast == chars.length) {
                break;
            }

            // Add # equal space at begining of each word (except the first word)
            if (wordCount > 0) {
                for (int i = 0; i < equalSpace; i++) {
                    chars[slow++] = ' ';
                }
            }

            // Copy the word over to slow
            while (fast < chars.length && !Character.isWhitespace(text.charAt(fast))) {
                chars[slow++] = text.charAt(fast++);
            }
            wordCount++;
        }

        // Fill up the remaining with space
        while (slow < chars.length) {
            chars[slow++] = ' ';
        }

        return new String(chars);
    }
}