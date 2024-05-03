// Description:
// Given an original string input, and two strings S and T,
// from left to right replace all occurrences of S in input with T.

// Clarification:

// Assumption:
// All input strings are neither null nor empty

// Examples:
// Case 1:  T.length() <= S.length() i.e. the result string size <= original string size
// input = "appledogapple",
// S = "apple",
// T = "cat",
// input becomes "catdogcat"
//
// 0 1 2 3 4 5 6 7 8 9 10 11 12
// c a t d o g c a t p p  l  e
//                   s
//                           f

// Case 2: T.length() > S.length() i.e. the result string size > original string size
// input = "catdogcat"
// S = "cat",
// T = "apple",
// input becomes "appledogapple"
//
// c a t d o g c a t
// S size = 3
// T size = 5
// S occurrence = 2
// extra space needed = (T size - S size) * S occurrence = 4
//   a p p l e d o g a p p l e
//   s
// f

// Approach: Two Pointers -> "Pseudo In-Place"
// Case 1:
// slow:
// - The left-hand side of slow (excluding slow) are the result to return
// - Increments after a processed element is copied to slow
// fast:
// - Explore the unprocessed elements from left to right
// - Increments at every iteration
//
// 1. At each element, perform substring finding:
//    a. If a match:
//       i.Copy the target string to slow
//       ii. fast skips all matched elements
//    b. If not a match:
//       i. Copy the element from fast to slow

// Case 2:
// slow:
// - The right-hand side of slow (excluding slow) are the result to return
// - Moves after a processed element is copied to slow
// fast:
// - Explore unprocessed elements from left to right starting at input.length() - 1
// - Moves at every iteration
//
// 1. Scan through the input, count the occurrence of source string
// 2. Calculate extra space needed
// 3. Perform Case 1 in a reverse fashion

// Time Complexity:
// When T <= S, O(N * S)
// When T > S, O((N + T * O) * S)
// Space Complexity: O(N)

class Solution {
    public static String replaceAllOccurrence(String input, String source, String target) {
        if (target.length() <= source.length()) {
            char[] chars = input.toCharArray();
            return replaceAll(chars, source, target);
        } else {
            int sourceOccurrence = countAllOccurrence(input, source);
            int extraSpace = (target.length() - source.length()) * sourceOccurrence;
            char[] chars = new char[input.length() + extraSpace];
            for (int i = 0; i < input.length(); i++) {
                chars[i] = input.charAt(i);
            }

            return replaceAllReverse(chars, input.length(), source, target);
        }
    }

    private static String replaceAll(char[] chars, String source, String target) {
        int slow = 0;
        int fast = 0;

        while (fast < chars.length) {
            int i = 0;
            if (fast <= chars.length - source.length()) {
                // Check the current substring start at index fast matches the source string
                for (i = 0; i < source.length(); i++) {
                    if (chars[fast + i] != source.charAt(i)) {
                        break;
                    }
                }
            }

            // A match
            if (i == source.length()) {
                // Copy the target string to slow
                for (int j = 0; j < target.length(); j++) {
                    chars[slow++] = target.charAt(j);
                }
                // fast skips all matching characters
                fast += source.length();
            } else {
                // Copy the current element to slow
                chars[slow++] = chars[fast++];
            }
        }

        return new String(chars, 0, slow);
    }

    private static String replaceAllReverse(char[] chars, int originalSize, String source, String target) {
        int slow = chars.length - 1;
        int fast = originalSize - 1;

        while (fast >= 0) {
            int i = 0;
            if (fast >= source.length() - 1) {
                for (i = 0; i < source.length(); i++) {
                    if (chars[fast - i] != source.charAt(source.length() - 1 - i)) {
                        break;
                    }
                }
            }

            // A match
            if (i == source.length()) {
                // Copy the whole target to slow
                for (int j = target.length() - 1; j >= 0; j--) {
                    chars[slow--] = target.charAt(j);
                }
                // fast skips all matching elements
                fast -= source.length();
            } else {
                // Copy the current element from fast to slow
                chars[slow--] = chars[fast--];
            }
        }

        return new String(chars);
    }

    private static int countAllOccurrence(String input, String source) {
        int count = 0;
        int i, j;
        for (i = 0; i <= input.length() - source.length(); i++) {
            for (j = 0; j < source.length(); j++) {
                if (input.charAt(i + j) != source.charAt(j)) {
                    break;
                }
            }

            if (j == source.length()) {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        String input = "appledogapple";
        String source = "apple";
        String target = "";
        System.out.println(replaceAllOccurrence(input, source, target));

        String input2 = " apple dog apple ";
        String source2 = " ";
        String target2 = "!!!!!!!!!";
        System.out.println(replaceAllOccurrence(input2, source2, target2));
    }
}

// Time Analysis:
// Let input size be N
// Let source size be S
// Let target size be T
// Case 1: T <= S
// Scan through each element in the input, for each element check if it matches the source -> O(N * S)
// Total Time: O(N * S)

// Case 2: T > S
// Count the number of occurrence -> O(N * S)
// Scan through each element in expended input, for each element check if it matches the source -> O((N + (T - S) * O) * S)
// Total Time: O((N + (T - S) * O) * S) = O((N + T * O) * S)