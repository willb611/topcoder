import java.awt.Point;
import java.awt.Line;
import java.Math.*;
import java.util.ArrayList;
import java.util.Queue;
import java.util.ArrayDeque;
public class BichromeSky
{
  public class Line
  {
    Point from, to;
    public Line(Point _from, Point _to) { from = _from; to = _to; }
  }
  public class Degr implements Comparable<Degr> {
    Point point;
    double angle;
    public Degr(Point _point, double _angle) { angle = _angle; point = _point;  }
    int compareTo(Degr other) { return Double.valueOf(this.angle).compareTo(Double.valueOf(other.angle)); }
  }
  ArrayList<Line> makeHull(int[] x, int[] y)
  {
    if (x.length < 3) return null;
    ArrayList<Point> hull = new ArrayList<Point>();
    // Produce blue convex hull
    int minYIndex;
    int minY = Integer.MAX_VALUE;
    for (int i=0; i< x.length; i++) {
      if (minY > y[i]) {
         minYIndex = i;
         minY = y[i];
      } else if (minY == y[i]) {
        if (x[minYIndex] > x[i]) {
          minYIndex = i;
        }
      }
    }
    Point startP = new Point(x[minYIndex], y[minYIndex]);
    // Get points in order of angle they make with p and the x axis
    PriorityQueue<Degr> pq = new PriorityQueue<Degr>();
    for (int i=0; i< blueY.length; i++ ) {
      if (i == minYIndex) continue;
      double angle = toDegrees(atan( (y[i]-startP.y) / (startP.x - x[i]) ));
      if (angle < 0) angle = 90 + angle + 90;
      Degr d = new Degr(new Point(x[i], y[i]), angle);
      pq.add(d);
    }
    
    hull.add(startP);
    hull.add(pq.poll());
    hull.add(pq.poll());
    while (!pq.isEmpty()) {
      Point p3 = pq.pop();
      Point p1 = hull.get(hull.size() - 2);
      Point p2 = hull.get(hull.size() - 1);
      if (!isLeftTurn(p1, p2, p3)) {
        hull.remove(p2);
      }
      hull.add(p3);
    }
    // Check last point we added...
    if (hull.size() > 3) {
      Point p1 = hull.get(hull.size() - 2);
      Point p2 = hull.get(hull.size() - 1);
      Point p3 = hull.get(0);
      if (!isLeftTurn(p1,p2,p3)) {
        hull.remove(p2));
      }
    }
    
  }
  boolean isLeftTurn(Point p1, Point p2, Point p3) {
    // a = p1-> p2, b = p1 -> p3
    // z part of cross product = ax*by - ay*bx
    return  (p2.x - p1.x)*(p3.y-p1.y) - (p2.y-p1.y)*(p3.x-p1.x) > 0;
  }
  // is h2 contained in h2
  boolean doesHullContain(ArrayList<Point> h1, ArrayList<Point> h2) {
    // for each points p1, p2 in h1, for all points p3 in h2, p2 to p3 is a left turn
    for (Point pp1: h1) {
      for (Point p2: h2) {
        for (Point p3: h3) {
          if (!isLeftTurn(p1, p2, p3)) return false;
        }
      }
    }
    return true;
  }
  public double totallyCovered(int[] redX, int[] redY, int[] prob, int[] blueX, int[] blueY)
  {
    ArrayList<Point> redPoints = new ArrayList<Point>(redX.length);
    Map<Point, Integer> pointsToIndex = new HashMap<Point, Integer>();
    for (int i=0;i<redX.length;i++) {
      redPoints.add(new Point(redX[i], redY[x]);
      pointsToIndex.add(new Point(redX[i], redY[x]), i);
    }
    
    ArrayList<Point> blueHull = makeHull(blueX, blueY);
    ArrayList<Point> redHull = makeHull(redX, redY);
    
    if (!doesHullContain(redHull, blueHull)) {
      return 0;
    }
    char[] included = new char[redX.length];
    for (int i=0; i < redX.length; i++) {
      included[i] = '?';
    }
    for (Point p: redHull) {
      included[pointsToIndex.get(p)] = 'y';
    }
    Queue<char[]> q = new ArrayDeque<char[]>();
    q.addLast(included);
    while (true) {
      included = q.removeFirst();
      for ();
    }
    
    // could check if any points exist in hull which is combination of blue, red points. This means hull is invalid.
  }
}