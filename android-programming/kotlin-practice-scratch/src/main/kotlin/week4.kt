// 1번
fun doubleOddNumbers(list: List<Int>) : List<Int> {

    var newList : MutableList<Int> = mutableListOf()

    for(a in list) {
        if(a % 2 == 1) {
            var x : Int = a * 2
            newList.add(x)
        }
        else
            newList.add(a)
    }

    return newList
}


// 2번
class Person() {
    var name: String? = null
    var age: Int? = null
    var email: String? = null
    override fun toString() = "$name, $age, $email"
}

fun createPersonWith(_name: String, _age: Int, _email: String? = null) : Person{
    val person = with (Person()) {
        name = _name
        age = _age
        email = _email
        this
    }

    return person
}

fun createPersonApply(_name: String, _age: Int, _email: String? = null): Person {
    // TODO: implement function
    val person = Person().apply {
        name = _name
        age = _age
        email = _email
    }
    return person
}

fun main() {

    // 1번
    val testCases = listOf(
        Pair(listOf(1, 2, 3, 4, 5), listOf(2, 2, 6, 4, 10)),
        Pair(listOf(2, 4, 6, 8), listOf(2, 4, 6, 8)),
        Pair(listOf(1, 3, 5, 7), listOf(2, 6, 10, 14))
    )

    for ((input, expectedOutput) in testCases) {
        val actualOutput = doubleOddNumbers(input)
        if (actualOutput != expectedOutput) {
            println("Test failed for input $input. Expected output: $expectedOutput. Actual output: $actualOutput")
        } else {
            println("Test passed for input $input")
        }
    }

    // 2번
    val person1 = createPersonWith("Alice", 30)
    println(person1) // expected output: Alice, 30, null
    val person2 = createPersonApply("Bob", 25, "bob@example.com")
    println(person2) // expected output: Bob, 25, bob@example.com

}
