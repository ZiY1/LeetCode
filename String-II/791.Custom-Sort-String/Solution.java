// Clarification:

// Assumption:
// 1. Input strings are neither null nor empty
// 2. All chars in order are unqiue
// 3. Input strings contains only lowercase English letter

// Examples:
// order = "cba", s = "abcd"
//      abcd
//   ab      cd
//  a  b    c  d
//----------------
//   ba      dc
//      dcba

// order = "exv", s = "xwvee"
//           xwvee
//      xwv          ee
//   xw     v     e     e
//  x  w    v     e     e
// ------------------------
//   xw     v     e     e
//      xvw          ee
//           eexvw


// Data Structure: Hash Table
// - Store each element in order as <key = char, value = index> in a hash table 
// in order to quickly look up and compare characters by their index

// Approach: Merge Sort
// 1. Store each element in order in an hash table as <key = char, value = index>
// 2. Perform merge sort on s
// Base case:
// When the there is one element left (already sorted), return
// Recursive case:
// Assume the recursive call successfully sort the first half and the second half of the input string
// Merge them based on the order

// Time Complexity: O(NlogN), where N is the size of string s
// Space Complexity: O(N)
class Solution {
    public String customSortString(String order, String s) {
        // Put order in a hash map as <key = char, value = index>
        Map<Character, Integer> orderMap = new HashMap<>();
        for (int i = 0; i < order.length(); i++) {
            orderMap.put(order.charAt(i), i);
        }

        // Merge sort s
        char[] chars = s.toCharArray();
        mergeSort(chars, 0, chars.length - 1, orderMap);
        return new String(chars);
    }

    private void mergeSort(char[] chars, int left, int right, final Map<Character, Integer> order) {
        // Base case:
        // If there is only one element, it is already sorted
        if (left == right) {
            return;
        }

        // Recursive rule:
        int mid = left + (right - left) / 2;

        // Recursively sort the left half
        mergeSort(chars, left, mid, order);
        // Recurisvely sort the right half
        mergeSort(chars, mid + 1, right, order);

        // Merge
        merge(chars, left, mid, right, order);
    } 

    private void merge(char[] chars, int left, int mid, int right, final Map<Character, Integer> order) {
        // 0 1 2 3
        // b a d c

        // Copy the left and right half to aux array
        char[] leftArr = new char[mid - left + 1];
        char[] rightArr = new char[right - mid];
        for (int i = 0; i < leftArr.length; i++) {
            leftArr[i] = chars[left + i];
        }
        for (int j = 0; j < rightArr.length; j++) {
            rightArr[j] = chars[mid + 1 + j];
        }

        // Merge
        int leftArrCursor = 0;
        int rightArrCursor = 0;
        int charsCursor = left;

        while (leftArrCursor < leftArr.length && rightArrCursor < rightArr.length) {
            // Four Cases:
            // 1. Both keys are in order map, both orders matters
            // 2. Left key is in order map and right key is not in order map, right order does not matter
            // 3. Right key is in order map and left key is not in order map, left order does not matter
            // 4. Both key are not in order map, both order does not matter
            if (order.containsKey(leftArr[leftArrCursor]) && order.containsKey(rightArr[rightArrCursor])) {
                if (order.get(leftArr[leftArrCursor]) < order.get(rightArr[rightArrCursor])) {
                    chars[charsCursor++] = leftArr[leftArrCursor++];
                } else {
                    chars[charsCursor++] = rightArr[rightArrCursor++];
                }
            } else if (!order.containsKey(leftArr[leftArrCursor]) && order.containsKey(rightArr[rightArrCursor])) {
                chars[charsCursor++] = leftArr[leftArrCursor++];
            } else if (order.containsKey(leftArr[leftArrCursor]) && !order.containsKey(rightArr[rightArrCursor])) {
                chars[charsCursor++] = rightArr[rightArrCursor++];
            } else {
                chars[charsCursor++] = leftArr[leftArrCursor++];
                chars[charsCursor++] = rightArr[rightArrCursor++];
            }
        }

        while (leftArrCursor < leftArr.length) {
            chars[charsCursor++] = leftArr[leftArrCursor++];
        }

        while (rightArrCursor < rightArr.length) {
            chars[charsCursor++] = rightArr[rightArrCursor++];
        } 
    }
}

// Time Analysis:

// Recurrence Formula:
// T(n) = 2 * T(n/2) + O(n)

// Recursion Tree:
//  Level             Cost
//  0         o       N
//           / \
//  1       o   o     N/2 + N/2 = N
// ...
// log(2,N)           N
// Total Cost: O(NlogN)
