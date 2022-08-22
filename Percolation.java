import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final WeightedQuickUnionUF uf;
    private boolean[][] isOpen;
    private int count = 0;
    private final int n;

    // Creates n-by-n grid, with all sides initially blocked
    public Percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException("n must be strictly positive.");

        this.n = n;
        uf = new WeightedQuickUnionUF(n*n + 2);
        isOpen = new boolean[n+1][n+1];
    }
    private void boundsCheck(int row, int col) {
        if (row < 1 || col < 1 || row > n || col > n)
            throw new IllegalArgumentException("Index out of bounds.");
    }

    // Opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        boundsCheck(row, col);

        if (isOpen[row][col]) return;
        isOpen[row][col] = true;
        int i = n*(row-1) + col;
        if (row > 1 && isOpen[row-1][col]) uf.union(i-n, i);
        if (row < n && isOpen[row+1][col]) uf.union(i+n, i);
        if (col > 1 && isOpen[row][col-1]) uf.union(i-1, i);
        if (col < n && isOpen[row][col+1]) uf.union(i+1, i);
        if (row == 1) uf.union(0, col);
        if (row == n) uf.union(n*n+1, n*(n-1) + col);
        count++;
    }

    // Is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        boundsCheck(row, col);
        return isOpen[row][col];
    }

    // Is the site (row, col) full?
    public boolean isFull(int row, int col) {
        boundsCheck(row, col);
        return uf.find(0) == uf.find(n*(row-1)+col);
    }

    // Returns the number of open sites
    public int numberOfOpenSites()
    {   return count; }

    // Does the system percolate?
    public boolean percolates() {
        return uf.find(0) == uf.find(n*n+1);
    }

    // Test client
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);

        int totalCount = 0;
        double totalFract = 0;
        for (int trial = 0; trial < t; trial++) {
            Percolation grid = new Percolation(n);
            while (!grid.percolates()) {
                int i = StdRandom.uniform(1, n+1);
                int j = StdRandom.uniform(1, n+1);
                grid.open(i, j);
            }
            int count = grid.numberOfOpenSites();
            totalCount += count;
            totalFract += 1.0*count/(n*n);
        }
        System.out.printf("Open sites: %7d\n", totalCount/t);
        System.out.printf("Threshold : %7.5f\n", totalFract/t);
    }
}
