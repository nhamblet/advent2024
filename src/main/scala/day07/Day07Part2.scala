package day07

object Day07Part2 extends App {
  
    def solve(in: Day07Input): Long = {
        in.equations.filter(brute).map(_.target).sum
    }

    def brute(equation: Equation): Boolean = {
        def go(operands: List[Long], cur: Long): Boolean = {
            if (operands.isEmpty) {
                cur == equation.target
            } else if (go(operands.tail, cur + operands.head)) {
                true
            } else if (go(operands.tail, cur * operands.head)) {
                true
            } else {
                val ns = cur.toString() + operands.head.toString
                go(operands.tail, ns.toLong)
            }
        }
        go(equation.operands.toList.tail, equation.operands(0))
    }

    println("Test: " + solve(Day07Input.test()))
    println("Actual: " + solve(Day07Input.actual()))
}
