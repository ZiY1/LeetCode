// Assumption:

// Example: n = 2
//                                   o
//                +(? /                           \ +)?
// 1                 (                                 o
//              /          \
// 2        ((                ()
//        /    \           /      \
// 3    o      (()       ()(       o
//     / \     /  \     /  \      /  \
// 4  o   o   o  (())  o  ()()   o    o

// Algorithm: DFS/Backtracking

// DFS Technique:
// 1. What does it store on each level, and how many levels we have?
//   a. each level represents a position in which we could place either (  or  )
//   b. 2 * n levels
// 2. How many different states should we try to put on this level
//   a. Two

// Restrictions:
// 1. When could we place a left parenthesis '(' ?
//  a. when there are '(' left unplaced
// 2. When could we place a right parenthesis ')' ?
//  a. only when the # of '(' added so far > # of ')' added so far

// Time Complexity: O(2^(2N))
// Space Complexity (call stack): O(2 * N) = O(N)
class Solution {
    public List<String> generateParenthesis(int n) {
        StringBuilder oneValidParen = new StringBuilder();
        List<String> validParens = new ArrayList<>();
        generateParenthesisHelper(n, 0, 0, oneValidParen, validParens);
        return validParens;
    }

    private void generateParenthesisHelper(int n, int leftAdded, int rightAdded, StringBuilder oneValidParen, List<String> validParens) {
        // Base case:
        if (leftAdded + rightAdded == 2 * n) {
            validParens.add(new String(oneValidParen.toString()));
        }

        // Recursive case:

        // Try to add '(' if allowed
        if (leftAdded < n) {
            oneValidParen.append("(");
            generateParenthesisHelper(n, leftAdded + 1, rightAdded, oneValidParen, validParens);
            oneValidParen.deleteCharAt(oneValidParen.length() - 1); //!!! must delete after we append
        }

        // Try to add ')' if allowed
        if (leftAdded > rightAdded) { // if # of '(' added so far > # of ')' added so far
            oneValidParen.append(")");
            generateParenthesisHelper(n, leftAdded, rightAdded + 1, oneValidParen, validParens);
            oneValidParen.deleteCharAt(oneValidParen.length() - 1); //!!! must delete after we append
        }
    }
}

// Time Analysis:
//  Level                     Cost
//    0           o             2^0 = 1
//              /   \ 
//    1       o      o          2^1 = 2
//           / \    / \         
//          o   o  o   o        2^2 = 4
// ...
//   2N                         2^(2N)
// Total cost = 1 + 2 + 4 + ... 2^(2N) = 2^((2N)+1) - 1 = O(2^(2N))