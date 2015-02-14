//Ternary search is a search that find local minimum or maximum values in a
//function given the interval between A and B.
//If there are multiple local minimum and maximum values, ternary search
//will find one of them but not necessarily the maximum value of all points.

public class TernarySearch {
  // Let’s say we have a function f(x) with only one max point between A and
  // B.
  // Let m1 by 1/3 of the way from A and B and let m2 be 2/3 of the way
  // from B. 
  //
  public double tern(double a,double b){
    if(Math.abs(f(a)-f(b))<0.0001){
      return (a+b)/2.0;
    }
    double m1 = a+(b-a)/3.0;
    double m2 = a+(b-a)*2/3;
    if(f(a)<f(b)){
      return tern(m1,b);
    }else {
      return tern(a,m2);
    }
  }
}
