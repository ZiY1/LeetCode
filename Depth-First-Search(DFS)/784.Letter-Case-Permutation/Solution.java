// Assumption:
// Input string consists only English letters and digits

// Example:

// s = "a1b2"

// Layer
//                        o
//         +lowercase/         \ +uppercase
// 0: a             a            A
//                  |            |
// 1: 1             a1           A1
//                /   \        /    \
// 2: b         a1b   a1B     A1b   A1B
//               |     |      |      |
// 3: 2        a1b2  a1B2    A1b2  A1B2

// Algorithm: DFS/Backtracking

// DFS Technique:
// 1. What does it store on each level, and how many levels we have?
//   a1. If current element is a letter, it stores either an uppercase letter or a lowercase letter
//   a2. If current element is a digit, it stores the digit itself
//   b. total level = N where N is the length of the string
// 2. How many different states should we try to put on this level
//   a. If current element is a letter -> two states
//   b. If current element is a digit -> one state

// Time Complexity: 
// - Best Case: O(N)
// - Worst Case: O(2^N)
// Space Complexity (call stack): O(N)
class Solution {
    public List<String> letterCasePermutation(String s) {
        StringBuilder onePermutation = new StringBuilder();
        List<String> result = new ArrayList<>();
        letterCasePermutationHelper(s, 0, onePermutation, result);
        return result;
    }

    private void letterCasePermutationHelper(String s, int index, StringBuilder onePermutation, List<String> result) {
        // Base case:
        if (index == s.length()) {
            result.add(new String(onePermutation.toString()));
            return; // Don't forget to return
        }

        // Recursive case:
        char currElement = s.charAt(index);

        if (Character.isLetter(currElement)) {
            // Select lower case
            onePermutation.append(Character.toLowerCase(currElement));
            letterCasePermutationHelper(s, index + 1, onePermutation, result);
            onePermutation.deleteCharAt(onePermutation.length() - 1);

            // Select upper case
            onePermutation.append(Character.toUpperCase(currElement));
            letterCasePermutationHelper(s, index + 1, onePermutation, result);
            onePermutation.deleteCharAt(onePermutation.length() - 1);

        } else {
            onePermutation.append(currElement);
            letterCasePermutationHelper(s, index + 1, onePermutation, result);
            onePermutation.deleteCharAt(onePermutation.length() - 1);
        }
    }
}

// Time Analysis:

// Best case: input consists of all digits
// Total time is strightforward: O(N)

// Worst case: input consists of all letters
// Recursion Tree:

//  Level                     Cost
//    0           o             2^0 = 1
//              /   \ 
//    1       o      o          2^1 = 2
//           / \    / \         
//    2     o   o  o   o        2^2 = 4
// ...
//  N - 1                         2^(N-1)
// Total time: 2^(N-1) = O(2^N)