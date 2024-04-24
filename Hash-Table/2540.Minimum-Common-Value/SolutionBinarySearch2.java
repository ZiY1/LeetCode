// Clarification:

// Assumption:

// Algorithm: Binary Search
// Iterate through the smaller array, for each element X: perform binary search on the larger array

// Time Complexity: O(MlogN), where M is the size of the smaller array and N is the size of the larger array
// Space Complexity: O(1)
class Solution {
    public int getCommon(int[] nums1, int[] nums2) {
        int[] smaller = nums1.length < nums2.length ? nums1 : nums2;
        int[] larger = nums1.length < nums2.length ? nums2 : nums1;

        // Perform binary search on each element of the smaller array
        for (int i = 0; i < smaller.length; i++) {
            int result = binarySearch(larger, smaller[i]);
            // Return if found
            if (result != -1) {
                return result;
            }
        }

        return -1;
    }

    private int binarySearch(int[] nums, int target) {
        // [left, right) -> [left, mid) [mid + 1, right)
        int left = 0;
        int right = nums.length;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return nums[mid];
            } else if (nums[mid] < target) {
                // Search right half
                left = mid + 1;
            } else if (nums[mid] > target) {
                // Search left half
                right = mid;
            }
        }

        return -1;
    }
}

// Time Analysis: 
// Let M be the size of the smaller array and N be the size of the larger array
// Iterate through the smaller array -> O(M)
// For each element, perform binary search on the larger array -> O(logN)
// Total time: O(MlogN)

// Why iterate the smaller, binary seach the larger?
// O(MlogN) < O(NlogM) when M < N