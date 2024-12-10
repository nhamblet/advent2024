import scala.io.BufferedSource
import scala.io.Source

package object day10 {

  case class Day10Input(heights: Array[Array[Int]])

  object Day10Input {
    def apply(src: BufferedSource): Day10Input = {
        Day10Input(src.getLines().filter(_.nonEmpty).toArray.map(_.map(c => c.toString.toInt).toArray))
    }

    def test(): Day10Input =
        Day10Input(Source.fromResource("day10.test"))

    def actual(): Day10Input =
        Day10Input(Source.fromResource("day10.actual"))
  }

    def nextSteps(in: Day10Input)(pos: (Int, Int)): Set[(Int, Int)] = {
        val h = in.heights(pos._1)(pos._2)
        for {
            d <- Set((-1, 0), (1, 0), (0, -1), (0, 1)) if isHeight(in, h + 1)(pos._1 + d._1, pos._2 + d._2)
        } yield {
            (pos._1 + d._1, pos._2 + d._2)
        }
    }

    def isHeight(in: Day10Input, height: Int)(r: Int, c: Int): Boolean = {
        r >= 0 && r < in.heights.size && c >= 0 && c < in.heights(0).size && in.heights(r)(c) == height
    }
  
}
