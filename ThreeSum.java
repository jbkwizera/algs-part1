import java.util.Arrays;

public class ThreeSum {

    public static void main(String[] args) {
        /*
         * Given N distinct integers, how many triples sum to exactly zero?
         *
         * Quadratic solution hint: Given an integer x and a sorted array a[] of N integers, 
         * design a linear-time algorithm to determine if there exists two distinct indices
         * i and j such that a[i] + a[j] == x
         */

        int[] a = StdIn.readAllInts();
        int N = a.length;
        Arrays.sort(a);

        int count = 0;
        for (int k = 0; k < N; k++) {
            int i = 0;
            int j = N-1;
            while (i < j) {
                if (i == k) { i++; continue; }
                if (j == k) { j--; continue; }

                if      (a[i] + a[j] + a[k] < 0) i++;
                else if (a[i] + a[j] + a[k] > 0) j--;
                else {
                    System.out.printf("%2d: %4d %4d %4d\n", ++count, a[i], a[j], a[k]);
                    i++;
                }
            }
        }
    }
}

