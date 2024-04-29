// Clarification:

// Assumption:
// Size of hystack and needle are neither null or empty
// size of haystack >= size of needle

// Approach: Sliding Window
// 1. Iterate through the haystack from i = 0 to i <= haystack.length() - needle.length(), and for each element:
//  1.1 Iterate through the current window where from j = 0 to j < needle.length(), check each char: 
//      If all char matches, return j
//      Otherwise, break at the first unmatched char
// 2. If no match at the end of the scan, return -1

// Note: why i <= haystack.length() - needle.length()?
// Debug with single char, if haystack.length() = needle.length() = 1, i <= 0 will allow us to enter the loop

// Example:
//             0 1 2 3 4 5 6 7 8               0 1 2
// haystack = "c a d b u t s a d"    needle = "u t s"
//             u t s
//               u t s
//                 u t s
//                   u t s
//                     u t s 

// Time Complexity: O(N * M) where N is the size of the input string, and M is the size of the target string
// Space Complexity: O(1)
class Solution {
    public int strStr(String haystack, String needle) {
        if (haystack.length() < needle.length()) {
            return -1;
        }
        int i, j;

        for (i = 0; i <= (haystack.length() - needle.length()); i++) {
            for (j = 0; j < needle.length(); j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    break;
                }
            }

            if (j == needle.length()) {
                return i;
            }
        }

        return -1;
    }
}

// Time Analysis:
// Let size of haystack = N
// Let size of needle = M
// Outer Loop:
// N - M + 1 iterations
// Inner Loop:
// M iterations
// Worst Case:
// No early termination occurs due to mismatches, each iteration of the outer loop runs the inner loop to its full extent.
// Total Time: O(N * M)