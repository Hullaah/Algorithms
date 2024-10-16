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
    private final LineSegment[] lineSegments;

    public FastCollinearPoints(Point[] points) {
        LinkedList<LineSegment> ls = new LinkedList<>();
        for (int i = 0; i < points.length; i++) {
            Point p = points[i];
            Arrays.sort(points, p.slopeOrder());
            int j = 1, collinearCount = 1;
            Point minimumPointOnLineSegment = p, maximumPointOnLineSegment = p;
            for (int k = 1; k < points.length; k++) {
                if (p.slopeTo(points[k]) == p.slopeTo(points[j])) {
                    if (points[k].compareTo(minimumPointOnLineSegment) < 0)
                        minimumPointOnLineSegment = points[k];
                    if (points[k].compareTo(maximumPointOnLineSegment) > 0)
                        maximumPointOnLineSegment = points[k];
                    collinearCount++;
                }
                else {
                    if (collinearCount >= 4) {
                        LineSegment nls = new LineSegment(minimumPointOnLineSegment,
                                                          maximumPointOnLineSegment);
                        boolean segmentExists = false;
                        for (LineSegment s : ls) {
                            if (s.toString().equals(nls.toString())) {
                                segmentExists = true;
                                break;
                            }
                        }
                        if (!segmentExists)
                            ls.add(nls);
                    }
                    collinearCount = 1;
                    j = k + 1;
                }
            }
        }
        lineSegments = ls.toArray(new LineSegment[0]);
    }

    public int numberOfSegments() {
        return lineSegments.length;
    }

    public LineSegment[] segments() {
        return lineSegments;
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
