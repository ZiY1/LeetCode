// Description:
// Given a string, remove all leading/trailing/duplicated empty spaces.

// Assumption:
// The input string is not null or empty

// Example:
// "__Hello__World__" -> "Hello_World"

// Hello_Worldrld__
//            s
//                 f

// Algorithm: Two Pointers
// slow:
// - All elements on the left-hand side of slow (excluding slow) are result to return
// - Increment after an element to returned got copied to slow
// fast:
// - To discover each element
// - Increments at every iteration

// (W1)(_W2)(_W3)...(_Wn)
// 1. When the fast pointer discovers a non-whitespace element(say a letter), copy the whole word to slow.
// 2. When copy over a word to slow, add a white space expect the first word

// Time Complexity: O(N)
// Space Complexity: O(N) for Java since Java String is immutable
class Solution2 {
    public static String removeSpaces(String str) {
        char[] chars = str.toCharArray();
        int slow = 0;
        int fast = 0;
        int count = 0;

        while (fast < chars.length) {
            if (!Character.isWhitespace(chars[fast]) && count > 0) {
                chars[slow++] = ' ';
            }
            while (fast < chars.length && !Character.isWhitespace(chars[fast])) {
                chars[slow++] = chars[fast++];
                count++;
            }

            fast++;
        }

        return new String(chars, 0, slow);
    }

    public static void main(String[] args) {
        String str = "    !23bq      dqj[v..[      World!   ";
        String res = removeSpaces(str);
        System.out.println(res);
    }
}
