package day07

object Day07Part1 extends App {
  
    def solve(in: Day07Input): Long = {
        in.equations.filter(brute).map(_.target).sum
    }

    // this ends up not working because it assumes that "add all" is the smallest possible tail
    // but if there are 1s in the tail, then a "*1" would be smaller
    def plausible(equation: Equation): List[String] = {
        val lowTails = equation.operands.tails.map(_.sum).toList.tail.init
        val hiTails = equation.operands.tails.map(_.product).toList.tail.init
        def go(operands: List[Long], lows: List[Long], highs: List[Long], cur: Long, ops: List[String]): List[String] = {
            if (operands.isEmpty) {
                ops
            } else if (cur + lows.head > equation.target) {
                println(s"Bailing early too large at $cur with $lows / $highs, from ${ops.reverse}")
                List.empty
            } else if (cur * highs.head < equation.target) {
                println(s"Bailing early too small at $cur with $lows / $highs, from ${ops.reverse}")
                List.empty
            } else {
                val withPlus = go(operands.tail, lows.tail, highs.tail, cur + operands.head, "+" :: ops)
                if (withPlus.nonEmpty) {
                    withPlus
                } else {
                    go(operands.tail, lows.tail, highs.tail, cur * operands.head, "*" :: ops)
                }
            }
        }
        go(equation.operands.toList.tail, lowTails, hiTails, equation.operands(0), List.empty)
    }

    def brute(equation: Equation): Boolean = {
        def go(operands: List[Long], cur: Long): Boolean = {
            if (operands.isEmpty) {
                cur == equation.target
            } else if (go(operands.tail, cur + operands.head)) {
                true
            } else {
                go(operands.tail, cur * operands.head)
            }
        }
        go(equation.operands.toList.tail, equation.operands(0))
    }

    def bruteExplain(equation: Equation): List[String] = {
        def go(operands: List[Long], cur: Long, ops: List[String]): List[String] = {
            if (operands.isEmpty) {
                if (cur == equation.target) {
                    ops
                } else {
                    List.empty
                }
            } else {
                val withPlus = go(operands.tail, cur + operands.head, "+" :: ops)
                if (withPlus.nonEmpty) {
                    withPlus
                } else {
                    go(operands.tail, cur * operands.head, "*" :: ops)
                }
            }
        }
        go(equation.operands.toList.tail, equation.operands(0), List.empty)
    }

    println("Test: " + solve(Day07Input.test()))
    println("Actual: " + solve(Day07Input.actual()))
    // 12365389073290 too low
    // 12553187650171
}
