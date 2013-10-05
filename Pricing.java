
/*
 *
Price experiment

Uber is a company based in San Francisco, California that makes a mobile application that connects passengers with drivers of luxury vehicles for hire.

Uber is doing experiments to better understand the relationship between price and demand for rides. One hypothesis is that lower prices will show an increase in demand for cars.

Their goal is to find the cost/demand point that is best for everyone.

Experimental data will show that the relation between the price discount and the uber revenue can be approximated by a mathematical function. Lets consider the following function:

y = 1/2 ((-3x^2 + Ax + B) - x - 1)

where

    x represents the discount,
    y represents the uber revenue and
    A, B are two constant positive integers

The relation described above has a shape similar to the one shown below:

Given the two positive integer numbers A and B

Your task is to

    write a function that prints to the standard output (stdout) the discount value x that maximizes uber revenue y
    please print the found value x truncated to two decimal places without rounding.

Note that your function will receive the following arguments:

    a
        the positive integer number A
    b
        the positive integer number B

Data constraints

    the values of a, b constants will not exceed 2000

Efficiency constraints

    your function is expected to print the requested result and return in less than 2 seconds

Example
Input   Output

a: 250

b: 400
    

20.04
*/

import java.util.*;
import java.io.*;


public class Pricing {
  public static void uber_price(Integer a, Integer b) {
      // derivative = 
      // L = A - 6x 
      // M = 2 * Sqrt(x(A-3x)+B)
      // (1/2) * (L / M - 1)
      // root 
      // when A^2 + 12B != 0
      //    x = 1/12 * (2A - sqrt(A^2 + 12B))
      //
    double x = 1.0 / 12.0 * (2.0F * a - Math.sqrt(a*a + 12.0 * b));
    String val = String.format("%.3f", x);
    System.out.println(val.substring(0, val.length()-1));
  }
  public static void main(String[] args) throws Exception {
      uber_price(250, 400);
  }
}
