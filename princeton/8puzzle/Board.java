/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.Arrays;

public class Board {
    private final int[][] tiles;
    private final int N;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        N = tiles.length;
        this.tiles = new int[N][N];

        int i = 0;
        for (int[] row : tiles) {
            tiles[i++] = row.clone();
        }
    }

    // get the private field, tiles
    public int[][] getTiles() {
        int[][] tilesClone = new int[N][N];
        int i = 0;
        for (int[] row : tiles) {
            tilesClone[i++] = row.clone();
        }
        return tilesClone;
    }

    // string representation of this board
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tiles[i][j]));
            }
            s.append("\n");
        }
        return s.toString();

    }

    // board dimension n
    public int dimension() {
        return N;
    }

    // number of tiles out of place
    public int hamming() {
        int total = 0;
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                int positionValue = row * N + col + 1;
                if (tiles[row][col] != 0 && tiles[row][col] != positionValue)
                    total += 1;
            }
        }
        return total;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
    }

    // is the board the goal board?
    public boolean isGoal() {
        return hamming() == 0;
    }

    // does this board equals y?
    public boolean equals(Object y) {
        if (y == null)
            return false;
        if (y.getClass() != this.getClass())
            return false;
        if (y == this)
            return true;

        Board that = (Board) y;
        if (this.dimension() != that.dimension())
            return false;

        return Arrays.deepEquals(this.getTiles(), that.getTiles());
    }

    public static void main(String[] args) {

    }
}
