// Idea: implement FIFO queue using two stacks
// Stack1: to buffer all new elements -> push(x) goes to Stack1
// Stack2: To pop out the first element
//     Case2.1: If Stack2 is empty, then we move all elements from Stack1 to 
// Stack2 one by one. Then pop the top element from Stack2
//     Case2.2: If Stack2 is not empty, then call Stack2.pop()
class MyQueue {
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;
    public MyQueue() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }
    
    // Time Complexity: O(1)
    public void push(int x) {
        stack1.push(x);
    }
    
    // Amortized Time Complexity: O(1)
    public int pop() {
        // If stack2 is not empty, pop
        if (!stack2.empty()) {
            return stack2.pop();
        }
        // If stack2 is empty, push all elements from stack1 to stack2, then pop
        while (!stack1.empty()) {
            int e = stack1.pop();
            stack2.push(e);
        }
        return stack2.pop();
    }
    
    // Amortized Time Complexity: O(1)
    public int peek() {
        // If stack2 is not empty, peak
        if (!stack2.empty()) {
            return stack2.peek();
        }
        // If stack2 is empty, push all elements from stack1 to stack2, then pop
        while (!stack1.empty()) {
            int e = stack1.pop();
            stack2.push(e);
        }
        return stack2.peek();
    }
    
    // Time Complexity: O(1)
    public boolean empty() {
        return (stack1.empty() && stack2.empty());
    }
}

// Amortized Time Complexity Analysis:
// n elements (1, 2, ... n)
// First time call pop(): pop from stack1(n) + push to stack2(n) + pop from stack2(1) -> 2n + 1
// Second time call pop(): pop from stack2(1) -> 1
// Third time call pop(): pop from stack2(1) -> 1
// ...
// Nth time call pop(): pop from stack2(1) -> 1
// Amortized Time Complexity = (2n + 1 + n-1*(1)) / n = 3n / n = 3 -> O(1)

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
