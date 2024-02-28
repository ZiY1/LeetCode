import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

class Solution1Test {

    static class TestCase {
        int[] nums;
        int target;
        int expected;

        TestCase(int[] nums, int target, int expected) {
            this.nums = nums;
            this.target = target;
            this.expected = expected;
        }
    }

    @Test
    void testFindLargestNumSmallerThanTarget() {
        Solution solution = new Solution();

        // Edge case with a very long array
        int[] veryLongArray = IntStream.range(1, 1000001).toArray();
        int targetInArray = 500000;
        int expectedIndex = 499998; // 499999 is the largest number smaller than 500000

        TestCase edgeCase = new TestCase(veryLongArray, targetInArray, expectedIndex);

        // Test cases
        TestCase[] testCases = {
                new TestCase(new int[]{1, 3, 5, 7, 9}, 6, 2), // Normal case with an even-sized array
                new TestCase(new int[]{1, 3, 5, 7, 9}, 3, 0),
                new TestCase(new int[]{1, 3, 5, 7, 9}, 2, 0),
                new TestCase(new int[]{1, 3, 5, 7, 9}, 1, -1),
                new TestCase(new int[]{1}, 6, 0),
                new TestCase(new int[]{1}, 1, -1),
                new TestCase(new int[]{2, 4, 6, 8, 10, 12}, 7, 2), // Normal case with an odd-sized array
                new TestCase(new int[]{15, 20, 25, 30}, 5, -1), // Target is smaller than the smallest number
                new TestCase(new int[]{5, 10, 15}, 20, 2), // Target is greater than the largest number
                new TestCase(new int[]{-5, -3, 0, 2, 4}, 1, 2), // Array with negative numbers
                new TestCase(new int[]{5}, 3, -1), // Single-element array, target smaller than the only element
                new TestCase(new int[]{5}, 7, 0), // Single-element array, target greater than the only element
                new TestCase(new int[]{0, 0, 0}, 0, -1), // All elements are equal, target smaller
                new TestCase(new int[]{1, 1, 1}, 1, -1), // All elements are equal, target equal
                new TestCase(new int[]{1, 1, 1}, 2, 2), // All elements are equal, target greater
                edgeCase,
                // Add more test cases as needed
        };

        // Perform assertions for each test case
        for (TestCase testCase : testCases) {
            int result = solution.findLargestNumSmallerThanTargetBest(testCase.nums, testCase.target);
            assertEquals(testCase.expected, result);
        }
    }
}
