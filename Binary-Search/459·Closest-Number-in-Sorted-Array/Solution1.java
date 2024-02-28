class Solution {
    // Time Complexity: O(logN)
    // Space Complexity: O(1)
    public int closestNumber(int[] a, int target) {
        // We can turn this problem into finding the first index i 
        // s.t <= a[target] by. utilizing the [left, right) template 

        int left = 0;
        int right = a.length;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (a[mid] == target) {
                right = mid;
            } else if (a[mid] < target) {
                left = mid + 1;
            } else if (a[mid] > target) {
                right = mid;
            }
        }

        // Post-processing

        // First: check index left
        // 1. we know left could only search out of bound towards right,
        // If so, we know target doesn't exist and the cloest element to target
        // is the last element
        if (left >= a.length) {
            return a.length - 1;
        }

        // Second: check if left is target
        // 1. if left is target, the cloest element is itself
        // 2. if left is not target(target doesn't exist), 
        // left is the first element > target say 
        //  2.1 left is the first element -> left is the cloest
        //  2.2 otherwise, compare left and left - 1, whoever is cloest
        if (a[left] == target) {
            return left;
        }

        if (left == 0) {
            return left;
        } 

        if (Math.abs(a[left] - target) < Math.abs(a[left - 1] - target)) {
            return left;
        } else {
            return left - 1;
        }
        
    }
}