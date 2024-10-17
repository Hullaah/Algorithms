/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.LinkedList;

public class FastCollinearPoints {
    private LineSegment[] lineSegments;

    public FastCollinearPoints(Point[] points) {
        LinkedList<Point[]> ls = new LinkedList<>();
        for (int i = 0; i < points.length; i++) {
            Point p = points[i];
            Arrays.sort(points, p.slopeOrder());
            int j = 1, k = 1, collinearCount = 1;
            Point minimumPointOnLineSegment = p, maximumPointOnLineSegment = p;
            while (k < points.length) {
                if (p.slopeTo(points[k]) == p.slopeTo(points[j])) {
                    if (points[k].compareTo(minimumPointOnLineSegment) < 0)
                        minimumPointOnLineSegment = points[k];
                    if (points[k].compareTo(maximumPointOnLineSegment) > 0)
                        maximumPointOnLineSegment = points[k];
                    collinearCount++;
                    if (k == points.length - 1 && collinearCount >= 4) {
                        Point[] segment = { minimumPointOnLineSegment, maximumPointOnLineSegment };
                        if (!segmentExists(ls, segment))
                            ls.add(segment);
                    }
                    k++;
                }
                else {
                    if (collinearCount >= 4) {
                        Point[] segment = { minimumPointOnLineSegment, maximumPointOnLineSegment };
                        if (!segmentExists(ls, segment))
                            ls.add(segment);
                    }
                    collinearCount = 1;
                    j = k;
                    maximumPointOnLineSegment = p;
                    minimumPointOnLineSegment = p;
                }
            }
        }
        constructLineSegment(ls);
    }

    public int numberOfSegments() {
        return lineSegments.length;
    }

    public LineSegment[] segments() {
        return lineSegments.clone();
    }

    private void constructLineSegment(LinkedList<Point[]> ls) {
        int i = 0;
        lineSegments = new LineSegment[ls.size()];
        for (Point[] p : ls) {
            lineSegments[i++] = new LineSegment(p[0], p[1]);
        }
    }

    private static boolean segmentExists(LinkedList<Point[]> s, Point[] x) {
        for (Point[] p : s) {
            if ((p[0].compareTo(x[0]) == 0 && p[1].compareTo(x[1]) == 0) ||
                    (p[1].compareTo(x[0]) == 0 && p[0].compareTo(x[1]) == 0))
                return true;
        }
        return false;
    }

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
