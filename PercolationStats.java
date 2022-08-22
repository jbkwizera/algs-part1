import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private static final double CONFIDENCE_95 = 1.96;
    private final double mean;
    private final double sdev;
    private final int trials;

    // Perform independent trials on an  n-by-n grid
    public PercolationStats(int n, int t) {
        if (n < 1) throw new IllegalArgumentException("Grid size must be positive.");
        if (t < 1) throw new IllegalArgumentException("No. of trials must be positive.");
        
        trials = t;
        double[] x = new double[t];
        for (int trial = 0; trial < t; trial++) {
            Percolation grid = new Percolation(n);
            while (!grid.percolates()) {
                int i = StdRandom.uniform(1, n+1);
                int j = StdRandom.uniform(1, n+1);
                grid.open(i, j);
            }
            x[trial] = 1.0*grid.numberOfOpenSites()/(n*n);
        }
        mean = StdStats.mean(x);
        sdev = StdStats.stddev(x);
    }

    public double mean()
    {   return mean;    }

    public double stddev()
    {   return sdev;    }

    public double confidenceLo()
    {   return mean - CONFIDENCE_95 * sdev/Math.sqrt(trials); }

    public double confidenceHi()
    {   return mean + CONFIDENCE_95 * sdev/Math.sqrt(trials); }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);

        PercolationStats gridStats = new PercolationStats(n, t);
        System.out.printf("mean                    = %f\n", gridStats.mean());
        System.out.printf("stddev                  = %f\n", gridStats.stddev());
        System.out.printf("95%% confidence interval = [%f, %f]\n",
                gridStats.confidenceLo(), gridStats.confidenceHi());
    }
}
