/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.LinkedList;

public class BruteCollinearPoints {
    private LineSegment[] lineSegments;

    public BruteCollinearPoints(Point[] points) {
        if (points == null)
            throw new IllegalArgumentException("input array cannot be null");

        LinkedList<Point[]> ls = new LinkedList<>();

        for (int i = 0; i < points.length; i++) {
            if (points[i] == null)
                throw new IllegalArgumentException("points cannot be null");

            for (int j = i + 1; j < points.length; j++) {
                LinkedList<Point> collinearPoints = new LinkedList<>();
                collinearPoints.add(points[i]);
                collinearPoints.add(points[j]);
                for (int k = 0; k < points.length; k++) {
                    if (k == i || k == j)
                        continue;
                    double slopeA = points[i].slopeTo(points[k]);
                    double slopeB = points[i].slopeTo(points[j]);

                    if (slopeA == Double.NEGATIVE_INFINITY || slopeB == Double.NEGATIVE_INFINITY)
                        throw new IllegalArgumentException("duplicate points not allowed");
                    
                    if (Double.compare(slopeA, slopeB) == 0) {
                        collinearPoints.add(points[k]);
                    }
                }
                if (collinearPoints.size() >= 4) {
                    Point[] segment = {
                            minimumPoint(collinearPoints), maximumPoint(collinearPoints)
                    };
                    if (!segmentExists(ls, segment))
                        ls.add(segment);
                }
            }
        }
        constructLineSegment(ls);
    }

    private static Point minimumPoint(LinkedList<Point> points) {
        Point minimum = points.get(0);
        for (int i = 1; i < points.size(); i++) {
            Point p = points.get(i);
            if (p.compareTo(minimum) < 0)
                minimum = p;
        }
        return minimum;
    }

    private static Point maximumPoint(LinkedList<Point> points) {
        Point maximum = points.get(0);
        for (int i = 1; i < points.size(); i++) {
            Point p = points.get(i);
            if (p.compareTo(maximum) > 0)
                maximum = p;
        }
        return maximum;
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

    public int numberOfSegments() {
        return lineSegments.length;
    }

    public LineSegment[] segments() {
        return lineSegments.clone();
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
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
