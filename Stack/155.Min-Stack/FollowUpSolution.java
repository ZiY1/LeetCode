// Follow Up Question:
// How to optimize the usage of stack2 especially when there are lots of duplicates

// Idea: Store each new min only once, try to make the elements in stack2 a descending order and store the elements
// in stack2 in the format of 
// <currMinValue, size of the stack1 when this element is added to stack2>

// Stack1|| 3 3 3 3 1 1 1 -7 4 3 -6 -8
// Stack2|| <3,1> <1,5> <-7, 8> <-8, 12>

public class Tuple<X, Y> {
    public final int val;
    public final int stack1Size;

    public Tuple(int x, int y) {
        this.val = x;
        this.stack1Size = y;
    }
}

// Time Complexity: O(1) for all operations
// Space Complexity: O(N) where N is the total number of inputs
class MinStack {
    // Regular stack
    private Stack<Integer> stack1;
    // Min stack that keeps track of the current min
    private Stack<Tuple> stack2;

    public MinStack() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }
    
    // stack1 performs the regular push
    // stack2 push val iff val < currMin
    public void push(int val) {
        if (stack1.empty()) {
            stack1.push(val);
            Tuple tuple = new Tuple(val, stack1.size());
            stack2.push(tuple);
        } else {
            stack1.push(val);
            int currMin = stack2.peek().val;
            if (val < currMin) {
                Tuple tuple = new Tuple(val, stack1.size());
                stack2.push(tuple);
            }
        }
    }
    
    // stack1 perform regular pop
    // stack2 pop only if the stack1 size matches stack2.peek().stack1Size
    public void pop() {
        if (stack2.peek().stack1Size == stack1.size()) {
            stack2.pop();
        }
        stack1.pop();
    }
    
    public int top() {
        return stack1.peek();
    }
    
    public int getMin() {
        return stack2.peek().val;
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