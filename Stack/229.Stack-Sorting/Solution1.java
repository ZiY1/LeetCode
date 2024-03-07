class Solution {
    // Time Complexity: O(N^2)
    // Space Complexity: O(1)
    /**
     * @param stack1: an integer stack
     * @return: nothing
     */
    public void stackSorting(Stack<Integer> stack1) {
        // Idea: use two stacks to simulate selection sort
        // Note: we will build up the sorted ascending stack in stack2
        // At each iteration:
        // 1. Pop elements from stack1 and push to stack2 one by one, 
        // and keep track of the min, and its number.
        // 3. Pop unsorted elements from stack2 and push to stack1 one by one.
        // Q: How to identify unsoreted elements.
        // A: Two ways:
        //  a. Unsorted elements have value < min
        //  b. Store the size of stack2 (sorted size) at the begining of iteration
        // 4. Push the total number of min into stack2
        // Until stack1 is empty, meaning all elements are sorted

        Stack<Integer> stack2 = new Stack<>();

        while (!stack1.isEmpty()) {
            int min = stack1.peek();
            int minCount = 1;
            stack2.push(stack1.pop());

            // Transfer elements from stack1 to stack2, keep track of min
            // and its count
            while (!stack1.isEmpty()) {
                int curr = stack1.pop();
                stack2.push(curr);

                if (curr < min) {
                    min = curr;
                    minCount = 1;
                } else if (curr == min) {
                    minCount++;
                } 
            }

            // Now min is the min in the current iteration
            // Transfer all elements >= min from stack2 back to stack1
            while (!stack2.isEmpty() && stack2.peek() >= min) {
                int curr = stack2.pop();
                if (curr != min) {
                    stack1.push(curr);
                }
            }

            // Push all min into stack2
            while (minCount > 0) {
                stack2.push(min);
                minCount--;
            }
            
        }

        stack1.addAll(stack2);
    }
}
