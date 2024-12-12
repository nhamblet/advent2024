package day12

object Day12Part2 extends App {
  
    def solve(in: Day12Input): Long = {
        (for {
            r <- Range(0, in.grid.size)
            c <- Range(0, in.grid(0).size)
        } yield {
            RC(r, c)
        }).foldLeft(Accum.empty)((acc: Accum, rc: RC) => {
            if (acc.visited.contains(rc)) {
                acc
            } else {
                val region = fill(in, rc)
                val area = region.size
                val perim = sides(in, region)
                acc.increment(area * perim, region.map(_.rc))
            }
        }).cost
    }

    def sides(in: Day12Input, region: Set[WalledCell]): Int =
        (for {
            wc <- region // subtle... the comprehension here is over a set, so will leave a set, which is the top/left corners of all sides
            wall <- wc.walls
        } yield {
            follow(in, region)(wc.rc, wall)
        }).size

    def follow(in: Day12Input, region: Set[WalledCell])(rc: RC, ws: WallSide): (RC, WallSide) = {
        var cur = rc
        var next = cur
        def inRegion(rc: RC): Boolean = region.exists(wc => wc.rc == rc && wc.walls.contains(ws))
        while (inRegion(next)) {
            cur = next
            next = (ws match {
                case Left => RC.up
                case Right => RC.up
                case Up => RC.left
                case Down => RC.left
            })(cur)
        }
        (cur, ws)
    }

    sealed trait WallSide
    case object Left extends WallSide
    case object Right extends WallSide
    case object Up extends WallSide
    case object Down extends WallSide

    case class WalledCell(rc: RC, walls: Set[WallSide])

    def fill(in: Day12Input, rc: RC): Set[WalledCell] = {
        def go(rcs: List[RC], acc: Set[WalledCell]): Set[WalledCell] = {
            rcs match {
                case Nil => acc
                case rc :: tail => {
                    if (acc.exists(_.rc == rc)) {
                        go(tail, acc)
                    } else {
                        val walls = scala.collection.mutable.Set.empty[WallSide]
                        val friends = scala.collection.mutable.Set.empty[RC]
                        val u = RC.up(rc)
                        if (RC.inGrid(in.grid)(u) && (in(u) == in(rc))) {
                            friends.add(u)
                        } else {
                            walls.add(Up)
                        }
                        val d = RC.down(rc)
                        if (RC.inGrid(in.grid)(d) && (in(d) == in(rc))) {
                            friends.add(d)
                        } else {
                            walls.add(Down)
                        }
                        val l = RC.left(rc)
                        if (RC.inGrid(in.grid)(l) && (in(l) == in(rc))) {
                            friends.add(l)
                        } else {
                            walls.add(Left)
                        }
                        val r = RC.right(rc)
                        if (RC.inGrid(in.grid)(r) && (in(r) == in(rc))) {
                            friends.add(r)
                        } else {
                            walls.add(Right)
                        }
                        go(friends.toList ++ tail, acc + WalledCell(rc, walls.toSet))
                    }
                }
            }
        }
        go(List(rc), Set.empty)
    }

    case class Accum(cost: Long, visited: Set[RC]) {
        def increment(c: Long, vs: Set[RC]): Accum =
            Accum(cost + c, visited ++ vs)
    }
    object Accum {
        def empty(): Accum =
            Accum(0, Set.empty)
    }

    println("Test 1: " + solve(Day12Input.test(1))) // 80
    println("Test 2: " + solve(Day12Input.test(2))) // 436 = (4 * (1 * 4)) + (21 * 20)
    println("Test 3: " + solve(Day12Input.test(3))) // 1206
    println("Test 4: " + solve(Day12Input.test(4))) // 236
    println("Test 5: " + solve(Day12Input.test(5))) // 368
    println("Actual: " + solve(Day12Input.actual()))
}
