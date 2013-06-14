//package week4

// classes can have value parameters but traits cannot have parameters 
// scala.Any is root object, AnyVal and AnyRef derives it, ScalaObject extends AnyRef, AnyRef is alias for java.lang.Object
// Any defines ==, !=, hashCode, equals, toString
// Nothing is a subtype of every other type, there is no value for it 
// Null is a subtype of every other type, but it's incompatible with subtypes of Anyval

//

trait List[T] {
    def isEmpty: Boolean
    def head: T
    def tail: List[T]
    def nth[T](n : Int, xs: List[T]): T
}


// val is evaluated when object is initialized whereas def is 
// initialized everytime it's referred
// we don't need to define definition of head and tail as 
// they are declared val
class Cons[T](val head: T, val tail: List[T]) extends List[T] {
    def isEmpty = false
    override def toString = head + ":" + tail
    def nth[T](n : Int, xs: List[T]): T =
        if (xs.isEmpty) throw new IndexOutOfBoundsException("index " + n)
        else if (n == 0) xs.head 
        else nth(n-1, xs.tail)
}

class Nil[T] extends List[T] {
    def isEmpty = true
    def head: Nothing = throw new NoSuchElementException("Nil.head")
    def tail: Nothing = throw new NoSuchElementException("Nil.tail")
    override def toString = "nil"
    def nth[T](n : Int, xs: List[T]): T = head
}

// def singleton[T](elem: T) = new Cons[T](elem, new Nil[T]) 
// val list = new Cons(1, new Cons(2, new Cons(3, new Nil)))
// singleton[Int](1)
// singleton(true) // no need to add type 
