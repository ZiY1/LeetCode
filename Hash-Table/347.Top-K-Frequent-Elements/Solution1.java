// Assumption:
// Input array is not null

// Example:
// nums = [1,1,1,2,2,3], k = 2
//
// hash-table = {<1 : 3>, <2 : 2>, <3 : 1>}
//
// max-heap:
//      <1 : 3>
//    /        \
// <2 : 2>    <3 : 1>
//
// solution = [1,2]

// Algorithm:
// 1. Iterate through the input array, use a hash table <key = num, value = freq> to count frequency of each element.
// 2. Build a max-heap of size N using the frequencies stored in hashmap.
// 3. Extract the top k frequent elements from the heap.

// Data Structure: Hash Table + Max Heap
// Hash Table: to count frequency of each element
// Max Heap: to find top K frequent elements

// Time Complexity: O(N + KlogN)
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

        Comparator<ElementFreq> byFreqDescending = new Comparator<>() {
            @Override
            public int compare(ElementFreq f1, ElementFreq f2) {
                if (f1.frequency == f2.frequency) {
                    return 0;
                } else if (f1.frequency > f2.frequency) {
                    return -1;
                } else {
                    return 1;
                }
            }
        };

        // Step 2: Build a max-heap using the frequencies stored in hashmap.
        PriorityQueue<ElementFreq> maxHeap = new PriorityQueue<>(byFreqDescending);
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            maxHeap.offer(new ElementFreq(entry.getKey(), entry.getValue()));
        }

        // Step 3: Extract the top k frequent elements from the heap.
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = maxHeap.poll().value;
        }

        return result;
    }
}

// Time Analysis:
// 1. Iterate through the array, store in the hash table -> O(N)
//    a. hash table look up, store operations are O(1)
// 2. Build a max-heap of size N -> O(N)
// 3. Pop the max heap K times, for each pop  
//    a. extract root -> logN
// 3. O(KlogN)

// Total cost: O(N + KlogN)

// Best case: K is a much smaller than N
// O(N + ClogN) = O(N)

// Worst case: K is approaching N
// O(N + NlogN) = O(NlogN)

// Space Analysis:
// HashTable with N elements: O(N) 
// Max Heap with N elements: O(N)