import scala.io.BufferedSource
import scala.io.Source

package object day12 {

  case class Day12Input(grid: Array[Array[Char]]) {
    def rows = grid.size
    def cols = grid(0).size
    def apply(rc: RC): Char = grid(rc.r)(rc.c)
  }

  object Day12Input {
    def apply(src: BufferedSource): Day12Input = {
        Day12Input(src.getLines().toArray.map(_.toArray).filter(_.nonEmpty))
    }

    def test(n: Int): Day12Input =
        Day12Input(Source.fromResource(s"day12.test$n"))

    def actual(): Day12Input =
        Day12Input(Source.fromResource("day12.actual"))
  }

    case class RC(r: Int, c: Int)

    object RC {
        def up: RC => RC = rc => rc.copy(r = rc.r - 1)
        def down: RC => RC = rc => rc.copy(r = rc.r + 1)
        def left: RC => RC = rc => rc.copy(c = rc.c - 1)
        def right: RC => RC = rc => rc.copy(c = rc.c + 1)
        def inGrid[X](grid: Array[Array[X]]): RC => Boolean = rc => {
            rc.r >= 0 && rc.r < grid.size && rc.c >= 0 && rc.c < grid(rc.r).size
        }
    }
  
}
