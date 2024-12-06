package day05

object Day05Part2 extends App {
  
    def solve(in: Day05Input): Int =
        in.updatePages.filterNot(validOrder(in.rules)).map(fixOrder(in.rules)).map(takeMiddle).sum
    
    def fixOrder(rules: Array[(Int, Int)])(pages: Array[Int]): Array[Int] = {
        val pset = pages.toSet
        val applicableRules = rules.filter(r => pset.contains(r._1) && pset.contains(r._2))
        val ret = Array.empty[Int]
        def g(ps: Set[Int], rs: Array[(Int, Int)], agg: Array[Int]): Array[Int] = {
            if (ps.isEmpty) {
                return agg
            } else {
                ps.find(p => rs.forall(r => r._2 != p)) match {
                    case None => ???
                    case Some(h) => 
                        g(ps - h, rs.filter(r => r._1 != h && r._2 != h), agg :+ h)
                }
            }
        }
        g(pset, applicableRules, ret)
    }

    println("Test: " + solve(Day05Input.test()))
    println("Actual: " + solve(Day05Input.actual()))
}
