import scala.io.BufferedSource
import scala.io.Source

package object day11 {

  case class Day11Input(stones: Array[Long])

  object Day11Input {
    def apply(src: BufferedSource): Day11Input = {
        Day11Input(src.getLines().next.split(" ").map(_.toLong))
    }

    def test(): Day11Input =
        Day11Input(Source.fromResource("day11.test"))

    def actual(): Day11Input =
        Day11Input(Source.fromResource("day11.actual"))
  }

  sealed trait EvolvedStore
  case class EvolveSingle(n: Long)
  case class EvolveTwo(left: Long, right: Long)

  def evolve(n: Long) = {
    if (n == 0) {
      EvolveSingle(1)
    } else if (n.toString.size % 2 == 0) {
      val s = n.toString
      val (l, r) = s.splitAt(s.size / 2)
      EvolveTwo(l.toLong, r.toLong)
    } else {
      EvolveSingle(n * 2024)
    }
  }

    def time[X](f: => X): (X, Long) = {
        val before = System.nanoTime()
        val ret = f
        val after = System.nanoTime()
        (ret, (after - before) / 1000000l)
    }
  
}
