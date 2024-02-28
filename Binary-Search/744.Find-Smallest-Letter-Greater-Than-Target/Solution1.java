class Solution {
    // Time Complexity: O(logN)
    // Space Complexity: O(1)
    public char nextGreatestLetter(char[] letters, char target) {
        int left = 0;
        int right = letters.length;

        while(left < right) {
            int mid = left + (right - left) / 2;

            if (letters[mid] == target) {
                left = mid + 1;
            } else if (letters[mid] < target) {
                left = mid + 1;
            } else if (letters[mid] > target) {
                right = mid;
            }
        }

        // left could search out of bound towards right because [left, right)
        if (left < 0 || left >= letters.length) {
            return letters[0];
        }

        return letters[left];
    }
}
