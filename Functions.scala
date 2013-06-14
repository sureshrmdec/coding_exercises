// Functions are defined in classes, e.g. 
// (x: Int) => x * x is expanded to 
// { class AnonFun extends Function1[Int, Int] {
//     def apply(x: Int) = x * x
// }
// new AnonFun 
// Generally methods are not function values but if method is used as an 
// argument then it's converted into function value automatically.
// eta expansion == conversion of methods into function values

trait XList[T] {
    def isEmpty: Boolean
    def head: T
    def tail: XList[T]
    def nth[T](n : Int, xs: XList[T]): T
}


class XCons[T](val head: T, val tail: XList[T]) extends XList[T] {
    def isEmpty = false
    override def toString = head + ":" + tail
    def nth[T](n : Int, xs: XList[T]): T =
        if (xs.isEmpty) throw new IndexOutOfBoundsException("index " + n)
        else if (n == 0) xs.head 
        else nth(n-1, xs.tail)
}

class XNil[T] extends XList[T] {
    def isEmpty = true
    def head: Nothing = throw new NoSuchElementException("XNil.head")
    def tail: Nothing = throw new NoSuchElementException("XNil.tail")
    override def toString = "nil"
    def nth[T](n : Int, xs: XList[T]): T = head
}

object XList {
    // XList(1, 2) = XList.apply(1, 2)
    def apply[T](): XList[T] = new XNil() 
    def apply[T](x1: T, x2: T): XList[T] = new XCons(x1, new XCons(x2, new XNil))
}

abstract class mBoolean {
    def ifThenElse[T](t: => T, e: => T): T
    /*
    def && (x: => mBoolean): mBoolean = ifThenElse(x, mfalse)
    def || (x: => mBoolean): mBoolean = ifThenElse(mtrue, x)
    def unary_! = ifThenElse(mfalse, mtrue)
    def == (x: mBoolean): mBoolean = ifThenElse(x, x.unary_!)
    def != (x: mBoolean): mBoolean = ifThenElse(x.unary_!, x)
    def < (x: mBoolean) = ifThenElse(false, x)
    */
}


object mtrue extends mBoolean {
    def ifThenElse[T](t: => T, e: => T) = t
}
object mfalse extends mBoolean {
    def ifThenElse[T](t: => T, e: => T) = e
}

// Peono numbers
abstract class Nat {
    def isZero: Boolean 
    def predecessor: Nat 
    def successor: Nat 
    def +(that: Nat): Nat 
    def -(that: Nat): Nat 
}



class Succ(n: Nat) extends Nat {
    def isZero = false
    def predecessor = n
    def +(that: Nat) = new Succ(n + that)
    def -(that: Nat) = if (that.isZero) this else n - that.predecessor
    def successor = new Succ(this)
}

object Zero extends Nat {
    def isZero = true
    def predecessor = throw new Error("0.predecessor")
    def +(that: Nat) = that
    def -(that: Nat) = if (that.isZero) this else throw new Error("negative")
    def successor = new Succ(this)
}

/*

Lists should be covariant and arrays should not be covariant.

Immutable types can be covariant and mutable types cannot.

Let C[T] is parameterized type such that A <: B

C[A] <: C[B]            C is covariant 
C[A] >: C[B]            C is contravariant 
neither                 C is nonvariant or invariant

In Scala
class C[+A] {...}       C is covariant 
class C[-A] {...}       C is contravariant 
class C[A] {...}        C is nonvariant


If A2 <: A1 and B1 <: B2, then
    A1 => B1 <: A2 => B2 


Functions are contravariant in their argument types and 
    covariant in their result type 
e.g.
trait Function1[-T, +U] {
    def apply(x: T): U
}

Objects in scala cannot have type parameters 
First maek List covariant
trait List[+T] { 
    //....
}

Object Nil extends List[Nothing] {
    def isEmpty: Boolean = true 
    //..
}


Making Class covariant 
trait List[+T] { 
    // following method throws error for variance check because
    // it violates LSP, xs.prepend(Empty) so 
    // List[NonEmpty] cannot be subtypeof List[Intset]
    def prepend(elem: T): List[T] = new Cons(elem, this) 

    In order to fix it
    def prepend [U >: T] (elem: U): List[U] = new Cons(elem, this) 
    // above passes variance because covariant type parameters 
    // may appear in lower bound parameter.
}

//
*/
