import scala.io.BufferedSource
import scala.io.Source

package object day12 {

  case class Day12Input()

  object Day12Input {
    def apply(src: BufferedSource): Day12Input = {
        ???
    }

    def test(): Day12Input =
        Day12Input(Source.fromResource("day12.test"))

    def actual(): Day12Input =
        Day12Input(Source.fromResource("day12.actual"))
  }
  
}
