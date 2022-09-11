public class BitonicSearch {
    public static int peak(int[] a) {
        int lo = 1;
        int hi = a.length-2;
        while (lo <= hi) {
            int i = lo + (hi - lo)/2;
            if      (a[i] > a[i-1] && a[i] > a[i+1]) return i;
            else if (a[i] > a[i-1]) lo = i+1;
            else                    hi = i-1;
        }
        return -1;
    }

    public static int find(int[] a, int x, int lo, int hi) {
        while (lo <= hi) {
            int i = lo + (hi - lo)/2;
            if 
    public static boolean less(int[] a, int i, int j)
    {   return a[i] < a[j]; }

    public static boolean more(int[] a, int i, int j)
    {   return a[i] > a[j]; }

    public static void main(String[] args) {
        int[] a = StdIn.readInts();
        int x = Integer.parseInt(args[0]);
        int N = a.length;

        int k = peak(a);
        if (x > a[k]) System.out.printf("%2d %2d\n", k, leftFind(a, x, 0, k));
        else          System.out.printf("%2d %2d\n", k, rightFind(a, x, k, N-1));
    }
}
