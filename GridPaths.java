import java.util.*;

// NxN grid, robot can only move right and down. How many paths?
// (X-1 + Y-1)! / ((X-1)! * (Y-1)!)
public class GridPaths {
    static class Point {
        int x;
        int y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static boolean isFree(int x, int y) {
        return x >= 0 && x < 10 && y >= 0 && y < 10;
    }

    public static boolean getPaths(int x, int y, List<Point> path) {
        Point p = new Point(x, y);
        path.add(p);
        if (x == 0 && y == 0) return true;

        boolean success = false;
        if (x >= 1 && isFree(x-1, y)) { // right 
            success = getPaths(x-1, y, path);
        }
        if (!success && y >= 1 && isFree(x, y-1)) { // down
            success = getPaths(x, y-1, path);
        }
        if (!success) {
            path.remove(p);
        }
        return success;
    }
}

