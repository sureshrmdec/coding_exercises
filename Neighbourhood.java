


/*
Neighbourhood

Uber needs to connect a rider with a car as fast as possible. To achieve that, the system needs to accurately predict demand to make sure you get a car when you want one. Predicting demands requires knowledge about the locations from which most orders are likely to occur.

Given a neighbourhood represented as the coordinates of a convex polygon and a list of trip destinations

Your task is to

    write a function that prints to the standard output (stdout) the number of trips that ended in the given neighbourhood

Note that your function will receive the following arguments:

    neighbourhood
        which is an array of strings representing the coordinates of the polygon
        the convex polygon coordinates are presented in clockwise order
        each string contains the x,y coordinates of one vertex, separated by comma
    trips
        which is an array of strings representing the coordinates of the trips destinations
        each string contains the x,y coordinates of one destination, separated by comma

Data constraints

    the length of the arrays above will not exceed 100,000 entries
    all the coordinates values, for both the polygon and trip destinations, will be integer numbers in the [0, 10000] range

Efficiency constraints

    your function is expected to print the requested result and return in less than 2 seconds

Example
Input   Output
neighbourhood: ["1,3","4,5","3,1"]
trips: ["1,1","2,3","3,3","4,2"]  2

Explanation:

The only trips that ended in the neighbourhood are (2,3) and (3,3).

*/

import java.util.*;
import java.io.*;


public class Neighbourhood {
  static class Point {
    public final int x;
    public final int y;
    public Point(int x, int y) {
      this.x = x;
      this.y = y;
    }
    public static Point newPoint(String str) {
      String[] t = str.split(",");
      return new Point(Integer.parseInt(t[0]), Integer.parseInt(t[1]));
    }
  }
  public static boolean insidePolygon(List<Point> polygon, Point target) {
    double angle=0;
    int n = polygon.size();
    for (int i=0; i<n; i++) {
      Point p1 = new Point(polygon.get(i).x - target.x, polygon.get(i).y - target.y);
      Point p2 = new Point(polygon.get((i+1)%n).x - target.x, polygon.get((i+1)%n).y - target.y);
      angle += angle2D(p1, p2);
    }
    return Math.abs(angle) >= Math.PI;
  }

   //Return the angle between two vectors on a plane
   //The angle is from vector 1 to vector 2, positive anticlockwise
   //The result is between -pi -> pi
  private static double angle2D(Point p1, Point p2) {
    double TWOPI = Math.PI * 2;
    double theta1 = Math.atan2(p1.y, p1.x);
    double theta2 = Math.atan2(p2.y, p2.x);
    double dtheta = theta2 - theta1;
    while (dtheta > Math.PI) {
      dtheta -= TWOPI;
    }
    while (dtheta < -Math.PI) {
      dtheta += TWOPI;
    }
    return dtheta;
  }
  public static void count_trips(String[] neighbourhood, String[] trips) {
    List<Point> polygon = new ArrayList<Point>();

    for (String n : neighbourhood) {
      polygon.add(Point.newPoint(n));
    }
    int count = 0;
    for (String t : trips) {
      Point p = Point.newPoint(t);
      if (insidePolygon(polygon, p)) {
        count++;
      }
    }
    System.out.println(count);
  }

  public static void main(String[] args) throws Exception {
    count_trips(new String[] {"1,3","4,5","3,1"}, new String[] {"1,1","2,3","3,3","4,2"});
  }
}
