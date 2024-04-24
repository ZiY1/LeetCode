// Clarification:

// Assumption:
// Optimize space if possile

// Example:
// nums1 = [1,2,3] nums2 = [2,4]
// hash table = {<2, true>, <4, true>}

// Algorithm:
// 1. Put each element in the smaller array in a hash table as <key = nums[i], value = true (memory efficient and avoiding null pointer exception)>
// 2. Iterate through the other array, for each elemetnt, check whether it is in the hash table. Return the first match. Otherwise, return -1;

// Data Structure: Hash Table
// To store element scanned in samller array

// Discussion:
// Depends on wether we need to optimize time or space, 
// then we can make the desicion on samller or larger array goes into the hash table

// Time Complexity: O(N), where N is the size of larger array
// Space Complexity: O(M), where M is the size of the smaller array
class Solution {
    public int getCommon(int[] nums1, int[] nums2) {
        int[] smaller = nums1.length < nums2.length ? nums1 : nums2;
        int[] larger = nums1.length < nums2.length ? nums2 : nums1;

        Map<Integer, Boolean> hashMap = new HashMap<>();

        for (int i = 0; i < smaller.length; i++) {
            hashMap.put(smaller[i], true);
        }

        for (int i = 0; i < larger.length; i++) {
            if (hashMap.containsKey(larger[i])) {
                return  larger[i];
            }
        }
        
       return -1;
    }
}