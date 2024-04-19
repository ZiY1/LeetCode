// Assumption:
// All the values of coins are unique.

// Example:
// coins = [1,2,5], amount = 5
//                             o
//                    
//  Layer 0: Coin 1              x0:5                     x1:4                   x2:3              x3:2           x4:1      [x5:0]
//                                  
//  Layer 1: Coin 2      x0:5        x1:3  x2:1       x0:4  x1:3  [x2:0]       x0:3  x1:1       x0:2  [x1:0]      x0:1
//
//  Layer 2: Coin 5   x0:5  [x1:0]   x0:3  x0:1       x0:4  x0:3               x0:3  x0:1       x0:2              x0:1

// Algorithm: DFS/Backtracking

// DFS Technique:
// 1. What does each layer represent? How many layers do we have?
// - Each layer represents the valid # of ith coins selected
// e.g. type of coin on the current layer: 5 -> x0, x1, x2, ... until the amount remains is not enough for us to select more coins
// - Total N layers, where N = number of different type of coins avaliable
// 2. How many states do we have at each layer?
// - Dynamic, it depends on the amount left we can use

// Let N be the number of coins
// Let M be the amount
// Time Complexity: O(M^N)
// Space Complexity (call stack): O(N)

class Solution {
    public int change(int amount, int[] coins) {
        return changeHelper(coins, 0, amount);
        // return changeHelper2(coins, amount, 0);
    }

    private int changeHelper(int[] coins, int index, int amountLeft) {
        // Base case:
        if (amountLeft == 0) {
            return 1;
        } else if (index == coins.length) {
            return 0;
        }

        // Recursive case:
        int changeWays = 0;
        int currCoinValue = coins[index];
        // # of diffent number of curret type of the coin we can choose
        int numCoinToUse = amountLeft / currCoinValue;

        // Each different number of current type of the coin is a branch/recursion
        for (int i = 0; i <= numCoinToUse; i++) {
            changeWays += changeHelper(coins, index + 1, amountLeft - (currCoinValue * i));
        }

        return changeWays;
    }

    // Avoiding Duplicates: The start index ensures that we do not consider previous coins again, 
    // which prevents permutations of the same combination (e.g., using [2, 1] and [1, 2] are considered the same combination of making 3).
    private int changeHelper2(int[] coins, int amountLeft, int start) {
        if (amountLeft == 0) {
            return 1;
        } else if (amountLeft < 0) {
            return 0;
        }

        int changeWays = 0;
        // Select each different TYPE of coins at the current layer
        for (int i = start; i < coins.length; i++) {
            changeWays += changeHelper2(coins, amountLeft - coins[i], i);
        }

        return changeWays;
    }
}

// Time Analysis:

// Recursion Tree:

// Level                  Cost
//   0         o
//          / ... \      
//         o  ...  o     at most = M
//                       M^2
//  ...  
//   N                   M^N

// Where N is the number of diffrent types of coins
// Where M is the total amount
// Total cost: O(M^N)

// If amount = 99, coins = [1, 5, 10, 25], total cost = O(99^4)

// Thinking:
// Another Solution (worse than above):
// 1. What does each level represent, and how many layer do we need?
// - Each level represents the valid different type of coin we select
//     e.g. either choose 1 or 5 or 10 or 25
// - Total levels = amount (at most)
// 2. How many different states on each level
// - # of type of coins

// Level                  Cost
//   0         o
//          / ... \      
//         o  ...  o       N
//  ...
//   at most M

// Where N is the number of diffrent types of coins
// Where M is the total amount

// Time Complexity: O(N^M)

// If amount = 99, coins = [1, 5, 10, 25], total cost = O(4^99)
// Pro: easy to think
// Con 1: stack overflow
// Con 2: time complexity increases dramaticly as we increase to amount, amount = 9999 -> O(4^9999) :(
// Con 3: duplication!