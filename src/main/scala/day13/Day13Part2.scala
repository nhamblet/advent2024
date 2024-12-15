package day13

object Day13Part2 extends App {

    val DIFF = 10000000000000l
  
    def solve(in: Day13Input, diff: Long = DIFF): Long = {
        // println(s"7 % 3 == ${7 % 3}") // 1
        // println(s"-7 % 3 == ${(-7) % 3}") // -1
        // println(s"-7 % -3 == ${(-7) % (-3)}") // -1
        // println(s"7 % (-3) == ${7 % (-3)}") // 1
        // println(s"3 % -7 == ${3 % (-7)}") // 3
        // in.machines.foreach(m => {
        //     val p1 = part1(m)
        //     val p2 = solve(m)
        //     (p1, p2) match {
        //         case (Some(t1), Some(t2)) if t1 != t2 => println(s"MISMATCH Machine $m yielded $t1 and $t2")
        //         case (None, Some(t2)) => println(s"MISMATCH Machine $m yielded None / $t2")
        //         case (Some(t1), None) => println(s"MISMATCH Machine $m yielded $t1 / None")
        //         case _ => //
        //     }
        // })
        // in.machines.flatMap(m => part1(m).map(_._3)).sum
        in.machines.flatMap(m => solve(diff)(m).map(cost)).sum
    }

    def cost(solution: (Long, Long)): Long =
        3 * solution._1 + solution._2

    // px = a * ax + b * bx
    // gx = gcd(ax, bx) = cax * ax + cbx * bx => gx | px and px = gx * (px/gx) = ax * (cax * px/gx) + bx * (cbx * px/gx)
    // gy = gcd(ay, by) = cay * ay + cby * by => gy | py and py = gy * (py/gy) = ay * (cay * py/gy) + by * (cby * py/gy)
    // for any int t (cax * px/gx + t * bx, cbx * px/gx - t * ax) is an answer for px --- actually, bx/gx and ax/gx here
    //     because ax * (cax * px/gx + t * bx) + bx * (cbx * px/gx - t * ax)
    //             = ax * (cax * px/gx) + t*ax*bx + bx * (cbx * px/gx) - t*ax*bx
    // let's call that pair sx_t = (times to hit a, times to hit b) that solve px
    // we need that pair to also solve py
    //   cax * px/gx + t*bx = cay * py/gy + u * by for some u.       cax*px/gx + t*bx - cay*py/gy = u * by
    //   cbx * px/gx - t*ax = cby * py/gy - u * ay for the same u    cbx*px/gx - t*ax - cby*py/gy = u * ay
    // subtracting those two equations
    // u * (by - ay) = (cax*px/gx + t*bx - cay*py/gy) - (cbx*px/gx - t*ax - cby*py/g)
    //
    // consider our first A, B, (cax * px/gx) + (cbx * px/gx)
    // if both are positive, we're off to a good start, otherwise, one is negative (because both negative => px is negative but it isn't)
    //   if A is negative, we need to add enough bx-s to make it positive. The number is t=Math.ceil(|A| / bx). e.g. A=-5, bx=2 => needs 3
    //     that'd decrease B by t * ax, and if that's also negative, we're done without an answer
    //     otherwise, poth positive, so continue
    //   if B is negative, we need to add enough ax-s to make it positive. The number is t=-(Math.ceil(|B| / ax))
    //     that'd decrease A by t * bx, and again if that's negative we're done without an answer
    // if we're still going, both are positive
    //   we want to minimize A, so choose t so that A-t*bx > 0, e.g., A > -t/bx, i.e., t < A/bx, i.e. choose t = Math.floor(A / bx)
    //   then A becomes A-t*bx and B becomes B+t*ax (so is still positive)
    // if that doesn't work, increment A while B is still positive, until we find a winner or B is negative
    //
    // suppose a, b are a solution for px, meaning px = a * ca + b * cb for some ca and cb
    //   all other solutions are (a + t * (bx/gx), b - t * (bx / gx))
    // we want those solutions which are also solutions for py, meaning
    //   py = (a + t * (bx/gx)) * ay + (b - t * (ax/gx)) * by
    // all solutions to py, based on ay and by, are of the form
    //   py = ay * (cay * py/gy + u * by/gy) + by * (cby * py/gy - u * ay/gy), for some u
    // so we get a system of equations
    //   a + t * bx/gx = cay * py/gy + u * by/gy
    //   b - t * ax/gx = cby * py/gy - u * ay/gy
    // which we can try to solve:
    //   t = ((cay * py/gy + u * by/gy - a) * (gx/bx)
    //   b - ((cay * py/gy + u * by/gy - a) * (gx/bx)) * (ax/gx) = cby * py/gy - u * ay/gy
    // making
    //   u * ((by/gy) * (ax/bx) + (ay/gy)) = cby*(py/gy) + a(ax/bx) - cay*(py/gy)*(ax/bx) - b
    // and then sub that pack in to t above
    //

    def solve(diff: Long)(machine: Machine): Option[(Long, Long)] = {
        def ex = LinearEquation(machine.prize._1 + diff, machine.a.incX, machine.b.incX)
        def msx = initSolve(ex)
        def ey = LinearEquation(machine.prize._2 + diff, machine.a.incY, machine.b.incY)
        def msy = initSolve(ey)
        (msx, msy) match {
            case (Some(sx), Some(sy)) => slowJointSolution(ex, ey, sx, sy)
            case _ => None
        }
    }

    def jointSolution(e1: LinearEquation, e2: LinearEquation, s1: LinearSolution, s2: LinearSolution): Option[(Long, Long)] = {
        // e1 = (s1.a + t * s1.da) * e1.ka + (s1.b + t * s1.db) * e1.kb for any t
        // e2 = (s2.a + u * s2.da) * e2.ka + (s2.b + u * s2.db) * e2.kb for any u
        // we need the coefficients to be the same, because they're the number of button presses
        // so
        // s1.a + t * s1.da = s2.a + u * s2.da
        // s1.b + t * s1.db = s1.b + u * s2.db
        // solving first for t
        // t = (1 / s1.da) * (s2.a + u * s2.da - s1.a)
        // then subbing that in second
        // s1.b + (1 / s1.da) * (s2.a + u * s2.da - s1.a) * s1.db = s1.b + u * s2.db
        // s1.b + (1/s1.da)*(s2.a)*(s1.db) - (1/s1.da)*(s1.a)*(s1.db) - s1.b = u * (s2.db - (1/s1.da)*(s2.da)*(s1.db))
        ???
    }

    def slowJointSolution(e1: LinearEquation, e2: LinearEquation, s1: LinearSolution, s2: LinearSolution): Option[(Long, Long)] = {
        var a = s1.baseA
        var b = s1.baseB
        println(s"Solving $e1 and $e2 from $s1 and $s2") // not really using s2 though
        while((a >= 0) && (b >= 0) && (e2.target != a * e2.knownA + b * e2.knownB)) {
            a = a + s1.diffA
            b = b - s1.diffB
        }
        println(s"  Stopping at $a , $b giving ${a * e2.knownA + b * e2.knownB} (${e2.target == a * e2.knownA + b * e2.knownB})")
        if ((a >= 0) && (b >= 0) && (e2.target == a * e2.knownA + b * e2.knownB)) {
            Some((a, b))
        } else {
            None
        }
    }

    case class LinearEquation(target: Long, knownA: Int, knownB: Int)
    case class LinearSolution(baseA: Long, baseB: Long, diffA: Int, diffB: Int)

    // if
    //   val s = initSolve(eqn) is not None
    // then
    //   s.baseA and s.baseB are both positive, as are diffA and diffB
    //   (s.baseA + t * diffA) * eqn.knownA + (s.baseB - t * s.diffB) * eqn.knownB = eqn.target   for all t
    def initSolve(eqn: LinearEquation): Option[LinearSolution] = {
        val px = eqn.target
        val ax = eqn.knownA
        val bx = eqn.knownB
        val (gx, cax, cbx) = gcd(ax, bx)
        if ((px % gx) == 0) {
            // we might have a solution
            var a = cax.toLong * (px / gx).toLong
            var b = cbx.toLong * (px / gx).toLong
            if (a < 0) {
                val t = Math.ceil((-1.0d * a) / (bx/gx)).toLong
                a = a + t * (bx/gx)
                b = b - t * (ax/gx)
            }
            if (a >= 0 && b < 0) {
                val t = Math.ceil((-1.0d * b) / (ax/gx)).toLong
                a = a - t * (bx/gx)
                b = b + t * (ax/gx)
            }
            if (a > 0) {
                val t = Math.floor((1.0d * a) / (bx/gx)).toLong
                a = a - t * (bx/gx)
                b = b + t * (ax/gx)
            }
            if (a >= 0 && b >= 0) {
                Some(LinearSolution(a, b, bx/gx, ax/gx))
            } else {
                None
            }
        } else {
            None
        }
    }

    // def maybeSolveY(): Unit = {
    //         println(s"  Iterating from $a, $b")
    //         // a and b still both positive, this is the smallest possible a, but we can go up from there (as long as b stays positive)

    //         // the slow route is:

    //         // the exact route, hopefully, is:
    //         val qabx = ax.toDouble / bx.toDouble
    //         val cou = (by/gy) * qabx + (ay/gy)
    //         val altu = cby*(py/gy) + a*qabx - cay*(py/gy)*qabx
    //         val u = altu / cou
    //         val t = (cay*(py/gy) + u*(by/gy) -a)*(gx.toDouble/bx.toDouble)
    //         val tint = Math.round(t) // we'll iterate a little just in case
    //         a = origA + tint * (bx/gx)
    //         b = origB - tint * (ax/gx)
    //         println(s"  Quick looking from $a, $b based on $u and $t ($tint) from $origA, $origB with ${bx/gx} and ${ax/gx}")
    //         var idx = -100
    //         a = a + idx * (bx/gx)
    //         b = b - idx * (ax/gx)
    //         while ((idx < 200) && a >= 0 && b >= 0 && (py != a * ay + b * by)) {
    //             idx = idx + 1
    //             a = a + tint * (bx/gx)
    //             b = b - tint * (ax/gx)
    //         }
    //         println(s"    Stopped at $a, $b yielding $a * $ay + $b * $by = ${a * ay + b * by}")
    //         val quickAns = if (a >= 0 && b >= 0 && (py == a * ay + b * by)) {
    //             Some((a, b, 3 * a + b))
    //         } else {
    //             None
    //         }
    //         quickAns
    // }

    // return (g, ca, cb) st g = a*ca + b*cb
    // gcd(7, -3) = gcd(-3, -1) = gcd(-1, 0) = -1
    // gcd(-7, 3) = gcd(3, -7) = gcd(-7, -1) = gcd(-1, -7) = gcd(-7, 0)
    def gcd(a: Int, b: Int): (Int, Int, Int) = {
        if (a < b) {
            val (g, cb, ca) = gcd(b, a)
            (g, ca, cb)
        } else {
            if (b == 0) {
                (a, 1, 0)
            } else {
                val r = a % b
                val (g, cob, cor) = gcd(b, r) // g = b * cob + (r=a%b) * cor
                val q = a/b // integer division, now a = b * q + r
                (g, cor, cob - q * cor) // a*cor + b*(cob-q*cor) = a*cor + b*cob - (b/a)*b*cor = 
            }
        }
    }

    println("Test: " + solve(Day13Input.test()))
    println("Actual: " + solve(Day13Input.actual()))
}
