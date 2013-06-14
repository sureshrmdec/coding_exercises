
trait Expr {
    def isNum: Boolean
    def isSum: Boolean 
    def numValue: Int
    def leftOp: Expr 
    def rightOp: Expr 
}


class Num(n: Int) extends Expr {
    def isNum: Boolean = true
    def isSum: Boolean  = false
    def numValue: Int = n
    def leftOp: Expr  = throw new Error("leftOp")
    def rightOp: Expr  = throw new Error("rightOp")
}


class Sum(e1: Expr, e2: Expr) extends Expr {
    def isNum: Boolean = false
    def isSum: Boolean  = true
    def numValue: Int = throw new Error("num")
    def leftOp: Expr  = e1
    def rightOp: Expr  = e2
}

def eval(e: Expr): Int = {
    if (e.isNum) e.numValue 
    else if (e.isSum) eval(e.leftOp) + eval(e.rightOp)
    else throw new Error("unknown " + e)
}
