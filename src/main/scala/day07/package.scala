import scala.io.BufferedSource
import scala.io.Source

package object day07 {

  case class Day07Input()

  object Day07Input {
    def apply(src: BufferedSource): Day07Input = {
        ???
    }

    def test(): Day07Input =
        Day07Input(Source.fromResource("day07.test"))

    def actual(): Day07Input =
        Day07Input(Source.fromResource("day07.actual"))
  }
  
}
