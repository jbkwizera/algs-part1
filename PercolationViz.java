import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdRandom;

public class PercolationViz {
    PercolationStats stats;
    Percolation grid;
    boolean[][] isFull;
    int N;

    public PercolationViz(int n, int t) {
        N = n;
        stats = new PercolationStats(n, t);
        grid = new Percolation(n);
        while (!grid.percolates()) {
            int i = StdRandom.uniform(1, n+1);
            int j = StdRandom.uniform(1, n+1);
            grid.open(i, j);
        }
        isFull = new boolean[n+1][n+1];
        for (int j = 1; j <= N; j++)
            flow(isFull, 1, j);
    }

    public void grid() {
        int mn = 0;
        int mx = 20*N;
        int wd = 20;
        StdDraw.setXscale(mn-wd/2.0, mx+wd/2.0);
        StdDraw.setYscale(mn-wd/2.0, mx+wd/2.0);

        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.rectangle(mx/2.0, mx/2.0, mx/2.0, mx/2.0);
        StdDraw.filledRectangle(mx/2.0, mx/2.0, mx/2.0, mx/2.0);
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                double x = j      *wd - wd/2.0;
                double y = (N-i+1)*wd - wd/2.0;
                if (grid.isOpen(i, j)) {
                    StdDraw.setPenColor(StdDraw.WHITE);
                    StdDraw.filledRectangle(x, y, wd/2.0, wd/2.0);
                    StdDraw.setPenColor(StdDraw.BLACK);
                    StdDraw.rectangle(x, y, wd/2.0, wd/2.0);
                }
            }
        }
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                double x = j      *wd - wd/2.0;
                double y = (N-i+1)*wd - wd/2.0;
                if (isFull[i][j]) {
                    StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
                    StdDraw.filledRectangle(x, y, wd/2.0, wd/2.0);
                    StdDraw.setPenColor(StdDraw.BLACK);
                    StdDraw.rectangle(x, y, wd/2.0, wd/2.0);
                }
            }
            StdDraw.pause(100);
        }
    }

    private void flow(boolean[][] isFull, int i, int j) {
        if (i < 1 || i > N) return;
        if (j < 1 || j > N) return;
        if (!grid.isOpen(i, j)) return;
        if (isFull[i][j]) return;
        
        isFull[i][j] = true;
        flow(isFull, i+1,   j);
        flow(isFull, i,   j+1);
        flow(isFull, i,   j-1);
        flow(isFull, i-1,   j);
    }

    public void stats() {
        System.out.printf("mean   = %7.5f\n", stats.mean());
        System.out.printf("stddev = %7.5f\n", stats.stddev()); 
        System.out.printf("95%% CI = [%7.5f, %7.5f]\n",
                stats.confidenceLo(), stats.confidenceHi());
    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);

        PercolationViz pviz = new PercolationViz(N, T);
        pviz.grid();
        pviz.stats();
    }
}
