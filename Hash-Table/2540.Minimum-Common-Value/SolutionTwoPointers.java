// Clarification:

// Assumption:

// Example:
// nums1 = [1,2,3,6] nums2 = [2,3,4,5]
//            i               j

// Algorithm: Two Pointers
// 1. Let a pointer points to nums1 say i, a pointer points to nums2 say j
// 2. Move the pointer who is pointing to a smaller value until two pointer pointing to the same value or one pointer out of range

// Time Complexity: O(N + M), where N is the size of nums1 and M is the size of nums2
// Space Complexity: O(1)
class Solution {
    public int getCommon(int[] nums1, int[] nums2) {
        int i = 0; // points to nums1
        int j = 0; // points to nums2

        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                return nums1[i];
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }

        return -1;
    }
}

// Time Analysis: 
// What is the worst case?
// nums1 = [1,2,3,4,10]  nums2 = [6,7,8,9,10]
//                   i                     j