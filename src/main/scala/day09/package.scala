import scala.io.BufferedSource
import scala.io.Source

package object day09 {

  case class Day09Input(files: Array[Int], gaps: Array[Int])

  object Day09Input {
    def apply(src: BufferedSource): Day09Input = {
        val mingled = src.getLines.next.map(_.toString.toInt) // without .toString it returns ascii values
        val files = Stream.from(0).map(_ * 2).takeWhile(_ < mingled.size).map(mingled).toArray
        val gaps = Stream.from(0).map(_ * 2 + 1).takeWhile(_ < mingled.size).map(mingled).toArray
        Day09Input(files, gaps)
    }

    def test(): Day09Input =
        Day09Input(Source.fromResource("day09.test"))

    def actual(): Day09Input =
        Day09Input(Source.fromResource("day09.actual"))
  }
  
}
