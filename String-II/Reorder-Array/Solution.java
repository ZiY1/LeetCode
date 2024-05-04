// Description:
// Given an array of elements, reorder it as follow:
// { N1, N2, N3, …, N2k } → { N1, Nk+1, N2, Nk+2, N3, Nk+3, … , Nk, N2k }
// { N1, N2, N3, …, N2k+1 } → { N1, Nk+1, N2, Nk+2, N3, Nk+3, … , Nk, N2k, N2k+1 }
// Try to do it in place.

// Assumption:
// The input array is not null

// Example
// { 1, 2, 3, 4, 5, 6} → { 1, 4, 2, 5, 3, 6 }
// { 1, 2, 3, 4, 5, 6, 7, 8 } → { 1, 5, 2, 6, 3, 7, 4, 8 }
// { 1, 2, 3, 4, 5, 6, 7 } → { 1, 4, 2, 5, 3, 6, 7 }

// { 1, 2, 3, 4, A, B, C, D } → { 1, A, 2, B, 3, C, 4, D }

// Approach: Reverse Merge Sort

// We know how to perform a merge sort such as the following:

//         1A2B3C4D
//     1A2B        3C4D
//  1A    2B     3C    4D
// 1  A  2  B   3  C  4  D
// -------------------------  Chunk1  C2     C3    C4
//  1A    2B     3C    4D       1A    2B     3C    4D   Step 3
//     12AB         34CD        12    AB     34    CD   Step 2
//         1234ABCD             12    34     AB    CD   Step 1

// This question is asking a reverse mergesort.
// By looking at a reverse way we can see from step 1 to step 2, only thing
// happen is a swap of Chuck2 and Chuck3.

// Another example:

// 1 | 2 3 | 4 | 5 6
// 1 4 | 2 | 3 | 5 | 6
// 1 4 | 2 5 | 3 6
// 1 4 2 5 3 6

// In general:

// Chuck1   C2   C3    C4
// XXX     OOOO  XXX   OOOO
// XXX      XXX  OOOO  OOOO

// 1. We need to divide the array into 4 chuck where:
// size of C1 = size of C3
// size of C2 = size of C4
// 0 1 2 3 4 5 6 7 8 9 10 11 12 13
// X X X O O O O X X X O  O  O  O
// l     lm      m     rm       r

// size = right - left + 1 = 14
// mid = left + 1/2 * size = 7
// leftMid = left + 1/4 * size = 3.5 = 3
// rightMid = left + 3/4 * size = 10.5 = 10
// The key part is this 1/4 and 3/4, when size is not divisible by 4,
// leftMid and rightMid take floor at the same time to make sure they are same size

// Base Case:
// When there is one or two elements, return

// Recursive Case:
// 1. Swap Chuck2 and Chuck3
// 2. Assume the recursive call successfully reorder the array
//  2.1 Recursively reorder the left half
//  2.2 Recursively reorder the right half

// Time Complexity: O(NlogN)

// Master Method:
// T(N) = 2 * T(N/2) + O(N)
// N^(log_{b,a}) = N^(log_{2,2}) = N
// f(n) = N
// -> O(NlogN)

// Recursion Tree Method:
// Level            Cost
//   0       o       N
//          / \
//   1     o   o     2*(2/N) = N
//        / \ / \
//   2   o  o o  o   4*(4/N) = N
// ...
//  log_{2,N}        N
// Total cost = log_{2,N} * N = O(NlogN)

// Space Complexity (call stack): O(logN)
// Call stack size of the height of the recursion tree which is logN

public class Solution {
    public static void reorder(char[] input) {
        if (input.length % 2 == 0) {
            reorderHelper(input, 0, input.length - 1);
        } else {
            reorderHelper(input, 0, input.length - 2);
        }
    }

    private static void reorderHelper(char[] input, int left, int right) {
        // Base Case:
        if (right - left <= 1) {
            return;
        }

        // Recursive Case

        int size = right - left + 1;
        int mid = left + size / 2;
        int leftMid = left + size / 4;
        int rightMid = left + size * 3/4;

        //  C1       C2       C3        C4
        // 0 1 2   3 4 5 6   7 8 9   10 11 12 13
        // X X X | O O O O | X X X | O  O  O  O
        // l      lm         m      rm         r
        reverseString(input, leftMid, mid - 1);
        reverseString(input, mid, rightMid - 1);
        reverseString(input, leftMid, rightMid - 1);

        // Be aware of that C2 and C3 are swapped
        reorderHelper(input, left, left + 2 * (leftMid - left) - 1); //!!!
        reorderHelper(input, left + 2 * (leftMid - left), right); //!!!
    }

    private static void reverseString(char[] input, int left, int right) {
        // 0 1 2 3 4 5 6
        // a b c d e f g
        // l->       <-r
        while (left < right) {
            // Swap
            char temp = input[left];
            input[left] = input[right];
            input[right] = temp;
            // Updates
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        char[] input1 = {'1', '2', '3', '4', 'A', 'B', 'C', 'D'};
        reorder(input1);
        System.out.println(new String(input1));

        char[] input2 = {'1', '2', '3', '4', '5', '6', '7', '8'};
        reorder(input2);
        System.out.println(new String(input2));

        char[] input3 = {'1', '2', '3', '4', '5', '6', '7'};
        reorder(input3);
        System.out.println(new String(input3));

        char[] input4 = {};
        reorder(input4);
        System.out.println(new String(input4));
    }

}
