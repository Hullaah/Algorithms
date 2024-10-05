/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private int gridSize;
    private final double[] stats;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        gridSize = n;
        this.stats = new double[trials];

        for (int i = 0; i < trials; i++) {
            Percolation perc = new Percolation(n);
            stats[i] = runSimulation(perc, n * n);
        }
    }

    // sample mean for percolation threshold
    public double mean() {
        return StdStats.mean(stats);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(stats);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - (1.96 * stddev() / Math.sqrt(stats.length));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + (1.96 * stddev() / Math.sqrt(stats.length));
    }

    private double runSimulation(Percolation perc, int totalSites) {
        while (!perc.percolates()) {
            int row = StdRandom.uniformInt(1, gridSize + 1);
            int col = StdRandom.uniformInt(1, gridSize + 1);
            perc.open(row, col);
        }
        return (double) perc.numberOfOpenSites() / totalSites;
    }

    // test client
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats s = new PercolationStats(n, trials);

        System.out.println("mean    =   " + s.mean());
        System.out.println("stddev  =   " + s.stddev());
        System.out.println(
                "95%% confidence interval =   [" + s.confidenceLo() + ", " + s.confidenceHi()
                        + "]");
    }
}
