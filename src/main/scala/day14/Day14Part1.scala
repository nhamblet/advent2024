package day14

object Day14Part1 extends App {
  
    def solve(in: Day14Input, steps: Int = 100): Int = {
        def go(state: Array[PosDelta], steps: Int): Array[PosDelta] = {
            if (steps == 0) {
                state
            } else {
                go(step(in.gridSize)(state), steps - 1)
            }
        }
        val endState = go(in.robots, steps)
        endState.flatMap(r => quadrant(in.gridSize)(r)).groupBy(q => q).map(_._2.size).product
    }

    def step(gridSize: CR)(state: Array[PosDelta]): Array[PosDelta] =
        state.map(_.next(gridSize))
    
    sealed trait Hemi
    case object Before extends Hemi
    case object After extends Hemi
    case class Quadrant(lr: Hemi, tb: Hemi)

    def quadrant(gridSize: CR)(pd: PosDelta): Option[Quadrant] = {
        val lr = if (pd.pos._1 < gridSize._1 / 2) { Some(Before) } else if (pd.pos._1 > gridSize._1 / 2) { Some(After) } else { None }
        val tb = if (pd.pos._2 < gridSize._2 / 2) { Some(Before) } else if (pd.pos._2 > gridSize._2 / 2) { Some(After) } else { None }
        (lr, tb) match {
            case (Some(h), Some(v)) => Some(Quadrant(h, v))
            case _ => None
        }
    }
    
    
    println("Test: " + solve(Day14Input.test()))
    println("Actual: " + solve(Day14Input.actual()))
}
