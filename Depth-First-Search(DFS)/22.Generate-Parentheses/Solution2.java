// Assumption:

// Example: n = 2, left parenthesis remain: lp = 2, right parenthesis remain: rp = 2
//                                   o
//                            lp = 2, rp = 2
//                +(? /                           \ +)?
// 1                 (                                 o
//             lp = 1, rp = 2
//              /          \
// 2        ((                ()
//    lp = 0, rp = 2     lp = 1, rp = 1
//        /    \           /      \
// 3    o      (()       ()(       o
//      lp = 0, rp = 1 lp = 0, rp = 1
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
//  a. when there are '(' left unplaced: lp > 0
// 2. When could we place a right parenthesis ')' ?
//  a. only when the # of '(' added so far > # of ')' added so far: rp > lp

// Time Complexity: O(2^(2N))
// Space Complexity (call stack): O(2 * N) = O(N)
class Solution {
    public List<String> generateParenthesis(int n) {
       StringBuilder oneValidParen = new StringBuilder();
       List<String> validParens= new ArrayList<>();
       generateParenthesisHelper(n, n, oneValidParen, validParens);
       return validParens;
    }

    private void generateParenthesisHelper(int leftParenRemain, int rightParenRemain, StringBuilder oneValidParen, List<String> validParens) {
        // Base case:
        if (leftParenRemain == 0 && rightParenRemain == 0) {
            validParens.add(new String(oneValidParen.toString()));
        }

        // Recursive rule:
        
        // Try to add '(' if allowed
        if (leftParenRemain > 0) {
            oneValidParen.append('(');
            generateParenthesisHelper(leftParenRemain - 1, rightParenRemain, oneValidParen, validParens);
            oneValidParen.deleteCharAt(oneValidParen.length() - 1); //!!! must delete after we append
        }

        // Add right paren if allowed
        if (rightParenRemain > leftParenRemain) {
            oneValidParen.append(')');
            generateParenthesisHelper(leftParenRemain, rightParenRemain - 1, oneValidParen, validParens);
            oneValidParen.deleteCharAt(oneValidParen.length() - 1);
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