/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF sites;
    private boolean[] openIndicator;
    private int virtualTopSite, virtualBottomSite;
    private int numberOfOpenSites;
    private final int numberOfGrids;

    // creates an n-by-n grid with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Grid must be > 0");
        }

        openIndicator = new boolean[n * n + 2];
        numberOfGrids = n;

        sites = new WeightedQuickUnionUF(n * n + 2);
        virtualTopSite = 0;
        virtualBottomSite = n * n + 1;
        openIndicator[virtualBottomSite] = true;
        openIndicator[virtualTopSite] = true;

        for (int i = 1; i <= n; i++) {
            sites.union(virtualTopSite, i);
        }

        for (int i = n * n - n + 1; i <= n * n; i++) {
            sites.union(virtualBottomSite, i);
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (isOpen(row, col))
            return;
        if (row == 1 && col == 1) {
            if (isOpen(row + 1, col)) {
                sites.union(site(row + 1, col), site(row, col));
            }
            if (isOpen(row, col + 1)) {
                sites.union(site(row, col + 1), site(row, col));
            }
        }
        else if (row == 1 && col == numberOfGrids) {
            if (isOpen(row + 1, col)) {
                sites.union(site(row + 1, col), site(row, col));
            }
            if (isOpen(row, col - 1)) {
                sites.union(site(row, col - 1), site(row, col));
            }
        }
        else if (row == numberOfGrids && col == 1) {
            if (isOpen(row, col + 1)) {
                sites.union(site(row, col + 1), site(row, col));
            }
            if (isOpen(row - 1, col)) {
                sites.union(site(row - 1, col), site(row, col));
            }
        }
        else if (row == numberOfGrids && col == numberOfGrids) {
            if (isOpen(row - 1, col)) {
                sites.union(site(row - 1, col), site(row, col));
            }
            if (isOpen(row, col - 1)) {
                sites.union(site(row, col - 1), site(row, col));
            }
        }
        else if (col == 1) {
            if (isOpen(row + 1, col)) {
                sites.union(site(row + 1, col), site(row, col));
            }
            if (isOpen(row - 1, col)) {
                sites.union(site(row - 1, col), site(row, col));
            }
            if (isOpen(row, col + 1)) {
                sites.union(site(row, col + 1), site(row, col));
            }
        }
        else if (col == numberOfGrids) {
            if (isOpen(row + 1, col)) {
                sites.union(site(row + 1, col), site(row, col));
            }
            if (isOpen(row - 1, col)) {
                sites.union(site(row - 1, col), site(row, col));
            }
            if (isOpen(row, col - 1)) {
                sites.union(site(row, col - 1), site(row, col));
            }
        }
        else if (row == 1) {
            if (isOpen(row + 1, col)) {
                sites.union(site(row + 1, col), site(row, col));
            }
            if (isOpen(row, col + 1)) {
                sites.union(site(row, col + 1), site(row, col));
            }
            if (isOpen(row, col - 1)) {
                sites.union(site(row, col - 1), site(row, col));
            }
        }
        else if (row == numberOfGrids) {
            if (isOpen(row - 1, col)) {
                sites.union(site(row - 1, col), site(row, col));
            }
            if (isOpen(row, col + 1)) {
                sites.union(site(row, col + 1), site(row, col));
            }
            if (isOpen(row, col - 1)) {
                sites.union(site(row, col - 1), site(row, col));
            }
        }
        else {
            if (isOpen(row + 1, col)) {
                sites.union(site(row + 1, col), site(row, col));
            }
            if (isOpen(row - 1, col)) {
                sites.union(site(row - 1, col), site(row, col));
            }
            if (isOpen(row, col - 1)) {
                sites.union(site(row, col - 1), site(row, col));
            }
            if (isOpen(row, col + 1)) {
                sites.union(site(row, col + 1), site(row, col));
            }
        }
        openIndicator[site(row, col)] = true;
        numberOfOpenSites++;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        return openIndicator[site(row, col)];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        return isOpen(row, col) && sites.find(site(row, col)) == sites.find(virtualTopSite);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return sites.find(virtualTopSite) == sites.find(virtualBottomSite);
    }

    // get the site at (row, col)
    private int site(int row, int col) {
        if (row < 1 || col < 1 || row > numberOfGrids || col > numberOfGrids) {
            throw new IllegalArgumentException("Invalid site");
        }
        return (row - 1) * numberOfGrids + col;
    }

    public static void main(String[] args) {

    }
}
