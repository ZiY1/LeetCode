// import java.util.Deque;
// import java.util.LinkedList;
import java.util.Stack;


// Amortized Time Complexity: O(1)
// Why? Re-Balancing seems takes O(n) -> right but Re-Balancing rarely happens
// Re-Balancing Cost: Each element is moved at most twice - once to the buffer stack (stack3)
// and once back to either stack1 or stack2. -> cost per element: 2 -> total cost: 2n
// Consider the low frequency for performing Re-Balancing, we can spread the total cost over
// each element, 2n / n = 2 -> O(1)

// Space Complexity: O(n)

// Idea: Simulate Deque (double-ended queue) using three stacks
// deque
// 654321

//   s1      s2
// 654321|

// - addFirst, addLast are straightforward
// - The trick part is stack1 or stack2 is empty when performing removeFirst or removeLast, we have to re-balance
// the elements evenly between the two stacks with a help of additional stack3 (buffer)

// Goal:
//   s1      s2
//    654|321

// 1. Transfer the first half of the elements from s1 to s3
//   s1      s2
//    321|
// s3 456|
// 2. Transfer the second half of the elements from s1 to s2
//   s1      s2
//       |321
// s3 456|
// 3. Transfer the elements in s3 to s1
//   s1      s2
//    654|321
// s3 |
class MyDeque1 {
    // Stores left half of Deque
    public Stack<Integer> stack1;
    // Store right half of Deque
    public Stack<Integer> stack2;
    // Buffer
    public Stack<Integer> stack3;

    // Key idea:
    // 1 2 3 || || 4 5 6
    // stack1     stack2
    // where all elements are equally distributed between stack1 and stack2
    public MyDeque1() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
        stack3 = new Stack<>();
    }

    public void addFirst(int x) {
        stack1.push(x);
    }

    public int removeFirst() {
        // If stack1 is not empty, we can pop as normal
        if (!stack1.isEmpty()) {
            return stack1.pop();
        }
        // If stack1 is empty, we need to re-balance stack1 and stack2
        int threshold = stack2.size() / 2;

        // 1. Transfer the first half of the elements from s2 to s3
        while (stack2.size() > threshold) {
            stack3.push(stack2.pop());
        }

        // 2. Transfer the second half of the elements from s2 to s1
        while (stack2.size() > 0) {
            stack1.push(stack2.pop());
        }

        // 3. Transfer the elements in s3 to s2
        while (stack3.size() > 0) {
            stack2.push(stack3.pop());
        }

        // Finally perform pop from stack1
        return stack1.pop();
    }

    public void addLast(int x) {
        stack2.push(x);
    }

    public int removeLast() {
        // If stack2 is not empty, we can pop as normal
        if (!stack2.isEmpty()) {
            return stack2.pop();
        }
        // If stack2 is empty, we need to re-balance stack1 and stack2
        int threshold = stack1.size() / 2;

        // 1. Transfer the first half of the elements from s1 to s3
        while (stack1.size() > threshold) {
            stack3.push(stack1.pop());
        }

        // 2. Transfer the second half of the elements from s1 to s2
        while (stack1.size() > 0) {
            stack2.push(stack1.pop());
        }

        // 3. Transfer the elements in s3 to s1
        while (stack3.size() > 0) {
            stack1.push(stack3.pop());
        }

        // Finally perform pop from stack1
        return stack2.pop();
    }

}

class MyStack {
    public static void main(String[] args) {
        MyDeque1 dequeTest = new MyDeque1();
//        Deque<Integer> dequeCorrect = new LinkedList<>();

        for (int i = 6; i > 0; i--) {
            dequeTest.addLast(i);
        }

//        System.out.println(dequeTest.removeFirst());
//        System.out.println(dequeCorrect.removeFirst());
//        System.out.println();

//        System.out.println(dequeTest.removeLast());
//        System.out.println(dequeCorrect.removeLast());
//        System.out.println();

//        System.out.println(dequeTest.removeFirst());
//        System.out.println(dequeTest.removeFirst());
//        System.out.println(dequeTest.removeFirst());
//        System.out.println(dequeTest.removeFirst());
//        System.out.println();
//        System.out.println(dequeTest.stack2.size());
//        System.out.println(dequeTest.stack1.size());
//
//        System.out.println(dequeTest.removeFirst());
//        System.out.println();
//        System.out.println(dequeTest.stack2.size());
//        System.out.println(dequeTest.stack1.size());


    }
}
