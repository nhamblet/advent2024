import scala.io.BufferedSource
import scala.io.Source

package object day17 {

  case class Day17Input()

  object Day17Input {
    def apply(src: BufferedSource): Day17Input = {
        ???
    }

    def test(): Day17Input =
        Day17Input(Source.fromResource("day17.test"))

    def actual(): Day17Input =
        Day17Input(Source.fromResource("day17.actual"))
  }
  
}
