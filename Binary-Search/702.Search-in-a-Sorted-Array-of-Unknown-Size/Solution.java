// Clarification:

// Assumption:

// Approach: Binary Search
// 1. In order to perform binary search, we must know the left bound and right bound
// 2. To quickly find the right bound, we can jump by two times more each time until we jump out of bound i.e.
// x                    2^0
// xx                   2^1
// xxxx                 2^2
// xxxxxxxx             2^3
// xxxxxxxxxxxxxxxx     2^4
// ...
// x ... x              2^(n+1)
// Let's say we jump out of bound at size 2^(n+1), and still in bound at 2^n.
// If the array size is N, then we have jumped to 2N at most
// Now we can use this 2N as the right bound to perform binary search


/**
 * // This is ArrayReader's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface ArrayReader {
 *     public int get(int index) {}
 * }
 */

 class Solution {
    public int search(ArrayReader reader, int target) {
        int left = 0;
        int right = 1;
        int jumpFactor = 2;
        // Jump out two times more each time until out of bound
        while (reader.get(right) != Integer.MAX_VALUE) {
            right *= jumpFactor;
        }

        // Now perform binary search at the range[0, 2N]
        while (left <= right) { // Terminate when left + 1 = right meaning we have searched every element in this range
            int mid = left + (right - left) / 2;
            if (reader.get(mid) == target) {
                return mid;
            } else if (reader.get(mid) < target) {
                left = mid + 1;
            } else if (reader.get(mid) > target) {
                right = mid - 1;
            }
        }

        return -1;
    }
}

// Time Analysis:
// 1. Jump out two times more each time -> log(2, N)
// 2. Since we can jump to 2N at most, the search range is [0, 2N]
//    Jump in (binary search) -> log(2, 2N)
// Total Time: O(log(2, N) + log(2, 2N))

// Follow up: Why jump out two times more, why not jump out ten times more? Which one has faster time overall?
// - OK, let's analyze these two and find out:

// Qualitative Analysis:
// 1. To jump out: Ten times is faster
// 2. To jump in: Two times is faster because the over-shot distance is shorter

// Quantitative Analysis:
//                      Two Times         Ten Times
// Jump Out             log(2, N)          log(10, N)
// Jump In              log(2, 2N)        log(2, 10 N)

// Milestone:
// To see which function grows faster:
// Step 1:
// Let A = log(2, N) + log(2, 2N)
// Let B = log(10, N) + log(2, 10N)
// Step 2: Three Cases
// If limit(A - B) > 0, then A grows faster, B has a lower time
// If limit(A - B) = 0, then A and B grows at the same rate
// If limit(A - B) < 0, then B grows faster, A has a lower time

// Ten Times - Two Times
// log(10, N) + log(2, 10N) - (log(2, N) + log(2, 2N))
// = log(10, N) - log(2, N) + log(2, 10N) - log(2, 2N)
// = log(10, N) - log(2, N) + log(2, 10N/2N)
// = log(10, N) - log(2, N) + log(2, 5)
// = log(10, N) - log(2, N) + 2.5
// = < 0 + 2.5 when N is very large
// < 0

// Thus Ten Times has a lower time complexity
