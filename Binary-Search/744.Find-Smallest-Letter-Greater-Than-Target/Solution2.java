class Solution {
    // Time Complexity: O(logN)
    // Space Complexity: O(1)
    public char nextGreatestLetter(char[] letters, char target) {
        int left = 0;
        int right = letters.length - 1;

        while(left < right - 1) {
            int mid = left + (right - left) / 2;

            if (letters[mid] == target) {
                left = mid + 1;
            } else if (letters[mid] < target) {
                left = mid + 1;
            } else if (letters[mid] > target) {
                right = mid;
            }
        } 

        if (letters[left] > target) {
            return letters[left];
        }

        if (letters[right] > target) {
            return letters[right];
        }

        return letters[0];
    }
}
