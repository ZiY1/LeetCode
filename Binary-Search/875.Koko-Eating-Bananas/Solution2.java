class Solution {
    // Time Complexity: O(Nlog(C))
    // Space Complexity: O(1)
    public int minEatingSpeed(int[] piles, int h) {
        // we are looking for min k s.t finish eat all within h hours, similar to left
        // bound

        int maxBananas = piles[0];
        for (int i = 1; i < piles.length; i++) {
            if (piles[i] > maxBananas) {
                maxBananas = piles[i];
            }
        }

        int left = 1;
        int right = maxBananas;

        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            int hours = totalHoursFinishEating(piles, mid);

            if (hours == h) {
                // The current eating speed: mid could be correct,
                // but we want to see if there are slower speed that also satisfy hours == h
                right = mid;
            } else if (hours > h) {
                // The total hours finish eating is too long, speed up!
                left = mid + 1;
            } else if (hours < h) {
                // The total hours finish eating is within h, could be correct
                // but again, we want to see if if there are slower speed
                right = mid;
            }
        }

        // Post-processing
        int hourL = totalHoursFinishEating(piles, left);
        int hourR = totalHoursFinishEating(piles, right);

        // Check left first since if left and right both answer, we want the slower/samller speed
        if (hourL <= h) {
            return left;
        }

        if (hourR <= h) {
            return right;
        }

        return -1;
    }

    private int totalHoursFinishEating(int[] piles, int speed) {
        int count = 0;
        for (int i = 0; i < piles.length; i++) {
            count += (int) Math.ceil((double)piles[i] / speed);
        }
        return count;
    }

}