// Assumptions:
// 1. Input is a valid RPN.
// 2. The valid operators are '+', '-', '*', and '/'.
// 3. No division by zero.

// Examples:
// ["2","1","+","3","*"]

// Data Structure: 
// Stack: need to quickly look up the previous two operands when we scan an operator.

// Algorithm:
// 1. Iterate through the input array, for each element X:
//  1.1 If X is a operand, push to the stack
//  1.2 If X is a operator, pop the previous two operands: | ... op1, op2, evalute the exp and push the result in to the stack
// 2. Return the stack.peek(), the stack should have only a result left iff the input is a valid RPN

// Time Complexity: O(N)
// Space Complexity: O(N)

class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();

        // Iterate through the tokens string
        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];
            // If c is a integer, push to stack
            // Otherwise, pop 2 operands to evaluate then push the result back in
            try {
                int operand = Integer.parseInt(token);
                stack.push(operand);
            } catch (NumberFormatException e) {
                int operand2 = stack.pop();
                int operand1 = stack.pop();
                int result = evaluateExp(operand1, operand2, token.charAt(0));
                stack.push(result);
            }
        }

        return stack.peek();
    }

    private int evaluateExp(int operand1, int operand2, char operator) {
        switch (operator) {
            case '+':
                return operand1 + operand2;
            case '-':
                return operand1 - operand2;
            case '*':
                return operand1 * operand2;
            case '/':
                return (int) operand1 / operand2;
            default:
                return -1;
        }
    }
}
