import scala.io.BufferedSource
import scala.io.Source

package object day16 {

  case class Day16Input()

  object Day16Input {
    def apply(src: BufferedSource): Day16Input = {
        ???
    }

    def test(): Day16Input =
        Day16Input(Source.fromResource("day16.test"))

    def actual(): Day16Input =
        Day16Input(Source.fromResource("day16.actual"))
  }
  
}
