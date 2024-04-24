// Assumption:
// Input array is not null

// Example:
// nums = [1,1,1,2,2,3], k = 2
//
// hash-table = {<1 : 3>, <2 : 2>, <3 : 1>}
//
// min-heap:
//      <1 : 3>
//    / 
// <2 : 2> 
//
// solution = [1,2]

// Algorithm:
// 1. Iterate through the input array, use a hash table <key = num, value = freq> to count frequency of each element.
// 2. Build a min-heap of size K using the 0-th to (k-1)-th element stored in hash table
//    From k-th element to last element in hash table, if the element's frequency > root:
//    a. Pop the root
//    b. Insert this element in the min-heap
// 3. Extract the all elements from the min-heap of size K.

// Data Structure: Hash Table + Min Heap
// Hash Table: to count frequency of each element
// Min Heap: to find top K frequent elements

// Time Complexity: O(N + (N - K)logK + KlogK)
// Space Complexity: O(N)
class Solution {
    // Helper class to hold element and its frequency
    static class ElementFreq {
        int value;
        int frequency;

        ElementFreq(int value, int frequency) {
            this.value = value;
            this.frequency = frequency;
        }
    }

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        // Step 1: Use a hashmap to count frequency of each element.
        for (int i = 0; i < nums.length; i++) {
            if (frequencyMap.containsKey(nums[i])) {
                frequencyMap.put(nums[i], frequencyMap.get(nums[i]) + 1);
            } else {
                frequencyMap.put(nums[i], 1);
            }
        }

        Comparator<ElementFreq> byFreqAscending = new Comparator<>() {
            @Override
            public int compare(ElementFreq f1, ElementFreq f2) {
                return Integer.compare(f1.frequency, f2.frequency);
            }
        };

        // Step 2: Build a min-heap of size K
        int cursor = 0;
        PriorityQueue<ElementFreq> minHeap = new PriorityQueue<>(byFreqAscending);
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            if (cursor < k) {
                minHeap.offer(new ElementFreq(entry.getKey(), entry.getValue()));
            } else if (entry.getValue() > minHeap.peek().frequency) {
                minHeap.poll();
                minHeap.offer(new ElementFreq(entry.getKey(), entry.getValue()));
            }
            cursor++;
        }

        // Step 3: Extract the top k frequent elements from the heap.
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = minHeap.poll().value;
        }

        return result;
    }
}

// Time Analysis:
// 1. Iterate through the array, store in the hash table -> O(N)
//    a. hash table look up, store operations are O(1)
// 2. Build a min-heap of size K -> O(K)
//    From k-th element to last element in hash table -> O((N - K)logK)
//     a. Pop the root -> O(logK)
//     b. Insert this element in the min-heap -> O(logK)
// 3. Extract the all elements from the min-heap of size K. -> O(KlogK)
// Total cost: O(N + (N - K)logK + KlogK)

// Best case: K is a much smaller than N
// O(N + (N - C)logC + ClogC) = O(N)

// Worst case: K is approaching N
// O(N + (N - N)logN + NlogN) = O(NlogN)

// Space Analysis:
// HashTable with N elements: O(N) 
// Max Heap with N elements: O(N)