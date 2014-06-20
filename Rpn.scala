import scala.collection.mutable.Stack

object Rpn {
   val ops = Map("+" -> ((_:Double) + (_:Double)),
              "-" -> ((_:Double) + (_:Double)),
              "*" -> ((_:Double) * (_:Double)),
              "/" -> ((_:Double) / (_:Double)))

   def eval(tokens: Array[String]) : Double = {
      val stack = new Stack[Double]
      tokens.foreach(token => {
         if (ops.contains(token)) stack.push(ops(token)(stack.pop, stack.pop))
         else stack.push(token.toDouble)
      })
      stack.pop
   }

   def main(args: Array[String]) = {
      println("r: " + eval(args))
   }
}
