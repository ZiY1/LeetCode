// Idea: keep push() and pop() in sync between stack1 and stack2
// Stack1|| 3 1 -7 -6 -8
// Stack2|| 3 1 -7 -7 -8

// Time Complexity: O(1) for all operations
// Space Complexity: O(N) where N is the total number of input
class MinStack {
    // Regular stack
    private Stack<Integer> stack1;
    // Min stack that keeps track of the current min
    private Stack<Integer> stack2;

    public MinStack() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }
    
    // stack1 performs the regular push
    // stack2 push the current min element:
    // If new val < currMin, push new val; otherwise push currMin
    public void push(int val) {
        if (stack1.isEmpty()) {
            stack1.push(val);
            stack2.push(val);
        } else {
            stack1.push(val);
            int currMin = stack2.peek();
            if (val < currMin) {
                stack2.push(val);
            } else {
                stack2.push(currMin);
            }
        }
    }
    
    // Both stacks perform regular pop at same time
    public void pop() {
        stack1.pop();
        stack2.pop();
    }
    
    public int top() {
        return stack1.peek();
    }
    
    public int getMin() {
        return stack2.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
