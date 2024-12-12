package day12

object Day12Part1 extends App {
  
    def solve(in: Day12Input): Long = {
        val coords = for {
            r <- Range(0, in.grid.size)
            c <- Range(0, in.grid(0).size)
        } yield {
            RC(r, c)
        }
        (coords.foldLeft(Accum.empty)((acc: Accum, rc: RC) => {
            if (acc.visited.contains(rc)) {
                acc
            } else {
                val region = fill(in, rc)
                val area = region.size
                val perim = region.toList.map(_.walls).sum
                acc.increment(area * perim, region.map(_.rc))
            }
        })).cost
    }

    case class WalledCell(rc: RC, walls: Int)

    def fill(in: Day12Input, rc: RC): Set[WalledCell] = {
        def go(rcs: List[RC], acc: Set[WalledCell]): Set[WalledCell] = {
            rcs match {
                case Nil => acc
                case rc :: tail => {
                    if (acc.exists(_.rc == rc)) {
                        go(tail, acc)
                    } else {
                        val nbrs = neighbors(in, rc)
                        val extWalls = 4 - nbrs.size
                        val intWalls = nbrs.count(n => in(n) != in(rc))
                        val friends = nbrs.filter(n => in(n) == in(rc))
                        go(friends.toList ++ tail, acc + WalledCell(rc, extWalls + intWalls))
                    }
                }
            }
        }
        go(List(rc), Set.empty)
    }

    def neighbors(in: Day12Input, rc: RC): Set[RC] = {
        Set(RC.up, RC.down, RC.left, RC.right).map(f => f(rc)).filter(RC.inGrid(in.grid))
    }

    case class Accum(cost: Long, visited: Set[RC]) {
        def increment(c: Long, vs: Set[RC]): Accum =
            Accum(cost + c, visited ++ vs)
    }
    object Accum {
        def empty(): Accum =
            Accum(0, Set.empty)
    }

    for {
        t <- Range(1, 4)
    }
    println(s"Test $t: " + solve(Day12Input.test(t)))
    println("Actual: " + solve(Day12Input.actual()))
}
