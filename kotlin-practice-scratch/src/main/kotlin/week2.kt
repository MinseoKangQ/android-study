fun main() {

    // 1
    println("문제1")

    for(i in 2..9) {
        for(j in 2..9) {
            println("${i}x${j}=${i*j}")
        }
    }

    println()


    // 2
    println("문제2")

    fun find_number(a: Int, list : List<Int>) : Int {
        var low = 0
        var high = list.size-1

        while(low <= high) {
            val mid = (low + high) / 2

            if(list[mid] == a)
                return mid
            else if (list[mid] > a)
                high = mid - 1
            else
                low = mid + 1
        }
        return -1
    }

    val a = listOf(1,2,3,4,5,6,7,8,9,10)
    println(find_number(2,a))
    println(find_number(9,a))
    println(find_number(5,a))
    println()


    // 3
    println("문제3")

    val koreaScore = arrayOf(0, 0, 0, 0, 1, 0, 0, 2, 0)
    val japanScore = arrayOf(1, 1, 0, 0, 0, 0, 0, 0, 0)
    var koreaSum = 0
    var japanSum = 0

    val basicArray = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
    print("회\t\t")
    basicArray.forEach{
        print("${it} ")
    }

    print("Run")
    println()

    print("Korea\t")
    koreaScore.forEach() {
        print("${it} ")
        koreaSum += it
    }
    print(" ${koreaSum}")
    println()

    print("Japan\t")
    japanScore.forEach() {
        print("${it} ")
        japanSum += it
    }
    print(" ${japanSum}")
    println()
    println()


    // 4
    println("문제4")
    val scoreArray = arrayOf(15, 4, 8, 9, 13, 12, 10, 9, 11, 6)

    fun find_min(array : List<Int>) : Int {
        var min = Integer.MAX_VALUE
        array.forEach { if(min>it) min = it }
        return min
    }

    fun find_avg(array : List<Int>) : Double {
        var sum = 0.0
        array.forEach { sum += it.toDouble() }
        return sum/array.size
    }

    println("min : ${find_min(scoreArray.toList())}, average : ${find_avg(scoreArray.toList())}")
}