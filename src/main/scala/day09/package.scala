import scala.io.BufferedSource
import scala.io.Source

package object day09 {

  case class Day09Input()

  object Day09Input {
    def apply(src: BufferedSource): Day09Input = {
        ???
    }

    def test(): Day09Input =
        Day09Input(Source.fromResource("day09.test"))

    def actual(): Day09Input =
        Day09Input(Source.fromResource("day09.actual"))
  }
  
}
