// Description:
// Given an original string input, and two strings S and T,
// from left to right replace all occurrences of S in input with T.

// Clarification:

// Assumption:
// All input strings are neither null nor empty

// Examples:
// Case 1:  T.length() <= S.length() i.e. the result string size <= original string size
// Case 2: T.length() > S.length() i.e. the result string size > original string size

// input = "catdogcat"
// S = "cat",
// T = "apple",
// input becomes "appledogapple"

// c a t d o g c a t
//                   f
// a p p l e d o g a p p l e

// Approach: "Two" Pointers & String Buffer
// slow:
// - We use a string buffer to build the result string out-of-place
// - Append a processed element to the string buffer from fast
// fast:
// - Explore the unprocessed elements from left to right
// - Increments at every iteration
//
// For each element in the string, check it matches the source
// If a match: append the target string to string buffer, fast skips all the current source elements
// If not a match: append the current element from fast to string builder

// Time Complexity: O(N * S)
// Space Complexity: O(N)
class Solution {
    public static String replaceAllOccurrence(String input, String source, String target) {
        StringBuilder resultBuilder = new StringBuilder();
        int fast = 0;
        // Scan through each element
        while (fast < input.length()) {
            int i = 0;
            // For each element, perform substring finding
            if (fast <= input.length() - source.length()) {
                for (i = 0; i < source.length(); i++) {
                    if (input.charAt(fast + i) != source.charAt(i)) {
                        break;
                    }
                }
            }

            // If a match
            if (i == source.length()) {
                // Append the target to string builder
                for (int j = 0; j < target.length(); j++) {
                    resultBuilder.append(target.charAt(j));
                }
                // fast skips the current source
                fast += source.length();
            } else { // No match
                // Append the current element to string builder from fast
                resultBuilder.append(input.charAt(fast++));
            }
        }

        return resultBuilder.toString();
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
// Scan through each element, check each element matches the source string -> O(N * S)
// If a match, copy target string -> O(T)
// If no match, copy current element -> O(1)
// Total Time: O(N * S)
