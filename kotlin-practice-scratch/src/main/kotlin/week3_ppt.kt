import kotlin.properties.Delegates

interface ClickListener {
    fun onClick()
    fun onTouch() = println("touched")
}

sealed class Expr {
    class Num(val value: Int) : Expr()
    class Sum(val left: Expr, val right: Expr) : Expr()
}

interface GetSetI {
    var prop: String
}

interface ClickListener2 {
    fun onClick()
}

object ClickListenerImpl : ClickListener2 {
    override fun onClick() = println("clicked")
}

val String.lastChar: Char
    get() = get(length -1)

interface Base {
    fun print()
    fun printHello()
}

fun main () {

    println("--- Class 예제 ---")
    class Animal(val name: String)

    class Person(val firstName: String, _lastName : String) {
        var lastName : String = _lastName
            get() = field.uppercase()
            set(value) { field = "[$value]"}
    }

    val a = Animal("Dog")
    println("Animal: ${a.name}")
    val p = Person("Junyoung", "Heo")
    println("Name: ${p.firstName} ${p.lastName}")
    p.lastName = "Heo"
    println("Name: ${p.firstName} ${p.lastName}")
    println()

    println("--- Interface 예제 ---")



    class View(private val id: Int) : ClickListener {
        override fun onClick() = println("clicked $id")
    }

    val v = View(10)
    v.onClick()
    v.onTouch()
    println()

    println("--- 상속, 오버라이드 예제 ---")

    class NoInherit(val name: String)

    open class Inherit(val name: String) {
        fun first() = println("first")
        open fun second() = println("second")
    }

    open class Child(_name: String) : Inherit(_name) {
        final override fun second() = println("override second")
        open fun third() = println("third")
    }

    class GrandChild : Child("") {
        override fun third() = println("override third")
    }

    val c = Child("child")
    c.first()
    c.second()
    c.third()

    val g = GrandChild()
    g.first()
    g.second()
    g.third()
    println()

    println("--- 중첩(Nested) 클래스 예제 ---")
    class Outer(var m : Int = 0) {
        inner class Inner {
            fun doSomthing() {
                this@Outer.m++;
                println("Inner.doSomething ${this@Outer.m}")
            }
        }
    }

    var inner = Outer().Inner()
    inner.doSomthing()
    println()

    println("--- 봉인(Sealed) 클래스 예제 ---")


    fun eval(e : Expr): Int =
        when(e) {
            is Expr.Num -> e.value
            is Expr.Sum -> eval(e.right) + eval(e.left)
            else -> 0
        }

    var r = eval(Expr.Sum(Expr.Num(10), Expr.Num(10)))
    println(r)
    println()

    println("--- Constructor 예제 ---")
    class ConstEx1 constructor(_prop: Int) {
        val prop: Int
        val prop2: Int
        init {
            prop = _prop
            prop2 = _prop * 2
        }
    }

    class ConstEx2 constructor(_prop: Int) {
        val prop = _prop
    }

    class ConstEx3 constructor(val prop: Int)

    open class ConstEx4(val prop: Int, val prop2: Int = 0) {
        constructor(_prop: Int, _prop2: Int, _prop3: Int) : this(_prop + _prop3, _prop2) {}
    }

    class ConstEx5(_prop: Int) : ConstEx4(_prop)

    ConstEx1(1)
    ConstEx2(1)
    ConstEx3(1)
    ConstEx4(1)
    ConstEx4(1,2)
    ConstEx4(1, 2, 3)
    ConstEx5(1)
    println()

    println("-- Property와 getter/setter 예제 ---")


    class GetSet(_prop: String) : GetSetI {
        override var prop: String = _prop
            set(value) { field = value.substringBefore('@')}
            get() = field.uppercase()

        var prop2: Int = 0
            private set
    }

    val getset = GetSet("test@removed")
    println(getset.prop)
    getset.prop = "ok@removedd"
    println(getset.prop)
// getset.prop2 = 10
    println()

    println("--- Data 클래스 예제 ---")
    data class MyClass(val a: Int, val b: String)

    val str1 = "Hello, Kotlin"
    val str2 = "Hello, Kotlin"
    val class1 = MyClass(10, "class1")
    val class2 = MyClass(10, "class1")
    val class3 = MyClass(20, "class3")

    println(str1 == str2)
    println(class1 == class2) // 내용이 같은 지 확인
    println(class1 == class3)
    println(class1 === class2) // 레퍼런스가 같은지 확인
    println()

    println("--- object 키워드 ---")

//    fun setClickListener2(listener2: ClickListener2) = listener2.onClick()
//
//    class Touch() {
//        val objectNums : Int
//            get() = num
//        init { num++ }
//        companion object { var num: Int = 0}
//    }
//
//    setClickListener2(ClickListenerImpl)
//
//    setClickListener2(object : ClickListener2 {
//        override fun onClick() = println("clicked2")
//    })
//
//    Touch()
//    Touch()
//    println(Touch().objectNums)
//    println()

    println("--- Extension Method/Property 예제 ---")
    fun <T> Collection<T>.join(separator: String = " ") : String {
        val result = StringBuilder()
        for((index, element) in withIndex()) {
            if(index > 0) result.append(separator)
            result.append(element)
        }
        return result.toString()
    }



    println(listOf("1", "2", "3").join())
    println(arrayListOf(1, 2, 3).join(", "))
    println("Hello".lastChar)
    println()

    println("--- 연산자 오버로딩 ---")

    data class Complex(val real: Double, val img: Double) {
        operator fun plus(other: Complex): Complex
                = Complex(real + other.real, img + other.img)

        override fun toString(): String = "${real}+${img}i"
    }

    operator fun Complex.minus(other: Complex) : Complex
            = Complex(real - other.real, img - other.img)

    val c1 = Complex(1.0, 2.0)
    val c2 = Complex(2.0, 2.0)
    val c3 = c1 + c2
    println(c3)
    println(c3 == Complex(3.0, 3.0))
    println()

    println("--- 위임 예제 ---")


    class BaseImpl(val x : Int) : Base {
        override fun print() { print(x) }
        override fun printHello() { println("Hello") }
    }

    class Derived(val b : Base) : Base by b {
        override fun print() {
            b.print()
            println("ok")
        }
    }

    val b = BaseImpl(10)
    val d = Derived(b)
    d.print()
    d.printHello()
    println()

    class User(val map: MutableMap<String, Any?>) {
        val lazyValue : String by lazy {
            println("computed!")
            "Hello"
        }

        var name: String by map
        var age: Int by map

        var nameOb: String by Delegates.observable("<no name>") {
                prop, old, new -> println("$old -> $new")
        }
    }

    val user = User(mutableMapOf("name" to "John Doe", "age" to 25))
    println(user.lazyValue)
    println(user.lazyValue)

    println(user.name)
    println(user.age)
    user.age = 30
    println(user.map)

    user.nameOb = "first"
    user.nameOb = "second"
    println()

    println("--- Generic 예제 ---")
    fun <T : Number> myPlus(op1 : T, op2: T): String = "$op1$op2"

    class MyGeneric<T: Number>(private val prop: T) {
        fun test(arg: T): String {
            println(arg)
            return myPlus(prop, arg)
        }
    }

    println(myPlus(1,1))
    println(myPlus(1.0, 1.0))
    println(MyGeneric(10).test(10))

}