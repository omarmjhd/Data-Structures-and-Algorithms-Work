/**
 * Assignment to teach dynamic programming using 3 simple example problems:
 * 1. Fibonacci numbers
 * 2. Longest common subsequence
 * 3. Edit distance
 *
 * @author Julia Ting (julia.ting@gatech.edu)
 */
public class DynamicProgrammingAssignment {
    public static int num_calls = 0; //DO NOT TOUCH

    /**
     * Calculates the nth fibonacci number: fib(n) = fib(n-1) + fib(n-2).
     * Remember that fib(0) = 0 and fib(1) = 1.
     *
     * This should NOT be done recursively - instead, use a 1 dimensional
     * array so that intermediate fibonacci values are not re-calculated.
     *
     * The running time of this function should be O(n).
     *
     * @param n A number
     * @return The nth fibonacci number
     */
    public static int fib(int n) {
        num_calls++; //DO NOT TOUCH

        if (n == 0) {

            return 0;

        }

        int[] fibonacci = new int[n + 1];
        fibonacci[0] = 0;
        fibonacci[1] = 1;

        for (int i = 2; i <= n; i++) {

            fibonacci[i] = fibonacci[i - 1] + fibonacci[i - 2];

        }

        return fibonacci[fibonacci.length - 1];

    }

    /**
     * Calculates the length of the longest common subsequence between a and b.
     *
     * @param a
     * @param b
     * @return The length of the longest common subsequence between a and b
     */
    public static int lcs(String a, String b) {
        num_calls++; //DO NOT TOUCH

        int aLength = a.length();
        int bLength = b.length();
        int[][] substrings = new int[aLength + 1][bLength + 1];

        for (int i = 0; i < aLength + 1; i++) {

            substrings[i][0] = 0;

        }

        for (int j = 0; j < bLength + 1; j++) {

            substrings[0][j] = 0;

        }

        for (int i = 1; i < aLength + 1; i++) {

            for (int j = 1; j < bLength + 1; j++) {

                if (a.charAt(i - 1) == b.charAt(j - 1)) {

                    substrings[i][j] = 1 + substrings[i - 1][j - 1];

                } else {

                    substrings[i][j] = Math.max(substrings[i][j - 1],
                            substrings[i - 1][j]);
                }

            }
        }
        return substrings[aLength][bLength];
    }

    /**
     * Calculates the edit distance between two strings.
     *
     * @param a A string
     * @param b A string
     * @return The edit distance between a and b
     */
    public static int edit(String a, String b) {
        num_calls++; //DO NOT TOUCH

        int aLength = a.length();
        int bLength = b.length();
        int[][] edit = new int[aLength + 1][bLength + 1];

        for (int i = 0; i < aLength + 1; i++) {

            edit[i][0] = i;

        }

        for (int i = 0; i < bLength + 1; i++) {

            edit[0][i] = i;

        }

        for (int i = 1; i < aLength + 1; i++) {

            for (int j = 1; j < bLength + 1; j++) {

                if (a.charAt(i - 1) == b.charAt(j - 1)) {

                    edit[i][j] = edit[i - 1][j - 1];

                } else {

                    edit[i][j] = Math.min(edit[i - 1][j - 1],
                        Math.min(edit[i][j - 1], edit[i - 1][j])) + 1;

                }

            }

        }

        return edit[aLength][bLength];
    }
}

