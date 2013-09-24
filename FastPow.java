/*
Challenge: Fast power

Searching for a string in a large data store by simply comparing characters would take ages or a huge amount of computing power. We need a way to do it fast!

The first step to building a fast search algorithm is to encode the data in a format that makes search efficient.

A critical operation in the encoding process which we’ll get familiar with a bit later is the exponentiation of integer numbers.

Given a list of pairs of integers (a,b)

Your task is to

    * write a function that prints to the standard output (stdout) for each pair the result of (ab) modulo 4211 (one per line)

Note that your function will receive the following arguments:

    * a
          o which is an array of integers giving the first element (a) of each pair
    * b
          o which is an array of integers giving the second element (b) of each pair

The ith pair is defined by a[i] and b[i].

Data constraints

    * the length of a, b arrays will not exceed 10000
    * elements of a, b arrays are integer numbers in the [0, 50000]

Efficiency constraints

    * your function is expected to print the requested result and return in less than 2 seconds
    * make sure you don't use any methods that do significant work for you (e.g. pow() in Python)

Example
Input   Output

a: 41619, 169, 29227, 13232

b: 6, 5, 7, 8
   

3226
3461
2507
2597 


http://homepages.math.uic.edu/~leon/cs-mcs401-s08/handouts/fastexp.pdf 
*/



class FastPow {  
    private static long exp(int a, int b, int m) { 
       long x= a;
       long y = b % 2 == 1 ? a : 1;
       long n = (long) Math.floor(b / 2);
       while (n > 0) { 
          x = (x * x) % m;
          if (n % 2 == 1) { 
             y = y == 1 ? x : (y * x) % m;
          }
         n = (long) Math.floor(n / 2);
       }
       return y;
   }


   public static void fast_power(Integer[] a, Integer[] b) {
      for (int i=0; i<a.length; i++) {
         System.out.println(exp(a[i], b[i], 4211));
      }
   }  
   public static void main(String[] args) throws Exception { 
      Integer[] a = new Integer[] {41619,169,29227,13232};
      Integer[] b = new Integer[] {6,5,7,8};
      //a = new Integer[] {46634};
      //b = new Integer[] {3}; // 4006
      fast_power(a, b);
   }
}



