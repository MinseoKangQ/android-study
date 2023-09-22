import java.lang.Integer.parseInt
import java.lang.NumberFormatException as NFE

fun main() {

    // Variables & Value
    val a : Int = 10
    var b : Int = 12
    // a = 11 // compile error
    b = 13

    // Type Inference
    val intergerVal: Int = 10
    val StringVal : String = "a string"
    val typeInference = 10
    val typeInference2 = "a string"

    // Types
    val i = 10
    val i2 : UInt = 10U
    // val lov : Long = i // compile error
    val lov2 : Long = i.toLong() // 명시적 값 변환

    // if
    val x = if (a > 10) "big" else "small"

    // Any, is
    val t : Any = 10
    println((t is Int))

    // as
    fun test(obj : Any) {
        if(obj is Int) println("obj is Int.")
        if(obj is String) println("obj is String.")

        println("print obj as string > ${(obj as? String ?: "")}")
        // ?: // Elvis
    }

    test(1)
    test("Strings")

    // String
    val version = "1.3.50"
    val javaStyle = "Hello, Kotiln " + version + "!"
    val kotlinStyle = "Hello, Kotlin ${version}!"
    println(javaStyle)
    println(kotlinStyle)

    val num = 10
    println("val num is equal to 10: ${num == 10}.")

    println("""\$""") // \$
    println("\$") // $
    println("""
    |hello
    |my name is kotlin.
""".trimMargin())

    // == or ===
    // == : 내용 비교, === : 객체 레퍼런스 비교
    data class MyClass(val a : Int, val b : String)
    // 'data' auto-generates equals/hashCode/toString/copy
    val str1 = "Hello, Kotlin"
    val str2 = "Hello, Kotlin"
    val class1 = MyClass(10, "class1")
    val class2 = MyClass(10, "class1")
    val class3 = MyClass(20, "class2")

    println(str1 == str2) // true
    println(class1 == class2) // true
    println(class1 == class3) // false
    println(class1 === class3) // false

    // array
    val intArrays = arrayOf(1,2, 3, 4, 5)
    val strArrays = arrayOfNulls<String>(5)
    val dbArrays = emptyArray<Double>()

    println(intArrays[0])
    intArrays[0] = 10
    println(intArrays[0])

    for(s in strArrays) {
        print("$s, ")
    }
    println()
    println(dbArrays.size)

    // for and Iteration
    val array = arrayOf("Hello", "This", "is", "Kotlin")

    for(a in array)
        print("$a ")
    println()

    for((idx, a) in array.withIndex())
        print("($idx, $a) ")
    println()

    for(i in 1..10)
        print("$i ")
    println()

    for(i in 'a'..'f')
        print("$i ")
    println()

    for(i in 1..10 step 2)
        print("$i ")
    println()

    (10 downTo 1).forEach{
        print("$it ")
    }
    println()

    var j = 0
    while(j < 10) {
        j++
        print("$j ")
    }
    println()

    // function
    fun myFunc(arg1 : Int, arg2 : String = "default", arg3 : Int = 10) {
        println("$arg1 $arg2 $arg3")
    }

    fun sumFunc(a : Int, b : Int) = a + b;

    myFunc(1, "hello", 5)
    myFunc(arg1 = 2)
    myFunc(2, arg3 = 5)

    fun localFunc(a : Int) : Int {
        return a + 10
    }

    println(sumFunc(1,2))
    println(localFunc(10))

    // Lambda
    val sum = { x : Int, y : Int -> x + y}
    println(sum(10, 20))

    fun lambdaTest(a : (Int) -> Int) : Int {
        return a(10)
    }

    lambdaTest ( {x -> x + 10})
    lambdaTest ( { it + 10 })
    lambdaTest { it + 10}

    data class MyClass2(val a : Int, val b : String)
    val array2 = arrayOf(MyClass2(10, "class1"), MyClass2(20, "class"), MyClass2(30, "class3"))
    println(array2.filter({c : MyClass2 -> c.a < 15}))
    array2.filter() { c : MyClass2 -> c.a < 15 }
    array2.filter { c -> c.a < 15 }
    array2.filter { it.a < 15}

    val title = "Num:"
    val list = listOf(1,2,3,4)
    list.forEach({println("$title $it")})

    // when
    fun test1(arg : Any) {
        when(arg) {
            10 -> println("10")
            in 0..9 -> println("0 <= x <= 9")
            is String -> println("Hello, $arg")
            !in 0..100 -> println("x < 0 and x > 100")
            else -> {
                println("unknown")
            }
        }
    }

    fun test2(arg : Int) : Int {
        return when(arg) {
            in 0..9 -> 10
            in 10..19 -> 20
            in 20..29 -> 30
            else -> 40
        }
    }

    test1(10)
    test1(5)
    test1("String")
    test1(200)
    test1(50)
    println(test2(15))
    println(test2(50))

    // Null Safety
    fun testNull(arg : String?) {
        println(arg?.uppercase())
        println(arg?.uppercase() ?: "-")
    }

    var nullable : String? = null
    var nonNullable : String = "nonNullable"

    testNull(nonNullable)
    testNull(nullable)

    nullable?.uppercase()
    try {
        nullable!!.uppercase()
    }catch (e : java.lang.NullPointerException) {
        println("NullPointerException")
    }

    // Exception
    val x2 = try {
        Integer.parseInt("10")
    } catch(e : NumberFormatException) { 0 }
    println(x2)

    // Collections
    // Array, List, Set, Map (immutable)
    // ArrayList, MutableList, MutableSet, MutableMap (mutable)

    val array3 = arrayOf(1, 2, 3)
    val arrayList = array3.toMutableList()
    val list2 = listOf(1, 2, 3)
    val mutableList = list.toMutableList()
    val set = setOf(1, 2, 3, 3)
    val mutableSet = set.toMutableSet()
    val map = mapOf("one" to 1, "two" to 2, "three" to 3)
    val mutableMap = map.toMutableMap()

    arrayList.add(4)
    arrayList[arrayList.lastIndex]++
    mutableList.add(4)
    mutableList[0] = 0
    mutableSet.add(4)
    mutableMap["four"] = 4

    println(arrayList)
    println(mutableList)
    println(mutableSet)
    println(mutableMap)

    // Collection, Iteration
    val e_array = arrayListOf<String>()
    val e_list = mutableListOf<Int>()
    val e_set = mutableSetOf<Int>()
    val e_map = mutableMapOf<String, Int>()

    e_array.add("1")
    e_list.add(1)
    e_set.add(1)
    e_map["one"] = 1

    for(a in e_array) print("$a")
    println()
    for(a in e_list) print("$a")
    println()
    for(a in e_set) print("$a")
    println()
    for((k,v) in e_map) print("$k - $v")
}