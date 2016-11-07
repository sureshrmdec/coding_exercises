/**
https://www.hackerrank.com/contests/walmart-codesprint-algo/challenges/delivery
Jamal works for a food delivery company that labels each food with a positive integer from  to . At the beginning of each day, he receives  food delivery requests. Each request is in the form foodi personk, meaning he must buy  from one of the restaurants that make it and deliver it to . He must deliver each request in the same order in which he received it.

Jamal's city is organized like a complete binary tree where  lives at intersection . As a reminder, a complete binary tree is a binary tree in which every level, except possibly the last, is completely filled and all the nodes are as far left as possible: KnuthCompleteBinaryTree

The city has a restaurant at each intersection, and traveling from an intersection to an adjacent intersection takes minute. Jamal starts his workday at intersection  and can't hold more than one food item at a time.

Given the layout of the city and Jamal's sequence of delivery requests, calculate the minimum time it will take him to deliver all the requests in the order he received them.

Input Format

The first line contains three space-separated positive integers describing the respective values of  (the number of intersections),  (the number of types of foods), and  (the number of delivery requests). 
Each line  of the  subsequent lines (where ) contains a maximum of  space-separated positive integers. The first integer denotes the number of restaurants that make food type . The remaining numbers on the line denote the restaurants that make the food type . 
Each line  of the  subsequent lines contains a food order request in the form foodi personk, where  is the type of food being ordered and  is the person it must be delivered to.

Constraints

Each food is made by a maximum of  restaurants.
Output Format

Print the amount of time (in minutes) it takes for Jamal to deliver all  food requests.

Sample Input

5 5 6
3 1 4 5
1 3
3 2 3 5
4 1 3 4 5
3 1 2 5
5 3
3 5
3 2
2 5
2 1
3 3
Sample Output

15
Explanation

We have  foods:

Food type  is available from the restaurants at intersections , , and .
Food type  is available from the restaurant at intersection .
Food type  is available from the restaurants at intersections , , and .
Food type  is available from the restaurants at intersections , , , and .
Food type  is available from the restaurants at intersections , , and .
Jamal has the following  delivery requests:

Buy food type  and deliver it to intersection .
Buy food type  and deliver it to intersection .
Buy food type  and deliver it to intersection .
Buy food type  and deliver it to intersection .
Buy food type  and deliver it to intersection .
Buy food type  and deliver it to intersection .
Jamal's path is , where bolded intersections are intersections in which Jamal completed a delivery. The length of this path is .
*/

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Delivery {
  static class Intersection {
    int id;
    List<Integer> foods = new ArrayList<>();
    Intersection parent;
    Intersection left;
    Intersection right;
    int distance;

    public Intersection(int id) {
      this.id = id;
    }
    @Override
    public int hashCode() {
      return id;
    }
    @Override 
    public boolean equals(Object o) {
      if (o instanceof Intersection == false) return false;
      Intersection other = (Intersection) o;
      return id == other.id;
    }
    @Override 
    public String toString() {
      return String.valueOf(id);
    }
  }

  static Map<Integer, Intersection> intersections = new HashMap<>();
  static Map<Integer, List<Intersection>> restaurantsByFood = new HashMap<>();

  static void addrestaurantByFood(int restaurant, int food) {
    Intersection x = intersections.get(restaurant);
    List<Intersection> list = restaurantsByFood.get(food);
    if (list == null) {
      list = new ArrayList<>();
      restaurantsByFood.put(food, list);
    }
    list.add(x);
  }

  static Intersection getIntersection(int n) {
    Intersection x = intersections.get(n);
    if (x == null) {
      x = new Intersection(n);
      intersections.put(n, x);
    }
    return x;
  }

  static Intersection commonAncestor(Intersection from, Intersection to) {
     if (from == null) return to;
     if (to == null) return from;
     while (Math.abs(from.id - to.id) > 1 && to.parent != null) {
       to = to.parent;
     }
     if (from.parent == to.parent) {
       return from.parent;
     }
     //System.out.println("\tChecking commonAncestor(" + from + "-> " + to + ") " + from.parent + "/" + to.parent);
     return commonAncestor(from.parent, to.parent);
  }


  static Intersection _commonAncestor(Intersection from, Intersection to) {
     Set<Intersection> visited = new HashSet<>();
     Intersection x =commonAncestor(from, to, visited);
		 return x;
  }
                                                                                                                                
  static Intersection commonAncestor(Intersection from, Intersection to, Set<Intersection> visited) {
      if (from == null || to == null) {
         if (from == null && to == null) {
            return null;
         }   
         if (from != null) {
            return from.parent;
         }   
         if (to != null) {
            return to.parent;
         }   
      }   
      if (from.parent == to.parent) {
         return from.parent;
      }   

      if (isChild(from.parent, to, visited)) {
         return from.parent;
      }   
      return commonAncestor(from.parent, to);
   }   

   static boolean isChild(Intersection parent, Intersection child, Set<Intersection> visited) {
      if (child == null) {
         return false;
      }   
      if (parent == null) {
         return false;
      }   
      if (visited.contains(parent)) {
         return false;
      }   
      visited.add(parent);
      if (parent.left == child || parent.right == child) {
         return true;
      }
      if (parent.left != null) {
         if (isChild(parent.left, child, visited)) {
            return true;
         }
      }
      if (parent.right != null) {
         if (isChild(parent.right, child, visited)) {
            return true;
         }
      }
      return false;
   }


  static int distance(Intersection first, Intersection second) { 
    Intersection from = null;
    Intersection to = null;
    if (first.id < second.id) {
			from = first;
			to = second;
		} else {
			from = second;
			to = first;
		}
    if (from == to) return 0;
 
    if (from.left == to || from.right == to) return 1; 

    Intersection parent = commonAncestor(from, to);
    //System.out.println("\t found " + parent + " as parent of " + from + "->" + to);
    if (parent == null) {
      return distance(from, to, 0);
    } else {
      return distance(parent, from, 0) + distance(parent, to, 0);
    }
		//System.out.println("\t\tdistance(" + from + "," + to + ") --Distance from parent " + parent + " to from " + from + "=" + distance(parent, from, 0) + ", distance from parent " + parent + " to " + to + "=" + distance(parent, to, 0));
  }

  static int distance(Intersection first, Intersection second, int distance) {
    Intersection from = null;
    Intersection to = null;
    if (first.id < second.id) {
			from = first;
			to = second;
		} else {
			from = second;
			to = first;
		}
    if (from == to) return 0;
 
    if (from.left == to || from.right == to) {
        //System.out.println("\t\tdistance(" + from + "->" + to + ", found child " + (distance+1));
        return distance+1;
    }


    //
    if (from.left != null) {
      int d = distance(from.left, to, distance+1);
      if (d >= 0) {
        return d;
      }
    }
    if (from.right != null) {
      int d = distance(from.right, to, distance+1);
      if (d >= 0) {
        return d;
      }
    }
    throw new RuntimeException("Could not find path from " + from + "=>" + to);
  }

  static Intersection getMinRestaurant(List<Intersection> list, Intersection from, Intersection food) {
    if (list.size() == 1) {
			return list.get(0);
    }
    int min = Integer.MAX_VALUE;
    Intersection minX = null;
    for (Intersection x : list) {
			if (from == x) {
				x.distance = 0;
				return x;
			}
      int d = distance(x, from);
      if (d > 0 && d < min) {
        min = d;
        minX = x;
        minX.distance = d;
      } else if (d > 0 && d <= min && x == food) {
				x.distance = 0;
				return x;
      }
    }
    
 	  //System.out.println("\tReturning " + minX + ", from " + list + ", from " + from + ", distance " + min);
    return minX;
  }


  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt(); // # of intersection
    int m = in.nextInt(); // # of foods
    int q = in.nextInt(); // # of delivery requests 
    for (int i=1; i<=m; i++) {
      int count = in.nextInt();
      for (int j=0; j<count; j++) {
        Intersection x = getIntersection(in.nextInt());
        x.foods.add(i);
        addrestaurantByFood(x.id, i);
      }
    }
    //
    Intersection top = intersections.get(1);
    List<Intersection> queue = new ArrayList<>();
    queue.add(top);
    while (!queue.isEmpty()) {
      Intersection x = queue.remove(0);
      x.left = intersections.get(x.id * 2);
      x.right = intersections.get(x.id * 2 + 1);
      if (x.left != null && x.right != null) {
        x.left.parent = x;
        x.right.parent = x;
        queue.add(x.left);
        queue.add(x.right);
      }
    }
      
    Intersection prev = top;
    int distance = 0;
    for (int i=0; i<q; i++) {
      Intersection food = intersections.get(in.nextInt()); 
      Intersection person = intersections.get(in.nextInt()); 
      List<Intersection> list = restaurantsByFood.get(food.id);
      Intersection x = getMinRestaurant(list, prev, food);

      int xdistance = distance(x, prev);
      distance += xdistance; 

      int ydistance = distance(x, person);
      distance += ydistance;

      System.out.println("Delivering food " + food + "->" + person + ", from " + prev + "->" + x + "=" + xdistance + " -- " + x + "=>" + person + "=" + ydistance + ", total " + distance);
      prev = person;
    }
    System.out.println(distance);
  }
}
