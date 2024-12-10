package day09

object Day09Part1 extends App {
  
    def solve(in: Day09Input): Long = {
        var fileIdx = 0
        var gapIdx = 0
        var inFileIdx = 0
        var blockIdx = 0l
        var movedFileIdx = in.files.size - 1
        var movedInFileIdx = in.files(movedFileIdx)
        var ret = 0l
        while (fileIdx < movedFileIdx || (fileIdx == movedFileIdx && inFileIdx < movedInFileIdx)) {
            // read a file
            while (inFileIdx < in.files(fileIdx) && (fileIdx < movedFileIdx || inFileIdx < movedInFileIdx)) {
                ret = ret + (fileIdx * blockIdx)
                blockIdx = blockIdx + 1
                inFileIdx = inFileIdx + 1
            }
            inFileIdx = 0
            fileIdx = fileIdx + 1
            // fill a gap
            if (gapIdx < in.gaps.size) {
                var inGapIdx = 0
                while (inGapIdx < in.gaps(gapIdx)) {
                    while(inGapIdx < in.gaps(gapIdx) && movedInFileIdx >= 0 && (movedFileIdx > fileIdx || (movedFileIdx == fileIdx && movedInFileIdx > 0))) {
                        ret = ret + (movedFileIdx * blockIdx)
                        blockIdx = blockIdx + 1
                        movedInFileIdx = movedInFileIdx - 1
                        if (movedInFileIdx <= 0) {
                            movedFileIdx = movedFileIdx - 1
                            movedInFileIdx = in.files(movedFileIdx)
                        }
                        inGapIdx = inGapIdx + 1
                    }
                    if (!(movedFileIdx > fileIdx || (movedFileIdx == fileIdx && movedInFileIdx > 0))) {
                        inGapIdx = in.gaps(gapIdx) // break
                    }
                }
                gapIdx = gapIdx + 1
            }
        }
        ret
    }

    println("Test: " + solve(Day09Input.test()))
    println("Actual: " + solve(Day09Input.actual()))
}
